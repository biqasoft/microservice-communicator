package com.biqasoft.microservice.communicator.interfaceimpl;

import com.biqasoft.microservice.communicator.interfaceimpl.demo.UserAccount;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Nikita Bakaev, ya@nbakaev.ru
 *         Date: 7/25/2016
 *         All Rights Reserved
 */
@SpringBootTest(classes = StartApplicationTest.class)
@WebAppConfiguration
@Test(suiteName = "microserviceCommunicationInterface")
@ActiveProfiles({"development", "test"})
public class MicroserviceInterfaceImplementationFactoryTest extends AbstractTestNGSpringContextTests {

    @Test(enabled = true)
    public void testCreate() throws Exception {
        Object o = MicroserviceInterface.create(MicroserviceUsersRepository.class);
        Assert.assertNotNull(o, "MicroserviceInterfaceImplementorFactory object is NULL");

        if (o instanceof MicroserviceUsersRepository) {
            UserAccount userAccount = ((MicroserviceUsersRepository) o).returnSingleObject();

            Assert.assertNotNull(userAccount);
            Assert.assertNotNull(userAccount.getId());
        } else {
            Assert.fail(o.toString() + "Not implements interface");
        }
    }

    @Test(enabled = true, description = "assert that we create implementation of interface")
    public void testIsImplementInterface() throws Exception {
        Object o = MicroserviceInterface.create(MicroserviceUsersRepository.class);

        if (!(o instanceof MicroserviceUsersRepository)) {
            Assert.fail("Not implements interface");
        }
    }

}