package org.codigolibre.auditbpmn.wso2mediator.command;

import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.axiom.om.OMAttribute;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.MessageContext;
import org.apache.synapse.SynapseConstants;
import org.apache.synapse.config.xml.ValueFactory;
import org.apache.synapse.config.xml.ValueSerializer;
import org.apache.synapse.mediators.Value;
import org.codigolibre.auditbpmn.jaxb.ActivityTypeAudit;
import org.codigolibre.auditbpmn.jaxb.BusinessProcessAudit;
import org.codigolibre.auditbpmn.jaxb.ErrorType;
import org.codigolibre.auditbpmn.jaxb.ObjectFactory;
import org.codigolibre.auditbpmn.jaxb.ReceiveTaskAuditType;
import org.codigolibre.auditbpmn.jaxb.SendTaskAudit;
import org.codigolibre.auditbpmn.jaxb.SubProcessAuditType;
import org.codigolibre.auditbpmn.jaxb.TaskAuditType;
import org.codigolibre.auditbpmn.wso2mediator.AuditMediatorUtils;

public class BusinessProcessCommand implements Command {

	private static Log log = LogFactory.getLog(BusinessProcessCommand.class);

	private Value id;
	private Value name;
	private Value description;
	private Value startTime;
	private Value endTime;
	private Value status;

	private Value errorCode;
	private Value errorMessage;
	private Value errorDetail;

	private Value closeWithStatus;
	private boolean isCascadeClose = false;
	private boolean isCaptureMsg = false;
	
	private Value correlationID;
	protected ParamsSubCommand contextParams;
	
	private ObjectFactory objF = new ObjectFactory();


	@Override
	public void execute(BusinessProcessAudit businessProcessAudit,
			MessageContext synCtx) {

		if (log.isDebugEnabled() || log.isTraceEnabled()) {
			log.debug("execute BusinessProcessAudit comando");
		}

		// Verificar si la transacción esta recien creada para inicializarla

		if (businessProcessAudit.getStartTime() == null) {

			XMLGregorianCalendar xgcal;
			try {
				xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(
						(GregorianCalendar) GregorianCalendar.getInstance());
				businessProcessAudit.setStartTime(xgcal);
			} catch (Exception e) {
				log.error(
						"Error creando Fecha Inicio Transaccion "
								+ e.getMessage(), e);
			}
			businessProcessAudit.setId(synCtx.getMessageID());
			businessProcessAudit
					.setStatus(AuditMediatorUtils.IN_PROGRESS_STATUS);
		}

		if (closeWithStatus != null) {
			status = closeWithStatus;
		}


		if (status != null) {
			businessProcessAudit.setStatus(status.evaluateValue(synCtx));

			// si no está en curso, actualizando fecha fin
			if (!AuditMediatorUtils.IN_PROGRESS_STATUS
					.equalsIgnoreCase(businessProcessAudit.getStatus())) {
				XMLGregorianCalendar xgcal;
				try {
					if (businessProcessAudit.getEndTime() == null) {
						xgcal = DatatypeFactory.newInstance()
								.newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar.getInstance());
						businessProcessAudit.setEndTime(xgcal);
					}
				} catch (DatatypeConfigurationException e) {
					log.error("Error parsing end date "
									+ e.getMessage(), e);
				}

			}

			if (AuditMediatorUtils.ERROR_STATUS
					.equalsIgnoreCase(businessProcessAudit.getStatus())) {
				// Capture error information
				
				businessProcessAudit.setError(objF.createErrorType());

				// Nota: Casting a Object, puede venir un String o un
				// Integer
				try{
					if (businessProcessAudit.getError().getErrorCode()==null){
					businessProcessAudit.getError().setErrorCode(
						((Object) synCtx
								.getProperty(SynapseConstants.ERROR_CODE))
								.toString());
					}

				}catch (Exception e) {}
				
				if (businessProcessAudit.getError().getErrorMessage()==null){
				businessProcessAudit.getError().setErrorMessage((String) synCtx
						.getProperty(SynapseConstants.ERROR_MESSAGE));
				}
				if (businessProcessAudit.getError().getErrorDetail()==null){
				businessProcessAudit.getError().setErrorDetail((String) synCtx
						.getProperty(SynapseConstants.ERROR_DETAIL));
				}

			}

		}
		if (name != null) {
			businessProcessAudit.setName(name.evaluateValue(synCtx));
		}
		if (description != null) {
			businessProcessAudit.setDescription(description
					.evaluateValue(synCtx));
		}

