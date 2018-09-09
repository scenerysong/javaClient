package VC.client.bz.Impl;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import VC.client.vo.StuSrv;
import VC.common.MessageType;
import VC.common.User;
import VC.common.UserMessage;

public class StuSrvImpl extends ClientSrvImpl implements StuSrv{

	public StuSrvImpl() {
		super();
	}
	public StuSrvImpl(String username) {
		super(username);
	}
	public StuSrvImpl(String username, Socket socket) {
		super(username, socket);
	}
	
	/* (non-Javadoc)
	 * @see VC.client.bz.Impl.StuSrv#getMyInfo()
	 */
	@Override
	public User getMyInfo() throws IOException, ClassNotFoundException {
		
		User myUser = new User();
		String type = MessageType.CMD_GET_MY_USER;
		UserMessage sendmsg = new UserMessage();
		sendmsg.setType(type);
		sendmsg.setID(getUseraccount());
		sendmsg.setUsername(getUseraccount());

		this.SendMessage(sendmsg);

		UserMessage rcvmsg = new UserMessage();
		rcvmsg = (UserMessage) this.ReceiveMessage();
		myUser = rcvmsg.getUser();

		return myUser;
	}
	
	/* (non-Javadoc)
	 * @see VC.client.bz.Impl.StuSrv#updateMyInfo(VC.common.User)
	 */
	@Override
	public boolean updateMyInfo(User usr) throws IOException, ClassNotFoundException {
		
		String type = MessageType.CMD_UPDATE_MY_USER;
		UserMessage sendmsg = new UserMessage();
		sendmsg.setType(type);
		sendmsg.setUser(usr);
		sendmsg.setID(getUseraccount());
		sendmsg.setUsername(getUseraccount());
		
		this.SendMessage(sendmsg);
		
		UserMessage rcvmsg = new UserMessage();
		rcvmsg = (UserMessage) this.ReceiveMessage();
		boolean res = false;
		res = rcvmsg.isRes();
		
		return res;
	}
	
/* (non-Javadoc)
 * @see VC.client.bz.Impl.StuSrv#getPersonInfo(java.lang.String)
 */
@Override
public User getPersonInfo(String usr) throws IOException, ClassNotFoundException {
		
		User myUser = new User();
		String type = MessageType.CMD_GET_MY_USER;
		UserMessage sendmsg = new UserMessage();
		sendmsg.setType(type);
		sendmsg.setID(getUseraccount());
		sendmsg.setUsername(usr);
		
		
		this.SendMessage(sendmsg);

		UserMessage rcvmsg = new UserMessage();
		rcvmsg = (UserMessage) this.ReceiveMessage();
		myUser = rcvmsg.getUser();

		return myUser;
	}
}
