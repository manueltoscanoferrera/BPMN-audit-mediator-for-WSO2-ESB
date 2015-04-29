package org.codigolibre.auditbpmn.wso2mediator.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.MessageContext;
import org.apache.synapse.config.xml.ValueFactory;
import org.apache.synapse.config.xml.ValueSerializer;
import org.apache.synapse.mediators.Value;
import org.codigolibre.auditbpmn.jaxb.ParamType;
import org.codigolibre.auditbpmn.wso2mediator.AuditMediatorUtils;

/**
 * Params Subcommand
 * Process the inputParams and outputParams tags
 *
 */
public class ParamsSubCommand implements SubCommand {
	
	private static Log log = LogFactory.getLog(ParamsSubCommand.class);

	private String type; // input or output param
	private List<Param> params = new ArrayList<Param>();

	public void execute(Object base, MessageContext synCtx) {
		Param param = null;
		
		try {
			List<ParamType> baseList = (List<ParamType>) base;
			ParamType paramType;
			for (Iterator iterator = params.iterator(); iterator.hasNext();) {
				 param = (Param) iterator.next();
				paramType = new ParamType();
				paramType.setName(param.getName());
				paramType.setValue(param.getValue().evaluateValue(synCtx));
				baseList.add(paramType);
			}
		} catch (Exception e) {
			log.error("Error evaluating param: " + param + ". " +  e.getMessage(),e);

		}

	}

	public void parse(OMElement paramsTag) {

		if (log.isDebugEnabled() || log.isTraceEnabled()) {
			log.debug("Parse params");
		}

		ValueFactory valueFactory = new ValueFactory();

		this.type = paramsTag.getLocalName();

		for (Iterator iterator = paramsTag.getChildElements(); iterator
				.hasNext();) {
			OMElement param = (OMElement) iterator.next();

			if (AuditMediatorUtils.isTag(param,
					AuditMediatorUtils.PARAM_TAG_NAME)) {

				String name = param
						.getAttributeValue(AuditMediatorUtils.NAME_ATT_QNAME);

				Value value = valueFactory.createValue(
						AuditMediatorUtils.VALUE_ATT_NAME, param);

				if (StringUtils.isBlank(name) || value == null) {
					log.error("Error, attributes name and value required");
					break;
				}

				params.add(new Param(name, value));

			} else {
				log.error("Unable to create the Audit mediator. "
						+ "Unexpected element: "
						+ param
						+ " inside the Params TAG of Audit mediator configuration");

			}
		}
	}

	public OMElement serialize(OMFactory fac) {

		if (log.isDebugEnabled() || log.isTraceEnabled()) {
			log.debug("Serialize command");
		}

		/*
		 * org.apache.axiom.om.OMNamespace nullNS = fac.createOMNamespace(
		 * XMLConfigConstants.NULL_NAMESPACE, "");
		 */
		ValueSerializer valueSerializer = new ValueSerializer();

		OMElement root = AuditMediatorUtils.createTag(fac, this.type);

		for (Iterator iterator = params.iterator(); iterator.hasNext();) {
			Param param = (Param) iterator.next();

			OMElement paramTag = AuditMediatorUtils.createTag(fac,
					AuditMediatorUtils.PARAM_TAG_NAME);

			root.addChild(paramTag);

			paramTag.addAttribute(fac.createOMAttribute(
					AuditMediatorUtils.NAME_ATT_NAME,
					AuditMediatorUtils.nullNS, param.getName()));

			valueSerializer.serializeValue(param.getValue(),
					AuditMediatorUtils.VALUE_ATT_NAME, paramTag);
		}

		return root;
	}

	private class Param {

		public Param(String name, Value value) {
			super();
			this.name = name;
			this.value = value;
		}

		private String name;

		private Value value;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Value getValue() {
			return value;
		}

		public void setValue(Value value) {
			this.value = value;
		}

	}

}
