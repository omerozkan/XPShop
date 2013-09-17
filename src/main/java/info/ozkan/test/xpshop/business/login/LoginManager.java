package info.ozkan.test.xpshop.business.login;
/**
 * Login işlemini sağlar
 */
public interface LoginManager {
	/**
	 * Login işlemini gerçekleştirir
	 * @param email eposta adresi
	 * @param password parola
	 * @return işlem sonucu
	 */
    LoginResult login(String email, String password);
}
