package org.codigolibre.auditbpmn.wso2mediator;

import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.synapse.config.xml.ValueSerializer;
import org.apache.synapse.config.xml.XMLConfigConstants;
import org.apache.synapse.mediators.Value;
import org.codigolibre.auditbpmn.jaxb.ActivityTypeAudit;
import org.codigolibre.auditbpmn.jaxb.SubProcessAuditType;

public class AuditMediatorUtils {

	private static ValueSerializer valueSerializer = new ValueSerializer();

	// context variable where audit JAXB object is stored
	public static final String AUDIT_MEDIATOR_CONTEXT_PROPERTY = "AUDIT_MEDIATOR_JAXB_TRANSACTION_OBJECT";

	// context variable that disables this mediator
	public static final String AUDIT_MEDIATOR_DISABLED_CONTEXT_PROPERTY = "AUDIT_MEDIATOR_DISABLED";

	
	// context variable that disables this mediator
	public static final String AUDIT_MEDIATOR_DISABLED_JMS_WS_PARAM_CAPTURE_PROPERTY = "AUDIT_MEDIATOR_DISABLED_JMS_WS_PARAM_CAPTURE";

	// namespace for the audit tag... we change the value latter, is more
	// comfortable to work with synapse namespace
	public static String AUDIT_MEDIATOR_NAMESPACE = "urn:org:codigolibre:businessprocessaudit:type:v1.0.0";

	public static OMNamespace AUDIT_MEDIATOR_NAMESPACE_OM = null;

	// root tag
	public static final String AUDIT_TAG_NAME = "audit";
	public static final QName AUDIT_QNAME = new QName(
			XMLConfigConstants.SYNAPSE_NAMESPACE, AUDIT_TAG_NAME);

	// mains tags
	public static final String BUSINESS_PROCESS_TAG_NAME = "businessProcess";
	public static final String TASK_TAG_NAME = "task";
	public static final String RECEIVE_TASK_TAG_NAME = "receiveTask";
	public static final String SEND_TASK_TAG_NAME = "sendTask";
	public static final String SUBPROCESS_TAG_NAME = "subProcess";
	public static final String SERIALIZE_TAG_NAME = "serialize";

	// subtags
	public static final String WEB_SERVICE_TAG_NAME = "webService";
	public static final String JMS_SERVICE_TAG_NAME = "jmsService";

	// Commons tags
	public static final String NAME_TAG_NAME = "name";
	public static final String DESCRIPTION_TAG_NAME = "description";
	public static final String ID_TRANSACCION_TAG_NAME = "idTransaccion";
	public static final String START_TIME_TAG_NAME = "startTime";
	public static final String END_TIME_TAG_NAME = "endTime";
	public static final String STATUS_TAG_NAME = "status";

	// params tags
	public static final String INPUT_PARAMS_TAG_NAME = "inputParams";
	public static final String OUTPUT_PARAMS_TAG_NAME = "outputParams";
	public static final String CONTEXT_PARAMS_TAG_NAME = "contextParams";
	public static final String PARAM_TAG_NAME = "param";

	// Webservice tags
	public static final String ENDPOINT_TAG_NAME = "endPoint";
	public static final String OPERATION_TAG_NAME = "operation";
	public static final String TYPE_TAG_NAME = "type";
	public static final String IDMSG_TAG_NAME = "idMessage";
	public static final String MESSAGE_REQUEST_TAG_NAME = "msgRequest";
	public static final String MESSAGE_RESPONSE_TAG_NAME = "msgResponse";
	public static final String REPLY_TO_TAG_NAME = "replyTO";
	public static final String IP_CLIENT_TAG_NAME = "ipClient";
	public static final String HOST_CLIENT_TAG_NAME = "hostClient";
	public static final String ID_CLIENT_TAG_NAME = "idClient";
	public static final String IP_SERVER_TAG_NAME = "ipServer";
	public static final String HOST_SERVER_TAG_NAME = "hostServer";
	
	// JMS Service tags

	public static final String DESTINATION_TAG_NAME = "destination";
	public static final String DESTINATION_TYPE_TAG_NAME = "destinationType";
	public static final String DEFAULT_REPLY_DESTINATION_TAG_NAME = "defaultReplyDestination";
	public static final String DEFAULT_REPLY_DESTINATION_TYPE_TAG_NAME = "defaultReplyDestinationType";
	public static final String CONTENT_TYPE_TAG_NAME = "contentType";
	public static final String ID_CORRELATION_TAG_NAME = "idCorrelation";