		if (id != null) {
			businessProcessAudit.setId(id.evaluateValue(synCtx));
		}
		if (correlationID != null) {
			businessProcessAudit.setCorrelationID(correlationID.evaluateValue(synCtx));
		}
		
		if (startTime != null) {

			try {
				XMLGregorianCalendar xgcal = DatatypeFactory.newInstance()
						.newXMLGregorianCalendar(
								startTime.evaluateValue(synCtx));
				businessProcessAudit.setStartTime(xgcal);
			} catch (Exception e) {
				log.error(
						"Error en parseo fechaInicioTransaccionValue "
								+ e.getMessage(), e);

			}
		}
		if (endTime != null) {

			try {
				XMLGregorianCalendar xgcal = DatatypeFactory.newInstance()
						.newXMLGregorianCalendar(endTime.evaluateValue(synCtx));
				businessProcessAudit.setEndTime(xgcal);
			} catch (Exception e) {
				log.error("Error en parseo campo  fechaFinTransaccionValue "
						+ e.getMessage(), e);
			}
		}

		if (errorCode != null) {

			if (businessProcessAudit.getError() == null) {
				ErrorType errorObject = objF.createErrorType();
				businessProcessAudit.setError(errorObject);
			}

			businessProcessAudit.getError().setErrorCode(
					errorCode.evaluateValue(synCtx));
		}

		if (errorMessage != null) {

			if (businessProcessAudit.getError() == null) {
				ErrorType errorObject = objF.createErrorType();
				businessProcessAudit.setError(errorObject);
			}
			businessProcessAudit.getError().setErrorMessage(
					errorMessage.evaluateValue(synCtx));
		}
		if (errorDetail != null) {

			if (businessProcessAudit.getError() == null) {
				ErrorType errorObject = objF.createErrorType();
				businessProcessAudit.setError(errorObject);
			}
			businessProcessAudit.getError().setErrorDetail(
					errorDetail.evaluateValue(synCtx));
		}
		
		if (contextParams != null) {
			contextParams.execute(businessProcessAudit.getContextParams(), synCtx);
		}

