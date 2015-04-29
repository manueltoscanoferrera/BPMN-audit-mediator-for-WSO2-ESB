## Goal

Provide a xml that collects business process execution.

## Applications.

* Audit and system logs.
* Common data model to facilitate the implementation of EIPs as Dead Letter Pattern channel, invalid message channel.
* Process coordination, etc.


##  Works done
  * Definition of XML, using as a basis BPMN. See businessProcessAudit.xsd for a description of xml  
  * Created a mediator for wso2 esb, to facilitate the generation of XML audit within the ESB, Project AuditMediator

## Future works


## Business Process Audit XML Example.
```
<businessProcessAudit xmlns="urn:org:codigolibre:businessprocessaudit:type:v1.0.0">
			<id>52</id>
			<name>Consulta Demanda Por ID Abreviada</name>
			<description>Consulta Demanda Por ID Abreviada</description>
			<startTime>2015-04-29T12:07:43.664+02:00</startTime>
			<endTime>2015-04-29T12:07:43.669+02:00</endTime>
			<status>OK</status>
			<activities>
				<receiveTaskAudit>
					<id>ProxyPruebas</id>
					<name>prueba</name>
					<description>echo</description>
					<startTime>2015-04-29T12:07:43.665+02:00</startTime>
					<endTime>2015-04-29T12:07:52.422+02:00</endTime>
					<status>OK</status>
					<webServiceAudit>
						<endPoint>local:///services/prueba.pruebaLocalSoap11Endpoint/</endPoint>
						<operation>urn:echoString</operation>
						<type>http://www.w3.org/ns/wsdl/in-out</type>
						<msgRequest>&lt;?xml version="1.0" encoding="utf-8"?&gt;&lt;soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:echo="http://echo.services.core.carbon.wso2.org"&gt;&lt;soapenv:Header xmlns:wsa="http://www.w3.org/2005/08/addressing"&gt;&lt;wsse:Security xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd"&gt;&lt;wsse:UsernameToken wsu:Id="UsernameToken-8"&gt;&lt;wsse:Username&gt;admin&lt;/wsse:Username&gt;&lt;wsse:Password Type="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText"&gt;admin&lt;/wsse:Password&gt;&lt;wsse:Nonce EncodingType="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary"&gt;2dSnL+J7dRketCGOGUrM8w==&lt;/wsse:Nonce&gt;&lt;wsu:Created&gt;2015-04-29T10:07:51.486Z&lt;/wsu:Created&gt;&lt;/wsse:UsernameToken&gt;&lt;wsu:Timestamp wsu:Id="TS-7"&gt;&lt;wsu:Created&gt;2015-04-29T10:07:51.486Z&lt;/wsu:Created&gt;&lt;wsu:Expires&gt;2015-04-29T10:08:01.486Z&lt;/wsu:Expires&gt;&lt;/wsu:Timestamp&gt;&lt;/wsse:Security&gt;&lt;wsa:Action&gt;urn:echoString&lt;/wsa:Action&gt;&lt;wsa:MessageID&gt;1111&lt;/wsa:MessageID&gt;&lt;/soapenv:Header&gt;&lt;soapenv:Body&gt;      &lt;echo:echoString&gt;         &lt;!--Optional:--&gt;         &lt;in&gt;qwwqw&lt;/in&gt;      &lt;/echo:echoString&gt;   &lt;/soapenv:Body&gt;&lt;/soapenv:Envelope&gt;</msgRequest>
						<msgResponse>&lt;?xml version="1.0" encoding="utf-8"?&gt;&lt;soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"&gt;&lt;soapenv:Body&gt;&lt;ns:echoStringResponse xmlns:ns="http://echo.services.core.carbon.wso2.org"&gt;&lt;return&gt;qwwqw&lt;/return&gt;&lt;/ns:echoStringResponse&gt;&lt;/soapenv:Body&gt;&lt;/soapenv:Envelope&gt;</msgResponse>
						<ipClient>127.0.1.1</ipClient>
						<hostClient>usuario-laptop7</hostClient>
						<idClient>admin</idClient>
						<ipServer>127.0.1.1</ipServer>
						<hostServer>usuario-laptop7</hostServer>
					</webServiceAudit>
				</receiveTaskAudit>
				<sendTaskAudit>
					<id>callWSConsultaDemandaPorID</id>
					<name>Consulta Demanda Por ID</name>
					<description>echo</description>
					<startTime>2015-04-29T12:07:43.667+02:00</startTime>
					<endTime>2015-04-29T12:07:52.418+02:00</endTime>
					<status>OK</status>
					<webServiceAudit>
						<endPoint>http://usuario-laptop7:8280/services/echo</endPoint>
						<operation>echoString</operation>
						<type>http://www.w3.org/ns/wsdl/in-out</type>
						<idMsg>urn:uuid:7938c7ca-0195-4954-9017-5934b22bb83f</idMsg>
						<msgRequest>&lt;?xml version="1.0" encoding="utf-8"?&gt;&lt;soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:echo="http://echo.services.core.carbon.wso2.org"&gt;&lt;soapenv:Header xmlns:wsa="http://www.w3.org/2005/08/addressing"&gt;&lt;wsse:Security xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd"&gt;&lt;wsse:UsernameToken wsu:Id="UsernameToken-8"&gt;&lt;wsse:Username&gt;admin&lt;/wsse:Username&gt;&lt;wsse:Password Type="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText"&gt;admin&lt;/wsse:Password&gt;&lt;wsse:Nonce EncodingType="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary"&gt;2dSnL+J7dRketCGOGUrM8w==&lt;/wsse:Nonce&gt;&lt;wsu:Created&gt;2015-04-29T10:07:51.486Z&lt;/wsu:Created&gt;&lt;/wsse:UsernameToken&gt;&lt;wsu:Timestamp wsu:Id="TS-7"&gt;&lt;wsu:Created&gt;2015-04-29T10:07:51.486Z&lt;/wsu:Created&gt;&lt;wsu:Expires&gt;2015-04-29T10:08:01.486Z&lt;/wsu:Expires&gt;&lt;/wsu:Timestamp&gt;&lt;/wsse:Security&gt;&lt;wsa:Action&gt;urn:echoString&lt;/wsa:Action&gt;&lt;wsa:MessageID&gt;1111&lt;/wsa:MessageID&gt;&lt;/soapenv:Header&gt;&lt;soapenv:Body&gt;      &lt;echo:echoString&gt;         &lt;!--Optional:--&gt;         &lt;in&gt;qwwqw&lt;/in&gt;      &lt;/echo:echoString&gt;   &lt;/soapenv:Body&gt;&lt;/soapenv:Envelope&gt;</msgRequest>
						<msgResponse>&lt;?xml version="1.0" encoding="utf-8"?&gt;&lt;soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"&gt;&lt;soapenv:Body&gt;&lt;ns:echoStringResponse xmlns:ns="http://echo.services.core.carbon.wso2.org"&gt;&lt;return&gt;qwwqw&lt;/return&gt;&lt;/ns:echoStringResponse&gt;&lt;/soapenv:Body&gt;&lt;/soapenv:Envelope&gt;</msgResponse>
						<ipClient>127.0.1.1</ipClient>
						<hostClient>usuario-laptop7</hostClient>
						<ipServer>127.0.1.1</ipServer>
						<hostServer>usuario-laptop7</hostServer>
					</webServiceAudit>
				</sendTaskAudit>
			</activities>
		</businessProcessAudit>

```

