package org.codigolibre.auditbpmn.wso2mediator;

import org.junit.Test;

public class BusinessProcessAuditTestCase extends AbstractAuditMediatorTestCase {

	private static String businessProxy = " <audit>\n" + 
			"     <businessProcess id=\"52\">\n" + 
			"       <name value=\"Consulta Demanda Por ID Abreviada\"/>\n" + 
			"       <description value=\"Consulta Demanda Por ID Abreviada\"/>\n" + 
			"       <correlationID value=\"cccc\" />\n" + 
			"		<startTime  value=\"2010-02-20T13:35:08.901+01:00\"/>\n" + 
			"		<endTime  value=\"2015-02-20T13:35:08.901+01:00\"/>\n" + 
			"		<status value=\"1\" /> \n" + 
			"		<errorCode value=\"aaaa\" />\n" + 
			"		<errorMessage value=\"bbbbb\"/>\n" + 
			"		<errorDetail value=\"cccc\" />\n" + 
			"		<contextParams>\n" + 
			"			<param name=\"inputP1\" value=\"valueinputP\"/>\n" + 
			"			<param name=\"inputP2\" value=\"{$ctx:custom}\"/>\n" + 
			"		</contextParams>\n" + 
			"     </businessProcess>"
			+ "	<serialize target=\"as\"/> \n" + "</audit>";

	/*
	 * Transaction xml OK
	 */
	private static String businessProxyResultToCOMPARE = "<businessProcessAudit xmlns=\"urn:org:codigolibre:businessprocessaudit:type:v1.0.0\">\n" + 
			"	<id>52</id>\n" + 
			"	<name>Consulta Demanda Por ID Abreviada</name>\n" + 
			"	<description>Consulta Demanda Por ID Abreviada</description>\n" + 
			"	<correlationID>cccc</correlationID>\n" + 
			"	<startTime>2010-02-20T13:35:08.901+01:00</startTime>\n" + 
			"	<endTime>2015-02-20T13:35:08.901+01:00</endTime>\n" + 
			"	<status>1</status>\n" + 
			"	<error>\n" + 
			"		<errorCode>aaaa</errorCode>\n" + 
			"		<errorMessage>bbbbb</errorMessage>\n" + 
			"		<errorDetail>cccc</errorDetail>\n" + 
			"	</error>\n" + 
			"	<contextParams>\n" + 
			"		<param name=\"inputP1\">valueinputP</param>\n" + 
			"		<param name=\"inputP2\">custom variable value</param>\n" + 
			"	</contextParams>\n" + 
			"</businessProcessAudit>";

	

	@Test
	public void testBusinessProxyyTags() throws Exception {
		genericTest(businessProxy, businessProxyResultToCOMPARE);
	}



}
