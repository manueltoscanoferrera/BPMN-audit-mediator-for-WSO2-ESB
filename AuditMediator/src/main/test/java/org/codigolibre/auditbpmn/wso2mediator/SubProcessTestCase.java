package org.codigolibre.auditbpmn.wso2mediator;

import org.junit.Test;

public class SubProcessTestCase extends AbstractAuditMediatorTestCase  {

	private static String simpleSubprocessProxy = "<audit>\n" + 
			"\n" + 
			"<task id=\"Root1\" closeWithStatus=\"OK\">\n" + 
			"	<startTime  value=\"2010-02-20T13:35:08.901+01:00\"/>\n" + 
			"	<endTime  value=\"2015-02-20T13:36:08.901+01:00\"/>\" \n" + 
			"</task>\n" + 
			"\n" + 
			"\n" + 
			"<subProcess new=\"true\">\n" + 
			"	<name value=\"process1\" />\n" + 
			"	<startTime  value=\"2010-02-20T13:35:08.901+01:00\"/>\n" + 
			"	<endTime  value=\"2015-02-20T13:36:08.901+01:00\"/>\" \n" + 
			"</subProcess>\n" + 
			"\n" + 
			"<task>\n" + 
			"<name value=\"child1.1\" />\n" + 
			"	<startTime  value=\"2010-02-20T13:35:08.901+01:00\"/>\n" + 
			"	<endTime  value=\"2015-02-20T13:36:08.901+01:00\"/>\" \n" + 
			"</task>\n" + 
			"<task new=\"false\" closeWithStatus=\"OK\"/>\n" + 
			"\n" + 
			"<subProcess new=\"false\" closeWithStatus=\"OK\" />\n" + 
			"\n" + 
			"<task id=\"RootEnd\" closeWithStatus=\"OK\">\n" + 
			"	<startTime  value=\"2010-02-20T13:35:08.901+01:00\"/>\n" + 
			"	<endTime  value=\"2015-02-20T13:36:08.901+01:00\"/>\" \n" + 
			"</task>\n" + 
			"\n" + 
			"<serialize target=\"as\"/>\n" + 
			"</audit>";

	/*
	 * Transaction xml OK
	 */
	private static String simpleSubprocessBussinesResultCOMPARE = "\n" + 
			"<businessProcessAudit xmlns=\"urn:org:codigolibre:businessprocessaudit:type:v1.0.0\">\n" + 
			"	<id xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<name xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<startTime xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<endTime xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<status xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<activities>\n" + 
			"		<taskAudit>\n" + 
			"			<id>Root1</id>\n" + 
			"			<name xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"			<startTime>2010-02-20T13:35:08.901+01:00</startTime>\n" + 
			"			<endTime>2015-02-20T13:36:08.901+01:00</endTime>\n" + 
			"			<status>OK</status>\n" + 
			"		</taskAudit>\n" + 
			"		<subProcessAudit>\n" + 
			"			<id xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"			<name>process1</name>\n" + 
			"			<startTime>2010-02-20T13:35:08.901+01:00</startTime>\n" + 
			"			<endTime>2015-02-20T13:36:08.901+01:00</endTime>\n" + 
			"			<status>OK</status>\n" + 
			"			<activities>\n" + 
			"				<taskAudit>\n" + 
			"					<id xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"					<name>child1.1</name>\n" + 
			"					<startTime>2010-02-20T13:35:08.901+01:00</startTime>\n" + 
			"					<endTime>2015-02-20T13:36:08.901+01:00</endTime>\n" + 
			"					<status>OK</status>\n" + 
			"				</taskAudit>\n" + 
			"			</activities>\n" + 
			"		</subProcessAudit>\n" + 
			"		<taskAudit>\n" + 
			"			<id>RootEnd</id>\n" + 
			"			<name xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"			<startTime>2010-02-20T13:35:08.901+01:00</startTime>\n" + 
			"			<endTime>2015-02-20T13:36:08.901+01:00</endTime>\n" + 
			"			<status>OK</status>\n" + 
			"		</taskAudit>\n" + 
			"	</activities>\n" + 
			"</businessProcessAudit>\n" + 
			"";

	
	private static String chainingSubProcessProxy="<audit>\n" + 
			"\n" + 
			"<task id=\"Root1\" closeWithStatus=\"OK\">\n" + 
			"	<startTime  value=\"2010-02-20T13:35:08.901+01:00\"/>\n" + 
			"	<endTime  value=\"2015-02-20T13:36:08.901+01:00\"/>\" \n" + 
			"</task>\n" + 
			"\n" + 
			"\n" + 
			"<subProcess new=\"true\">\n" + 
			"	<name value=\"process1\" />\n" + 
			"	<startTime  value=\"2010-02-20T13:35:08.901+01:00\"/>\n" + 
			"	<endTime  value=\"2015-02-20T13:36:08.901+01:00\"/>\" \n" + 
			"</subProcess>\n" + 
			"\n" + 
			"<task>\n" + 
			"<name value=\"child1.1\" />\n" + 
			"	<startTime  value=\"2010-02-20T13:35:08.901+01:00\"/>\n" + 
			"	<endTime  value=\"2015-02-20T13:36:08.901+01:00\"/>\" \n" + 
			"</task>\n" + 
			"<task new=\"false\" closeWithStatus=\"OK\"/>\n" + 
			"\n" + 
			"\n" + 
			"<subProcess new=\"true\">\n" + 
			"	<name value=\"process1.1\" />\n" + 
			"	<startTime  value=\"2010-02-20T13:35:08.901+01:00\"/>\n" + 
			"	<endTime  value=\"2015-02-20T13:36:08.901+01:00\"/>\" \n" + 
			"</subProcess>\n" + 
			"\n" + 
			"<task>\n" + 
			"<name value=\"child1.1.1\" />\n" + 
			"	<startTime  value=\"2010-02-20T13:35:08.901+01:00\"/>\n" + 
			"	<endTime  value=\"2015-02-20T13:36:08.901+01:00\"/>\" \n" + 
			"</task>\n" + 
			"<task new=\"false\" closeWithStatus=\"ERROR\"/>\n" + 
			"\n" + 
			"\n" + 
			"<subProcess new=\"false\" closeWithStatus=\"ERROR\" />\n" + 
			"<task>\n" + 
			"<name value=\"child1.2\" />\n" + 
			"	<startTime  value=\"2010-02-20T13:35:08.901+01:00\"/>\n" + 
			"	<endTime  value=\"2015-02-20T13:36:08.901+01:00\"/>\" \n" + 
			"</task>\n" + 
			"<task new=\"false\" closeWithStatus=\"STATE CUSTOM\"/>\n" + 
			"\n" + 
			"<subProcess new=\"false\" closeWithStatus=\"OK\" />\n" + 
			"\n" + 
			"<task id=\"RootEnd\" closeWithStatus=\"OK\">\n" + 
			"	<startTime  value=\"2010-02-20T13:35:08.901+01:00\"/>\n" + 
			"	<endTime  value=\"2015-02-20T13:36:08.901+01:00\"/>\" \n" + 
			"</task>\n" + 
			"\n" + 
			"<serialize target=\"as\"/>\n" + 
			"</audit>";
	
