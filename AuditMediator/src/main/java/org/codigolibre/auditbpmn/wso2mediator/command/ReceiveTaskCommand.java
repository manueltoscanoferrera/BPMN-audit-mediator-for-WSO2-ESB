package org.codigolibre.auditbpmn.wso2mediator.command;

import javax.xml.bind.JAXBElement;

import org.apache.axiom.om.OMAttribute;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.synapse.MessageContext;
import org.apache.synapse.SynapseConstants;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.codigolibre.auditbpmn.jaxb.ActivityTypeAudit;
import org.codigolibre.auditbpmn.jaxb.BusinessProcessAudit;
import org.codigolibre.auditbpmn.jaxb.JMSServiceAuditType;
import org.codigolibre.auditbpmn.jaxb.ReceiveTaskAuditType;
import org.codigolibre.auditbpmn.jaxb.WebServiceAuditType;
import org.codigolibre.auditbpmn.wso2mediator.AuditMediatorUtils;

/**
 * Receive task Audit Command
 */
public class ReceiveTaskCommand extends ActivityBaseCommand {

	private boolean isCaptureMsg = false;


	private SubCommand implementationService;

	public ReceiveTaskCommand() {
		super(AuditMediatorUtils.RECEIVE_TASK_TAG_NAME,
				ReceiveTaskAuditType.class);
	}

	@Override
	protected JAXBElement createNewActivity() {
		return objF.createReceiveTaskAudit(new ReceiveTaskAuditType());
	}

	@Override
	public void subClassExecute(BusinessProcessAudit businessProcessAudit,
			ActivityTypeAudit activity, MessageContext synCtx) {

		if (log.isDebugEnabled() || log.isTraceEnabled()) {
			log.debug("Execute Receive Task comando");
			log.debug("isCaptureMsg " + isCaptureMsg + "  isNew " + isNew
					+ " closeWithStatus " + closeWithStatus);
		}

		if (isCaptureMsg) {

			if (implementationService == null) {
				this.setImplementationService(new WebServiceImplementationSubComand());//Default
			}

			if (isNew && closeWithStatus == null) {
				if (implementationService instanceof WebServiceImplementationSubComand)
					((WebServiceImplementationSubComand) implementationService)
							.setCaptureRequest(true);

				if (implementationService instanceof JMSImplementationSubComand)
					((JMSImplementationSubComand) implementationService)
							.setCaptureRequest(true);

			} else {
				if (implementationService instanceof WebServiceImplementationSubComand){
					((WebServiceImplementationSubComand) implementationService)
							.setCaptureResponse(true);

				}else if (implementationService instanceof
				 JMSImplementationSubComand){
				 ((JMSImplementationSubComand)implementationService).setCaptureResponse(true);}
			}
		}

		if (closeWithStatus != null || status != null) {
			if (implementationService instanceof WebServiceImplementationSubComand)
				((WebServiceImplementationSubComand) implementationService)
						.setClose(true);

			if (implementationService instanceof JMSImplementationSubComand)
				((JMSImplementationSubComand) implementationService)
						.setClose(true);

		}

		if (implementationService != null) {

			ReceiveTaskAuditType receiveTaskAuditType = (ReceiveTaskAuditType) activity;

			if (receiveTaskAuditType.getImplementationServiceAudit() == null) {

				if (implementationService instanceof WebServiceImplementationSubComand) {
					WebServiceAuditType webservice = objF
							.createWebServiceAuditType();
					receiveTaskAuditType.setImplementationServiceAudit(objF
							.createWebServiceAudit(webservice));

				} else if (implementationService instanceof JMSImplementationSubComand) {
					JMSServiceAuditType jmsService = objF
							.createJMSServiceAuditType();
					receiveTaskAuditType.setImplementationServiceAudit(objF
							.createJmsServiceAudit(jmsService));

				}

			}

			implementationService.execute(receiveTaskAuditType
					.getImplementationServiceAudit().getValue(), synCtx);

			if (StringUtils.isBlank(activity.getName())) {
				activity.setName((String) synCtx
						.getProperty(SynapseConstants.PROXY_SERVICE));
			}
			
			if (StringUtils.isBlank(activity.getDescription())) {
				try{
				activity.setDescription((((Axis2MessageContext) synCtx)
						.getAxis2MessageContext()).getAxisService().getDocumentation()  );
				}catch (Exception e){}
			}
		}

	}

	@Override
	public void subClassParseRoot(OMElement activityElement) {

		if (log.isDebugEnabled() || log.isTraceEnabled()) {
			log.debug("Parse Receive Task command");
		}

		OMAttribute captureMSGAtribute = activityElement
				.getAttribute(AuditMediatorUtils.CAPTURE_MSG_ATT_QNAME);

		if (captureMSGAtribute != null) {
			try {
				isCaptureMsg = Boolean.parseBoolean(captureMSGAtribute
						.getAttributeValue());
			} catch (Exception e) {
				log.error("Error in Task, attribute captureMSG: "
						+ captureMSGAtribute.getAttributeValue(), e);

			}
		}

	}

	@Override
	public void subClassParseChild(OMElement activityElement) {
		
		
		if (AuditMediatorUtils.isTag(activityElement,
				AuditMediatorUtils.WEB_SERVICE_TAG_NAME)) {
			implementationService = new WebServiceImplementationSubComand();
			implementationService.parse(activityElement);
			((WebServiceImplementationSubComand)implementationService).setExternalServiceInvocation(false);

		} else if (AuditMediatorUtils.isTag(activityElement,
				AuditMediatorUtils.JMS_SERVICE_TAG_NAME)) {
			implementationService = new JMSImplementationSubComand();
			implementationService.parse(activityElement);
			((JMSImplementationSubComand)implementationService).setExternalServiceInvocation(false);

		} else {
			log.error("Unable to create the Audit mediator. "
					+ "Unexpected element: "
					+ activityElement
					+ " inside the businessProcess TAG of Audit mediator configuration");

		}

	}

	@Override
	public void subClassSerialize(OMFactory fac, OMElement root) {
		// OMElement root = super.serialize(fac);

		if (log.isDebugEnabled() || log.isTraceEnabled()) {
			log.debug("serialize Receive task comando");
		}

		if (isCaptureMsg) {
			root.addAttribute(fac.createOMAttribute(
					AuditMediatorUtils.CAPTURE_MSG_ATT_NAME,
					AuditMediatorUtils.nullNS, Boolean.TRUE.toString()));
		}

		if (implementationService != null) {
			root.addChild(implementationService.serialize(fac));
		}

	}

	@Override
	public void subClassInitializeActivity(ActivityTypeAudit activity) {

	}

	public boolean isCaptureMsg() {
		return isCaptureMsg;
	}

	public void setCaptureMsg(boolean isCaptureMsg) {
		this.isCaptureMsg = isCaptureMsg;
	}

	public SubCommand getImplementationService() {
		return implementationService;
	}

	public void setImplementationService(SubCommand implementationService) {
		this.implementationService = implementationService;
	}

	



}