package VC.client.bz.Impl;

import java.io.IOException;
import java.net.Socket;

import VC.common.LoginMessage;
import VC.common.MessageType;

public class MenuSrvImpl extends ClientSrvImpl{
	
	public MenuSrvImpl() {
		super();
	}
	public MenuSrvImpl(String name, Socket sock) {
		super(name, sock);
	}
	
	public void delLogin(String name) throws IOException, ClassNotFoundException {

		LoginMessage sendmsg = new LoginMessage();
		String type = MessageType.CMD_DEL_LOGIN;
		sendmsg.setID(name);
		sendmsg.setType(type);

		this.SendMessage(sendmsg);

		LoginMessage rcvmsg = new LoginMessage();
		rcvmsg = (LoginMessage) this.ReceiveMessage();
	}
}