## Audit Mediator Example: ESB Proxy with process Audit and Dead Letter Channel EIP
```
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse"
       name="prueba"
       transports="https http"
       startOnLoad="true"
       trace="enable">
   <description/>
   <target>
      <endpoint>
         <address uri="http://usuario-laptop7:8280/services/echo"/>
      </endpoint>
      <inSequence>
         <audit>
            <businessProcess id="52">
               <name value="Consulta Demanda Por ID Abreviada"/>
               <description value="Consulta Demanda Por ID Abreviada"/>
            </businessProcess>
            <receiveTask id="ProxyPruebas" new="true">
               <webService>
                  <msgRequest capture="true"/>
               </webService>
            </receiveTask>
            <sendTask id="callWSConsultaDemandaPorID" new="true">
               <name value="Consulta Demanda Por ID"/>
               <webService>
                  <msgRequest capture="true"/>
               </webService>
            </sendTask>
         </audit>
      </inSequence>
      <outSequence>
         <audit>
            <businessProcess closeWithStatus="OK" cascadeClose="true" captureMsg="true">
               <status value="OK"/>
            </businessProcess>
            <serialize store="audit_store" media-type="xml"/>
         </audit>
         <send/>
      </outSequence>
      <faultSequence>
         <makefault version="soap11">
            <code xmlns:tns="http://www.w3.org/2003/05/soap-envelope" value="tns:Receiver"/>
            <reason expression="get-property('ERROR_MESSAGE')"/>
         </makefault>
         <audit>
            <businessProcess closeWithStatus="ERROR" cascadeClose="true" captureMsg="true"/>
            <serialize store="dead_letter_channel" media-type="xml"/>
         </audit>
         <respond/>
      </faultSequence>
   </target>
   <publishWSDL uri="http://usuario-laptop7:8280/services/echo?wsdl"/>
   <policy key="conf:/repository/axis2/service-groups/prueba/services/prueba/policies/UTOverTransport"/>
   <parameter name="ScenarioID">scenario1</parameter>
   <enableSec/>
</proxy>
```
