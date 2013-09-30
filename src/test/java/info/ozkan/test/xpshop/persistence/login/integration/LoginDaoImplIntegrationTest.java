package info.ozkan.test.xpshop.persistence.login.integration;

import static org.junit.Assert.*;
import info.ozkan.test.xpshop.persistence.DBUnitTestCase;
import info.ozkan.test.xpshop.persistence.login.LoginDao;
import info.ozkan.test.xpshop.persistence.login.LoginDaoResult;

import org.dbunit.database.IDatabaseConnection;
import org.ietf.jgss.Oid;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.metadata.Db2CallMetaDataProvider;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
public class LoginDaoImplIntegrationTest {

    @Autowired
    private LoginDao dao;

    /**
     * Bilgibankasında test_email eposta adresine sahip bir müşteri
     * bulunmaktadır. Gerekli sınıflar aracılığı ile müşteri bilgileri edinilir.
     */
    @Test
    @DatabaseSetup("/dbunit-dataset.xml")
    public void testEmail() {
        String email = "test_email";
        String password = "test_password";
        LoginDaoResult result = dao.findUser(email, password);
        assertEquals(email, result.getCostumer().getEmail());
        assertEquals(password, result.getCostumer().getPassword());
    }
}
