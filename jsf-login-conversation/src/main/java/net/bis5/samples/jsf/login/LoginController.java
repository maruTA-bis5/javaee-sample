package net.bis5.samples.jsf.login;

import java.io.IOException;
import java.io.Serializable;
import java.io.UncheckedIOException;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

@RequestScoped
@Named
public class LoginController implements Serializable {

	/** $Comment$ */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private LoginInfo info = new LoginInfo();
	{
		info.setUsercode("user");
	}

	@Inject
	@Getter
	@Setter
	LoginLogic logic;

	@Getter
	@Setter
	private String message;

	private static final String KEY_MESSAGE = "message";

	@PostConstruct
	public void initialize() {
		Optional<String> message = Optional.ofNullable(
				(String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get(KEY_MESSAGE));
		message.ifPresent(this::setMessage);
	}

	public void doLogin() {
		logic.start();
		logic.doLogin(info.getUsercode(), info.getPassword(), this::loginCallback);
	}

	public void loginCallback(boolean isSuccess) {
		String msg;
		if (isSuccess) {
			msg = "Login Success.";
		} else {
			msg = "LOGIN FAIL!!!";
		}
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put(KEY_MESSAGE, msg);
		try {
			logic.end();
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml?faces-redirect=true");
		} catch (IOException ex) {
			throw new UncheckedIOException(ex);
		}
	}

}
