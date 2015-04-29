package org.codigolibre.auditbpmn.wso2mediator;

import org.codigolibre.auditbpmn.wso2mediator.AuditMediatorFactory;
import org.junit.Test;

public class TaskTestCase extends AbstractAuditMediatorTestCase {

	

	private static String taskProxyTag1 = "<audit xmlns=\"http://ws.apache.org/ns/synapse\">\n" + 
			"	<task id=\"id\" new=\"true\" closeWithStatus=\"ERROR\">\n" + 
			"		<name value=\"task validation\"/>\n" + 
			"		<description value=\"Description task validation\"/>\n" + 
			"		<startTime  value=\"2010-02-20T13:35:08.901+01:00\"/>\n" + 
			"		<endTime  value=\"2015-02-20T13:35:08.901+01:00\"/>\n" + 
			"		<status value=\"1\" /> \n" + 
			"		<errorCode value=\"aaaa\" />\n" + 
			"		<errorMessage value=\"bbbbb\"/>\n" + 
			"		<errorDetail value=\"cccc\" />\n" + 
			"		<inputParams>\n" + 
			"			<param name=\"inputP1\" value=\"valueinputP\"/>\n" + 
			"			<param name=\"inputP2\" value=\"{$ctx:custom}\"/>\n" + 
			"		</inputParams>\n" + 
			"		<outputParams>\n" + 
			"			<param name=\"outputP1\" value=\"valueoutputP1\"/>\n" + 
			"		</outputParams>\n" + 
			"	</task>\n" + 
			"	<serialize target=\"as\"/> \n" + 
			"</audit>";

	/*
	 * Transaction xml OK
	 */
	private static String taskProxyTag1BussinesResultCOMPARE = "<businessProcessAudit xmlns=\"urn:org:codigolibre:businessprocessaudit:type:v1.0.0\">\n" + 
			"	<id xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<name xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<startTime xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<endTime xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<status xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<activities>\n" + 
			"		<taskAudit>\n" + 
			"			<id>id</id>\n" + 
			"			<name>task validation</name>\n" + 
			"			<description>Description task validation</description>\n" + 
			"			<startTime>2010-02-20T13:35:08.901+01:00</startTime>\n" + 
			"			<endTime>2015-02-20T13:35:08.901+01:00</endTime>\n" + 
			"			<status>ERROR</status>\n" + 
			"			<error>\n" + 
			"				<errorCode>aaaa</errorCode>\n" + 
			"				<errorMessage>bbbbb</errorMessage>\n" + 
			"				<errorDetail>cccc</errorDetail>\n" + 
			"			</error>\n" + 
			"			<inputParams>\n" + 
			"				<param name=\"inputP1\">valueinputP</param>\n" + 
			"				<param name=\"inputP2\">custom variable value</param>\n" + 
			"			</inputParams>\n" + 
			"			<outputParams>\n" + 
			"				<param name=\"outputP1\">valueoutputP1</param>\n" + 
			"			</outputParams>\n" + 
			"		</taskAudit>\n" + 
			"	</activities>\n" + 
			"</businessProcessAudit>";

	
	public TaskTestCase() {
		super();
		auditMediatorFactory = new AuditMediatorFactory();
	}

	@Test
	public void testTask() throws Exception {
		genericTest(taskProxyTag1, taskProxyTag1BussinesResultCOMPARE);
	}



}
