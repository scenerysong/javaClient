package VC.client.vo;

import java.io.IOException;

import VC.common.User;

/**
 * 
 * @author song
 *学生学籍功能实现,继承ClientSrv类
 */
public interface StuSrv {

	/**
	 * 得到父类当前内置用户所有信息
	 * @return User
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	User getMyInfo() throws IOException, ClassNotFoundException;

	/**
	 * 按照给定的所有信息,更新当前用户所有信息
	 * @param usr
	 * @return 是否更新成功
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	boolean updateMyInfo(User usr) throws IOException, ClassNotFoundException;

	/**
	 * 按用户名得到某个用户的所有信息
	 * @param usr
	 * @return User
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	User getPersonInfo(String usr) throws IOException, ClassNotFoundException;

}