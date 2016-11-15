package net.bis5.samples.jsf.login;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Consumer;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;

@ConversationScoped
// XXX たぶんOAuthとかつかうとConversationがもっと有効的になる
public class DefaultLoginLogic implements LoginLogic, Serializable {

	/** $Comment$ */
	private static final long serialVersionUID = 1L;
	@Inject
	Conversation conversation;

	/**
	 * @see net.bis5.samples.jsf.login.LoginLogic#doLogin(java.lang.String,
	 *      java.lang.String, java.util.function.Consumer)
	 */
	@Override
	public void doLogin(String usercode, String password, Consumer<Boolean> callback) {
		// XXX This is sample!!!
		callback.accept(Objects.equals(usercode, password));

	}

	/**
	 * @see net.bis5.samples.jsf.login.LoginLogic#start()
	 */
	@Override
	public void start() {
		if (!conversation.isTransient()) {
			conversation.begin();
		}
	}

	/**
	 * @see net.bis5.samples.jsf.login.LoginLogic#end()
	 */
	@Override
	public void end() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

}
