package VC.client.vo;

import java.io.IOException;

public interface LoginSrv {

	boolean judgeLogin(String name, String passwd) throws IOException, ClassNotFoundException;

	void register(String name, String passwd, String admincode) throws IOException, ClassNotFoundException;

}