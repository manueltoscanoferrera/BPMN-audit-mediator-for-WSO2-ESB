package org.codigolibre.auditbpmn.wso2mediator.command;

import java.io.StringWriter;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMAttribute;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axis2.description.Parameter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.MessageContext;
import org.apache.synapse.SynapseConstants;
import org.apache.synapse.config.xml.ValueFactory;
import org.apache.synapse.config.xml.ValueSerializer;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.endpoints.AbstractEndpoint;
import org.apache.synapse.mediators.Value;
import org.codigolibre.auditbpmn.jaxb.WebServiceAuditType;
import org.codigolibre.auditbpmn.wso2mediator.AuditMediatorUtils;

/**
 * WebService Implementation SubComand Audit
 * 
 */
public class WebServiceImplementationSubComand implements SubCommand {

	private static final Log log = LogFactory
			.getLog(WebServiceImplementationSubComand.class);

	private String tag_name;

	private Value endPoint;
	private Value operation;
	private Value type;
	private Value idMsg;
	private Value msgRequest;
	private Value msgResponse;
	private Value replyTO;
	private Value ipClient;
	private Value hostClient;
	private Value idClient;
	private Value ipServer;
	private Value hostServer;
	private boolean isClose = false;

	private boolean captureRequest = false;
	private boolean captureResponse = false;

	private boolean isExternalServiceInvocation = false;
	private boolean isJSON = false;

	@Override
	public void execute(Object parent, MessageContext synCtx) {

		if (log.isDebugEnabled() || log.isTraceEnabled()) {
			log.debug("execute WebService Implementation SubComand");
		}

		WebServiceAuditType webservice = (WebServiceAuditType) parent;

		// TODO: xforward-header

		if (endPoint != null) {
			webservice.setEndPoint(endPoint.evaluateValue(synCtx));
		}
		if (operation != null) {
			webservice.setOperation(operation.evaluateValue(synCtx));
		}

		if (type != null) {
			webservice.setType(type.evaluateValue(synCtx));
		}
		if (idMsg != null) {
			webservice.setIdMsg(idMsg.evaluateValue(synCtx));
		}
		if (msgRequest != null) {
			webservice.setMsgRequest(msgRequest.evaluateValue(synCtx));
		}
		if (msgResponse != null) {
			webservice.setMsgResponse(msgResponse.evaluateValue(synCtx));
		}
		if (replyTO != null) {
			webservice.setReplyTO(replyTO.evaluateValue(synCtx));
		}

		if (ipClient != null) {
			webservice.setIpClient(ipClient.evaluateValue(synCtx));
		}

		if (hostClient != null) {
			webservice.setHostClient(hostClient.evaluateValue(synCtx));
		}

		if (idClient != null) {
			webservice.setIdClient(idClient.evaluateValue(synCtx));
		}

		if (ipServer != null) {
			webservice.setIpServer(ipServer.evaluateValue(synCtx));
		}

		if (hostServer != null) {
			webservice.setHostServer(hostServer.evaluateValue(synCtx));
		}

		if (captureRequest) {

			StringWriter envelope = new StringWriter();
			try {
				synCtx.getEnvelope().serialize(envelope);
				webservice.setMsgRequest(envelope.toString());
			} catch (XMLStreamException e) {
				log.error(
						"Error in capture Request, MSG Context Envelope error:  "
								+ e.getMessage(), e);
			}
		}

		if (captureResponse) {

			StringWriter envelope = new StringWriter();
			try {
				synCtx.getEnvelope().serialize(envelope);

				webservice.setMsgResponse(envelope.toString());

			} catch (XMLStreamException e) {
				log.error(
						"Error in capture Response, MSG Context Envelope error: "
								+ e.getMessage(), e);
			}

		}

		autoComplete(webservice, synCtx);

	}

