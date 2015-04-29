package org.codigolibre.auditbpmn.wso2mediator;

import org.junit.Test;

public class ActivityBaseTestCase extends AbstractAuditMediatorTestCase {

	private static String completeActivityProxy = "<audit xmlns=\"http://ws.apache.org/ns/synapse\">\n"
			+ "	<task id=\"id\" new=\"true\" closeWithStatus=\"ERROR\">\n"
			+ "		<name value=\"task validation\"/>\n"
			+ "		<description value=\"Description task validation\"/>\n"
			+ "		<startTime  value=\"2010-02-20T13:35:08.901+01:00\"/>\n"
			+ "		<endTime  value=\"2015-02-20T13:35:08.901+01:00\"/>\n"
			+ "		<status value=\"1\" /> \n"
			+ "		<errorCode value=\"aaaa\" />\n"
			+ "		<errorMessage value=\"bbbbb\"/>\n"
			+ "		<errorDetail value=\"cccc\" />\n"
			+ "		<inputParams>\n"
			+ "			<param name=\"inputP1\" value=\"valueinputP\"/>\n"
			+ "			<param name=\"inputP2\" value=\"{$ctx:custom}\"/>\n"
			+ "		</inputParams>\n"
			+ "		<outputParams>\n"
			+ "			<param name=\"outputP1\" value=\"valueoutputP1\"/>\n"
			+ "		</outputParams>\n"
			+ "	</task>\n"
			+ "	<serialize target=\"as\"/> \n" + "</audit>";

	/*
	 * Transaction xml OK
	 */
	private static String completeActivityProxyResultToCOMPARE = "<businessProcessAudit xmlns=\"urn:org:codigolibre:businessprocessaudit:type:v1.0.0\">\n"
			+ "	<id xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n"
			+ "	<name xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n"
			+ "	<startTime xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n"
			+ "	<endTime xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n"
			+ "	<status xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n"
			+ "	<activities>\n"
			+ "		<taskAudit>\n"
			+ "			<id>id</id>\n"
			+ "			<name>task validation</name>\n"
			+ "			<description>Description task validation</description>\n"
			+ "			<startTime>2010-02-20T13:35:08.901+01:00</startTime>\n"
			+ "			<endTime>2015-02-20T13:35:08.901+01:00</endTime>\n"
			+ "			<status>ERROR</status>\n"
			+ "			<error>\n"
			+ "				<errorCode>aaaa</errorCode>\n"
			+ "				<errorMessage>bbbbb</errorMessage>\n"
			+ "				<errorDetail>cccc</errorDetail>\n"
			+ "			</error>\n"
			+ "			<inputParams>\n"
			+ "				<param name=\"inputP1\">valueinputP</param>\n"
			+ "				<param name=\"inputP2\">custom variable value</param>\n"
			+ "			</inputParams>\n"
			+ "			<outputParams>\n"
			+ "				<param name=\"outputP1\">valueoutputP1</param>\n"
			+ "			</outputParams>\n"
			+ "		</taskAudit>\n"
			+ "	</activities>\n"
			+ "</businessProcessAudit>";

	private static String errorCaptureProxyWithCloseWithStatus = "<audit xmlns=\"http://ws.apache.org/ns/synapse\">\n"
			+ "	<task id=\"id\" new=\"true\" closeWithStatus=\"ERROR\">\n"
			+ "		<name value=\"task validation\"/>\n"
			+ "		<description value=\"Description task validation\"/>\n"
			+ "		<startTime  value=\"2010-02-20T13:35:08.901+01:00\"/>\n"
			+ "		<endTime  value=\"2015-02-20T13:35:08.901+01:00\"/>\n"
			+ "	</task>\n" + "	<serialize target=\"as\"/> \n" + "</audit>";

	private static String errorCaptureProxyWithStatus = "<audit xmlns=\"http://ws.apache.org/ns/synapse\">\n"
			+ "	<task id=\"id\" new=\"true\">\n"
			+ "		<name value=\"task validation\"/>\n"
			+ "		<description value=\"Description task validation\"/>\n"
			+ "		<startTime  value=\"2010-02-20T13:35:08.901+01:00\"/>\n"
			+ "		<endTime  value=\"2015-02-20T13:35:08.901+01:00\"/>\n"
			+ "		<status  value=\"ERROR\"/>\n"
			+ "	</task>\n"
			+ "	<serialize target=\"as\"/> \n" + "</audit>";

	private static String errorCaptureProxyResultToCOMPARE = "<businessProcessAudit xmlns=\"urn:org:codigolibre:businessprocessaudit:type:v1.0.0\">\n"
			+ "	<id xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n"
			+ "	<name xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n"
			+ "	<startTime xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n"
			+ "	<endTime xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n"
			+ "	<status xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n"
			+ "	<activities>\n"
			+ "		<taskAudit>\n"
			+ "			<id>id</id>\n"
			+ "			<name>task validation</name>\n"
			+ "			<description>Description task validation</description>\n"
			+ "			<startTime>2010-02-20T13:35:08.901+01:00</startTime>\n"
			+ "			<endTime>2015-02-20T13:35:08.901+01:00</endTime>\n"
			+ "			<status>ERROR</status>\n"
			+ "			<error>\n"
			+ "				<errorCode>ERROR_CODE value</errorCode>\n"
			+ "				<errorMessage>ERROR_MESSAGE value</errorMessage>\n"
			+ "				<errorDetail>ERROR_DETAIL value</errorDetail>\n"
			+ "			</error>\n"
			+ "		</taskAudit>\n"
			+ "	</activities>\n"
			+ "</businessProcessAudit>";

