package org.codigolibre.auditbpmn.wso2mediator.command;

import javax.xml.bind.JAXBElement;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.synapse.MessageContext;
import org.codigolibre.auditbpmn.jaxb.ActivityTypeAudit;
import org.codigolibre.auditbpmn.jaxb.BusinessProcessAudit;
import org.codigolibre.auditbpmn.jaxb.SubProcessAuditType;
import org.codigolibre.auditbpmn.wso2mediator.AuditMediatorUtils;

/**
 * SubProcess Audit Command
 */
public class SubProcessCommand extends ActivityBaseCommand {

	public SubProcessCommand() {
		super(AuditMediatorUtils.SUBPROCESS_TAG_NAME, SubProcessAuditType.class);

	}

	@Override
	protected JAXBElement<?> createNewActivity() {

		return objF.createSubProcessAudit(new SubProcessAuditType());
	}

	@Override
	public void subClassExecute(BusinessProcessAudit businessProcessAudit,
			ActivityTypeAudit activity, MessageContext synCtx) {
	}

	public void parseChildElementSubClass(OMElement element) {

	}

	@Override
	public void subClassParseRoot(OMElement activityElement) {
	}

	@Override
	public void subClassParseChild(OMElement activityElement) {
		log.error("Unable to create the Audit mediator. "
				+ "Unexpected element: " + activityElement + " inside the "
				+ proxyTagName + " of Audit mediator configuration");
	}

	@Override
	public void subClassSerialize(OMFactory fac, OMElement root) {

	}

	@Override
	public void subClassInitializeActivity(ActivityTypeAudit activity) {

	}

}