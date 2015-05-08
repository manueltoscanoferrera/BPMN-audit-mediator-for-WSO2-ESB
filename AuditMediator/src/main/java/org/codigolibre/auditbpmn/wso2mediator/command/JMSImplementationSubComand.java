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
import org.apache.axis2.transport.jms.JMSConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.MessageContext;
import org.apache.synapse.SynapseConstants;
import org.apache.synapse.config.xml.ValueFactory;
import org.apache.synapse.config.xml.ValueSerializer;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.endpoints.AbstractEndpoint;
import org.apache.synapse.mediators.MediatorProperty;
import org.apache.synapse.mediators.Value;
import org.codigolibre.auditbpmn.jaxb.JMSServiceAuditType;
import org.codigolibre.auditbpmn.wso2mediator.AuditMediatorUtils;

/**
 * JMS Service Implementation SubComand Audit
 * 
 */
public class JMSImplementationSubComand implements SubCommand {

	private static final Log log = LogFactory
			.getLog(JMSImplementationSubComand.class);

	private String tag_name;

	private Value endPoint;
	private Value destination;
	private Value destinationType;
	private Value defaultReplyDestination;
	private Value defaultReplyDestinationType;
	private Value contentType;
	private Value idMessage;
	private Value idCorrelation;
	private Value msgRequest;
	private Value msgResponse;
	private Value ipClient;
	private Value hostClient;
	private Value idClient;
	private Value ipServer;
	private Value hostServer;

	private boolean isClose = false;

	private boolean captureRequest = false;
	private boolean captureResponse = false;
	private boolean isExternalServiceInvocation = false;

	@Override
	public void execute(Object parent, MessageContext synCtx) {

		if (log.isDebugEnabled() || log.isTraceEnabled()) {
			log.debug("execute WebService Implementation SubComand");
		}

		JMSServiceAuditType jmsService = (JMSServiceAuditType) parent;

		if (endPoint != null) {
			jmsService.setEndPoint(endPoint.evaluateValue(synCtx));
		}

		if (destination != null) {
			jmsService.setDestination(destination.evaluateValue(synCtx));
		}
		if (destinationType != null) {
			jmsService
					.setDestinationType(destinationType.evaluateValue(synCtx));
		}
		if (defaultReplyDestination != null) {
			jmsService.setDefaultReplyDestination(defaultReplyDestination
					.evaluateValue(synCtx));
		}
		if (defaultReplyDestinationType != null) {
			jmsService
					.setDefaultReplyDestinationType(defaultReplyDestinationType
							.evaluateValue(synCtx));
		}
		if (contentType != null) {
			jmsService.setContentType(contentType.evaluateValue(synCtx));
		}

		if (idMessage != null) {
			jmsService.setIdMsg(idMessage.evaluateValue(synCtx));
		}

		if (idCorrelation != null) {
			jmsService.setIdCorrelation(idCorrelation.evaluateValue(synCtx));
		}
		if (idCorrelation != null) {
			jmsService.setIdCorrelation(idCorrelation.evaluateValue(synCtx));
		}

		if (msgRequest != null) {
			jmsService.setMsgRequest(msgRequest.evaluateValue(synCtx));
		}
		if (msgResponse != null) {
			jmsService.setMsgResponse(msgResponse.evaluateValue(synCtx));
		}

		if (ipClient != null) {
			jmsService.setIpClient(ipClient.evaluateValue(synCtx));
		}

		if (hostClient != null) {
			jmsService.setHostClient(hostClient.evaluateValue(synCtx));
		}

		if (idClient != null) {
			jmsService.setIdClient(idClient.evaluateValue(synCtx));
		}

		if (ipServer != null) {
			jmsService.setIpServer(ipServer.evaluateValue(synCtx));
		}

		if (hostServer != null) {
			jmsService.setHostServer(hostServer.evaluateValue(synCtx));
		}

		if (captureRequest) {

			StringWriter envelope = new StringWriter();
			try {
				synCtx.getEnvelope().serialize(envelope);
				jmsService.setMsgRequest(envelope.toString());
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

				jmsService.setMsgResponse(envelope.toString());

			} catch (XMLStreamException e) {
				log.error(
						"Error in capture Response, MSG Context Envelope error: "
								+ e.getMessage(), e);
			}

		}

		autoComplete(jmsService, synCtx);

	}

