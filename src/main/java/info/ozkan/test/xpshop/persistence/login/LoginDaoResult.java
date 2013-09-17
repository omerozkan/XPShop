package info.ozkan.test.xpshop.persistence.login;

import info.ozkan.test.xpshop.business.login.StatusCodes;
import info.ozkan.test.xpshop.domain.Customer;

/**
 * bilgibankasından kullanıcı sorgularken
 * üst katmana dönen sonuç nesnesi 
 */
public class LoginDaoResult {
	/**
	 * Status kodu
	 */
	private StatusCodes status;
    /**
	 * Müşteri
	 */
	private Customer costumer;

	public void setStatus(StatusCodes status) {
		this.status = status;
	}

	public StatusCodes getStatus()
	{
		return this.status;
	}

	public Customer getCostumer() {
	    return costumer;
    }

	public void setCostumer(Customer costumer) {
	    this.costumer = costumer;
    }

}