	/**
	 * 
	 * @param webservice
	 * @param synCtx
	 */
	private void autoComplete(WebServiceAuditType webservice,
			MessageContext synCtx) {

		// check if we are disabled
		String isDisabled = (String) synCtx
				.getProperty(AuditMediatorUtils.AUDIT_MEDIATOR_DISABLED_JMS_WS_PARAM_CAPTURE_PROPERTY);

		if (!StringUtils.isBlank(isDisabled)
				&& "TRUE".equalsIgnoreCase(isDisabled)) {

			if (log.isDebugEnabled()) {
				log.debug("AuditMediator WS & JMS capture params desactivate by property "
						+ AuditMediatorUtils.AUDIT_MEDIATOR_DISABLED_JMS_WS_PARAM_CAPTURE_PROPERTY
						+ " with value " + isDisabled);

			}

			return;
		}
		
		org.apache.axis2.context.MessageContext axis2smc = ((Axis2MessageContext) synCtx)
				.getAxis2MessageContext();


		if (log.isDebugEnabled()) {// DEBUG
			log.debug(" isClose " + isClose);
			log.debug(" isExternalServiceInvocation "
					+ isExternalServiceInvocation);
			// DEBUG
			try {
				ArrayList<Parameter> params = axis2smc.getAxisService()
						.getParameters();
				log.debug(" Params  axis2smc.getAxisService().getParameters()");

				for (Iterator iterator = params.iterator(); iterator.hasNext();) {
					Parameter parameter = (Parameter) iterator.next();
					if (parameter.getName().indexOf("wsdl") < 0)
						log.debug(parameter.getName() + " value -> "
								+ parameter.getValue());
				}

			} catch (Exception e) {
				// e.printStackTrace();
			} // dont worry
		}

		if (!isExternalServiceInvocation) { // receive task

			if (StringUtils.isBlank(webservice.getEndPoint())) {
				try {
					webservice
							.setEndPoint(axis2smc
									.getAxisService()
									.getEndpoint(
											axis2smc.getAxisService()
													.getEndpointName())
									.getEndpointURL());

				} catch (Exception e) {
					// e.printStackTrace();
				} // dont worry
			}

			if (StringUtils.isBlank(webservice.getType())) {
				try {
					if (((String) axis2smc.getProperty("messageType"))
							.toLowerCase().indexOf("json") > -1) {
						isJSON = true;
						webservice.setType(AuditMediatorUtils.JSON_WEBSERVICE_TYPE);
					} else {
						webservice.setType(AuditMediatorUtils.SOAP_WEBSERVICE_TYPE);
					}
				} catch (Exception e) {
				}
			}

			if (StringUtils.isBlank(webservice.getOperation()) && !isJSON) {

				webservice.setOperation(axis2smc.getWSAAction());
			}

			if (StringUtils.isBlank(webservice.getIpClient())) {

				webservice.setIpClient((String) axis2smc
						.getProperty("REMOTE_ADDR"));
			}

			if (StringUtils.isBlank(webservice.getHostClient())) {

				webservice.setHostClient((String) axis2smc
						.getProperty("REMOTE_HOST"));
			}

			try {
				if (StringUtils.isBlank(webservice.getIpServer())) {
					webservice.setIpServer(InetAddress.getLocalHost()
							.getHostAddress());
				}

				if (StringUtils.isBlank(webservice.getHostServer())) {
					webservice.setHostServer(InetAddress.getLocalHost()
							.getHostName());
				}

			} catch (Exception e) {
			} // dont worry

			try {
				if (StringUtils.isBlank(webservice.getIdClient())) {
					webservice.setIdClient((String) axis2smc
							.getProperty("username"));
				}
			} catch (Exception e) {
			} // dont worry

		} else { // send task

			try {
				if (StringUtils.isBlank(webservice.getIpClient())) {

					webservice.setIpClient(InetAddress.getLocalHost()
							.getHostAddress());
				}
			} catch (UnknownHostException e) {
			}

			try {
				if (StringUtils.isBlank(webservice.getHostClient())) {

					webservice.setHostClient(InetAddress.getLocalHost()
							.getHostName());

				}
			} catch (UnknownHostException e) {
			}

			if (isClose) { // ya se ha realizado la llamada al WS...
				if (log.isDebugEnabled()) {
					log.debug("synCtx.getProperty(SynapseConstants.LAST_ENDPOINT)) "
							+ synCtx.getProperty(SynapseConstants.LAST_ENDPOINT));
				}

				if (synCtx.getProperty(SynapseConstants.LAST_ENDPOINT) != null) {

					AbstractEndpoint endPointlast1 = (AbstractEndpoint) synCtx
							.getProperty(SynapseConstants.LAST_ENDPOINT);

					if (StringUtils.isBlank(webservice.getEndPoint())) {
						try {
							if (StringUtils.isNotBlank(endPointlast1.getName())) {
								webservice.setEndPoint(endPointlast1.getName());

							} else {
								webservice.setEndPoint(endPointlast1
										.getDefinition().getAddress());
							}

						} catch (Exception e) {
						}

					}

					/*
					 * log.debug("Propiedades del last endpoint ");
					 * 
					 * for (Iterator iterator = endPointlast1.getProperties()
					 * .iterator(); iterator.hasNext();) { MediatorProperty type
					 * = (MediatorProperty) iterator .next();
					 * 
					 * log.debug(type.getName() + " valor " + type.getValue());
					 * 
					 * }
					 */
					if (StringUtils.isBlank(webservice.getType())) {
						try {
							if (((String) axis2smc.getProperty("messageType"))
									.toLowerCase().indexOf("json") > -1) {
								isJSON = true;
								webservice.setType(AuditMediatorUtils.JSON_WEBSERVICE_TYPE);
							} else {
								webservice.setType(AuditMediatorUtils.SOAP_WEBSERVICE_TYPE);
							}
						} catch (Exception e) {
						}
					}


					if (StringUtils.isBlank(webservice.getOperation())
							&& !isJSON) {
						webservice.setOperation(axis2smc.getWSAAction());
					}

					try {
						if (StringUtils.isBlank(webservice.getIdClient())) {

							// ver si lo se puede sacar la política del endpoint
							// y de ahí el username

							// endPointlast1.getDefinition().getInboundWsSecPolicyKey()
							webservice.setIdClient((String) axis2smc
									.getProperty("username"));
						}
					} catch (Exception e) {
					} // dont worry

					if (StringUtils.isBlank(webservice.getIdMsg())) {

						webservice.setIdMsg(axis2smc.getMessageID());
					}

					try {
						URL url = new URL(endPointlast1.getDefinition()
								.getAddress());
						webservice.setIpServer(InetAddress.getByName(
								url.getHost()).getHostAddress());
						webservice.setHostServer(url.getHost());
					} catch (Exception e) {
					}

				}

			}

		}

	}

