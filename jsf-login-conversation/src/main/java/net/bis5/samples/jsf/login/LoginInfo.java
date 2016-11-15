package net.bis5.samples.jsf.login;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginInfo implements Serializable {

	/** $Comment$ */
	private static final long serialVersionUID = 1L;
	private String usercode;
	private String password;

}
