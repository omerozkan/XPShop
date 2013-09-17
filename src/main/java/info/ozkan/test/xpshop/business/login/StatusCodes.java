package info.ozkan.test.xpshop.business.login;
/**
 * Login işleminden sonra üretilecek status kodları
 */
public enum StatusCodes {
	/**
	 * Login işlemi başarılı
	 */
	LOGIN_SUCCESSFULL(0),
	/**
	 * Eposta adresi geçersiz
	 */
	EMAIL_INVALID(1), 
	/**
	 * Parola geçersiz
	 */
	PASSWORD_INVALID(2);
	/**
	 * Status kodunun integer değeri
	 */
	private int value;

	/**
	 * Status kod
	 * @param value
	 */
	private StatusCodes(int value)
	{
		this.value = value;
	}

    /**
	 * integer Değeri
	 * @return integer değer
	 */
	public int getValue() {
		return value;
	}

}
