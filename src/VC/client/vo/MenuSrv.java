package VC.client.vo;

import java.io.IOException;

/**
 * 
 * @author song
 * 主界面功能,用于补充登录之后的基本功能,继承ClientSrv方法
 */
public interface MenuSrv {

	/**
	 * 注销用户,将用户所有数据删除
	 * @param name
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void delLogin(String name) throws IOException, ClassNotFoundException;

}