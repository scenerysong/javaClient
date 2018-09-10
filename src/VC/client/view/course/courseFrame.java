package VC.client.view.course;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;

public class courseFrame extends JFrame {
	JButton button1 = new JButton("选课");
	JButton button2 = new JButton("退课");
	JButton button3 = new JButton("我的课程");
	/*
	 * 在选课主界面设置三个按钮，分别是“选课”、“退课”、“我的课程”， 点击按钮会跳转到相应的界面
	 */
	JFrame jf = new JFrame();

	public courseFrame(String pusrname, Socket psocket) {

		jf.setTitle("虚拟校园选课系统");
		jf.setSize(new Dimension(500, 200));
		jf.setLocation(200, 200);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		jf.setLayout(null);
		/*
		 * 设置选课主界面的标题、大小、关闭方式
		 */
		jf.getContentPane().add(button1, null);
		jf.getContentPane().add(button2, null);
		jf.getContentPane().add(button3, null);
		/*
		 * 将三个按钮添加到选课主界面
		 */
		button1.setBounds(new Rectangle(50, 50, 70, 27));
		button2.setBounds(new Rectangle(150, 50, 70, 27));
		button3.setBounds(new Rectangle(250, 50, 150, 27));
		/*
		 * 设置三个按钮的位置
		 */
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new chooseCourse(pusrname, psocket);
				jf.dispose();
				// jf.setVisible(false);
			}
		});
		/*
		 * 点击按钮一，即“选课”，将触发的事件，跳转到选课界面
		 */
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new giveupCourse(pusrname, psocket);
				jf.dispose();
				// jf.setVisible(false);
			}
		});
		/*
		 * 点击按钮二，即“退课”，将触发的事件，跳转到退课界面
		 */
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new mycourse(pusrname, psocket);
				jf.dispose();
				// jf.setVisible(false);
			}
		});
		/*
		 * 点击按钮三，即“我的课程”，将触发的事件，查看选择的课程
		 */
	}
}
