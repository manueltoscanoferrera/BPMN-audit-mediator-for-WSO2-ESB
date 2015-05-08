package org.codigolibre.auditbpmn.wso2mediator;

import java.util.Iterator;
import java.util.List;

import org.apache.axiom.om.OMElement;
import org.apache.synapse.Mediator;
import org.apache.synapse.config.xml.AbstractMediatorSerializer;
import org.codigolibre.auditbpmn.wso2mediator.command.Command;

public class AuditMediatorSerializer extends AbstractMediatorSerializer {

	@Override
	public String getMediatorClassName() {
		return AuditMediator.class.getName();
	}

	@Override
	protected OMElement serializeSpecificMediator(Mediator mediator) {

		if (log.isDebugEnabled() || log.isTraceEnabled()) {
			log.debug("Audit Mediator Serialize Tag");
		}

		if (!(mediator instanceof AuditMediator)) {
			handleException("Unsupported mediator passed in for serialization: "
					+ mediator.getType());
		}

		AuditMediator auditMediator = (AuditMediator) mediator;

		List<Command> commands = auditMediator.getAuditCommands();
		
		// more comfortable working with synapse namespace than "audit meditor"
		// namespace
		AuditMediatorUtils.setAuditMediatorBaseNS(synNS);
		
		// To use a custom namespace, uncomment
		// OMNamespace auditNS =
		// fac.createOMNamespace(AuditMediatorUtils.AUDIT_MEDIATOR_NAMESPACE ,
		// "audit");
		// AuditMediatorUtils.setAuditMediatorBaseNS(auditNS);

		OMElement auditElement = AuditMediatorUtils.createTag(fac,
				AuditMediatorUtils.AUDIT_TAG_NAME);

		for (Iterator<Command> iterator = commands.iterator(); iterator
				.hasNext();) {
			Command command = iterator.next();

			if (log.isDebugEnabled() || log.isTraceEnabled()) {
				log.debug("Serialize command " + command);
			}
			auditElement.addChild(command.serialize(fac));

		}

		return auditElement;
	}

}
