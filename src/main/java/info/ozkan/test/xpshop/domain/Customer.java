package info.ozkan.test.xpshop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Müşteri model sınıfı
 */
@Entity
@Table(name="CUSTOMER")
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	@Column(name="email")
    private String email;
	@Column(name="password")
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
	/**
	 * 
	 * @return
	 */
	public Long getId() {
	    return id;
    }
	/**
	 * 
	 * @param id
	 */
	public void setId(Long id) {
	    this.id = id;
    }
}
