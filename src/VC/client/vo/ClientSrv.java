package VC.client.vo;

import java.io.IOException;
import java.net.Socket;
import VC.common.Message;

/**
 *  ����������Socket��Ϣ,��ǰ�û���Ϣ�Լ����ͺͽ�����Ϣ�ķ��� <br>
 *  ���ڸ���ģ�鹦��ʵ��ʱ�̳�ʹ�� <br>
 * @author song
 *
 */
public interface ClientSrv {

	/**
	 * ������Ϣ�ķ���,Ҫ���͵���Ϣ����Ҫ�̳�Message��
	 * @param sendmsg true
	 */
	void SendMessage(Message sendmsg) throws IOException;

	/**
	 * ������Ϣ�ķ���,���ܵ���ϢΪMessage�� <br>
	 * �ɴ����д��Ĭ�Ͻ���Ϊ��Ӧ����Ϣ <br>
	 */
	Message ReceiveMessage() throws IOException, ClassNotFoundException;

	Socket getSocket();

	void setSocket(Socket socket);

	String getUseraccount();

	void setUseraccount(String useraccount);

}