	private static String chainingSubProcessBussinesResultCOMPARE="\n" + 
			"<businessProcessAudit xmlns=\"urn:org:codigolibre:businessprocessaudit:type:v1.0.0\">\n" + 
			"	<id xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<name xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<startTime xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<endTime xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<status xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<activities>\n" + 
			"		<taskAudit>\n" + 
			"			<id>Root1</id>\n" + 
			"			<name xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"			<startTime>2010-02-20T13:35:08.901+01:00</startTime>\n" + 
			"			<endTime>2015-02-20T13:36:08.901+01:00</endTime>\n" + 
			"			<status>OK</status>\n" + 
			"		</taskAudit>\n" + 
			"		<subProcessAudit>\n" + 
			"			<id xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"			<name>process1</name>\n" + 
			"			<startTime>2010-02-20T13:35:08.901+01:00</startTime>\n" + 
			"			<endTime>2015-02-20T13:36:08.901+01:00</endTime>\n" + 
			"			<status>OK</status>\n" + 
			"			<activities>\n" + 
			"				<taskAudit>\n" + 
			"					<id xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"					<name>child1.1</name>\n" + 
			"					<startTime>2010-02-20T13:35:08.901+01:00</startTime>\n" + 
			"					<endTime>2015-02-20T13:36:08.901+01:00</endTime>\n" + 
			"					<status>OK</status>\n" + 
			"				</taskAudit>\n" + 
			"				<subProcessAudit>\n" + 
			"					<id xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"					<name>process1.1</name>\n" + 
			"					<startTime>2010-02-20T13:35:08.901+01:00</startTime>\n" + 
			"					<endTime>2015-02-20T13:36:08.901+01:00</endTime>\n" + 
			"					<status>ERROR</status>\n" + 
			"					<error>\n" + 
			"						<errorCode>ERROR_CODE value</errorCode>\n" + 
			"						<errorMessage>ERROR_MESSAGE value</errorMessage>\n" + 
			"						<errorDetail>ERROR_DETAIL value</errorDetail>\n" + 
			"					</error>\n" + 
			"					<activities>\n" + 
			"						<taskAudit>\n" + 
			"							<id xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"							<name>child1.1.1</name>\n" + 
			"							<startTime>2010-02-20T13:35:08.901+01:00</startTime>\n" + 
			"							<endTime>2015-02-20T13:36:08.901+01:00</endTime>\n" + 
			"							<status>ERROR</status>\n" + 
			"							<error>\n" + 
			"								<errorCode>ERROR_CODE value</errorCode>\n" + 
			"								<errorMessage>ERROR_MESSAGE value</errorMessage>\n" + 
			"								<errorDetail>ERROR_DETAIL value</errorDetail>\n" + 
			"							</error>\n" + 
			"						</taskAudit>\n" + 
			"					</activities>\n" + 
			"				</subProcessAudit>\n" + 
			"				<taskAudit>\n" + 
			"					<id xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"					<name>child1.2</name>\n" + 
			"					<startTime>2010-02-20T13:35:08.901+01:00</startTime>\n" + 
			"					<endTime>2015-02-20T13:36:08.901+01:00</endTime>\n" + 
			"					<status>STATE CUSTOM</status>\n" + 
			"				</taskAudit>\n" + 
			"			</activities>\n" + 
			"		</subProcessAudit>\n" + 
			"		<taskAudit>\n" + 
			"			<id>RootEnd</id>\n" + 
			"			<name xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"			<startTime>2010-02-20T13:35:08.901+01:00</startTime>\n" + 
			"			<endTime>2015-02-20T13:36:08.901+01:00</endTime>\n" + 
			"			<status>OK</status>\n" + 
			"		</taskAudit>\n" + 
			"	</activities>\n" + 
			"</businessProcessAudit>";
	
	
	
	@Test
	public void testSimple() throws Exception {
		genericTest(simpleSubprocessProxy, simpleSubprocessBussinesResultCOMPARE);
	}

	
	@Test
	public void testchainningSubprocessSimple() throws Exception {
		genericTest(chainingSubProcessProxy, chainingSubProcessBussinesResultCOMPARE);
	}


}
