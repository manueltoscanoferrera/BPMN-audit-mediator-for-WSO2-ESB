
package org.codigolibre.auditbpmn.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subProcessAuditType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subProcessAuditType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:org:codigolibre:businessprocessaudit:type:v1.0.0}ActivityTypeAudit">
 *       &lt;sequence>
 *         &lt;element name="activities" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{urn:org:codigolibre:businessprocessaudit:type:v1.0.0}activity" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subProcessAuditType", propOrder = {
    "activities"
})
public class SubProcessAuditType
    extends ActivityTypeAudit
{

    @XmlElementWrapper
    @XmlElementRef(name = "activity", namespace = "urn:org:codigolibre:businessprocessaudit:type:v1.0.0", type = JAXBElement.class)
    protected List<JAXBElement<?>> activities;

    public List<JAXBElement<?>> getActivities() {
        if (activities == null) {
            activities = new ArrayList<JAXBElement<?>>();
        }
        return activities;
    }

    public void setActivities(List<JAXBElement<?>> activities) {
        this.activities = activities;
    }

}
