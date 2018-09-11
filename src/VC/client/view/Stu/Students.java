package VC.client.view.Stu;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;

import VC.client.bz.Impl.StuSrvImpl;
import VC.client.vo.StuSrv;
import VC.common.User;
import javafx.application.Application;
import javafx.application.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.beans.*;
import javafx.scene.control.ChoiceBox;

public class Students extends Application {
	private Label tip = new Label("欢迎使用虚拟校园1.0");
	private TextField account = new TextField();
	private PasswordField password = new PasswordField();
	private Button back = new Button("返回");
	private Button rewrite = new Button("修改信息");
	private Button search = new Button("查询");// 使用的按钮

	private TextField name = new TextField("名字");
	private TextField sex = new TextField("性别");
	private TextField study = new TextField("学籍");
	private TextField birthday = new TextField("生日");
	private FlowPane pane = new FlowPane();
	private Button b1 = new Button("确		认");
	private Button b2 = new Button("取		消");
	private String usrname;
	private Socket socket;
	
	public StuSrv stusrv;
	public Students() {
		super();
	}
	public Students(String pusrname, Socket psocket) {
		super();
		this.setUsrname(pusrname);
		this.setSocket(psocket);
	}
	
	public void start(Stage login) {

		// TO DO:add the request to get the initial information
		
		stusrv = new StuSrvImpl(this.getUsrname(),this.getSocket());
		User myinfo = new User();
		try {
			myinfo = stusrv.getMyInfo();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(myinfo.getPersonname());
		name = new TextField(myinfo.getPersonname());
		sex = new TextField(myinfo.getSex());
		study = new TextField(myinfo.getRace());
		birthday = new TextField(myinfo.getBirthday());
		
		
		pane.setPadding(new Insets(11,12,13,14)); pane.setHgap(30); pane.setVgap(10); 
		pane.getChildren().add(new Label("名字")); pane.getChildren().addAll(name);
		pane.getChildren().add(new Label("性别")); pane.getChildren().addAll(sex);
		pane.getChildren().add(new Label("学籍")); pane.getChildren().addAll(study);
		pane.getChildren().add(new Label("生日")); pane.getChildren().addAll(birthday);
		
		password.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER)
				backAction();
		});

		//back.setOnAction(e -> backAction());
		//pane.getChildren().addAll(back);// 点击返回

		rewrite.setOnAction(e -> rewriteAction());
		pane.getChildren().addAll(rewrite);// 点击修改信息

		search.setOnAction(e -> searchAction());
		pane.getChildren().addAll(search);// 点击查询

		name.setEditable(false);
		sex.setEditable(false);
		study.setEditable(false);
		birthday.setEditable(false);// 将学籍初始界面信息固定

		tip.setMinWidth(200);
		tip.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(tip);

		Scene scene = new Scene(pane, 280, 200);
		login.setTitle("虚拟校园系统1.0");
		login.setScene(scene);
		login.setResizable(false);
		login.show();
	}

	private void backAction() {

	}// 返回菜单

	private TextField newaccount = new TextField();// 姓名

	ChoiceBox cb1 = new ChoiceBox(FXCollections.observableArrayList("男", "女"));

	ChoiceBox cb2 = new ChoiceBox(FXCollections.observableArrayList("汉族", "其他"));

	ChoiceBox cb3 = new ChoiceBox(FXCollections.observableArrayList("1990", "1991", "1992", "1993", "1994", "1995",
			"1996", "1997", "1998", "1999", "2000", "2001", "2002"));

	ChoiceBox cb4 = new ChoiceBox(
			FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"));

	ChoiceBox cb5 = new ChoiceBox(FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
			"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
			"29", "30", "31"));

	// 使用组合框
	
	private Stage Sign = new Stage();
	private Label signtip = new Label("修改您的学籍信息");

	private void rewriteAction() {
		GridPane s = new GridPane();

		account.setEditable(false);
		password.setEditable(false);
		account.setText(null);
		password.setText(null);
		tip.setText("欢迎使用虚拟校园1.0");
		back.setOnAction(null);
		rewrite.setOnAction(null);
		search.setOnAction(null);

		s.setPadding(new Insets(11, 12, 13, 14));
		s.setHgap(15);
		s.setVgap(10);

		s.add(new Label("姓  名"), 0, 0);

		s.add(newaccount, 1, 0);

		s.add(new Label("性  别"), 0, 1);

		s.add(cb1, 1, 1);

		s.add(new Label("籍  贯"), 0, 2);

		s.add(cb2, 1, 2);

		s.add(new Label("生  日"), 0, 3);

		s.add(cb3, 1, 3);

		s.add(cb4, 1, 4);

		s.add(cb5, 1, 5);

		

		b1.setOnAction(e -> SignAction());
		s.add(b1, 1, 6);
		b2.setOnAction(e -> sexitAction());
		s.add(b2, 1, 7);

		signtip.setAlignment(Pos.CENTER);
		s.add(signtip, 1, 8);

		Sign.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent event) {
				newaccount.setText(null);

				account.setEditable(true);
				password.setEditable(true);
				back.setOnAction(e -> backAction());
				rewrite.setOnAction(e -> rewriteAction());
				search.setOnAction(e -> searchAction());
			}
		});

		Scene sscene = new Scene(s, 300, 370);
		Sign.setTitle("虚拟校园系统1.0-学籍管理");
		Sign.setScene(sscene);
		Sign.setResizable(false);
		Sign.setAlwaysOnTop(true);
		Sign.show();
	}

	// 确定修改的条件
	private void SignAction() {

		/*
		String test = (String) cb1.getValue();
		System.out.println(test);
		*/
		// add the server
		User person = new User();
		person.setUsername(this.usrname);
		person.setPersonname((String) newaccount.getText());
		person.setSex((String) cb1.getValue());
		person.setRace((String) cb2.getValue());
		String birth = (String)cb3.getValue() + "/" + (String)cb4.getValue() + "/" + (String)cb5.getValue();
		System.out.print(birth);
		person.setBirthday(birth);
		
		stusrv = new StuSrvImpl(this.getUsrname(),this.getSocket());
		try {
			stusrv.updateMyInfo(person);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		tip.setText("修改成功!");
		sexitAction();
		signtip.setText("修改您的学籍信息");
		newaccount.setText(null);

	}

	private void sexitAction() {
		Sign.close();
		// signtip.setText("1111");
		newaccount.setText(null);

		account.setEditable(true);
		password.setEditable(true);
		back.setOnAction(e -> backAction());
		rewrite.setOnAction(e -> rewriteAction());
		search.setOnAction(e -> searchAction());
	}

	private TextField number = new TextField();
	private TextField License = new TextField();
	private PasswordField Newpassword = new PasswordField();
	private PasswordField Newpassword1 = new PasswordField();
	private Stage Forget = new Stage();
	private Label searchtip = new Label("查询学生学籍");

	private void searchAction() {
		GridPane f = new GridPane();

		account.setEditable(false);
		password.setEditable(false);
		account.setText(null);
		password.setText(null);
		tip.setText("欢迎使用虚拟校园1.0");
		back.setOnAction(null);
		rewrite.setOnAction(null);
		search.setOnAction(null);

		f.setPadding(new Insets(11, 12, 13, 14));
		f.setHgap(15);
		f.setVgap(10);

		f.add(new Label("输入用户名"), 0, 0);

		f.add(number, 1, 0);

		Button b1 = new Button("确		认");

		b1.setOnAction(e -> SearchAction());
		f.add(b1, 1, 7);

		Button b2 = new Button("取		消");
		b2.setOnAction(e -> fexitAction());
		f.add(b2, 1, 8);

		signtip.setAlignment(Pos.CENTER);
		f.add(searchtip, 1, 9);

		Forget.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent event) {
				newaccount.setText(null);

				account.setEditable(true);
				password.setEditable(true);
				back.setOnAction(e -> backAction());
				rewrite.setOnAction(e -> rewriteAction());
				search.setOnAction(e -> searchAction());
			}
		});

		Scene sscene = new Scene(f, 300, 230);
		Forget.setTitle("虚拟校园系统1.0-查询学籍");
		Forget.setScene(sscene);
		Forget.setResizable(false);
		Forget.setAlwaysOnTop(true);
		Forget.show();
	}

	private Stage SSign = new Stage();

	public void SearchAction() {

		b1.setOnAction(null);
		b2.setOnAction(null);
		number.setEditable(false);
		
		TextField name = new TextField("名字");
		TextField sex = new TextField("性别");
		TextField study = new TextField("学籍");
		TextField birthday = new TextField("生日");// 查询反应在文本

		
		// add the server
		stusrv = new StuSrvImpl(this.getUsrname(),this.getSocket());
		User myinfo = new User();
		try {
			myinfo = stusrv.getPersonInfo(number.getText());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		name = new TextField(myinfo.getPersonname());
		sex = new TextField(myinfo.getSex());
		study = new TextField(myinfo.getRace());
		birthday = new TextField(myinfo.getBirthday());
		
		
		FlowPane ss = new FlowPane();
		ss.setPadding(new Insets(11, 12, 13, 14));
		ss.setHgap(30);
		ss.setVgap(10);

		ss.getChildren().add(new Label("名字"));

		ss.getChildren().addAll(name);

		ss.getChildren().add(new Label("性别"));

		ss.getChildren().addAll(sex);

		ss.getChildren().add(new Label("学籍"));

		ss.getChildren().addAll(study);

		ss.getChildren().add(new Label("生日"));

		ss.getChildren().addAll(birthday);

		back.setOnAction(e -> backAction2());
		ss.getChildren().addAll(back);// 点击返回

		Scene ssscene = new Scene(ss, 280, 270);
		SSign.setTitle("虚拟校园系统1.0-学籍管理");
		SSign.setScene(ssscene);
		SSign.setResizable(false);
		SSign.setAlwaysOnTop(true);
		SSign.show();

	}

	private void backAction2() {
		
		b1.setOnAction(e->SearchAction());
		b2.setOnAction(e->fexitAction());
		number.setEditable(true);
		
		SSign.close();
		searchtip.setText("欢迎使用虚拟校园1.0");
	}

	private void fexitAction() {
		Forget.close();
		searchtip.setText("欢迎使用虚拟校园1.0");
		number.setText(null);
		License.setText(null);
		Newpassword.setText(null);
		Newpassword1.setText(null);

		account.setEditable(true);
		password.setEditable(true);
		back.setOnAction(e -> backAction());
		rewrite.setOnAction(e -> rewriteAction());
		search.setOnAction(e -> searchAction());

	}

	/*
	public static void main(String[] args) {
		launch(args);
	}
	*/
	
	public String getUsrname() {
		return usrname;
	}
	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

}
