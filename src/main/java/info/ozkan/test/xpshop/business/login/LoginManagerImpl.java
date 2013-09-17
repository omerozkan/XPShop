package info.ozkan.test.xpshop.business.login;

import info.ozkan.test.xpshop.persistence.login.LoginDao;
import info.ozkan.test.xpshop.persistence.login.LoginDaoResult;
/**
 * LoginManager arayüzünün implementasyonu
 */
public class LoginManagerImpl implements LoginManager {
	/**
	 * Bilgibankası sorguları için
	 * kullanılan dao nesnesi
	 */
	private LoginDao loginDao;

	/**
	 * Login işlemini gerçekleştirir
	 * @param email eposta adresi
	 * @param password parola
	 * @return işlem sonucu
	 */
	public LoginResult login(String email, String password) {
		LoginDaoResult daoResult = loginDao.findUser(email, password);
		return copyResult(daoResult);
	}
	/**
	 * Persistence katmanından aldığı sonucu
	 * LoginResult nesnesine aktarır
	 * @param daoResult Persistence katmanı sonucu
	 * @return LoginResult nesnesi
	 */
	private LoginResult copyResult(LoginDaoResult daoResult) {
	    LoginResult result = new LoginResult();
	    if(daoResult.getCostumer() != null) {
	    	result.setCustomer(daoResult.getCostumer());
	    }
		result.setStatus(daoResult.getStatus());
	    return result;
    }

	/**
	 * Setter
	 * @param dao LoginDao nesnesi
	 */
	public void setLoginDao(LoginDao dao) {
		this.loginDao = dao;
	}

}
