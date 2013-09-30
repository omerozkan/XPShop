package info.ozkan.test.xpshop.persistence.login.integration;

import static org.junit.Assert.*;
import info.ozkan.test.xpshop.IntegrationAbstractTestCase;
import info.ozkan.test.xpshop.persistence.login.LoginDao;
import info.ozkan.test.xpshop.persistence.login.LoginDaoResult;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.springtestdbunit.annotation.DatabaseSetup;

public class LoginDaoImplIntegrationTest extends IntegrationAbstractTestCase{

    @Autowired
    private LoginDao dao;

    /**
     * Bilgibankasında test_email eposta adresine sahip bir müşteri
     * bulunmaktadır. Gerekli sınıflar aracılığı ile müşteri bilgileri edinilir.
     */
    @Test
    @DatabaseSetup(DATA_SET)
    public void testEmail() {
        String email = "test_email";
        String password = "test_password";
        LoginDaoResult result = dao.findUser(email, password);
        assertEquals(email, result.getCostumer().getEmail());
        assertEquals(password, result.getCostumer().getPassword());
    }
}
