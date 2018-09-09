package VC.client.bz.Impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import VC.client.vo.ClientSrv;
import VC.common.Message;
import VC.common.SocketConstant;

public class ClientSrvImpl implements ClientSrv {
	
	private static final String SERVER_ADDRESS = SocketConstant.SERVER_ADDRESS;
	private static final int SERVER_PORT = SocketConstant.SERVER_PORT;
	
	private Socket socket = null;
	private String Useraccount = null;
	
	public ClientSrvImpl(String user, Socket sock) {
		this.setSocket(sock);
		this.setUseraccount(user);
	}
	
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

	/* (non-Javadoc)
	 * @see VC.client.bz.Impl.ClientSrv#SendMessage(VC.common.Message)
	 */
	@Override
	public void SendMessage(Message sendmsg) throws IOException {
		
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
	
	/* (non-Javadoc)
	 * @see VC.client.bz.Impl.ClientSrv#ReceiveMessage()
	 */
	@Override
	public Message ReceiveMessage() throws IOException, ClassNotFoundException {
		
		Message rcvmsg = new Message();
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		rcvmsg = (Message) ois.readObject();
		
		return rcvmsg;
	}
	
	/* (non-Javadoc)
	 * @see VC.client.bz.Impl.ClientSrv#getSocket()
	 */
	@Override
	public Socket getSocket() {
		return socket;
	}

	/* (non-Javadoc)
	 * @see VC.client.bz.Impl.ClientSrv#setSocket(java.net.Socket)
	 */
	@Override
	public void setSocket(Socket socket) {
		this.socket = socket;
	}


	/* (non-Javadoc)
	 * @see VC.client.bz.Impl.ClientSrv#getUseraccount()
	 */
	@Override
	public String getUseraccount() {
		return Useraccount;
	}

	/* (non-Javadoc)
	 * @see VC.client.bz.Impl.ClientSrv#setUseraccount(java.lang.String)
	 */
	@Override
	public void setUseraccount(String useraccount) {
		Useraccount = useraccount;
	}
	
}
