package info.ozkan.test.xpshop.business.login;

import info.ozkan.test.xpshop.domain.Customer;
import info.ozkan.test.xpshop.presentation.login.StatusCodes;

public class LoginResult {
	private StatusCodes status;
	private Customer customer;
	
	public StatusCodes getStatus() {
		return status;
	}
	public void setStatus(StatusCodes status) {
		this.status = status;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
