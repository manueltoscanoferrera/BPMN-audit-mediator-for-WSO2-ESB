*****************
Compile
*****************

maven clean package


*****************
Deploy
*****************

Copy target/audit-mediator-1.3.0.jar to $ESB_HOME/repository/components/dropins


*****************
Configuration
*****************

1.- Logger 

1.1 Logger debug for AuditMediator
Edit $ESB_HOME/repository/conf/log4j.properties and include:

log4j.logger.org.codigolibre.auditbpmn.wso2mediator=INFO, CARBON_CONSOLE, CARBON_LOGFILE, CARBON_MEMORY, CARBON_SYS_LOG, ERROR_LOGFILE

1.2 Logger for custom serialize logger: example 	<serialize logger="loggerOUT" media-type="json"/>

Edit $ESB_HOME/repository/conf/log4j.properties and include:

log4j.logger.loggerOUT=INFO, CARBON_CONSOLE, CARBON_LOGFILE, CARBON_MEMORY, CARBON_SYS_LOG, ERROR_LOGFILE



2.- Disabling the component within the proxy / sequences by property.

<property name="AUDIT_MEDIATOR_DISABLED" value="true"/>


3.- Disabling the automatic capture of fields within the tags  jmsService and webService within the proxy / sequences by property

 <property name="AUDIT_MEDIATOR_DISABLED_JMS_WS_PARAM_CAPTURE" value="true"/>




****************
USES
****************

See examples and documentation


