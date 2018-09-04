package VC.client.bz.Impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import VC.common.Message;
import VC.common.SocketConstant;

public class ClientSrvImpl {
	
	private static final String SERVER_ADDRESS = SocketConstant.SERVER_ADDRESS;
	private static final int SERVER_PORT = SocketConstant.SERVER_PORT;
	
	private Socket socket = null;
	private String Useraccount = null;
	
	public ClientSrvImpl(String user) {
		
		this.setUseraccount(user);
		
		try {
			this.setSocket(new Socket(SERVER_ADDRESS, SERVER_PORT));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
			
	public ClientSrvImpl() {
		try {
			this.setSocket(new Socket(SERVER_ADDRESS, SERVER_PORT));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void SendMessage(Message sendmsg) throws IOException {
		
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
	
	public Message ReceiveMessage() throws IOException, ClassNotFoundException {
		
		Message rcvmsg = new Message();
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		rcvmsg = (Message) ois.readObject();
		
		return rcvmsg;
	}
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}


	public String getUseraccount() {
		return Useraccount;
	}

	public void setUseraccount(String useraccount) {
		Useraccount = useraccount;
	}
	
}
