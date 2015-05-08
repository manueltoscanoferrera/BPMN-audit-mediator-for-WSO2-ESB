package org.codigolibre.auditbpmn.wso2mediator;

import org.junit.Test;

public class ReceiveTaskTestCase extends AbstractAuditMediatorTestCase {

	private static String captureWSMSGProxy = "<audit xmlns=\"http://ws.apache.org/ns/synapse\">\n" + 
			"    <receiveTask captureMsg=\"true\" closeWithStatus=\"OK\" id=\"id\" new=\"true\">\n" + 
			"        <name value=\"name value\"/>\n" + 
			"        <startTime value=\"2010-02-20T13:35:08.901+01:00\"/>\n" + 
			"        <endTime value=\"2015-02-20T13:35:08.901+01:00\"/>\n" + 
			"    </receiveTask>\n" + 
			"	<serialize target=\"as\"/> \n" + 
			"</audit>";
	
	
	
	
	private static String receiveWSProxyComplete = "<businessProcessAudit xmlns=\"urn:org:codigolibre:businessprocessaudit:type:v1.0.0\">\n" + 
			"	<id xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<name xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<startTime xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<endTime xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<status xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"	<activities>\n" + 
			"		<receiveTaskAudit>\n" + 
			"			<id>id</id>\n" + 
			"			<name>name value</name>\n" + 
			"			<startTime>2010-02-20T13:35:08.901+01:00</startTime>\n" + 
			"			<endTime>2015-02-20T13:35:08.901+01:00</endTime>\n" + 
			"			<status>OK</status>\n" + 
			"			<webServiceAudit>\n" + 
			"				<endPoint xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"				<operation xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"				<type xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"				<msgResponse>&lt;?xml version='1.0' encoding='utf-8'?&gt;&lt;soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"&gt;&lt;soapenv:Body&gt;&lt;p:echoInt xmlns:p=\"http://echo.services.core.carbon.wso2.org\"&gt;\n" + 
			"      &lt;in&gt;1&lt;/in&gt;\n" + 
			"   &lt;/p:echoInt&gt;&lt;/soapenv:Body&gt;&lt;/soapenv:Envelope&gt;</msgResponse>\n" + 
			"				<ipServer>127.0.1.1</ipServer>\n" + 
			"				<hostServer>_HOST_</hostServer>\n" + 
			"			</webServiceAudit>\n" + 
			"		</receiveTaskAudit>\n" + 
			"	</activities>\n" + 
			"</businessProcessAudit>";

	

	private static String captureJMSProxy = "<audit xmlns=\"http://ws.apache.org/ns/synapse\">\n" + 
			"    <receiveTask captureMsg=\"true\" closeWithStatus=\"OK\" id=\"id\" new=\"true\">\n" + 
			"        <name value=\"name value\"/>\n" + 
			"        <startTime value=\"2010-02-20T13:35:08.901+01:00\"/>\n" + 
			"        <endTime value=\"2015-02-20T13:35:08.901+01:00\"/>\n" + 
			"        <jmsService>\n" + 
			"            <endPoint value=\"gov:/endpoint/ServiceEP.xml\"/>"
			+		 "</jmsService>"+
			"    </receiveTask>\n" + 
			"	<serialize target=\"as\"/> \n" + 
			"</audit>";

	
	
	private static String captureJMSProxyComplete = "<businessProcessAudit xmlns=\"urn:org:codigolibre:businessprocessaudit:type:v1.0.0\"><id xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"			<name xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"			<startTime xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"			<endTime xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"			<status xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>\n" + 
			"			<activities>\n" + 
			"				<receiveTaskAudit>\n" + 
			"					<id>id</id>\n" + 
			"					<name>name value</name>\n" + 
			"					<startTime>2010-02-20T13:35:08.901+01:00</startTime>\n" + 
			"					<endTime>2015-02-20T13:35:08.901+01:00</endTime>\n" + 
			"					<status>OK</status>\n" + 
			"					<jmsServiceAudit>\n" + 
			"						<endPoint>gov:/endpoint/ServiceEP.xml</endPoint>\n" + 
			"						<msgResponse>&lt;?xml version='1.0' encoding='utf-8'?&gt;&lt;soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"&gt;&lt;soapenv:Body&gt;&lt;p:echoInt xmlns:p=\"http://echo.services.core.carbon.wso2.org\"&gt;\n" + 
			"      &lt;in&gt;1&lt;/in&gt;\n" + 
			"   &lt;/p:echoInt&gt;&lt;/soapenv:Body&gt;&lt;/soapenv:Envelope&gt;</msgResponse>\n" + 
			"						<ipServer>127.0.1.1</ipServer>\n" + 
			"						<hostServer>usuario-laptop7</hostServer>\n" + 
			"					</jmsServiceAudit>\n" + 
			"				</receiveTaskAudit>\n" + 
			"			</activities>\n" + 
			"		</businessProcessAudit>";
	
	
	@Test
	public void testTask() throws Exception {
		genericTest(captureWSMSGProxy, receiveWSProxyComplete);	
		genericTest(captureJMSProxy, captureJMSProxyComplete);
		
	}



}
