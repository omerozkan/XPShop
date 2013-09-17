package info.ozkan.test.xpshop.persistence.login;

import info.ozkan.test.xpshop.business.login.StatusCodes;
import info.ozkan.test.xpshop.domain.Customer;

import java.util.List;

import javax.persistence.EntityManager;


public class LoginDaoImpl implements LoginDao {

	private EntityManager em;

	public LoginDaoResult findUser(String email, String password) {
		LoginDaoResult result = new LoginDaoResult();
		List<Customer> list = em.createQuery("from Customer c where c.email = :email")
		  .setParameter("email", email)
		  .getResultList();
		
		if(checkEmail(list)) {
			result.setStatus(StatusCodes.EMAIL_INVALID);
			return result;
		}
	    
		Customer customer = list.get(0);
		
		if(checkPassword(password, customer))
		{
			result.setStatus(StatusCodes.PASSWORD_INVALID);
			return result;
		}
		//email ve şifre doğru kullanıcı başarıyla giriş yapar
		loginSuccessfull(result, customer);
		return result;
	}

	private boolean checkPassword(String password, Customer customer) {
	    return !password.equals(customer.getPassword());
    }

	private boolean checkEmail(List<Customer> list) {
	    return list == null || list.size() == 0;
    }

	private void loginSuccessfull(LoginDaoResult result, Customer customer) {
	    result.setCostumer(customer);
		result.setStatus(StatusCodes.LOGIN_SUCCESSFULL);
    }

	public void setEm(EntityManager em) {
		this.em = em;
    }

}
