
package org.codigolibre.auditbpmn.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for JMSServiceAuditType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="JMSServiceAuditType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="endPoint" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="destination" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destinationType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="defaultReplyDestination" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="defaultReplyDestinationType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idMsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idCorrelation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="msgRequest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="msgResponse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ipClient" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hostClient" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idClient" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ipServer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hostServer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JMSServiceAuditType", propOrder = {
    "endPoint",
    "destination",
    "destinationType",
    "defaultReplyDestination",
    "defaultReplyDestinationType",
    "contentType",
    "idMsg",
    "idCorrelation",
    "msgRequest",
    "msgResponse",
    "ipClient",
    "hostClient",
    "idClient",
    "ipServer",
    "hostServer"
})
public class JMSServiceAuditType {

    @XmlElement(required = true, nillable = true)
    protected String endPoint;
    protected String destination;
    protected String destinationType;
    protected String defaultReplyDestination;
    protected String defaultReplyDestinationType;
    protected String contentType;
    protected String idMsg;
    protected String idCorrelation;
    protected String msgRequest;
    protected String msgResponse;
    protected String ipClient;
    protected String hostClient;
    protected String idClient;
    protected String ipServer;
    protected String hostServer;

    /**
     * Gets the value of the endPoint property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndPoint() {
        return endPoint;
    }

    /**
     * Sets the value of the endPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndPoint(String value) {
        this.endPoint = value;
    }

    /**
     * Gets the value of the destination property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the value of the destination property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestination(String value) {
        this.destination = value;
    }

    /**
     * Gets the value of the destinationType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationType() {
        return destinationType;
    }

    /**
     * Sets the value of the destinationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationType(String value) {
        this.destinationType = value;
    }

    /**
     * Gets the value of the defaultReplyDestination property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultReplyDestination() {
        return defaultReplyDestination;
    }

    /**
     * Sets the value of the defaultReplyDestination property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultReplyDestination(String value) {
        this.defaultReplyDestination = value;
    }

    /**
     * Gets the value of the defaultReplyDestinationType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultReplyDestinationType() {
        return defaultReplyDestinationType;
    }

    /**
     * Sets the value of the defaultReplyDestinationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultReplyDestinationType(String value) {
        this.defaultReplyDestinationType = value;
    }

    /**
     * Gets the value of the contentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Sets the value of the contentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentType(String value) {
        this.contentType = value;
    }

    /**
     * Gets the value of the idMsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdMsg() {
        return idMsg;
    }

    /**
     * Sets the value of the idMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdMsg(String value) {
        this.idMsg = value;
    }

    /**
     * Gets the value of the idCorrelation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdCorrelation() {
        return idCorrelation;
    }

    /**
     * Sets the value of the idCorrelation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCorrelation(String value) {
        this.idCorrelation = value;
    }

    /**
     * Gets the value of the msgRequest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgRequest() {
        return msgRequest;
    }

    /**
     * Sets the value of the msgRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgRequest(String value) {
        this.msgRequest = value;
    }

    /**
     * Gets the value of the msgResponse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgResponse() {
        return msgResponse;
    }

    /**
     * Sets the value of the msgResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgResponse(String value) {
        this.msgResponse = value;
    }

    /**
     * Gets the value of the ipClient property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpClient() {
        return ipClient;
    }

    /**
     * Sets the value of the ipClient property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpClient(String value) {
        this.ipClient = value;
    }

    /**
     * Gets the value of the hostClient property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostClient() {
        return hostClient;
    }

    /**
     * Sets the value of the hostClient property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostClient(String value) {
        this.hostClient = value;
    }

    /**
     * Gets the value of the idClient property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdClient() {
        return idClient;
    }

    /**
     * Sets the value of the idClient property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdClient(String value) {
        this.idClient = value;
    }

    /**
     * Gets the value of the ipServer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpServer() {
        return ipServer;
    }

    /**
     * Sets the value of the ipServer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpServer(String value) {
        this.ipServer = value;
    }

    /**
     * Gets the value of the hostServer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostServer() {
        return hostServer;
    }

    /**
     * Sets the value of the hostServer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostServer(String value) {
        this.hostServer = value;
    }

}
