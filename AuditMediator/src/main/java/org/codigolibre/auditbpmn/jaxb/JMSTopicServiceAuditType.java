
package org.codigolibre.auditbpmn.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for JMSTopicServiceAuditType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="JMSTopicServiceAuditType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="endPoint" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="queue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="msg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "JMSTopicServiceAuditType", propOrder = {
    "endPoint",
    "queue",
    "msg",
    "ipClient",
    "hostClient",
    "idClient",
    "ipServer",
    "hostServer"
})
public class JMSTopicServiceAuditType {

    @XmlElement(required = true, nillable = true)
    protected String endPoint;
    @XmlElement(required = true, nillable = true)
    protected String queue;
    protected String msg;
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
     * Gets the value of the queue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueue() {
        return queue;
    }

    /**
     * Sets the value of the queue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueue(String value) {
        this.queue = value;
    }

    /**
     * Gets the value of the msg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Sets the value of the msg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsg(String value) {
        this.msg = value;
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
