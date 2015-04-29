
package org.codigolibre.auditbpmn.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.codigolibre.auditbpmn.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ImplementationServiceAudit_QNAME = new QName("urn:org:codigolibre:businessprocessaudit:type:v1.0.0", "implementationServiceAudit");
    private final static QName _JmsServiceAudit_QNAME = new QName("urn:org:codigolibre:businessprocessaudit:type:v1.0.0", "jmsServiceAudit");
    private final static QName _SubProcessAudit_QNAME = new QName("urn:org:codigolibre:businessprocessaudit:type:v1.0.0", "subProcessAudit");
    private final static QName _TaskAudit_QNAME = new QName("urn:org:codigolibre:businessprocessaudit:type:v1.0.0", "taskAudit");
    private final static QName _WebServiceAudit_QNAME = new QName("urn:org:codigolibre:businessprocessaudit:type:v1.0.0", "webServiceAudit");
    private final static QName _Activity_QNAME = new QName("urn:org:codigolibre:businessprocessaudit:type:v1.0.0", "activity");
    private final static QName _ReceiveTaskAudit_QNAME = new QName("urn:org:codigolibre:businessprocessaudit:type:v1.0.0", "receiveTaskAudit");
    private final static QName _SendTaskAudit_QNAME = new QName("urn:org:codigolibre:businessprocessaudit:type:v1.0.0", "sendTaskAudit");
    private final static QName _JmsTopicServiceAudit_QNAME = new QName("urn:org:codigolibre:businessprocessaudit:type:v1.0.0", "jmsTopicServiceAudit");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.codigolibre.auditbpmn.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BusinessProcessAudit }
     * 
     */
    public BusinessProcessAudit createBusinessProcessAudit() {
        return new BusinessProcessAudit();
    }

    /**
     * Create an instance of {@link SubProcessAuditType }
     * 
     */
    public SubProcessAuditType createSubProcessAuditType() {
        return new SubProcessAuditType();
    }

    /**
     * Create an instance of {@link SendTaskAudit }
     * 
     */
    public SendTaskAudit createSendTaskAudit() {
        return new SendTaskAudit();
    }

    /**
     * Create an instance of {@link ReceiveTaskAuditType }
     * 
     */
    public ReceiveTaskAuditType createReceiveTaskAuditType() {
        return new ReceiveTaskAuditType();
    }

    /**
     * Create an instance of {@link JMSTopicServiceAuditType }
     * 
     */
    public JMSTopicServiceAuditType createJMSTopicServiceAuditType() {
        return new JMSTopicServiceAuditType();
    }

    /**
     * Create an instance of {@link JMSServiceAuditType }
     * 
     */
    public JMSServiceAuditType createJMSServiceAuditType() {
        return new JMSServiceAuditType();
    }

    /**
     * Create an instance of {@link WebServiceAuditType }
     * 
     */
    public WebServiceAuditType createWebServiceAuditType() {
        return new WebServiceAuditType();
    }

    /**
     * Create an instance of {@link ErrorType }
     * 
     */
    public ErrorType createErrorType() {
        return new ErrorType();
    }

    /**
     * Create an instance of {@link TaskAuditType }
     * 
     */
    public TaskAuditType createTaskAuditType() {
        return new TaskAuditType();
    }

    /**
     * Create an instance of {@link ParamType }
     * 
     */
    public ParamType createParamType() {
        return new ParamType();
    }

    /**
     * Create an instance of {@link ActivityTypeAudit }
     * 
     */
    public ActivityTypeAudit createActivityTypeAudit() {
        return new ActivityTypeAudit();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:org:codigolibre:businessprocessaudit:type:v1.0.0", name = "implementationServiceAudit")
    public JAXBElement<Object> createImplementationServiceAudit(Object value) {
        return new JAXBElement<Object>(_ImplementationServiceAudit_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JMSServiceAuditType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:org:codigolibre:businessprocessaudit:type:v1.0.0", name = "jmsServiceAudit", substitutionHeadNamespace = "urn:org:codigolibre:businessprocessaudit:type:v1.0.0", substitutionHeadName = "implementationServiceAudit")
    public JAXBElement<JMSServiceAuditType> createJmsServiceAudit(JMSServiceAuditType value) {
        return new JAXBElement<JMSServiceAuditType>(_JmsServiceAudit_QNAME, JMSServiceAuditType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubProcessAuditType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:org:codigolibre:businessprocessaudit:type:v1.0.0", name = "subProcessAudit", substitutionHeadNamespace = "urn:org:codigolibre:businessprocessaudit:type:v1.0.0", substitutionHeadName = "activity")
    public JAXBElement<SubProcessAuditType> createSubProcessAudit(SubProcessAuditType value) {
        return new JAXBElement<SubProcessAuditType>(_SubProcessAudit_QNAME, SubProcessAuditType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TaskAuditType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:org:codigolibre:businessprocessaudit:type:v1.0.0", name = "taskAudit", substitutionHeadNamespace = "urn:org:codigolibre:businessprocessaudit:type:v1.0.0", substitutionHeadName = "activity")
    public JAXBElement<TaskAuditType> createTaskAudit(TaskAuditType value) {
        return new JAXBElement<TaskAuditType>(_TaskAudit_QNAME, TaskAuditType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WebServiceAuditType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:org:codigolibre:businessprocessaudit:type:v1.0.0", name = "webServiceAudit", substitutionHeadNamespace = "urn:org:codigolibre:businessprocessaudit:type:v1.0.0", substitutionHeadName = "implementationServiceAudit")
    public JAXBElement<WebServiceAuditType> createWebServiceAudit(WebServiceAuditType value) {
        return new JAXBElement<WebServiceAuditType>(_WebServiceAudit_QNAME, WebServiceAuditType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:org:codigolibre:businessprocessaudit:type:v1.0.0", name = "activity")
    public JAXBElement<Object> createActivity(Object value) {
        return new JAXBElement<Object>(_Activity_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceiveTaskAuditType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:org:codigolibre:businessprocessaudit:type:v1.0.0", name = "receiveTaskAudit", substitutionHeadNamespace = "urn:org:codigolibre:businessprocessaudit:type:v1.0.0", substitutionHeadName = "activity")
    public JAXBElement<ReceiveTaskAuditType> createReceiveTaskAudit(ReceiveTaskAuditType value) {
        return new JAXBElement<ReceiveTaskAuditType>(_ReceiveTaskAudit_QNAME, ReceiveTaskAuditType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendTaskAudit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:org:codigolibre:businessprocessaudit:type:v1.0.0", name = "sendTaskAudit", substitutionHeadNamespace = "urn:org:codigolibre:businessprocessaudit:type:v1.0.0", substitutionHeadName = "activity")
    public JAXBElement<SendTaskAudit> createSendTaskAudit(SendTaskAudit value) {
        return new JAXBElement<SendTaskAudit>(_SendTaskAudit_QNAME, SendTaskAudit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JMSTopicServiceAuditType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:org:codigolibre:businessprocessaudit:type:v1.0.0", name = "jmsTopicServiceAudit", substitutionHeadNamespace = "urn:org:codigolibre:businessprocessaudit:type:v1.0.0", substitutionHeadName = "implementationServiceAudit")
    public JAXBElement<JMSTopicServiceAuditType> createJmsTopicServiceAudit(JMSTopicServiceAuditType value) {
        return new JAXBElement<JMSTopicServiceAuditType>(_JmsTopicServiceAudit_QNAME, JMSTopicServiceAuditType.class, null, value);
    }

}
