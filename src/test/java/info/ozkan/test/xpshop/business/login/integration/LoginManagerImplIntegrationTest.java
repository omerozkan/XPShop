package info.ozkan.test.xpshop.business.login.integration;

import static org.junit.Assert.*;

import javax.naming.ldap.ManageReferralControl;
import javax.naming.spi.DirStateFactory.Result;

import info.ozkan.test.xpshop.IntegrationAbstractTestCase;
import info.ozkan.test.xpshop.business.login.LoginManager;
import info.ozkan.test.xpshop.business.login.LoginResult;

import org.junit.Test;
import org.primefaces.component.password.Password;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;

public class LoginManagerImplIntegrationTest extends IntegrationAbstractTestCase{
    @Autowired
    private LoginManager manager;

    @Test
    @DatabaseSetup(DATA_SET)
    public void testEmail() throws Exception {
        String email = "test_email";
        String password = "test_password";
        LoginResult result = manager.login(email, password);
        assertEquals(email, result.getCustomer().getEmail());
        assertEquals(password, result.getCustomer().getPassword());
    }

}