	// attribute tags
	public static final String NAME_ATT_NAME = "name";
	public static final QName NAME_ATT_QNAME = new QName(
			XMLConfigConstants.NULL_NAMESPACE, NAME_ATT_NAME);

	public static final String VALUE_ATT_NAME = "value";
	public static final QName VALUE_ATT_QNAME = new QName(
			XMLConfigConstants.NULL_NAMESPACE, VALUE_ATT_NAME);

	public static final String CAPTURE_ATT_NAME = "capture";
	public static final QName CAPTURE_ATT_QNAME = new QName(
			XMLConfigConstants.NULL_NAMESPACE, CAPTURE_ATT_NAME);

	public static final String ID_ATT_NAME = "id";
	public static final QName ID_ATT_QNAME = new QName(
			XMLConfigConstants.NULL_NAMESPACE, ID_ATT_NAME);

	public static final String CLOSE_WITH_STATUS_ATT_NAME = "closeWithStatus";
	public static final QName CLOSE_WITH_STATUS_ATT_QNAME = new QName(
			XMLConfigConstants.NULL_NAMESPACE, CLOSE_WITH_STATUS_ATT_NAME);

	public static final String CASCADE_CLOSE_ATT_NAME = "cascadeClose";
	public static final QName CASCADE_CLOSE_ATT_QNAME = new QName(
			XMLConfigConstants.NULL_NAMESPACE, CASCADE_CLOSE_ATT_NAME);

	public static final String CAPTURE_MSG_ATT_NAME = "captureMsg";
	public static final QName CAPTURE_MSG_ATT_QNAME = new QName(
			XMLConfigConstants.NULL_NAMESPACE, CAPTURE_MSG_ATT_NAME);

	public static final String NEW_ATT_NAME = "new";
	public static final QName NEW_ATT_QNAME = new QName(
			XMLConfigConstants.NULL_NAMESPACE, NEW_ATT_NAME);

	public static final String TARGET_ATT_NAME = "target";
	public static final QName TARGET_ATT_QNAME = new QName(
			XMLConfigConstants.NULL_NAMESPACE, TARGET_ATT_NAME);

	public static final String STORE_ATT_NAME = "store";
	public static final QName STORE_ATT_QNAME = new QName(
			XMLConfigConstants.NULL_NAMESPACE, STORE_ATT_NAME);

	public static final String MEDIA_TYPE_ATT_NAME = "media-type";
	public static final QName MEDIA_TYPE_ATT_QNAME = new QName(
			XMLConfigConstants.NULL_NAMESPACE, MEDIA_TYPE_ATT_NAME);

	public static final String LOGGER_NAME_ATT_NAME = "logger";
	public static final QName LOGGER_NAME_QNAME = new QName(
			XMLConfigConstants.NULL_NAMESPACE, LOGGER_NAME_ATT_NAME);

	
	// Error tags
	public static final String ERROR_CODE_TAG_NAME = "errorCode";
	public static final String ERROR_MESSAGE_TAG_NAME = "errorMessage";
	public static final String ERROR_DETAIL_TAG_NAME = "errorDetail";

	// content types
	public final static String JSON_CONTENT_TYPE = "application/json";
	public final static String XML_CONTENT_TYPE = "application/xml";
	public final static String SOAP11_CONTENT_TYPE = "text/xml";
	public final static String SOAP12_CONTENT_TYPE = "application/soap+xml";
	public final static String JSON_TYPE = "json";
	public final static String XML_TYPE = "xml";

	public final static String JSON_WEBSERVICE_TYPE = "JSON";
	public final static String SOAP_WEBSERVICE_TYPE = "SOAP";
	
	// predefined states for lifecycle of a generic audit element
	public static final String IN_PROGRESS_STATUS = "UNSPECIFIED";
	public static final String OK_STATUS = "OK";
	public static final String ERROR_STATUS = "ERROR";

	// null Namespace
	public static final org.apache.axiom.om.OMNamespace nullNS = OMAbstractFactory
			.getOMFactory().createOMNamespace("", "");

	public static final String CORRELATION_ID_TAG_NAME = "correlationID";
	
	/**
	 * Utility to change easy the namespace of the audit mediator
	 * 
	 * @param ns
	 */
	public static void setAuditMediatorBaseNS(OMNamespace ns) {
		AUDIT_MEDIATOR_NAMESPACE_OM = ns;
		AUDIT_MEDIATOR_NAMESPACE = ns.getNamespaceURI();

	}

