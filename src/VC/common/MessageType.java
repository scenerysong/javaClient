package VC.common;

public interface MessageType {
	// 消息类型分为三类：命令CMD---数据DAT---结果RST

	// General
	String GENERAL_BOOK = "GENERAL_BOOK";

	// Command
	String CMD_QUY_BOOK_BOOKNAME = "CMD_QUY_BOOK_BOOKNAME";
	String CMD_JUDGE_LOGIN = "CMD_JUDGE_LOGIN";
	String CMD_GET_ALL_GOODS = "CMD_GET_ALL_GOODS";
	String CMD_GET_ALL_COURSE = "CMD_GET_ALL_COURSE";
	String CMD_REGIS_LOGIN = "CMD_REGIS_LOGIN";
	String CMD_DEL_LOGIN = "CMD_DEL_LOGIN";
	String CMD_LOGOUT = "CMD_LOGOUT";
	
	// Data
	String DAT_QUY_BOOK_BOOKNAME = "DAT_QUY_BOOK_BOOKNAME";

	// Result
	String RST_SUCCESS = "RST_SUCCESS";
	String RST_FAILURE = "RST_FAILURE";

	// COURSE
	String CMD_ADD_ALL_COURSE = "CMD_ADD_ALL_COURSE";
	String CMD_DELETE_ALL_COURSE = "CMD_DELETE_ALL_COURSE";
	String CMD_GET_ALL_MYCOURSE = "CMD_GET_ALL_MYCOURSE";

	// Library
	String CMD_GET_ALL_BOOK = "CMD_GET_ALL_BOOK";
	String CMD_ADD_ALL_BOOK = "CMD_ADD_ALL_BOOK";
	String CMD_DELETE_ALL_BOOK = "CMD_DELETE_ALL_BOOK";
	String CMD_GET_ALL_MYBOOK = "CMD_GET_ALL_MYBOOK";

	// Store
	String CMD_BUY_ALL_GOODS = "CMD_BUY_ALL_GOODS";
	String CMD_ADD_ALL_GOODS = "CMD_ADD_ALL_GOODS";
	String CMD_GET_ALL_MYGOODS = "CMD_GET_ALL_MYGOODS";
	
	//STU
	String CMD_GET_MY_USER = "CMD_GET_MY_USER";
	String CMD_QUY_USER = "CMD_QUY_USER";
	String CMD_UPDATE_MY_USER = "CMD_UPDATE_MY_USER";
}
