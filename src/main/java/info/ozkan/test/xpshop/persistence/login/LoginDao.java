package info.ozkan.test.xpshop.persistence.login;

import info.ozkan.test.xpshop.persistence.login.LoginDaoResult;

/**
 * Bilgibankasından müşteri bilgilerini
 * sorgulayan dao nesnesi
 */
public interface LoginDao {
	/**
	 * Girilen eposta ve parola ile
	 * kullanıcıyı arar
	 * @param email eposta adresi
	 * @param password parola
	 * @return LoginDaoResult nesnesi
	 */
    LoginDaoResult findUser(String email, String password);

}
