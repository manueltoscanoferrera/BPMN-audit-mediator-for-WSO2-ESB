
package org.codigolibre.auditbpmn.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WebServiceAuditType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WebServiceAuditType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="endPoint" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idMsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="msgRequest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="msgResponse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="replyTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "WebServiceAuditType", propOrder = {
    "endPoint",
    "operation",
    "type",
    "idMsg",
    "msgRequest",
    "msgResponse",
    "replyTO",
    "ipClient",
    "hostClient",
    "idClient",
    "ipServer",
    "hostServer"
})
public class WebServiceAuditType {

    @XmlElement(required = true, nillable = true)
    protected String endPoint;
    @XmlElement(required = true, nillable = true)
    protected String operation;
    @XmlElement(required = true, nillable = true)
    protected String type;
    protected String idMsg;
    protected String msgRequest;
    protected String msgResponse;
    protected String replyTO;
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
     * Gets the value of the operation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Sets the value of the operation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperation(String value) {
        this.operation = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
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
     * Gets the value of the replyTO property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReplyTO() {
        return replyTO;
    }

    /**
     * Sets the value of the replyTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReplyTO(String value) {
        this.replyTO = value;
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
