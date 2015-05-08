package org.codigolibre.auditbpmn.wso2mediator;

import java.util.Iterator;
import java.util.Properties;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.Mediator;
import org.apache.synapse.config.xml.AbstractMediatorFactory;
import org.codigolibre.auditbpmn.wso2mediator.command.BusinessProcessCommand;
import org.codigolibre.auditbpmn.wso2mediator.command.Command;
import org.codigolibre.auditbpmn.wso2mediator.command.ReceiveTaskCommand;
import org.codigolibre.auditbpmn.wso2mediator.command.SendTaskCommand;
import org.codigolibre.auditbpmn.wso2mediator.command.SerializeCommand;
import org.codigolibre.auditbpmn.wso2mediator.command.SubProcessCommand;
import org.codigolibre.auditbpmn.wso2mediator.command.TaskCommand;

public class AuditMediatorFactory extends AbstractMediatorFactory {

	private static Log log = LogFactory.getLog(AuditMediatorFactory.class);

	@Override
	public QName getTagQName() {
		return AuditMediatorUtils.AUDIT_QNAME;
	}

	@Override
	protected Mediator createSpecificMediator(OMElement element,
			Properties props) {

		if (log.isDebugEnabled() || log.isTraceEnabled()) {
			log.debug("Parsing Audit tag");
		}

		if (!AuditMediatorUtils.isTag(element,
				AuditMediatorUtils.AUDIT_TAG_NAME)) {
			handleException("Unable to create the Audit mediator. tag -> "
					+ element.getQName() + ":" + element.getLocalName()
					+ "Unexpected element as the Audit mediator configuration");
		}

		AuditMediator auditMediator = new AuditMediator();

		for (Iterator<OMElement> iterator = element.getChildElements(); iterator
				.hasNext();) {
			OMElement xmlCommand = iterator.next();

			if (log.isDebugEnabled() || log.isTraceEnabled()) {
				log.debug("Parsing tag " + xmlCommand);
			}

			Command comando = null;
			if (AuditMediatorUtils.isTag(xmlCommand,
					AuditMediatorUtils.BUSINESS_PROCESS_TAG_NAME)) {
				comando = new BusinessProcessCommand();
			} else if (AuditMediatorUtils.isTag(xmlCommand,
					AuditMediatorUtils.TASK_TAG_NAME)) {
				comando = new TaskCommand();
			} else if (AuditMediatorUtils.isTag(xmlCommand,
					AuditMediatorUtils.RECEIVE_TASK_TAG_NAME)) {
				comando = new ReceiveTaskCommand();
			} else if (AuditMediatorUtils.isTag(xmlCommand,
					AuditMediatorUtils.SEND_TASK_TAG_NAME)) {
				comando = new SendTaskCommand();
			} else if (AuditMediatorUtils.isTag(xmlCommand,
					AuditMediatorUtils.SERIALIZE_TAG_NAME)) {
				comando = new SerializeCommand();
			} else if (AuditMediatorUtils.isTag(xmlCommand,
					AuditMediatorUtils.SUBPROCESS_TAG_NAME)) {
				comando = new SubProcessCommand();
			} else {
				handleException("Unable to create the Audit mediator. "
						+ "Unexpected element: " + xmlCommand
						+ " inside the Audit mediator configuration");

			}
			auditMediator.getAuditCommands().add(comando);
			comando.parse(xmlCommand);
			

		}

		return auditMediator;
	}

}
