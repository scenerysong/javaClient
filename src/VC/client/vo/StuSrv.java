package VC.client.vo;

import java.io.IOException;

import VC.common.User;

public interface StuSrv {

	User getMyInfo() throws IOException, ClassNotFoundException;

	boolean updateMyInfo(User usr) throws IOException, ClassNotFoundException;

	User getPersonInfo(String usr) throws IOException, ClassNotFoundException;

}