	/**
	 * Utils method
	 */

	/**
	 * Is this "element" a tag of type "tagQname"
	 * 
	 * @param element
	 * @param tagQname
	 * @return
	 */
	public static boolean isTag(OMElement element, String tagQname) {
		/**
		 * The Namespace and capitalizations are ignored in the comparison for
		 * easy editing tags inside the tag audit
		 */
		if (tagQname.equalsIgnoreCase(element.getLocalName())) {
			return true;
		} else
			return false;
	}

	/**
	 * Build a new tag of type "tagName"
	 * 
	 * @param fac
	 * @param tagName
	 * @return
	 */
	public static OMElement createTag(OMFactory fac, String tagName) {
		return fac.createOMElement(tagName, AUDIT_MEDIATOR_NAMESPACE_OM);
	}

	/**
	 * Add a new tag (tagChild) to this element "parentElement"
	 * 
	 * @param parentElement
	 * @param tagChild
	 */
	public static void addChildTag(OMElement parentElement, String tagChild) {
		OMElement campoElement = parentElement.getOMFactory().createOMElement(
				tagChild, AUDIT_MEDIATOR_NAMESPACE_OM);
		parentElement.addChild(campoElement);
	}

	/**
	 * Add a new tag (tagChild) with value "value" to this element
	 * "parentElement"
	 * 
	 * @param parentElement
	 * @param tagChild
	 * @param value
	 */
	public static void addChildTagWithValue(OMElement parentElement,
			String tagChild, Value value) {
		OMElement campoElement = parentElement.getOMFactory().createOMElement(
				tagChild, AUDIT_MEDIATOR_NAMESPACE_OM);
		valueSerializer.serializeValue(value,
				AuditMediatorUtils.VALUE_ATT_NAME, campoElement);
		parentElement.addChild(campoElement);
	}

	/**
	 * Add a new attribute with value "value" to this element "parentElement"
	 * 
	 * @param parentElement
	 * @param attributeName
	 * @param value
	 */
	public static void addAttributeWithValue(OMElement parentElement,
			String attributeName, Value value) {
		valueSerializer.serializeValue(value, attributeName, parentElement);
	}

	/**
	 * Add a new tag (tagChild ) with an attribute with value "value" to this
	 * element "parentElement"
	 * 
	 * @param parentElement
	 * @param tagChild
	 * @param attributeName
	 * @param value
	 */
	public static void addChildTagWithAttributeValue(OMElement parentElement,
			String tagChild, String attributeName, Value value) {
		OMElement campoElement = parentElement.getOMFactory().createOMElement(
				tagChild, AUDIT_MEDIATOR_NAMESPACE_OM);
		valueSerializer.serializeValue(value, attributeName, campoElement);
		parentElement.addChild(campoElement);
	}

	/**
	 * Add a new tag (tagChild ) with an attribute with value "value" to this
	 * element "parentElement"
	 * 
	 * @param parentElement
	 * @param tagChild
	 * @param attributeName
	 * @param value
	 */
	public static void addChildTagWithAttributeValue(OMElement parentElement,
			String tagChild, String attributeName, String value) {
		OMElement campoElement = parentElement.getOMFactory().createOMElement(
				tagChild, AUDIT_MEDIATOR_NAMESPACE_OM);

		campoElement.addAttribute(parentElement.getOMFactory()
				.createOMAttribute(
						attributeName,
						parentElement.getOMFactory().createOMNamespace(
								XMLConfigConstants.NULL_NAMESPACE, ""), value));
		parentElement.addChild(campoElement);
	}

	/**
	 * This method return the activity list in which insert new object
	 * 
	 * @param activities
	 * @return
	 */
	public static List<JAXBElement<?>> findLastNoClosedActivitiesList(
			List<JAXBElement<?>> activities) {

		for (int i = activities.size() - 1; i >= 0; i--) {

			JAXBElement<?> ativity = (JAXBElement<?>) activities.get(i);

			if (ativity.getValue() instanceof SubProcessAuditType) {
				SubProcessAuditType temp = (SubProcessAuditType) ativity
						.getValue();
				if (temp.getStatus().equalsIgnoreCase(IN_PROGRESS_STATUS)) { // subprocess
																				// is
																				// open,
					return findLastNoClosedActivitiesList(temp.getActivities());
				}
			}

		}
		// NOTE: BusinessProcess Activities list is always open
		return activities;
	}

