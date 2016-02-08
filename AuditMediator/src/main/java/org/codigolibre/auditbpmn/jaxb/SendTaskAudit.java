
package org.codigolibre.auditbpmn.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{urn:org:codigolibre:businessprocessaudit:type:v1.0.0}ActivityTypeAudit">
 *       &lt;sequence>
 *         &lt;element ref="{urn:org:codigolibre:businessprocessaudit:type:v1.0.0}implementationServiceAudit"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "implementationServiceAudit"
})
public class SendTaskAudit
    extends ActivityTypeAudit
{

    @XmlElementRef(name = "implementationServiceAudit", namespace = "urn:org:codigolibre:businessprocessaudit:type:v1.0.0", type = JAXBElement.class)
    protected JAXBElement<?> implementationServiceAudit;

    /**
     * Gets the value of the implementationServiceAudit property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     {@link JAXBElement }{@code <}{@link WebServiceAuditType }{@code >}
     *     {@link JAXBElement }{@code <}{@link JMSServiceAuditType }{@code >}
     *     {@link JAXBElement }{@code <}{@link JMSTopicServiceAuditType }{@code >}
     *     
     */
    public JAXBElement<?> getImplementationServiceAudit() {
        return implementationServiceAudit;
    }

    /**
     * Sets the value of the implementationServiceAudit property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     {@link JAXBElement }{@code <}{@link WebServiceAuditType }{@code >}
     *     {@link JAXBElement }{@code <}{@link JMSServiceAuditType }{@code >}
     *     {@link JAXBElement }{@code <}{@link JMSTopicServiceAuditType }{@code >}
     *     
     */
    public void setImplementationServiceAudit(JAXBElement<?> value) {
        this.implementationServiceAudit = value;
    }

}