	/**
	 * 
	 * @param jmsService
	 * @param synCtx
	 */

	private void autoComplete(JMSServiceAuditType jmsService,
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

		if (log.isDebugEnabled()) {
			log.debug(" isClose " + isClose);
			log.debug(" isExternalServiceInvocation " + isExternalServiceInvocation);
		}

		if (StringUtils.isBlank(jmsService.getDestination())) {
			try {
				jmsService.setDestination((String) axis2smc.getAxisService()
						.getParameter(JMSConstants.PARAM_DESTINATION)
						.getValue());
			} catch (Exception e) {
			}
		}

		if (StringUtils.isBlank(jmsService.getDestinationType())) {
			try {
				jmsService.setDestinationType((String) axis2smc
						.getAxisService()
						.getParameter(JMSConstants.PARAM_DEST_TYPE).getValue());
			} catch (Exception e) {
			}
		}

		if (StringUtils.isBlank(jmsService.getDefaultReplyDestination())) {
			try {
				jmsService.setDefaultReplyDestination((String) axis2smc
						.getAxisService()
						.getParameter(
								"transport.jms.DefaultReplyDestinationType")
						.getValue());
			} catch (Exception e) {
			}
		}

		if (StringUtils.isBlank(jmsService.getDefaultReplyDestinationType())) {
			try {
				jmsService.setDefaultReplyDestinationType((String) axis2smc
						.getAxisService()
						.getParameter("transport.jms.DefaultReplyDestination")
						.getValue());
			} catch (Exception e) {
			}
		}

		if (StringUtils.isBlank(jmsService.getContentType())) {
			try {
				jmsService.setContentType((String) axis2smc.getAxisService()
						.getParameter("transport.jms.ContentType").getValue());
			} catch (Exception e) {
			}
		}

		if (StringUtils.isBlank(jmsService.getIdMsg())) {
			try {
				jmsService.setIdMsg(axis2smc.getMessageID());
			} catch (Exception e) {
			}
		}

		if (!isExternalServiceInvocation) { // receive task

			if (StringUtils.isBlank(jmsService.getEndPoint())) {

				try {
					jmsService.setEndPoint(axis2smc.getAxisService()
							.getEndpointName());

				} catch (Exception e) {
				}
			}
			

			if (StringUtils.isBlank(jmsService.getIpClient())) {

				jmsService.setIpClient((String) axis2smc
						.getProperty("REMOTE_ADDR"));
			}

			if (StringUtils.isBlank(jmsService.getHostClient())) {

				jmsService.setHostClient((String) axis2smc
						.getProperty("REMOTE_HOST"));
			}

			try {
				if (StringUtils.isBlank(jmsService.getIpServer())) {
					jmsService.setIpServer(InetAddress.getLocalHost()
							.getHostAddress());
				}

				if (StringUtils.isBlank(jmsService.getHostServer())) {
					jmsService.setHostServer(InetAddress.getLocalHost()
							.getHostName());
				}

			} catch (Exception e) {
			} // dont worry


		} else { // send task

			try {
				if (StringUtils.isBlank(jmsService.getIpClient())) {

					jmsService.setIpClient(InetAddress.getLocalHost()
							.getHostAddress());
				}
			} catch (UnknownHostException e) {
			}

			try {
				if (StringUtils.isBlank(jmsService.getHostClient())) {

					jmsService.setHostClient(InetAddress.getLocalHost()
							.getHostName());

				}
			} catch (UnknownHostException e) {
			}

			if (isClose) { // ya se ha realizado la llamada al JMS...
				if (log.isDebugEnabled()){
					log.debug("synCtx.getProperty(SynapseConstants.LAST_ENDPOINT)) "
						+ synCtx.getProperty(SynapseConstants.LAST_ENDPOINT));
				}

				if (synCtx.getProperty(SynapseConstants.LAST_ENDPOINT) != null) {

					AbstractEndpoint endPointlast1 = (AbstractEndpoint) synCtx
							.getProperty(SynapseConstants.LAST_ENDPOINT);

					if (StringUtils.isBlank(jmsService.getEndPoint())) {
						try {
							if (StringUtils.isNotBlank(endPointlast1.getName())) {
								jmsService.setEndPoint(endPointlast1.getName());

							} else {
								jmsService.setEndPoint(endPointlast1
										.getDefinition().getAddress());
							}

						} catch (Exception e) {
						}

					}


					try {
						URL url = new URL(endPointlast1.getDefinition()
								.getAddress());
						jmsService.setIpServer(InetAddress.getByName(
								url.getHost()).getHostAddress());
						jmsService.setHostServer(url.getHost());
					} catch (Exception e) {
					}

				}

			}

		}

	}