		if (isCascadeClose) { // close the last activity and the first receivetask

			ActivityTypeAudit activity = AuditMediatorUtils
					.findLastNoClosedActivity(businessProcessAudit
							.getActivities());

			ActivityBaseCommand commandCascase = null;

			if (activity instanceof SendTaskAudit) {

				SendTaskCommand commandCascaseTemp = new SendTaskCommand();
				
				commandCascase = commandCascaseTemp;
				if (isCaptureMsg) {
					commandCascaseTemp.setCaptureMsg(true);
				}
				
			} else if (activity instanceof ReceiveTaskAuditType) {
				ReceiveTaskCommand commandCascaseTemp = new ReceiveTaskCommand();
				commandCascase = commandCascaseTemp;
				if (isCaptureMsg) {
					commandCascaseTemp.setCaptureMsg(true);
				}
			

			} else if (activity instanceof TaskAuditType) {
				commandCascase = new TaskCommand();
			} else if (activity instanceof SubProcessAuditType) {
				commandCascase = new SubProcessCommand();
			}

			if (commandCascase != null) {
				commandCascase.setCloseWithStatus(status);
				commandCascase.setNew(false);
				commandCascase.execute(businessProcessAudit, synCtx);
			}

			ActivityTypeAudit firstActivityReceive = AuditMediatorUtils
					.findFirstActivityOfType(
							businessProcessAudit.getActivities(),
							ReceiveTaskAuditType.class.getName());

			if (firstActivityReceive.getEndTime() == null
					&& !activity.equals(firstActivityReceive)) {

				ReceiveTaskCommand commandCascaseTemp = new ReceiveTaskCommand();
				commandCascaseTemp.setCloseWithStatus(status);
				commandCascaseTemp.setNew(false);
				if (isCaptureMsg) {
					commandCascaseTemp.setCaptureMsg(true);
				}
				commandCascaseTemp.execute(businessProcessAudit, synCtx);
			}

		}
	}

	@Override
	public void parse(OMElement businessProcess) {

		if (log.isDebugEnabled() || log.isTraceEnabled()) {
			log.debug("Parse Transaction command");
		}

		ValueFactory valueFactory = new ValueFactory();

		OMAttribute idAtribute = businessProcess
				.getAttribute(AuditMediatorUtils.ID_ATT_QNAME);
		OMAttribute closeWithStatusAtribute = businessProcess
				.getAttribute(AuditMediatorUtils.CLOSE_WITH_STATUS_ATT_QNAME);
		OMAttribute cascadeCloseAtribute = businessProcess
				.getAttribute(AuditMediatorUtils.CASCADE_CLOSE_ATT_QNAME);
		OMAttribute captureMSGAtribute = businessProcess
				.getAttribute(AuditMediatorUtils.CAPTURE_MSG_ATT_QNAME);

		if (idAtribute != null) {
			String temp = idAtribute.getAttributeValue();
			if (!StringUtils.isBlank(temp)) {
				id = valueFactory.createValue(AuditMediatorUtils.ID_ATT_NAME,
						businessProcess);
			}
		}

		if (closeWithStatusAtribute != null) {
			String temp = closeWithStatusAtribute.getAttributeValue();
			if (!StringUtils.isBlank(temp)) {
				closeWithStatus = valueFactory.createValue(
						AuditMediatorUtils.CLOSE_WITH_STATUS_ATT_NAME,
						businessProcess);
			}
		}

		if (cascadeCloseAtribute != null) {
			try {
				isCascadeClose = Boolean.parseBoolean(cascadeCloseAtribute
						.getAttributeValue().trim());
			} catch (Exception e) {
				log.error("Error in attribute cascadeClose: "
						+ cascadeCloseAtribute.getAttributeValue(), e);

			}
		}

		if (captureMSGAtribute != null) {
			try {
				isCaptureMsg = Boolean.parseBoolean(captureMSGAtribute
						.getAttributeValue().trim());
			} catch (Exception e) {
				log.error("Error in attribute captureMsg: "
						+ captureMSGAtribute.getAttributeValue(), e);

			}
		}

		for (Iterator iterator = businessProcess.getChildElements(); iterator
				.hasNext();) {
			OMElement param = (OMElement) iterator.next();

			valueFactory = new ValueFactory();

			if (log.isDebugEnabled() || log.isTraceEnabled()) {
				log.debug("Parse element " + param);
			}

			if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.NAME_TAG_NAME)) {
				name = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);
			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.DESCRIPTION_TAG_NAME)) {
				description = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);
			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.START_TIME_TAG_NAME)) {
				startTime = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);
			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.END_TIME_TAG_NAME)) {
				endTime = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);
			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.STATUS_TAG_NAME)) {
				status = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);
			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.ERROR_CODE_TAG_NAME)) {
				errorCode = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);
			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.ERROR_MESSAGE_TAG_NAME)) {
				errorMessage = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);
			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.ERROR_DETAIL_TAG_NAME)) {
				errorDetail = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);
			} else if (AuditMediatorUtils.isTag(param,
						AuditMediatorUtils.CORRELATION_ID_TAG_NAME)) {
					correlationID = valueFactory.createValue(
							AuditMediatorUtils.VALUE_ATT_NAME, param);
			}  else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.CONTEXT_PARAMS_TAG_NAME)) {
				contextParams = new ParamsSubCommand();
				contextParams.parse(param);
			} else {
				log.error("Unable to create the Audit mediator. "
						+ "Unexpected element: "
						+ param
						+ " inside the businessProcess TAG of Audit mediator configuration");

			}

		}

	}

	@Override
	public OMElement serialize(OMFactory fac) {

		if (log.isDebugEnabled() || log.isTraceEnabled()) {
			log.debug("serialize Business Process comando");
		}

		ValueSerializer valueSerializer = new ValueSerializer();

		OMElement root = AuditMediatorUtils.createTag(fac,
				AuditMediatorUtils.BUSINESS_PROCESS_TAG_NAME);

		if (id != null) {
			AuditMediatorUtils.addAttributeWithValue(root,
					AuditMediatorUtils.ID_ATT_NAME, id);
		}
		if (closeWithStatus != null) {
			AuditMediatorUtils.addAttributeWithValue(root,
					AuditMediatorUtils.CLOSE_WITH_STATUS_ATT_NAME,
					closeWithStatus);
		}

		if (isCascadeClose) {
			root.addAttribute(fac.createOMAttribute(
					AuditMediatorUtils.CASCADE_CLOSE_ATT_NAME,
					AuditMediatorUtils.nullNS, Boolean.TRUE.toString()));
		}

		if (isCaptureMsg) {
			root.addAttribute(fac.createOMAttribute(
					AuditMediatorUtils.CAPTURE_MSG_ATT_NAME,
					AuditMediatorUtils.nullNS, Boolean.TRUE.toString()));
		}

		if (name != null) {

			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.NAME_TAG_NAME, name);
		}

		if (correlationID != null) {

			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.CORRELATION_ID_TAG_NAME, correlationID);
		}
		
		if (description != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.DESCRIPTION_TAG_NAME, description);
		}

		if (startTime != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.START_TIME_TAG_NAME, startTime);
		}
		if (endTime != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.END_TIME_TAG_NAME, endTime);
		}
		if (status != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.STATUS_TAG_NAME, status);
		}
		if (errorCode != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.ERROR_CODE_TAG_NAME, errorCode);
		}
		if (errorMessage != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.ERROR_MESSAGE_TAG_NAME, errorMessage);
		}
		if (errorDetail != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.ERROR_DETAIL_TAG_NAME, errorDetail);
		}
		if (contextParams != null) {
			root.addChild(contextParams.serialize(fac));
		}

		return root;
	}

	public Value getId() {
		return id;
	}

	public void setId(Value id) {
		this.id = id;
	}

	public Value getName() {
		return name;
	}

	public void setName(Value name) {
		this.name = name;
	}

	public Value getDescription() {
		return description;
	}

	public void setDescription(Value description) {
		this.description = description;
	}

	public Value getStartTime() {
		return startTime;
	}

	public void setStartTime(Value startTime) {
		this.startTime = startTime;
	}

	public Value getEndTime() {
		return endTime;
	}

	public void setEndTime(Value endTime) {
		this.endTime = endTime;
	}

	public Value getStatus() {
		return status;
	}

	public void setStatus(Value status) {
		this.status = status;
	}

	public Value getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Value errorCode) {
		this.errorCode = errorCode;
	}

	public Value getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(Value errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Value getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(Value errorDetail) {
		this.errorDetail = errorDetail;
	}

	public boolean isCascadeClose() {
		return isCascadeClose;
	}

	public void setCascadeClose(boolean isCascadeClose) {
		this.isCascadeClose = isCascadeClose;
	}

	public boolean isCaptureMsg() {
		return isCaptureMsg;
	}

	public void setCaptureMsg(boolean isCaptureMsg) {
		this.isCaptureMsg = isCaptureMsg;
	}

	public Value getCorrelationID() {
		return correlationID;
	}

	public void setCorrelationID(Value correlationID) {
		this.correlationID = correlationID;
	}

	public ParamsSubCommand getContextParams() {
		return contextParams;
	}

	public void setContextParams(ParamsSubCommand contextParams) {
		this.contextParams = contextParams;
	}

	

}
