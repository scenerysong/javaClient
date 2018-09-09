package VC.client.view;



import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.*;

import java.io.IOException;

import VC.client.bz.Impl.LoginSrvImpl;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author ASUS
 *
 */
public class Login extends Application{
	
	private Label tip = new Label("欢迎使用虚拟校园1.0");
	private TextField account = new TextField();
	private PasswordField password = new PasswordField();
	private Button log = new Button("登录");
	private Button sign = new Button("用户注册");
	private Button forget = new Button("忘记密码");


	private FlowPane pane = new FlowPane();	
	private DoubleProperty balance = new SimpleDoubleProperty();

	//用于登录的
	public LoginSrvImpl loginsrv = new LoginSrvImpl();
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	
	
	public void start(Stage login) {
		pane.setPadding(new Insets(11,12,13,14));
		pane.setHgap(15);
		pane.setVgap(10);
		
		pane.getChildren().add(new Label("账号"));
		account.setPromptText("Account");
		pane.getChildren().addAll(account);
		
		
		pane.getChildren().add(new Label("密码"));
		password.setPromptText("Password");
		pane.getChildren().addAll(password);
		
		
		password.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				if(e.getCode()==KeyCode.ENTER)logAction();
			}
		});
		log.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				logAction();
			}
		});
		pane.getChildren().addAll(log);
		
		sign.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				signAction();
			}
		});
		pane.getChildren().addAll(sign);
		
		/*
		forget.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				forgetAction();
			}
		});
		pane.getChildren().addAll(forget);
		*/
		
		tip.setMinWidth(200);
		tip.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(tip);
		
		login.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
        		Sign.close();
        		Forget.close();
            }
        });

		balance.addListener(new InvalidationListener() {
			public void invalidated(Observable ov) {
				login.close();
			}
		});
		
		Scene scene = new Scene(pane,230,140);
		login.setTitle("虚拟校园系统1.0");
		login.setScene(scene);
		login.setResizable(false);
		login.show();
	}
	
	
	private void logAction() {
		//String RightAccount = "seu";
		//String RightPassword = "dongdajiruan";
		// 就比如这个地方就要用sendmessage方法发送账户,并接受密码来比较
		// 然后才有下面的这个操作
		// 实现如下
		try {
			try {
				// loginsrv = new LoginSrvImpl();
				if(loginsrv.judgeLogin(account.getText(), password.getText())) {
					Menu a = new Menu(account.getText(), loginsrv.getSocket());
					Stage menu = new Stage();
					a.start(menu);
					balance.set(1);
				}
				else {
					password.setText("");
					tip.setText("账户或密码输入错误，请重试");
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		// 完成实现
		if(account.getText().equals(RightAccount) && password.getText().equals(RightPassword)) {
			Menu a = new Menu();
			Stage menu = new Stage();
			a.start(menu);
			balance.set(1);
		}
		else {
			password.setText("");
			tip.setText("账户或密码输入错误，请重试");
		}
		*/
	}
	
	private TextField newaccount = new TextField();
	private TextField license = new TextField();
	private PasswordField newpassword = new PasswordField();
	private PasswordField newpassword1 = new PasswordField();
	private Stage Sign = new Stage();
	private Label signtip = new Label("欢迎使用虚拟校园1.0");

	private void signAction() {
		GridPane s = new GridPane();
		
		account.setEditable(false);
		password.setEditable(false);
		account.setText("");
		password.setText("");
		tip.setText("欢迎使用虚拟校园1.0");
		log.setOnAction(null);
		sign.setOnAction(null);
		forget.setOnAction(null);
		
		s.setPadding(new Insets(11,12,13,14));
		s.setHgap(15);
		s.setVgap(10);

		
		s.add(new Label("账	号"),0,0);
		
		s.add(newaccount,1,0);
		
		s.add(new Label("授 权 码"),0,1);
		
		s.add(license,1,1);
		
		s.add(new Label("密	码"),0,2);

		s.add(newpassword,1,2);

		s.add(new Label("确认密码"),0,3);
		
		s.add(newpassword1,1,3);
		
		Button b1 = new Button("确		认");
		newpassword1.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				if(e.getCode()==KeyCode.ENTER)logAction();
			}
		});
		b1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				SignAction();
			}
		});
		s.add(b1,1,4);
		
		Button b2 = new Button("取		消");
		b2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				sexitAction();
			}
		});
		s.add(b2,1,5);
		
		signtip.setAlignment(Pos.CENTER);
		s.add(signtip, 1, 6);
		
		Sign.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
        		signtip.setText("欢迎使用虚拟校园1.0");
        		newaccount.setText("");
        		license.setText("");
        		newpassword.setText("");
        		newpassword1.setText("");
        		account.setEditable(true);
        		password.setEditable(true);
        		log.setOnAction(new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent e) {
        				logAction();
        			}
        		});
        		sign.setOnAction(new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent e) {
        				signAction();
        			}
        		});
        		forget.setOnAction(new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent e) {
        				forgetAction();
        			}
        		});	
            }
        });
		
		Scene sscene = new Scene(s,260,250);
		Sign.setTitle("虚拟校园系统1.0-用户注册");
		Sign.setScene(sscene);
		Sign.setResizable(false);
		Sign.show();
	}
	
	private void SignAction() {
		String x = newpassword.getText();
		String y = newaccount.getText();
		if(y.length()>16) {
			signtip.setText("账户的长度不能超过16个字符");
		}
		else if(y.length()<1) {
			signtip.setText("账户不能为空");
		}
		else {
			int z = 0;
			for(int i = 0;i < x.length();i++) {
				if((x.charAt(i) < 48 || x.charAt(i) > 122) && !(x.charAt(i) >= 58 && x.charAt(i) <= 64))
				{
					signtip.setText("密码中只能包含字母或数字");
					z = 1;
				}
			}
			for(int j = 0;j < y.length();j++) {
				if((y.charAt(j) < 48 || y.charAt(j) > 122) && !(y.charAt(j) >= 58 && y.charAt(j) <= 64))
				{
					signtip.setText("账户中只能包含字母或数字");
					z = 1;
				}
			}
			if(newpassword.getText().equals(newpassword1.getText()))
			{
				if(x.length()<6||x.length()>16)
				{
					signtip.setText("密码的长度应为6-16个字符");
				}
				else {
					if(z == 0) {
						//发送注册信息
						try {
							loginsrv.register(newaccount.getText(), newpassword.getText(), license.getText());
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						sexitAction();
						signtip.setText("欢迎使用虚拟校园1.0");
						tip.setText("注册成功！");
						newaccount.setText("");
						license.setText("");
						newpassword.setText("");
						newpassword1.setText("");
						
					}
				}
			}
			else
				signtip.setText("请输入相同的密码");
		}

/*		tip.setText("注册成功!");
		sexitAction();
		signtip.setText("欢迎使用虚拟校园1.0");
		newaccount.setText("");
		license.setText("");
		newpassword.setText("");
		newpassword1.setText("");
*/	}
	
	private void sexitAction() {
		Sign.close();
		signtip.setText("欢迎使用虚拟校园1.0");
		newaccount.setText("");
		license.setText("");
		newpassword.setText("");
		newpassword1.setText("");
		account.setEditable(true);
		password.setEditable(true);
		log.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				logAction();
			}
		});
		sign.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				signAction();
			}
		});
		forget.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				forgetAction();
			}
		});	
		}
	
	private TextField oldaccount = new TextField();
	private TextField License = new TextField();
	private PasswordField Newpassword = new PasswordField();
	private PasswordField Newpassword1 = new PasswordField();
	private Stage Forget = new Stage();
	private Label forgettip = new Label("欢迎使用虚拟校园1.0");
	
	private void forgetAction() {
		GridPane f = new GridPane();
		
		account.setEditable(false);
		password.setEditable(false);
		account.setText("");
		password.setText("");
		tip.setText("欢迎使用虚拟校园1.0");
		log.setOnAction(null);
		sign.setOnAction(null);
		forget.setOnAction(null);
		
		f.setPadding(new Insets(11,12,13,14));
		f.setHgap(15);
		f.setVgap(10);

		
		f.add(new Label("账	号"),0,0);
		
		f.add(oldaccount,1,0);
		
		f.add(new Label("授 权 码"),0,1);
		
		f.add(License,1,1);
		
		f.add(new Label("新 密 码"),0,2);

		f.add(Newpassword,1,2);

		f.add(new Label("确认密码"),0,3);
		
		f.add(Newpassword1,1,3);
		
		Button b1 = new Button("确		认");
		Newpassword1.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				if(e.getCode()==KeyCode.ENTER)logAction();
			}
		});
		b1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				ForgetAction();
			}
		});
		f.add(b1,1,4);
		
		Button b2 = new Button("取		消");
		b2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				fexitAction();
			}
		});
		f.add(b2,1,5);
		
		signtip.setAlignment(Pos.CENTER);
		f.add(forgettip, 1, 6);
	
		Forget.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
        		newaccount.setText("");
        		license.setText("");
        		newpassword.setText("");
        		newpassword1.setText("");
        		account.setEditable(true);
        		password.setEditable(true);
        		log.setOnAction(new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent e) {
        				logAction();
        			}
        		});
        		sign.setOnAction(new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent e) {
        				signAction();
        			}
        		});
        		forget.setOnAction(new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent e) {
        				forgetAction();
        			}
        		});	
            }
        });
		
		Scene sscene = new Scene(f,260,250);
		Forget.setTitle("虚拟校园系统1.0-忘记密码");
		Forget.setScene(sscene);
		Forget.setResizable(false);
		Forget.show();
	}
	
	public void ForgetAction() {
		String x = Newpassword.getText();
		String y = oldaccount.getText();
		if(y.length()>16) {
			forgettip.setText("账户的长度不能超过16个字符");
		}
		else if(y.length()<1) {
			forgettip.setText("账户不能为空");
		}
		else {
			int z = 0;
			for(int i = 0;i < x.length();i++) {
				if((x.charAt(i) < 48 || x.charAt(i) > 122) && !(x.charAt(i) >= 58 && x.charAt(i) <= 64))
				{
					forgettip.setText("密码中只能包含字母或数字");
					z = 1;
				}
			}
			for(int j = 0;j < y.length();j++) {
				if((y.charAt(j) < 48 || y.charAt(j) > 122) && !(y.charAt(j) >= 58 && y.charAt(j) <= 64))
				{
					forgettip.setText("账户中只能包含字母或数字");
					z = 1;
				}
			}
			if(Newpassword.getText().equals(Newpassword1.getText()))
			{
				if(x.length()<6||x.length()>16)
				{
					forgettip.setText("密码的长度应为6-16个字符");
				}
				else {
					if(z == 0) {
						//授权码
						try {
							loginsrv.register(oldaccount.getText(), Newpassword.getText(), License.getText());
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						fexitAction();
						forgettip.setText("欢迎使用虚拟校园1.0");
						oldaccount.setText("");
						License.setText("");
						Newpassword.setText("");
						Newpassword1.setText("");
						
						account.setEditable(true);
						password.setEditable(true);
						log.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent e) {
								logAction();
							}
						});
						sign.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent e) {
								signAction();
							}
						});
						forget.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent e) {
								forgetAction();
							}
						});
					}
				}
			}
			else
				forgettip.setText("请输入相同的密码");
		}
	}
	
	private void fexitAction() {
		Forget.close();
		forgettip.setText("欢迎使用虚拟校园1.0");
		oldaccount.setText("");
		License.setText("");
		Newpassword.setText("");
		Newpassword1.setText("");
		

		account.setEditable(true);
		password.setEditable(true);
		log.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				logAction();
			}
		});
		sign.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				signAction();
			}
		});
		forget.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				forgetAction();
			}
		});
	}

	
}

