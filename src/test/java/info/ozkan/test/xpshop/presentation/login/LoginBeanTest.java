package info.ozkan.test.xpshop.presentation.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import info.ozkan.test.xpshop.business.login.LoginManager;
import info.ozkan.test.xpshop.business.login.LoginResult;
import info.ozkan.test.xpshop.business.login.StatusCodes;

import org.junit.Before;
import org.junit.Test;

/**
 * LoginBean JSF ManagedBean testi
 * @author Ömer Özkan
 *
 */
public class LoginBeanTest {

    /**
     * Kullanıcı hoş geldiniz sayfası
     */
    private static final String WELCOME_PAGE = "welcome";

    /**
     * Kullanıcı login sayfası
     */
    private static final String LOGIN_PAGE = "login";

    /**
     * Test edilecek olan nesne
     */
    private LoginBean loginBean = new LoginBean();

    /**
     * LoginBean nesnesinin kullandığı mock nesnesi
     */
    private LoginManager loginManager;
    /**
     * Örnek eposta adresi
     */
    private String email = "test_username";

    /**
     * Örnek parola
     */
    private String password = "test_password";

    /**
     * Mock nesnesini oluşturur
     */
    @Before
    public void setUp() {
        loginManager = mock(LoginManager.class);
        loginBean.setManager(loginManager);
    }

    /**
     * @param result
     *
     */
    private void setMockResult(LoginResult result) {
        when(loginManager.login(email, password)).thenReturn(result);
    }

    /**
     * Kullanıcı eposta adresini ve parolasını girmeden
     * * login butonuna tıklar. Sistem şu hata mesajını verir:
     * * Lütfen eposta adresinizi ve parolanızı giriniz!
     * * @throws Exception
     */
    @Test
    public void loginUsernameAndPasswordEmpty() throws Exception {
        loginBean.setEmail("");
        loginBean.setPassword("");
        assertFailLogin();
        assertEquals(LoginBean.INVALID_LOGIN, loginBean.getErrorMessage());
     }

    /**
	 * login başarısız olduğunda her zaman oluşacak sonuçları
	 * test eder
	 */
	private void assertFailLogin() {
		String url = loginBean.login();
		assertFalse(loginBean.isSuccessfull());
		assertEquals(LOGIN_PAGE, url);
	}

	/**
	 * Kullanıcı eposta adresini girer ama şifresini
	 * girmeden login butonuna tıklar. Sistem şu hata mesajını verir:
	 * Lütfen parolanızı giriniz!
	 * @throws Exception
	 */
	@Test
	public void loginUsernameSuppliedPasswordEmpty() throws Exception {
		loginBean.setEmail(email);
		loginBean.setPassword("");
		assertFailLogin();
		assertEquals(LoginBean.PASSWORD_MISSING,
			loginBean.getErrorMessage());
	}

	/**
	 * Kullanıcı eposta adresini boş bırakır,
	 * şifresini girdikten sonra login butonuna tıklar.
	 * Sistem şu hata mesajını verir:
	 * Lütfen eposta adresinizi giriniz!
	 * @throws Exception
	 */
	@Test
	public void loginUsernameEmptyPasswordSupplied() throws Exception {
		loginBean.setEmail("");
		loginBean.setPassword(password);
		assertFailLogin();
		assertEquals(LoginBean.EMAIL_MISSING,
			loginBean.getErrorMessage());
	}

	/**
	 * Kullanıcı login sayfasına gider.
	 * Eposta adresini ve şifresini girer ve login butonuna tıklar.
	 * Eposta adresi ve şifre doğrudur
	 * Login işlemi gerçekleşir. Üye hoşgeldiniz sayfasına
	 * yönlendirilir
	 * @throws Exception
	 */
	@Test
	public void loginSuccessfull() throws Exception {
		setAccountLogin();

		LoginResult result = new LoginResult();
		result.setStatus(StatusCodes.LOGIN_SUCCESSFULL);
		setMockResult(result);

		String url = loginBean.login();
		assertTrue(loginBean.isSuccessfull());
		assertEquals(WELCOME_PAGE, url);

		verifiyLoginManagerMock();
	}

	/**
	 * Test için kullanılan kullanıcı bilgilerini girer
	 */
	private void setAccountLogin() {
		loginBean.setEmail(email);
		loginBean.setPassword(password);
	}

    /**
	 * loginBean nesnesinin login metodunu çağrıldığı kontrol edilir
	 */
	private void verifiyLoginManagerMock() {
		verify(loginManager).login(email, password);
	}

	/**
	 * Kullanıcı login sayfasına gider.
	 * Email adresi ve şifresini girer ve
	 * login butouna tıklar
	 * Email adresi geçersizdir.
	 * Kullanıcıya email adresiniz gerçersizdir,
	 * lütfen tekrar ediniz hata mesajı gösterilir
	 * @throws Exception
	 */
	@Test
	public void loginEmailInvalid() throws Exception {
		setAccountLogin();

		LoginResult result = new LoginResult();
		result.setStatus(StatusCodes.EMAIL_INVALID);

		setMockResult(result);

		assertFailLogin();
		assertEquals(LoginBean.EMAIL_INVALID,
			loginBean.getErrorMessage());
		verifiyLoginManagerMock();
	}

	/**
	 * Kullanıcı login sayfasına gider.
	 * Email adresini ve şifresini girer
	 * ve login butonunta tıklar.
	 * Eposta adresi geçerlidir fakat parola geçersizdir.
	 * Parola hatalı, lütfen tekrar giriniz hata mesajı
	 * gösterilir
	 * @throws Exception
	 */
	@Test
	public void loginPasswordInvalid() throws Exception {
		setAccountLogin();

		LoginResult result = new LoginResult();
		result.setStatus(StatusCodes.PASSWORD_INVALID);

		setMockResult(result);

		assertFailLogin();
		assertEquals(LoginBean.PASSWORD_INVALID,
			loginBean.getErrorMessage());

		verifiyLoginManagerMock();
	}

}
