package VC.client.bz.Impl;

import java.io.IOException;

import org.jasypt.util.text.BasicTextEncryptor;

import VC.client.vo.LoginSrv;
import VC.common.LoginMessage;
import VC.common.MessageType;

public class LoginSrvImpl extends ClientSrvImpl implements LoginSrv {

	public LoginSrvImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see VC.client.bz.Impl.LoginSrv#judgeLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean judgeLogin(String name, String passwd) throws IOException, ClassNotFoundException {

		boolean ans = false;
		LoginMessage sendmsg = new LoginMessage();
		String type = MessageType.CMD_JUDGE_LOGIN;
		sendmsg.setID(name);
		sendmsg.setType(type);
		BasicTextEncryptor encryptorText = new BasicTextEncryptor();
		sendmsg.setPasswd(encryptorText.decrypt(passwd));

		this.SendMessage(sendmsg);

		LoginMessage rcvmsg = new LoginMessage();
		rcvmsg = (LoginMessage) this.ReceiveMessage();
		ans = rcvmsg.isLoginstat();

		return ans;
	}

	/* (non-Javadoc)
	 * @see VC.client.bz.Impl.LoginSrv#register(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void register(String name, String passwd, String admincode) throws IOException, ClassNotFoundException {

		LoginMessage sendmsg = new LoginMessage();
		String type = MessageType.CMD_REGIS_LOGIN;
		sendmsg.setID(name);
		sendmsg.setType(type);
		BasicTextEncryptor encryptorText = new BasicTextEncryptor();
		sendmsg.setPasswd(encryptorText.decrypt(passwd));
		sendmsg.setAdmincode(encryptorText.decrypt(admincode));

		this.SendMessage(sendmsg);

		LoginMessage rcvmsg = new LoginMessage();
		rcvmsg = (LoginMessage) this.ReceiveMessage();
	}

}
