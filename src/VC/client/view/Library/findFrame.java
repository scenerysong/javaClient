package VC.client.view.Library;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import VC.client.view.Library.mainFrame;
import VC.client.bz.Impl.LibrarySrvImpl;
import VC.client.view.Library.borrowbook;
import VC.client.view.Library.mainFrame;
import VC.client.view.Library.mybook;
import VC.common.Book;
public class findFrame extends JFrame {
	public static DefaultTableModel model = new DefaultTableModel();
	
	JPanel jp = new JPanel();
	JPanel jp1 = new JPanel();
	JPanel jp2 = new JPanel();
	JPanel jp3 = new JPanel();
	JFrame jf = new JFrame();
	public LibrarySrvImpl booksrv;
	List<Book> booklist = new ArrayList<Book>();
	public findFrame(String pusrname, Socket psocket) {
		
		booksrv = new LibrarySrvImpl(pusrname, psocket);
		
		jf.setTitle("虚拟校图书馆查询界面");
		jf.setSize(new Dimension(700, 505));
		jf.setLocation(200, 200);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jf.setLayout(null);
		jf.setLayout(new GridLayout(4, 1));		

		JLabel jb = new JLabel("查询页面");
		jp.add(jb);
		jf.add(jp);
		JLabel jb1 = new JLabel("输入要查询的书名：");
		final JTextField jt = new JTextField(15);
		jp1.add(jb1);
		jp1.add(jt);
		jf.add(jp1);
		JScrollPane scrollPane = new JScrollPane();
		jp3.add(scrollPane, BorderLayout.CENTER);
		jf.add(jp3);

		JTable tb = new JTable();
		//tb.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 锟斤拷锟斤拷锟窖★拷锟侥Ｊ斤拷锟斤拷锟轿伙拷锟斤拷锟斤拷锟窖★拷瘛⒌锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷选锟斤拷锟斤拷锟斤拷选锟斤拷
		tb.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		tb.setRowHeight(30);
		JTableHeader header = tb.getTableHeader(); // 锟斤拷取 JTable 锟斤拷头锟侥讹拷锟斤拷
		header.setFont(new Font("微软雅黑",  Font.PLAIN, 16));
		header.setPreferredSize(new Dimension(header.getWidth(), 35)); // 锟斤拷锟矫达拷锟斤拷锟斤拷锟斤拷锟窖★拷锟叫 
		scrollPane.setViewportView(tb);
		model = (DefaultTableModel) tb.getModel();
		model.setColumnIdentifiers(new Object[] {  "编号", "书名", "馆藏", "数量" });

		JButton bt = new JButton("查询");
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					booklist=booksrv.searchName(jt.getText());
					for(int i=0;i<booklist.size();i++) {
						String name =booklist.get(i).getBookName();
						String id = booklist.get(i).getBookID();
						String publisher = booklist.get(i).getBookPublisher();
						String num = booklist.get(i).getBookNum();
						model.addRow(new Object[] { id, name, publisher, num });
						tb.setModel(model);
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				new borrowbook(pusrname, psocket);
            	setVisible(false);
			}
		});
		
		JButton bt2 = new JButton("返回");
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new mainFrame(pusrname, psocket);
				jf.setVisible(false);
			}
		});
		
		jp2.add(bt);
		jp2.add(bt2);
		jf.add(jp2);

		jf.setVisible(true);
	}

	/*
	public static void main(String[] args) {
		new findFrame();
	}
	*/

}