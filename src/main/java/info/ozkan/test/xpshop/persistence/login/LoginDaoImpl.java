package info.ozkan.test.xpshop.persistence.login;

import info.ozkan.test.xpshop.business.login.StatusCodes;
import info.ozkan.test.xpshop.domain.Customer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * LoginDao implementasyonu
 */
@Repository
public class LoginDaoImpl implements LoginDao {

	/**
	 * JPA EM nesnesi
	 */
	private EntityManager em;

	/**
	 * Girilen eposta ve parola ile
	 * kullanıcıyı arar
	 * @param email eposta adresi
	 * @param password parola
	 * @return LoginDaoResult nesnesi
	 */
	@Transactional
	public LoginDaoResult findUser(String email, String password) {
		LoginDaoResult result = new LoginDaoResult();
		List<Customer> list = em
		        .createQuery("from Customer c where c.email = :email")
		        .setParameter("email", email).getResultList();

		if (checkEmail(list)) {
			result.setStatus(StatusCodes.EMAIL_INVALID);
			return result;
		}

		Customer customer = list.get(0);

		if (checkPassword(password, customer)) {
			result.setStatus(StatusCodes.PASSWORD_INVALID);
			return result;
		}
		// email ve şifre doğru kullanıcı başarıyla giriş yapar
		loginSuccessfull(result, customer);
		return result;
	}
	
	/**
	 * Parolayı kontrol eder
	 * @param password parola
	 * @param customer müşteri nesnesi
	 * @return parolalar eşit değilse true döner
	 */
	private boolean checkPassword(String password, Customer customer) {
		return !password.equals(customer.getPassword());
	}
	/*
	 * Eposta adresini kontrol eder
	 */
	private boolean checkEmail(List<Customer> list) {
		return list == null || list.size() == 0;
	}
	/**
	 * eposta ve şifre doğru olduğunda result
	 * nesine gerekli değerleri inject eder
	 * @param result
	 * @param customer
	 */
    private void loginSuccessfull(LoginDaoResult result, Customer customer) {
		result.setCostumer(customer);
		result.setStatus(StatusCodes.LOGIN_SUCCESSFULL);
	}

	/**
	 * set EntityManager
	 * @param em entityManager
	 */
    @PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}

}
