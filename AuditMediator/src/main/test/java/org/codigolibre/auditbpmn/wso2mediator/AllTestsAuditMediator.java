package org.codigolibre.auditbpmn.wso2mediator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AuditMediatorSerializationTestCase.class,
		ActivityBaseTestCase.class, SerializeTestCase.class,
		TaskTestCase.class, SubProcessTestCase.class, SendTaskTestCase.class,
		ReceiveTaskTestCase.class, BusinessProcessAuditTestCase.class })
public class AllTestsAuditMediator {

}
