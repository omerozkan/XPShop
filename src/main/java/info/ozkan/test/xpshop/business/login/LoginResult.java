package info.ozkan.test.xpshop.business.login;

import info.ozkan.test.xpshop.domain.Customer;

/**
 * Orta katmanın login işleminden sonra
 * oluşan sonucu sakladığı model sınıfı
 */
public class LoginResult {
    /**
     * Status kodu
     */
    private StatusCodes status;
    /**
     * Müşteri nesnesi
     */
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
