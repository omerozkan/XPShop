package info.ozkan.test.xpshop.business.login;

import info.ozkan.test.xpshop.persistence.login.LoginDao;
import info.ozkan.test.xpshop.persistence.login.LoginDaoResult;

public class LoginManagerImpl implements LoginManager {

	private LoginDao loginDao;

	public LoginResult login(String email, String password) {
		LoginDaoResult daoResult = loginDao.findUser(email, password);
		return copyResult(daoResult);
	}

	private LoginResult copyResult(LoginDaoResult daoResult) {
	    LoginResult result = new LoginResult();
	    if(daoResult.getCostumer() != null) {
	    	result.setCustomer(daoResult.getCostumer());
	    }
		result.setStatus(daoResult.getStatus());
	    return result;
    }

	public void setLoginDao(LoginDao dao) {
		this.loginDao = dao;
	}

}
