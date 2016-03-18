
## Kibana Dashboard for BPMN Audit Mediator logs
<br/>
<div align="center">
<img src="https://raw.githubusercontent.com/manueltoscanoferrera/BPMN-audit-mediator-for-WSO2-ESB/master/KibanaDashboard/dashboard_audit.png" width="80%" height="80%" />
</div>
<br/>


Product versions required: elasticsearch-2.2.0, logstash-2.X, kibana-4.4.1
  

1.- Configure a logger in the wso2 Carbon (log4j.properties) in which record the auditMediator serialization
```
		log4j.logger.loggerOUT=INFO, AUDIT_LOGFILE

		# AUDIT_LOGFILE
		log4j.appender.AUDIT_LOGFILE=org.wso2.carbon.logging.appenders.CarbonDailyRollingFileAppender
		log4j.appender.AUDIT_LOGFILE.File=${carbon.home}/repository/logs/${instance.log}/audit${instance.log}.log
		log4j.appender.AUDIT_LOGFILE.Append=true
		log4j.appender.AUDIT_LOGFILE.layout=org.wso2.carbon.utils.logging.TenantAwarePatternLayout
		log4j.appender.AUDIT_LOGFILE.layout.ConversionPattern=%m%n
		log4j.appender.AUDIT_LOGFILE.layout.TenantPattern=%U%@%D [%T] [%S]
		log4j.appender.AUDIT_LOGFILE.threshold=INFO
```

2.- Services must serialize to the logger configured previosly in json format
```
    <serialize target="auditLog" media-type="json" logger="loggerOUT"/>
```
3.- Use logstash to send the content of audit.log file to elastic search in all the wso2 servers
    3.1 Install logstash in the wso2 server o server with logs files access.
    3.2 Copy and edit the file logstash-audit.conf
    3.3 Run logstash 
```		
    ./bin/logstash -f logstash-audit.conf
``` 
    Note: The logstash-audit.conf include the generation of a new param: "businessProcessAudit.duration"

4.- Kibana
    4.1- Refresh field list in index when audit logs are presents in elasticseach
    4.2- Import Dashboard (export-dashboard_audit.json)
