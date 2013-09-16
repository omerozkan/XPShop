package info.ozkan.test.xpshop.persistence.login;

import info.ozkan.test.xpshop.persistence.login.LoginDaoResult;

public interface LoginDao {

	LoginDaoResult findUser(String email, String password);

}
