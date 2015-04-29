package org.codigolibre.auditbpmn.wso2mediator.command;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.synapse.MessageContext;

/**
 * Subcommands are other types of commands in which it is defined the JAXB object to be update
 * see ParamsSubCommand or WebServiceImplementationSubCommand
 *
 */
public interface SubCommand {

	/**
	 * Update the JAXB Object "value" with this subcommand
	 * @param businessProcessAudit
	 * @param synCtx
	 */
	public void execute(Object value, MessageContext synCtx);

	/**
	 * Parse the sub command tags defined in the proxy or sequence 
	 * 
	 * @param element
	 */
	public void parse(OMElement element);

	/**
	 * serializes the subcommand to their corresponding tags
	 * 
	 * @param fac
	 * @return
	 */
	public OMElement serialize(OMFactory fac);

}
