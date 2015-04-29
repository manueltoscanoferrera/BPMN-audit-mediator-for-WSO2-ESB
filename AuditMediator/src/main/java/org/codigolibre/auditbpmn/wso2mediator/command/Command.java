package org.codigolibre.auditbpmn.wso2mediator.command;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.synapse.MessageContext;
import org.codigolibre.auditbpmn.jaxb.BusinessProcessAudit;

public interface Command {


	/**
	 * Update the Business Audit object with this command
	 * @param businessProcessAudit
	 * @param synCtx
	 */
	public void execute(BusinessProcessAudit businessProcessAudit,
			MessageContext synCtx);

	/**
	 * Parse the commands tags defined in the proxy or sequence 
	 * comando
	 * 
	 * @param element
	 */
	public void parse(OMElement element);

	/**
	 * serializes the command to their corresponding tags
	 * 
	 * @param fac
	 * @return
	 */
	public OMElement serialize(OMFactory fac);

}
