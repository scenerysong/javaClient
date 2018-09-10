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
import VC.client.vo.LibrarySrv;
import VC.common.Book;

public class findFrame extends JFrame {
	public static DefaultTableModel model = new DefaultTableModel();
	/*
	 * 定义四个分区，将查询界面分为四部分
	 */
	JPanel jp = new JPanel();
	JPanel jp1 = new JPanel();
	JPanel jp2 = new JPanel();
	JPanel jp3 = new JPanel();
	JFrame jf = new JFrame();
	public LibrarySrv booksrv;
	List<Book> booklist = new ArrayList<Book>();

	public findFrame(String pusrname, Socket psocket) {

		booksrv = new LibrarySrvImpl(pusrname, psocket);
		/*
		 * 设置查询界面的相关属性，包括标题 第一个分区中含输入所要查询书籍的书名的文本框 第二分区显示查询的书籍的相关信息
		 * 第三个分区包含两个按钮，分别是“查询”、“返回” 第四个分区是一个位于底部的滚动条
		 */
		jf.setTitle("虚拟校图书馆查询界面");
		jf.setSize(new Dimension(700, 505));
		jf.setLocation(200, 200);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
		/*
		 * 创建第二个分区的表格
		 */
		JTable tb = new JTable();
		tb.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		tb.setRowHeight(30);
		JTableHeader header = tb.getTableHeader();
		header.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		header.setPreferredSize(new Dimension(header.getWidth(), 35));
		scrollPane.setViewportView(tb);
		/*
		 * 建立model，存储所查询书籍的相关信息
		 */
		model = (DefaultTableModel) tb.getModel();
		model.setColumnIdentifiers(new Object[] { "书名", "作者", "出版社" });
		/*
		 * 按钮“查询”所触发的事件 将数据库中传来的书籍信息存在model中，并显示在表格中
		 */
		JButton bt = new JButton("查询");
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					booklist = booksrv.searchName(jt.getText());
					for (int i = 0; i < booklist.size(); i++) {
						String name = booklist.get(i).getBookName();
						String publisher = booklist.get(i).getBookPublisher();
						String author = booklist.get(i).getBookAuthor();
						model.addRow(new Object[] { name, author, publisher });
						tb.setModel(model);
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
			}
		});
		/*
		 * 按钮“返回”所触发的事件
		 */
		JButton bt2 = new JButton("返回");
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new mainFrame(pusrname, psocket);
				jf.dispose();
			}
		});

		jp2.add(bt);
		jp2.add(bt2);
		jf.add(jp2);
		jf.setVisible(true);
	}
}