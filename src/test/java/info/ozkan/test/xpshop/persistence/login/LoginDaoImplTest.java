package info.ozkan.test.xpshop.persistence.login;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import info.ozkan.test.xpshop.business.login.StatusCodes;
import info.ozkan.test.xpshop.domain.Customer;

import org.junit.Before;
import org.junit.Test;

public class LoginDaoImplTest {
    /**
     * Testlerde kullanılan eposta adresi
     */
	private String email = "test_email";
	/**
	 * Testlerde kullanılan parola
	 */
    private String password = "test_password";
    /**
	 * Query mock nesnesi
	 */
	private Query query = mock(Query.class);
    /**
	 * EntitiyManager mock nesnesi
	 */
	private EntityManager em = mock(EntityManager.class);
    /**
	 * Test edilen LoginDaoImpl sınıfı
	 */
	private LoginDaoImpl dao = new LoginDaoImpl();
    /**
	 * EntitiyManager a gönderilmesi gereken sorgu
	 */
	private String jql = "from Customer c where c.email = :email";

	/**
	 * EntityManager injection
	 */
	@Before
	public void setUp() {
	    dao.setEm(em);
    }
	/**
	 * Mock metodları tanımlar
	 * @param list sorgudan sonra dönecek olan liste
	 */
	private void configureMockMethods(List<Customer> list) {
	    when(query.setParameter(contains("email"), contains(email)))
	        .thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		when(em.createQuery(jql)).thenReturn(query);
    }
	
	/**
	 * Mock metodların çağrıldığından emin olunur
	 */
	private void verifyMockMethods()
	{
		verify(query).setParameter(contains("email"), contains(email));
		verify(query).getResultList();
		verify(em).createQuery(jql);
	}

	/**
	 * Kullanıcı tarafından girilen eposta adresi
	 * geçersizdir. Bilgibankasında bu eposta
	 * adresiyle ilişkili bir hesap bulunamaz.
	 * EMAIL_INVALID status kodu oluşturur
	 * @throws Exception
	 */
	@Test
    public void emailInvalid() throws Exception {
		configureMockMethods(null);
		LoginDaoResult result = dao.findUser(email, password);
		assertEquals(StatusCodes.EMAIL_INVALID, result.getStatus());
		verifyMockMethods();
	}

	/**
	 * Kullanıcı tarafından girilen eposta
     * adresi geçerlidir. Bilgibankasında bu
	 * eposta adresi ile ilişkili bir hesap bulunur.
     * Girilen şifre geçersizdir. PASSWORD_INVALID status
	 * kodu oluşturur.
	 * @throws Exception
	 */
	@Test
    public void passwordInvalid() throws Exception {
		Customer customer = new Customer();
		customer.setEmail(email);
		customer.setPassword("");
		List<Customer> list = new ArrayList<Customer>();
		list.add(customer);
		configureMockMethods(list);
		LoginDaoResult result = dao.findUser(email, password);
		assertEquals(StatusCodes.PASSWORD_INVALID, result.getStatus());
		verifyMockMethods();
    }
	/**
	 * Kullanıcı tarafından girilen eposta
     * ve şifre geçerliidir.
	 * LOGIN_SUCCESSFULL statüs kodu oluşturulur
	 * @throws Exception
	 */
	@Test
    public void loginSuccessfull() throws Exception {
		Customer customer = new Customer();
		customer.setEmail(email);
		customer.setPassword(password);
		List<Customer> list = new ArrayList<Customer>();
		list.add(customer);
		configureMockMethods(list);
	    LoginDaoResult result = dao.findUser(email, password);
		assertEquals(StatusCodes.LOGIN_SUCCESSFULL, result.getStatus());
    }

}
