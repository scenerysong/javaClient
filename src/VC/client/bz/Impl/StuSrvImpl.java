package VC.client.bz.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import VC.common.MessageType;
import VC.common.User;
import VC.common.UserMessage;

public class StuSrvImpl extends ClientSrvImpl{

	public StuSrvImpl() {
		super();
	}
	public StuSrvImpl(String username) {
		super(username);
	}
	
	public User getMyInfo() throws IOException, ClassNotFoundException {
		
		User myUser = new User();
		String type = MessageType.CMD_GET_MY_USER;
		UserMessage sendmsg = new UserMessage();
		sendmsg.setType(type);
		sendmsg.setID(getUseraccount());

		this.SendMessage(sendmsg);

		UserMessage rcvmsg = new UserMessage();
		rcvmsg = (UserMessage) this.ReceiveMessage();
		myUser = rcvmsg.getUser();

		return myUser;
	}
	
	public boolean updateMyInfo(User usr) throws IOException, ClassNotFoundException {
		
		String type = MessageType.CMD_UPDATE_MY_USER;
		UserMessage sendmsg = new UserMessage();
		sendmsg.setType(type);
		sendmsg.setUser(usr);
		
		this.SendMessage(sendmsg);
		
		UserMessage rcvmsg = new UserMessage();
		rcvmsg = (UserMessage) this.ReceiveMessage();
		boolean res = false;
		res = rcvmsg.isRes();
		
		return res;
	}
	
public User getPersonInfo(String usr) throws IOException, ClassNotFoundException {
		
		User myUser = new User();
		String type = MessageType.CMD_GET_MY_USER;
		UserMessage sendmsg = new UserMessage();
		sendmsg.setType(type);
		sendmsg.setID(usr);

		this.SendMessage(sendmsg);

		UserMessage rcvmsg = new UserMessage();
		rcvmsg = (UserMessage) this.ReceiveMessage();
		myUser = rcvmsg.getUser();

		return myUser;
	}
}
