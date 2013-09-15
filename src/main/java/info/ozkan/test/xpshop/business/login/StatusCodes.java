package info.ozkan.test.xpshop.business.login;

public enum StatusCodes {

	LOGIN_SUCCESSFULL(0),
	EMAIL_INVALID(1), 
	PASSWORD_INVALID(2);
	private int value;
	
	private StatusCodes(int value)
	{
		this.setValue(value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
