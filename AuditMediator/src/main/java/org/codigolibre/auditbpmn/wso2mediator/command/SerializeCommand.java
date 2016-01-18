package org.codigolibre.auditbpmn.wso2mediator.command;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamWriter;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMAttribute;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.store.MessageStoreMediator;
import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;
import org.codigolibre.auditbpmn.jaxb.BusinessProcessAudit;
import org.codigolibre.auditbpmn.wso2mediator.AuditMediatorUtils;

/**
 * Serialize Audit Command
 *
 */
public class SerializeCommand implements Command {
	private static Log log = LogFactory.getLog(SerializeCommand.class);

	// JAXBContext is thread-safe
	private static final JAXBContext jaxbContext = initContext();

	private String target;
	private String storeName;
	private String mediaType;
	private String loggerName;
	private Log logger; //logger to out the serializa
	
	// we re-use the WSO2 MessageStoreMediator to perform the store in JMS
	private MessageStoreMediator storeMediator;

	
	private static JAXBContext initContext() {
		try {
			return JAXBContext.newInstance(BusinessProcessAudit.class);
		} catch (JAXBException e) {
			log.error(e);
			return null;
		}
	}

	@Override
	public void execute(BusinessProcessAudit businessProcessAudit,
			MessageContext synCtx) {

		if (log.isDebugEnabled() || log.isTraceEnabled()) {
			log.debug("execute Serialize comando");
		}
		try {

			String stringBusiness = null;
			
			if (StringUtils.isNotBlank(mediaType) &&  mediaType.equalsIgnoreCase(AuditMediatorUtils.JSON_TYPE)){
					
				// JAXB to JSON String              
	              Configuration config = new Configuration();
	              config.setIgnoreNamespaces(true);
 
	              MappedNamespaceConvention con = new MappedNamespaceConvention(config);
	      		  StringWriter writer = new StringWriter();
	              XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(con, writer);
	       
	              Marshaller marshaller = jaxbContext.createMarshaller();
	              marshaller.marshal(businessProcessAudit, xmlStreamWriter);
	              
	              stringBusiness = writer.toString();
		              
			}else {
				// JAXB to XML String
				StringWriter stringWriter = new StringWriter();
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
				jaxbMarshaller.marshal(businessProcessAudit, stringWriter);
				stringBusiness = stringWriter.toString();
			}

			if (log.isDebugEnabled() || log.isTraceEnabled()) {
				log.debug("Serialize result  " + stringBusiness);
			}

			if (target != null) {
					synCtx.setProperty(target, stringBusiness.toString());
			}
			// target and store allowed both simultaneously
			if (storeName != null) {
				try {

					MessageContext ms = synCtx.getEnvironment()
							.createMessageContext();
					ms.setConfiguration(synCtx.getConfiguration()); // necessary to find the store inside StoreMediator. 

					// no other way if we use StoreMediator
					
					ms.setEnvelope(OMAbstractFactory.getSOAP11Factory()
								.getDefaultEnvelope());
					ms.getEnvelope()
								.getBody().setText(stringBusiness);

				
					if (storeMediator != null){
						storeMediator.mediate(ms);
					}
					else
					{
						log.error("ERROR storeMediator is null, no storing ");
					}

				} catch (Exception e) {
					log.error("Error storing inside the store: "
							+ storeName + " " + e.getMessage(), e);

				}
			}
			if (logger != null) {
	
			    logger.info(stringBusiness.toString());
			}


		} catch (Exception e) {
			log.error(
					"Error in Serialize Command "
							+ e.getMessage(), e);

		}

	}

	@Override
	public void parse(OMElement element) {

		if (log.isDebugEnabled() || log.isTraceEnabled()) {
			log.debug("Parse Serialize comando");
		}

		OMAttribute destinoAtributo = element
				.getAttribute(AuditMediatorUtils.TARGET_ATT_QNAME);

		OMAttribute storeAtributo = element
				.getAttribute(AuditMediatorUtils.STORE_ATT_QNAME);
		
		OMAttribute mediaTypeAttribute = element
				.getAttribute(AuditMediatorUtils.MEDIA_TYPE_ATT_QNAME);
		
		OMAttribute loggerAttribute = element
				.getAttribute(AuditMediatorUtils.LOGGER_NAME_QNAME);

		if (destinoAtributo != null) {
			target = destinoAtributo.getAttributeValue();

			if (log.isDebugEnabled() || log.isTraceEnabled()) {
				log.debug(AuditMediatorUtils.TARGET_ATT_QNAME + " " + target);
			}

		}

		if (storeAtributo != null) {
			storeName = storeAtributo.getAttributeValue();
			storeMediator = new MessageStoreMediator();

			storeMediator.setMessageStoreName(storeName);
			if (log.isDebugEnabled() || log.isTraceEnabled()) {
				log.debug(AuditMediatorUtils.STORE_ATT_QNAME + " " + storeName);
			}

		}
		
		if (mediaTypeAttribute != null) {
			mediaType = mediaTypeAttribute.getAttributeValue();

			if (log.isDebugEnabled() || log.isTraceEnabled()) {
				log.debug(AuditMediatorUtils.MEDIA_TYPE_ATT_QNAME + " " + mediaType);
			}

		}

		if (loggerAttribute != null) {
			loggerName = loggerAttribute.getAttributeValue();
			logger = LogFactory.getLog(loggerName);

			if (log.isDebugEnabled() || log.isTraceEnabled()) {
				log.debug(AuditMediatorUtils.LOGGER_NAME_QNAME + " " + loggerAttribute.getAttributeValue());
			}

		}
		

	}

	@Override
	public OMElement serialize(OMFactory fac) {

		if (log.isDebugEnabled() || log.isTraceEnabled()) {
			log.debug("Serialize Serialize comando");
		}

		/*
		 * org.apache.axiom.om.OMNamespace nullNS = fac.createOMNamespace(
		 * XMLConfigConstants.NULL_NAMESPACE, "");
		 */
		OMElement root = AuditMediatorUtils.createTag(fac,
				AuditMediatorUtils.SERIALIZE_TAG_NAME);

		if (target != null) {

			root.addAttribute(fac.createOMAttribute(
					AuditMediatorUtils.TARGET_ATT_NAME,
					AuditMediatorUtils.nullNS, target));
		}

		if (storeName != null) {
			root.addAttribute(fac.createOMAttribute(
					AuditMediatorUtils.STORE_ATT_NAME,
					AuditMediatorUtils.nullNS, storeName));
		}
		
		if (mediaType != null) {
			root.addAttribute(fac.createOMAttribute(
					AuditMediatorUtils.MEDIA_TYPE_ATT_NAME,
					AuditMediatorUtils.nullNS, mediaType));
		}
		
		if (loggerName != null) {
			root.addAttribute(fac.createOMAttribute(
					AuditMediatorUtils.LOGGER_NAME_ATT_NAME,
					AuditMediatorUtils.nullNS, loggerName));
		}

		return root;
	}

	
}
