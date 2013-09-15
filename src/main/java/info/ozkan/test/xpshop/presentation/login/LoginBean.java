package info.ozkan.test.xpshop.presentation.login;

import info.ozkan.test.xpshop.business.login.LoginManager;
import info.ozkan.test.xpshop.business.login.LoginResult;
import info.ozkan.test.xpshop.business.login.StatusCodes;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="loginBean")
public class LoginBean {

	/**
	 * Eposta ve parola bilgisi boş girildiğinde
	 * gösterilecek hata mesajı
	 */
	protected static final String INVALID_LOGIN =
			"Lütfen eposta adresinizi ve parolanızı giriniz!";
	/**
	 * Eposta adresi boş girilip, parola bilgisi
	 * girildiğinde gösterilecek hata mesajı
	 */
	protected static final String EMAIL_MISSING =
			"Lütfen eposta adresinizi giriniz!";

	/**
	 * Eposta adresi girilip, parola boş bırakıldığında
	 * gösterilecek hata mesajı
	 */
	protected static final String PASSWORD_MISSING =
			"Lütfen parolanızı giriniz!";
	/**
	 * Eposta adresi geçersiz olduğunda gösterilecek olan
	 * hata mesajı
	 */
	protected static final String EMAIL_INVALID =
		"Eposta adresiniz geçersizdir! Lütfen tekrar giriniz.";
	/**
	 * Eposta adresi geçerli fakat parola geçersiz olduğunda
	 * gösterilecek olan hata mesajı
	 */
	protected static final String PASSWORD_INVALID =
			"Parola hatalı, lütfen tekrar deneyiniz!";

	/**
	 * LoginManager business katmanı nesnesi
	 */
	private LoginManager manager;
	/**
	 * Eposta bilgisi
	 */
	private String email;
	/**
	 * Parola bilgisi
	 */
	private String password;
	/**
	 * Hata mesajı
	 */
	private String errorMessage = "";

	public void setManager(LoginManager manager) {
		this.manager = manager;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isSuccessfull() {
		return errorMessage.isEmpty(); 
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}
	/*
	 * login işlemini gerçekleştirecek olan metod
	 * @return
	 */
	public String login() {
		if (email.isEmpty() && password.isEmpty())
		{
			errorMessage = INVALID_LOGIN;
		}
		else if (password.isEmpty())
		{
			errorMessage = PASSWORD_MISSING;
		}
		else if (email.isEmpty())
		{
			errorMessage = EMAIL_MISSING;
		}
		else
		{
			LoginResult result = manager.login(email, password);
			if (result.getStatus().equals(
					StatusCodes.LOGIN_SUCCESSFULL)) {
				return "welcome";
			}
			else if (result.getStatus().equals(
					StatusCodes.EMAIL_INVALID)) {
				errorMessage = EMAIL_INVALID;
			}
			else if (result.getStatus().equals(
					StatusCodes.PASSWORD_INVALID)) {
				errorMessage = PASSWORD_INVALID;
			}
		}
		return "login";
	}

}
