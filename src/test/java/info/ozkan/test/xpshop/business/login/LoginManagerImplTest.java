package info.ozkan.test.xpshop.business.login;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import info.ozkan.test.xpshop.persistence.login.LoginDao;
import info.ozkan.test.xpshop.persistence.login.LoginDaoResult;

import org.junit.Before;
import org.junit.Test;

public class LoginManagerImplTest {
	/**
	 * Test edilen sınıfın nesnesi
	 */
	private LoginManagerImpl manager = new LoginManagerImpl();
	/**
	 * Dao mock nesnesi
	 */
	private LoginDao dao = mock(LoginDao.class);
	/**
	 * Testlerde kullanılan doğru eposta adresi
	 */
	private String email = "test_email";
	/**
	 * Testlerde kullanılan doğru parola
	 */
	private String password = "test_password";
	/**
	 * LoginDao dan dönecek olan sonuç nesnesi
	 */
	private LoginDaoResult daoResult = new LoginDaoResult();

	@Before
	public void setUp() throws Exception {
		manager.setLoginDao(dao);
	}
	/**
	 * dao mock nesnesinin hangi sonucu
	 * döndereceğini belirler
	 * @param daoResult
	 */
	private void returnDaoResult(LoginDaoResult daoResult) {
		when(dao.findUser(email, password)).thenReturn(daoResult);
	}
    /**
     * metodların çağrıldığından emin ol
     */
	private void verifyMockMethods() {
		verify(dao).findUser(email, password);
	}

	/**
	 * Kullanıcı tarafından girilen eposta adresi
	 * geçersizdir. Bilgibankasında bu eposta
	 * adresiyle ilişkili bir hesap bulunamaz.
	 * Kullanıcıya "Eposta adresiniz geçersiz,
	 * lütfen tekrar ediniz!" hata mesajı
	 * gönderilir
	 * @throws Exception
	 */
	@Test
	public void emailInvalid() throws Exception {
		daoResult.setStatus(StatusCodes.EMAIL_INVALID);
		returnDaoResult(daoResult);
		LoginResult result = manager.login(email, password);
		assertEquals(StatusCodes.EMAIL_INVALID, result.getStatus());
		verifyMockMethods();
	}

	/**
	 * Kullanıcı tarafından girilen eposta adresi
	 * geçerlidir. Bilgibankasında bu epoosta
	 * adresiyle ilişkili bir hesap bulunur.
	 * Kullanıcı tarafından girilen şifre
	 * geçersizdir. Kullancııya "Parola hatalı, lütfen
	 * tekrar deneyiniz" mesajı gönderilir.
	 * @throws Exception
	 */
	@Test
    public void passwordInvalid() throws Exception {
	    daoResult.setStatus(StatusCodes.PASSWORD_INVALID);
	    returnDaoResult(daoResult);
	    LoginResult result = manager.login(email, password);
	    assertEquals(StatusCodes.PASSWORD_INVALID, result.getStatus());
	    verifyMockMethods();
    }

	/**
	 * Kullanıcı tarafından girilen eposta
	 * adresi geçerlidir. Bilgibankasında
	 * bu eposta adresiyle ilişkili bir hesap bulunur.
	 * Kullanıcı tarafından girilen şifre geçerlidir.
	 * Login işlemi gerçekleşir.
	 * @throws Exception
	 */
	@Test
    public void loginSuccess() throws Exception {
	    daoResult.setStatus(StatusCodes.LOGIN_SUCCESSFULL);
	    returnDaoResult(daoResult);
	    LoginResult result = manager.login(email, password);
	    assertEquals(StatusCodes.LOGIN_SUCCESSFULL, result.getStatus());
	    verifyMockMethods();
    }

}