	@Override
	public void parse(OMElement jmsElement) {

		ValueFactory valueFactory = new ValueFactory();

		for (Iterator iterator = jmsElement.getChildElements(); iterator
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
					AuditMediatorUtils.DESTINATION_TAG_NAME)) {
				destination = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);
			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.DESTINATION_TYPE_TAG_NAME)) {
				destinationType = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);
			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.DEFAULT_REPLY_DESTINATION_TAG_NAME)) {
				defaultReplyDestination = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);

			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.DEFAULT_REPLY_DESTINATION_TYPE_TAG_NAME)) {
				defaultReplyDestinationType = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);

			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.CONTENT_TYPE_TAG_NAME)) {
				contentType = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);

			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.IDMSG_TAG_NAME)) {
				idMessage = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);

			} else if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.ID_CORRELATION_TAG_NAME)) {
				idCorrelation = valueFactory.createValue(
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
				AuditMediatorUtils.JMS_SERVICE_TAG_NAME);

		if (endPoint != null) {

			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.ENDPOINT_TAG_NAME, endPoint);
		}

		if (destination != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.DESTINATION_TAG_NAME, destination);
		}

		if (destinationType != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.DESTINATION_TYPE_TAG_NAME,
					destinationType);
		}

		if (defaultReplyDestination != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.DEFAULT_REPLY_DESTINATION_TAG_NAME,
					defaultReplyDestination);
		}

		if (defaultReplyDestinationType != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.DEFAULT_REPLY_DESTINATION_TYPE_TAG_NAME,
					defaultReplyDestinationType);
		}

		if (contentType != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.CONTENT_TYPE_TAG_NAME, contentType);
		}

		if (idMessage != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.IDMSG_TAG_NAME, idMessage);
		}

		if (idCorrelation != null) {
			AuditMediatorUtils.addChildTagWithValue(root,
					AuditMediatorUtils.ID_CORRELATION_TAG_NAME, idCorrelation);
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

	public Value getDestination() {
		return destination;
	}

	public void setDestination(Value destination) {
		this.destination = destination;
	}

	public Value getDestinationType() {
		return destinationType;
	}

	public void setDestinationType(Value destinationType) {
		this.destinationType = destinationType;
	}

	public Value getDefaultReplyDestination() {
		return defaultReplyDestination;
	}

	public void setDefaultReplyDestination(Value defaultReplyDestination) {
		this.defaultReplyDestination = defaultReplyDestination;
	}

	public Value getDefaultReplyDestinationType() {
		return defaultReplyDestinationType;
	}

	public void setDefaultReplyDestinationType(Value defaultReplyDestinationType) {
		this.defaultReplyDestinationType = defaultReplyDestinationType;
	}

	public Value getContentType() {
		return contentType;
	}

	public void setContentType(Value contentType) {
		this.contentType = contentType;
	}

	public Value getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(Value idMessage) {
		this.idMessage = idMessage;
	}

	public Value getIdCorrelation() {
		return idCorrelation;
	}

	public void setIdCorrelation(Value idCorrelation) {
		this.idCorrelation = idCorrelation;
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

	public boolean isClose() {
		return isClose;
	}

	public void setClose(boolean isClose) {
		this.isClose = isClose;
	}

	public boolean isCaptureRequest() {
		return captureRequest;
	}

	public void setCaptureRequest(boolean captureRequest) {
		this.captureRequest = captureRequest;
	}

	public boolean isExternalServiceInvocation() {
		return isExternalServiceInvocation;
	}

	public void setExternalServiceInvocation(boolean isExternalServiceInvocation) {
		this.isExternalServiceInvocation = isExternalServiceInvocation;
	}

	public boolean isCaptureResponse() {
		return captureResponse;
	}

	public void setCaptureResponse(boolean captureResponse) {
		this.captureResponse = captureResponse;
	}

}
