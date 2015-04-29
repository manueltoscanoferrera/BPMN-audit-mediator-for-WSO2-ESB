package org.codigolibre.auditbpmn.wso2mediator;

import java.util.Properties;

import org.apache.synapse.Mediator;
import org.apache.synapse.MessageContext;
import org.apache.synapse.config.SynapseConfigUtils;
import org.apache.synapse.mediators.TestUtils;
import org.junit.Test;

public class SerializeTestCase extends AbstractAuditMediatorTestCase {

	private String serializeXMLProxyVariable = "<audit xmlns=\"http://ws.apache.org/ns/synapse\">\n"
			+ "	<serialize target=\"salidavariable\"/>\n" + "</audit>";

	private String serializeXMLProxyVariableCOMPARE = "<businessProcessAudit xmlns=\"urn:org:codigolibre:businessprocessaudit:type:v1.0.0\">\n" + 
			"	<id xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<name xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<startTime xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<endTime xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<status xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"</businessProcessAudit>\n"
			;
	
	
	private String serializeJSON = "<audit xmlns=\"http://ws.apache.org/ns/synapse\">\n"
			+ "	<serialize target=\"salidavariable\" media-type=\"json\"/>\n" + "</audit>";
	
	private String serializeJSONCOMPARE = "{\"businessProcessAudit\":{\"id\":{\"@nil\":\"true\"},\"name\":{\"@nil\":\"true\"},\"startTime\":{\"@nil\":\"true\"},\"endTime\":{\"@nil\":\"true\"},\"status\":{\"@nil\":\"true\"}}}";
	


	@Test
	public void testXMLSerializeInsideVariable() throws Exception {
		genericTest(serializeXMLProxyVariable, serializeXMLProxyVariableCOMPARE);
	}
	
	
	@Test
	public void testJSON() throws Exception {
		beforeClass();
		String proxyData = serializeJSON;
		String serializaOKCompare =serializeJSONCOMPARE;
		
		Mediator auditMediator = auditMediatorFactory.createMediator(
				SynapseConfigUtils.stringToOM(serializeJSON),
				new Properties());

		
		
		MessageContext synCtx = TestUtils
				.createLightweightSynapseMessageContext("<empty/>");

		// perform mediation
		assertTrue(auditMediator.mediate(synCtx));

		// check if audit xml contents variable serialization and validate xml
		// transaccion
		String variable = null;
		if (proxyData.indexOf("target=\"") > 0) {
			variable = proxyData.substring(
					proxyData.indexOf("target=\"") + 8,
					proxyData
							.indexOf("\"", proxyData
									.indexOf("target=\"") + 8));

		} else
			return;

		String serializationXML = (String) synCtx.getProperty(variable);

		assertEquals("\n Valid "
				+ serializaOKCompare + "\n Generated "
				+ serializationXML,serializaOKCompare ,serializationXML);

	}
	
	
	

}
