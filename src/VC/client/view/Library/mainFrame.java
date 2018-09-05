package VC.client.view.Library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
 
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

//import gui.MainFrame2;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField; 

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class mainFrame extends JFrame{
	
	private String usrname;
	private Socket passocket;
	
	JButton button1 = new JButton("��ѯ");
	JButton button2 = new JButton("����");
	JButton button3 = new JButton("����");
	JButton button4 = new JButton("�ҵ��鵥");
	JFrame jf = new JFrame();
	public mainFrame(String pusrname, Socket psocket) {
		
		// this.setUsrname(pusrname);
		// this.setPassocket(psocket);
		
		jf.setTitle("����Уͼ���");
		jf.setSize(new Dimension(550, 200));
		jf.setLocation(200, 200);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jf.setLayout(null);
		
		jf.getContentPane().add(button1, null);
		jf.getContentPane().add(button2, null);
		jf.getContentPane().add(button3, null);
		jf.getContentPane().add(button4, null);
		
		button1.setBounds(new Rectangle(50, 50, 70, 27));
		button2.setBounds(new Rectangle(150, 50, 70, 27));
		button3.setBounds(new Rectangle(250, 50, 70, 27));
		button4.setBounds(new Rectangle(350, 50, 150, 27));
		
		button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new findFrame(pusrname, psocket);
            	jf.setVisible(false);
            }
         });
		
		button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new borrowbook(pusrname, psocket);
            	jf.setVisible(false);
            }
         });
		
		button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new returnbooks(pusrname, psocket);
            	jf.setVisible(false);
            }
         });
		button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new mybook(pusrname, psocket);
            	jf.setVisible(false);
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
	
	/*
	public static void main(String[] args) {
		new mainFrame();
	}
	*/
}
