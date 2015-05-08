package org.codigolibre.auditbpmn.wso2mediator;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.axiom.om.OMElement;
//import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.synapse.ManagedLifecycle;
import org.apache.synapse.MessageContext;
import org.apache.synapse.SynapseLog;
import org.apache.synapse.core.SynapseEnvironment;
import org.apache.synapse.mediators.AbstractMediator;
import org.codigolibre.auditbpmn.jaxb.BusinessProcessAudit;
import org.codigolibre.auditbpmn.jaxb.ObjectFactory;
import org.codigolibre.auditbpmn.wso2mediator.command.Command;

public class AuditMediator extends AbstractMediator implements ManagedLifecycle {

	private List<Command> auditCommands = new ArrayList<Command>();

	private ObjectFactory objF = new ObjectFactory();

	@Override
	public boolean mediate(MessageContext synCtx) {

		SynapseLog synLog = getLog(synCtx);

		if (synLog.isTraceOrDebugEnabled()) {
			synLog.traceOrDebug("Start : AuditMediator");
		}

		// check if we are disabled
		String isDisabled = (String) synCtx
				.getProperty(AuditMediatorUtils.AUDIT_MEDIATOR_DISABLED_CONTEXT_PROPERTY);

		if (!StringUtils.isBlank(isDisabled)
				&& "TRUE".equalsIgnoreCase(isDisabled)) {

			if (synLog.isTraceOrDebugEnabled()) {
				synLog.traceOrDebug("AuditMediator desactivate by property "
						+ AuditMediatorUtils.AUDIT_MEDIATOR_DISABLED_CONTEXT_PROPERTY
						+ " with value " + isDisabled);
			}

			return true;
		}

		// Jaxb Audit object is store inside message context, inside the
		// variable AuditMediatorUtils.AUDIT_MEDIATOR_CONTEXT_PROPERTY

		BusinessProcessAudit businessProcessAudit = (BusinessProcessAudit) synCtx
				.getProperty(AuditMediatorUtils.AUDIT_MEDIATOR_CONTEXT_PROPERTY);

		if (businessProcessAudit == null) {
			businessProcessAudit = objF.createBusinessProcessAudit();
			synCtx.setProperty(
					AuditMediatorUtils.AUDIT_MEDIATOR_CONTEXT_PROPERTY,
					businessProcessAudit);
		}

		// executing the audit commands
		for (Iterator<Command> iterator = getAuditCommands().iterator(); iterator
				.hasNext();) {
			iterator.next().execute(businessProcessAudit, synCtx);
		}

		if (synLog.isTraceOrDebugEnabled()) {// print the current Business Process Audit
			JAXBContext jaxbContext;
			try {
				jaxbContext = JAXBContext
						.newInstance(BusinessProcessAudit.class);

				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				StringWriter auditXMLWriter = new StringWriter();
				jaxbMarshaller.marshal(businessProcessAudit, auditXMLWriter);

				OMElement serializeOM = new AuditMediatorSerializer()
						.serializeMediator(null, this);

				synLog.traceOrDebug("**** Audit XML Trace: \n    Audit tag processed -> "
						+ serializeOM.toString()
						+ "\n    BussinessAudit result -> "
						+ auditXMLWriter.toString());

				// JAXB to JSON
				/*
				 * Configuration config = new Configuration();
				 * config.setIgnoreNamespaces(true);
				 * 
				 * MappedNamespaceConvention con = new
				 * MappedNamespaceConvention(config); StringWriter writer = new
				 * StringWriter(); XMLStreamWriter xmlStreamWriter = new
				 * MappedXMLStreamWriter(con, writer);
				 * 
				 * Marshaller marshaller = jaxbContext.createMarshaller();
				 * marshaller.marshal(businessProcessAudit, xmlStreamWriter);
				 * 
				 * 
				 * synLog.traceTrace(" json " + writer.toString());
				 */

			} catch (JAXBException e) {
				synLog.auditError(e.getMessage()); // wso2... i want a log with
													// Throwable param ;(
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(SynapseEnvironment arg0) {
	}

	public List<Command> getAuditCommands() {
		return auditCommands;
	}

	public void setAuditCommands(List<Command> auditCommands) {
		this.auditCommands = auditCommands;
	}

}