	/**
	 * Return the last activity in edition
	 * 
	 * @param activities
	 * @return
	 */
	public static ActivityTypeAudit findLastNoClosedActivity(
			List<JAXBElement<?>> activities) {

		for (int i = activities.size() - 1; i >= 0; i--) {

			ActivityTypeAudit activity = (ActivityTypeAudit) activities.get(i)
					.getValue();

			if (activity instanceof SubProcessAuditType) {
				SubProcessAuditType temp = (SubProcessAuditType) activity;
				if (temp.getStatus().equalsIgnoreCase(IN_PROGRESS_STATUS)
						&& temp.getActivities().size() > 0) { // if subprocess
																// is close,
																// children
																// should be
																// closed too
					return findLastNoClosedActivity(temp.getActivities());
				}
			}

			if (activity.getEndTime() == null)
				return activity;

		}
		return null;
	}

	/**
	 * Return the last activity in edition of type "className"
	 * 
	 * @param activities
	 * @param className
	 * @return
	 */
	public static ActivityTypeAudit findLastNoClosedActivityOfType(
			List<JAXBElement<?>> activities, String className) {

		for (int i = activities.size() - 1; i >= 0; i--) {

			ActivityTypeAudit activity = (ActivityTypeAudit) activities.get(i)
					.getValue();

			if (activity instanceof SubProcessAuditType) {

				if (activity.getStatus().equalsIgnoreCase(IN_PROGRESS_STATUS)) { // if
																					// subprocess
																					// is
																					// close,
																					// children
																					// should
																					// be
																					// closed
																					// too

					ActivityTypeAudit result = findLastNoClosedActivityOfType(
							((SubProcessAuditType) activity).getActivities(),
							className);
					if (result != null) {
						return result;
					} else if (activity.getClass().getName()
							.equalsIgnoreCase(className)) {
						return activity;
					}
					;
				}
			}

			if (activity.getClass().getName().equalsIgnoreCase(className)
					&& activity.getStatus()
							.equalsIgnoreCase(IN_PROGRESS_STATUS)) {
				return activity;
			}

		}
		return null;
	}

	/**
	 * Return the last activity of type "className"
	 * 
	 * @param activities
	 * @param className
	 * @return
	 */
	public static ActivityTypeAudit findLastActivityOfType(
			List<JAXBElement<?>> activities, String className) {

		for (int i = activities.size() - 1; i >= 0; i--) {

			ActivityTypeAudit activity = (ActivityTypeAudit) activities.get(i)
					.getValue();

			if (activity.getClass().getName().equalsIgnoreCase(className)) {
				return activity;
			}

			if (activity instanceof SubProcessAuditType) {
				SubProcessAuditType temp = (SubProcessAuditType) activity;
				ActivityTypeAudit result = findLastActivityOfType(
						temp.getActivities(), className);
				if (result != null)
					return result;
			}
		}
		return null;
	}

	/**
	 * Return the first activity of type
	 * 
	 * @param activities
	 * @param className
	 * @return
	 */
	public static ActivityTypeAudit findFirstActivityOfType(
			List<JAXBElement<?>> activities, String className) {

		for (int i = 0; i < activities.size(); i++) {

			ActivityTypeAudit activity = (ActivityTypeAudit) activities.get(i)
					.getValue();

			if (activity.getClass().getName().equalsIgnoreCase(className)) {
				return activity;
			}

			if (activity instanceof SubProcessAuditType) {
				SubProcessAuditType temp = (SubProcessAuditType) activity;

				ActivityTypeAudit result = findFirstActivityOfType(
						temp.getActivities(), className);
				if (result != null)
					return result;
			}

		}
		return null;
	}

	/**
	 * Return the activity of type "className" and id "id"
	 * 
	 * @param activities
	 * @param id
	 * @param className
	 * @return
	 */
	public static ActivityTypeAudit findActivityByIdAndClass(
			List<JAXBElement<?>> activities, String id, String className) {

		for (int i = 0; i < activities.size(); i++) {

			ActivityTypeAudit activity = (ActivityTypeAudit) activities.get(i)
					.getValue();

			if (activity.getId().equalsIgnoreCase(id)
					&& activity.getClass().getName()
							.equalsIgnoreCase(className)) {
				return activity;
			}

			if (activity instanceof SubProcessAuditType) {
				SubProcessAuditType temp = (SubProcessAuditType) activity;

				ActivityTypeAudit result = findActivityByIdAndClass(
						temp.getActivities(), id, className);
				if (result != null)
					return result;

			}

		}
		return null;

	}

}
