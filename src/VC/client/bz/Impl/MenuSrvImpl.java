package VC.client.bz.Impl;

import java.io.IOException;
import java.net.Socket;

import VC.client.vo.MenuSrv;
import VC.common.LoginMessage;
import VC.common.MessageType;

public class MenuSrvImpl extends ClientSrvImpl implements MenuSrv{
	
	public MenuSrvImpl() {
		super();
	}
	public MenuSrvImpl(String name, Socket sock) {
		super(name, sock);
	}
	
	/* (non-Javadoc)
	 * @see VC.client.bz.Impl.MenuSrv#delLogin(java.lang.String)
	 */
	@Override
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
