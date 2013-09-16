package info.ozkan.test.xpshop.persistence.login;

import info.ozkan.test.xpshop.business.login.StatusCodes;
import info.ozkan.test.xpshop.domain.Customer;

public class LoginDaoResult {

	private StatusCodes status;
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
