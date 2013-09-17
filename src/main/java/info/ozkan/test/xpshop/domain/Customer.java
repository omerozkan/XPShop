package info.ozkan.test.xpshop.domain;
/**
 * Müşteri model sınıfı
 */
public class Customer {
    private String email;
    private String password;

    /**
     * Eposta adresi
     * @return eposta adresi
     */
	public String getEmail() {
	    return email;
    }
	/**
	 * Eposta adresi
	 * @param email eposta adresi
	 */
	public void setEmail(String email) {
	    this.email = email;
    }
	/**
	 *
	 * @return parola
	 */
	public String getPassword() {
	    return password;
    }
    /**
	 * parola
	 * @param password
	 */
	public void setPassword(String password) {
	    this.password = password;
    }
}
