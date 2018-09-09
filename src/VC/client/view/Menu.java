package VC.client.view;

import java.io.IOException;
import java.net.Socket;
import VC.client.view.Stu.Students;
import VC.client.view.course.courseFrame;
import VC.client.vo.MenuSrv;
import VC.client.bz.Impl.MenuSrvImpl;
import VC.client.view.Library.mainFrame;
import VC.client.view.Shop.Store;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Menu extends Application{
	
	private String usrname;
	private Socket passocket;
	public Menu() {
		super();
	}
	public Menu(String a, Socket s) {
		super();
		menusrv = new MenuSrvImpl(a, s);
		this.setUsrname(a);
		this.setPassocket(s);
	}
	
	private Label Tip = new Label("欢迎使用虚拟校园1.0");
	private Button logout = new Button("注销");
	private Button xueji = new Button("学籍系统");
	private Button xuanke = new Button("选课系统");
	private Button tushuguan = new Button("图书系统");
	private Button shangdian = new Button("商店系统");
	private BorderPane pane = new BorderPane();	
	private DoubleProperty balance = new SimpleDoubleProperty();
	public MenuSrv menusrv;

	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public void start(Stage menu) {
		pane.setTop(getHBox());
		pane.setCenter(getVBox());
		
		logout.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				logoutAction();
			}
		});
		xueji.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				XuejiAction();
			}
		});
		xuanke.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				XuankeAction();
			}
		});
		tushuguan.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				TushuguanAction();
			}
		});
		shangdian.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				ShangdianAction();
			}
		});
		
		balance.addListener(new InvalidationListener() {
			public void invalidated(Observable ov) {
				menu.close();
			}
		});

		
		Scene scene = new Scene(pane,350,250);
		menu.setTitle("虚拟校园系统1.0");
		menu.setScene(scene);
		menu.setResizable(false);
		menu.show();

	}
	
	private HBox getHBox() {
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(15,15,15,15));
		hBox.setAlignment(Pos.BASELINE_RIGHT);
		Tip.setFont(Font.font("黑体",20));
		hBox.getChildren().add(Tip);
		hBox.getChildren().add(new Label("   "));
		Label account = new Label(this.getUsrname());
		account.setFont(Font.font("黑体",14));
		hBox.getChildren().add(account);
		hBox.getChildren().add(logout);


		return hBox;
	}	
	
	private VBox getVBox() {
		VBox vBox = new VBox(10);
		vBox.setPadding(new Insets(15,5,15,5));
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().add(xueji);
		vBox.getChildren().add(xuanke);
		vBox.getChildren().add(tushuguan);
		vBox.getChildren().add(shangdian);
		
		
		return vBox;
	}
	
	private void logoutAction() {
		
		// 注销当前账户的方法
		try {
			menusrv.delLogin(usrname);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Login a = new Login();
		Stage log = new Stage();
		a.start(log);
		balance.set(1);
	}
	
	private void XuejiAction() {
		new Students(this.getUsrname(), this.getPassocket()).start(new Stage());
	}
	
	private void XuankeAction() {
		new courseFrame(this.getUsrname(),this.getPassocket());
	}
	
	private void TushuguanAction() {
		new mainFrame(this.getUsrname(),this.getPassocket());
	}
	
	private void ShangdianAction() {
		new Store(this.getUsrname(), this.getPassocket()).start(new Stage());
	}

	public String getUsrname() {
		return usrname;
	}

	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}

	public Socket getPassocket() {
		return passocket;
	}

	public void setPassocket(Socket passocket) {
		this.passocket = passocket;
	}
}
