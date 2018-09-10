package VC.client.vo;

import java.io.IOException;

/**
 * 
 * @author song
 * 登录时的功能,继承ClientSrv类
 */
public interface LoginSrv {

	/**
	 * 判断是否登录成功,向服务器发送账户密码,服务器进行判断比较
	 * @param name
	 * @param passwd
	 * @return 账户密码是否正确
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	boolean judgeLogin(String name, String passwd) throws IOException, ClassNotFoundException;

	/**
	 * 用户注册功能,根据授权码与后端比较的结果来判断是否拥有管理员权限
	 * @param name
	 * @param passwd
	 * @param admincode
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void register(String name, String passwd, String admincode) throws IOException, ClassNotFoundException;

}