	private static String valueExpresionProxy = "<audit xmlns=\"http://ws.apache.org/ns/synapse\">\n"
			+ "	<task id=\"id\" new=\"true\" closeWithStatus=\"ERROR\">\n"
			+ "		<name value=\"task validation\"/>\n"
			+ "		<description value=\"Description task validation\"/>\n"
			+ "		<startTime  value=\"2010-02-20T13:35:08.901+01:00\"/>\n"
			+ "		<endTime  value=\"2015-02-20T13:35:08.901+01:00\"/>\n"
			+ "		<status value=\"1\" /> \n"
			+ "		<errorCode value=\"aaaa\" />\n"
			+ "		<errorMessage value=\"bbbbb\"/>\n"
			+ "		<errorDetail value=\"cccc\" />\n"
			+ "		<inputParams>\n"
			+ "			<param name=\"inputP1\" value=\"{//in}\"/>\n"
			+ "			<param name=\"inputP2\" value=\"{$ctx:custom}\"/>\n"
			+ "		</inputParams>\n"
			+ "		<outputParams>\n"
			+ "			<param name=\"outputP1\" value=\"valueoutputP1\"/>\n"
			+ "		</outputParams>\n"
			+ "	</task>\n"
			+ "	<serialize target=\"as\"/> \n" + "</audit>";

	private static String valueExpresionResultToCOMPARE = "<businessProcessAudit xmlns=\"urn:org:codigolibre:businessprocessaudit:type:v1.0.0\">\n"
			+ "	<id xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n"
			+ "	<name xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n"
			+ "	<startTime xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n"
			+ "	<endTime xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n"
			+ "	<status xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n"
			+ "	<activities>\n"
			+ "		<taskAudit>\n"
			+ "			<id>id</id>\n"
			+ "			<name>task validation</name>\n"
			+ "			<description>Description task validation</description>\n"
			+ "			<startTime>2010-02-20T13:35:08.901+01:00</startTime>\n"
			+ "			<endTime>2015-02-20T13:35:08.901+01:00</endTime>\n"
			+ "			<status>ERROR</status>\n"
			+ "			<error>\n"
			+ "				<errorCode>aaaa</errorCode>\n"
			+ "				<errorMessage>bbbbb</errorMessage>\n"
			+ "				<errorDetail>cccc</errorDetail>\n"
			+ "			</error>\n"
			+ "			<inputParams>\n"
			+ "				<param name=\"inputP1\">1</param>\n"
			+ "				<param name=\"inputP2\">custom variable value</param>\n"
			+ "			</inputParams>\n"
			+ "			<outputParams>\n"
			+ "				<param name=\"outputP1\">valueoutputP1</param>\n"
			+ "			</outputParams>\n"
			+ "		</taskAudit>\n"
			+ "	</activities>\n"
			+ "</businessProcessAudit>";

	private static String dateGenerationProxy = "<audit xmlns=\"http://ws.apache.org/ns/synapse\">\n"
			+ "	<task id=\"id\" new=\"true\"/>\n"
			+ "	<task id=\"id\" new=\"false\" closeWithStatus=\"OK\"/>\n"
			+ "	<serialize target=\"as\"/> \n" + "</audit>";

	@Test
	public void testCompleteProxyTags() throws Exception {
		genericTest(completeActivityProxy, completeActivityProxyResultToCOMPARE);
	}

	@Test
	public void testErrorCaptureWithCloseWithStatus() throws Exception {
		genericTest(errorCaptureProxyWithCloseWithStatus,
				errorCaptureProxyResultToCOMPARE);
	}

	@Test
	public void testErrorCaptureWithStatus() throws Exception {
		genericTest(errorCaptureProxyWithStatus,
				errorCaptureProxyResultToCOMPARE);
	}

	@Test
	public void testValueExpresion() throws Exception {
		genericTest(valueExpresionProxy, valueExpresionResultToCOMPARE);
	}

	@Test
	public void testDateGeneration() throws Exception {
		String serializeBusiness = validateAndGetSerialize(dateGenerationProxy);
		
		// workaround,  assertxpath Bug with namespace
		serializeBusiness = serializeBusiness.replaceAll("xmlns=\"urn:org:codigolibre:businessprocessaudit:type:v1.0.0\"", "");
		// note: name has a null value, perfect to this validation ... 
		assertXpathsNotEqual("/businessProcessAudit/name", "/businessProcessAudit/activities/taskAudit/startTime",
				serializeBusiness);
		
		assertXpathsNotEqual("/businessProcessAudit/name", "/businessProcessAudit/activities/taskAudit/endTime",
				serializeBusiness);

	}


}
