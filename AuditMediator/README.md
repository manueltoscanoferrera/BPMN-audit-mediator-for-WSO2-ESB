# Audit Mediator

Audit Mediator is a component designed to facilitate the generation of XML/JSON Bussiness Process Audit within wso2 ESB product.

AuditMediator works through " XML command" defined within the Sequences / ESB proxies. You can see the definition of these commands on the page: https://github.com/manueltoscanoferrera/BPMN-audit-mediator-for-WSO2-ESB/wiki/3.--Audit-Mediator-XML-Commands-for-sequences

Each "command" concrete: taskAudit, sendTaskAudit, etc., are responsible for editing its XML tag similar, within the "XML Business Process Audit" final. It may be a common practice that several commands act on a single XML tag, for example; You can define a TaskAudit command to set the creation of a Task, and other TaskAudit to mark the end of it.

<div align="center">
<img src="https://raw.githubusercontent.com/manueltoscanoferrera/BPMN-audit-mediator-for-WSO2-ESB/master/AuditMediator/docs/general.png" width="80%" height="80%" />
</div>

To increase performance, Audit Mediator works internally with a representation of the Audit Business Process in JAXB, rather than directly on XML or JSON. With each command, the Audit Mediator updates the JAXB Audit object with the new instructions.
Subsequently, by the "serialize" command it performs the conversion of such JAXB to XML or JSON structure, saving the result in a variable or in a store.


## More info about this mediator at the wiki.

Wiki https://github.com/manueltoscanoferrera/BPMN-audit-mediator-for-WSO2-ESB/wiki

