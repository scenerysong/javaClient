package VC.client.view.Library;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import VC.client.bz.Impl.LibrarySrvImpl;
import VC.common.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import java.awt.Container;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import VC.client.bz.Impl.CourseSrvImpl;
import VC.client.bz.Impl.LibrarySrvImpl;
import VC.client.view.Library.borrowbook;
import VC.client.view.Library.mainFrame;
import VC.client.view.Library.mybook;
import VC.common.Book;


public class mybook extends JFrame implements ActionListener{
	DefaultTableModel defaultModel = null;
	JPanel panel = new JPanel();
	JFrame f = new JFrame();
	public LibrarySrvImpl booksrv;
	public Object[][] p = null;
	public String[] n = { "����", "������ ","����" };
	
	public mybook(String pusrname, Socket psocket) {
		
		List<Book> booklist = new ArrayList<Book>();
		try {
			booksrv = new LibrarySrvImpl(pusrname, psocket);
			booklist = booksrv.getallMyBook();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		p = new Object[booklist.size()][3];
		for (int i = 0; i < booklist.size(); i++) {
			p[i][0] = booklist.get(i).getBookName();
			p[i][1] = booklist.get(i).getBookPublisher();
			p[i][2] = booklist.get(i).getBookAuthor();
		}
		
		defaultModel = new DefaultTableModel(p, n);
		JTable table = new JTable(defaultModel);
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		
		JScrollPane s = new JScrollPane(table);
        f.getContentPane().add(s, BorderLayout.CENTER);
        
        JButton b = new JButton("����");
		panel.add(b);
		b.addActionListener(this);

		Container contentPane = f.getContentPane();
		contentPane.add(panel, BorderLayout.NORTH);
		contentPane.add(s, BorderLayout.CENTER);
		f.getContentPane().add(s, BorderLayout.CENTER);
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setLocation(200, 200);
		f.setResizable(false);
        f.setTitle("����У԰ͼ���ϵͳ�ҵ��鵥");
        f.pack();
        f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});
	}

	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("����")) {
			new mainFrame(booksrv.getUseraccount(), booksrv.getSocket());
			f.setVisible(false);

		}
	}
	
	/*
	public static void main(String[] args) {
		new mybook();
	}
	*/


}
