package VC.client.bz.Impl;

import java.io.IOException;

import VC.common.LoginMessage;
import VC.common.MessageType;

public class LoginSrvImpl extends ClientSrvImpl {

	public LoginSrvImpl() {
		super();
	}

	public boolean judgeLogin(String name, String passwd) throws IOException, ClassNotFoundException {

		boolean ans = false;
		LoginMessage sendmsg = new LoginMessage();
		String type = MessageType.CMD_JUDGE_LOGIN;
		sendmsg.setID(name);
		sendmsg.setType(type);
		sendmsg.setPasswd(passwd);

		this.SendMessage(sendmsg);

		LoginMessage rcvmsg = new LoginMessage();
		rcvmsg = (LoginMessage) this.ReceiveMessage();
		ans = rcvmsg.isLoginstat();

		return ans;
	}

	public void register(String name, String passwd, String admincode) throws IOException, ClassNotFoundException {

		LoginMessage sendmsg = new LoginMessage();
		String type = MessageType.CMD_REGIS_LOGIN;
		sendmsg.setID(name);
		sendmsg.setType(type);
		sendmsg.setPasswd(passwd);
		sendmsg.setAdmincode(admincode);

		this.SendMessage(sendmsg);

		LoginMessage rcvmsg = new LoginMessage();
		rcvmsg = (LoginMessage) this.ReceiveMessage();
	}

}
