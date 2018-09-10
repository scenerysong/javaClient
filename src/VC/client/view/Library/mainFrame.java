package VC.client.view.Library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;

public class mainFrame extends JFrame {
	private String usrname;
	private Socket passocket;
	/*
	 * 在图书馆主界面设置四个按钮，分别是“查询”、“借书”、“还书”，“我的书单” 点击按钮会跳转到相应的界面
	 */
	JButton button1 = new JButton("查询");
	JButton button2 = new JButton("借书");
	JButton button3 = new JButton("还书");
	JButton button4 = new JButton("我的书单");
	JFrame jf = new JFrame();

	public mainFrame(String pusrname, Socket psocket) {
		/*
		 * 设置选课主界面的标题、大小、关闭方式
		 */
		jf.setTitle("虚拟图书馆");
		jf.setSize(new Dimension(550, 200));
		jf.setLocation(200, 200);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		jf.setLayout(null);
		/*
		 * 将四个按钮添加到选课主界面
		 */
		jf.getContentPane().add(button1, null);
		jf.getContentPane().add(button2, null);
		jf.getContentPane().add(button3, null);
		jf.getContentPane().add(button4, null);
		/*
		 * 设置四个按钮的位置
		 */
		button1.setBounds(new Rectangle(50, 50, 70, 27));
		button2.setBounds(new Rectangle(150, 50, 70, 27));
		button3.setBounds(new Rectangle(250, 50, 70, 27));
		button4.setBounds(new Rectangle(350, 50, 150, 27));
		/*
		 * 点击按钮一，即“查询”，将跳转到查询界面
		 */
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new findFrame(pusrname, psocket);
				jf.dispose();
			}
		});
		/*
		 * 点击按钮二，即“借书”，将跳转到借书界面
		 */
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new borrowbook(pusrname, psocket);
				jf.dispose();
			}
		});
		/*
		 * 点击按钮三，“还书”，将跳转到还书界面
		 */
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new returnbooks(pusrname, psocket);
				jf.dispose();
			}
		});
		/*
		 * 点击按钮四，即“我的书单”，将显示用户所借的书籍信息
		 */
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new mybook(pusrname, psocket);
				jf.dispose();
			}
		});

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
