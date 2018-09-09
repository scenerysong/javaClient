package VC.client.vo;

import java.io.IOException;
import java.net.Socket;
import VC.common.Message;

/**
 *  包含基本的Socket信息,当前用户信息以及发送和接受消息的方法 <br>
 *  用于各个模块功能实现时继承使用 <br>
 * @author song
 *
 */
public interface ClientSrv {

	/**
	 * 发送消息的方法,要发送的消息至少要继承Message类
	 * @param sendmsg true
	 */
	void SendMessage(Message sendmsg) throws IOException;

	/**
	 * 接受消息的方法,接受的信息为Message类 <br>
	 * 由代码编写者默认解析为相应的信息 <br>
	 */
	Message ReceiveMessage() throws IOException, ClassNotFoundException;

	Socket getSocket();

	void setSocket(Socket socket);

	String getUseraccount();

	void setUseraccount(String useraccount);

}