	@Override
	public void parse(OMElement webserviceElement) {

		ValueFactory valueFactory = new ValueFactory();

		for (Iterator iterator = webserviceElement.getChildElements(); iterator
				.hasNext();) {
			OMElement param = (OMElement) iterator.next();

			valueFactory = new ValueFactory();

			if (log.isDebugEnabled() || log.isTraceEnabled()) {
				log.debug("Parse element " + param);
			}

			if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.ENDPOINT_TAG_NAME)) {
				endPoint = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);
			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.OPERATION_TAG_NAME)) {
				operation = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);
			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.TYPE_TAG_NAME)) {
				type = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);
			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.IDMSG_TAG_NAME)) {
				idMsg = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);
			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.MESSAGE_REQUEST_TAG_NAME)) {
				OMAttribute atributteCapture = param
						.getAttribute(AuditMediatorUtils.CAPTURE_ATT_QNAME);
				if (atributteCapture != null
						&& atributteCapture.getAttributeValue() != null
						&& "TRUE".equalsIgnoreCase(atributteCapture
								.getAttributeValue())) {
					captureRequest = true;
				} else {
					msgRequest = valueFactory.createValue(
							AuditMediatorUtils.VALUE_ATT_NAME, param);
				}
			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.MESSAGE_RESPONSE_TAG_NAME)) {
				OMAttribute atributteCapture = param
						.getAttribute(AuditMediatorUtils.CAPTURE_ATT_QNAME);
				if (atributteCapture != null
						&& atributteCapture.getAttributeValue() != null
						&& "TRUE".equalsIgnoreCase(atributteCapture
								.getAttributeValue())) {
					captureResponse = true;
				} else {
					msgResponse = valueFactory.createValue(
							AuditMediatorUtils.VALUE_ATT_NAME, param);
				}
			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.REPLY_TO_TAG_NAME)) {
				replyTO = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);
			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.IP_CLIENT_TAG_NAME)) {
				ipClient = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);
			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.HOST_CLIENT_TAG_NAME)) {
				hostClient = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);
			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.ID_CLIENT_TAG_NAME)) {
				idClient = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);
			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.IP_SERVER_TAG_NAME)) {
				ipServer = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);
			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.HOST_SERVER_TAG_NAME)) {
				hostServer = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);
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
			log.debug("serialize Webservice Implementation SubComando");
		}

		ValueSerializer valueSerializer = new ValueSerializer();

		OMElement root = AuditMediatorUtils.createTag(fac,
				AuditMediatorUtils.WEB_SERVICE_TAG_NAME);

		if (endPoint != null) {

			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.ENDPOINT_TAG_NAME, endPoint);
		}

		if (operation != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.OPERATION_TAG_NAME, operation);
		}

		if (type != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.TYPE_TAG_NAME, type);
		}
		if (idMsg != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.IDMSG_TAG_NAME, idMsg);
		}
		if (msgRequest != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.MESSAGE_REQUEST_TAG_NAME, msgRequest);
		}
		if (captureRequest) {
			AuditMediatorUtils.addChildTagWithAttributeValue(root,
					AuditMediatorUtils.MESSAGE_REQUEST_TAG_NAME,
					AuditMediatorUtils.CAPTURE_ATT_NAME,
					Boolean.TRUE.toString());
		}

		if (msgResponse != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.MESSAGE_RESPONSE_TAG_NAME, msgResponse);
		}

		if (captureResponse) {
			AuditMediatorUtils.addChildTagWithAttributeValue(root,
					AuditMediatorUtils.MESSAGE_RESPONSE_TAG_NAME,
					AuditMediatorUtils.CAPTURE_ATT_NAME,
					Boolean.TRUE.toString());
		}

		if (replyTO != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.REPLY_TO_TAG_NAME, replyTO);
		}
		if (ipClient != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.IP_CLIENT_TAG_NAME, ipClient);
		}

		if (hostClient != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.HOST_CLIENT_TAG_NAME, hostClient);
		}

		if (idClient != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.ID_CLIENT_TAG_NAME, idClient);
		}

		if (ipServer != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.IP_SERVER_TAG_NAME, ipServer);
		}
		if (hostServer != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.HOST_SERVER_TAG_NAME, hostServer);
		}

		return root;
	}

	public String getTag_name() {
		return tag_name;
	}

	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}

	public Value getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Value endPoint) {
		this.endPoint = endPoint;
	}

	public Value getOperation() {
		return operation;
	}

	public void setOperation(Value operation) {
		this.operation = operation;
	}

	public Value getType() {
		return type;
	}

	public void setType(Value type) {
		this.type = type;
	}

	public Value getIdMsg() {
		return idMsg;
	}

	public void setIdMsg(Value idMsg) {
		this.idMsg = idMsg;
	}

	public Value getMsgRequest() {
		return msgRequest;
	}

	public void setMsgRequest(Value msgRequest) {
		this.msgRequest = msgRequest;
	}

	public Value getMsgResponse() {
		return msgResponse;
	}

	public void setMsgResponse(Value msgResponse) {
		this.msgResponse = msgResponse;
	}

	public Value getReplyTO() {
		return replyTO;
	}

	public void setReplyTO(Value replyTO) {
		this.replyTO = replyTO;
	}

	public Value getIpClient() {
		return ipClient;
	}

	public void setIpClient(Value ipClient) {
		this.ipClient = ipClient;
	}

	public Value getHostClient() {
		return hostClient;
	}

	public void setHostClient(Value hostClient) {
		this.hostClient = hostClient;
	}

	public Value getIdClient() {
		return idClient;
	}

	public void setIdClient(Value idClient) {
		this.idClient = idClient;
	}

	public Value getIpServer() {
		return ipServer;
	}

	public void setIpServer(Value ipServer) {
		this.ipServer = ipServer;
	}

	public Value getHostServer() {
		return hostServer;
	}

	public void setHostServer(Value hostServer) {
		this.hostServer = hostServer;
	}

	public boolean isCaptureRequest() {
		return captureRequest;
	}

	public void setCaptureRequest(boolean captureRequest) {
		this.captureRequest = captureRequest;
	}

	public boolean isCaptureResponse() {
		return captureResponse;
	}

	public void setCaptureResponse(boolean captureResponse) {
		this.captureResponse = captureResponse;
	}

	public static Log getLog() {
		return log;
	}

	public boolean isExternalServiceInvocation() {
		return isExternalServiceInvocation;
	}

	public void setExternalServiceInvocation(boolean isInvocation) {
		this.isExternalServiceInvocation = isInvocation;
	}

	public boolean isClose() {
		return isClose;
	}

	public void setClose(boolean isClose) {
		this.isClose = isClose;
	}

}
