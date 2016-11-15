package net.bis5.samples.jsf.login;

import java.util.function.Consumer;

public interface LoginLogic {

	void doLogin(String usercode, String password, Consumer<Boolean> callback);

	void start();

	void end();

}
