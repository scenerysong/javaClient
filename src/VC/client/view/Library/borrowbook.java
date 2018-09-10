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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import VC.client.bz.Impl.LibrarySrvImpl;
import VC.client.view.Library.borrowbook;
import VC.client.view.Library.mainFrame;
import VC.client.view.Library.mybook;
import VC.client.vo.LibrarySrv;
import VC.common.Book;

public class borrowbook extends JFrame implements ActionListener {
	JTable table = null;// 建立一个空表格
	JPanel panel = new JPanel();
	Vector v1 = new Vector();
	JFrame f = new JFrame();
	public LibrarySrvImpl booksrv;
	List<String> bookName = new ArrayList<String>();// 用来存储所选择的书籍名字

	public borrowbook(String pusrname, Socket psocket) {
		booksrv = new LibrarySrvImpl(pusrname, psocket);
		/*
		 * 接收从服务器端传来的书籍名称
		 */
		List<Book> booklist = new ArrayList<Book>();
		try {
			booklist = booksrv.getAllBook();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*
		 * 获取数据库中所有的书籍名，存入bookName中
		 */
		for (int i = 0; i < booklist.size(); i++) {
			bookName.add(booklist.get(i).getBookName());
		}
		/*
		 * 继承类MyTable18，建立一个表格 设置表格的属性，大小、滚动条、复选框
		 */
		MyTable18 mt = new MyTable18(booksrv);
		table = new JTable(mt);
		JCheckBox jc1 = new JCheckBox();
		table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(jc1));
		table.setPreferredScrollableViewportSize(new Dimension(400, 150));
		JScrollPane s = new JScrollPane(table);
		f.getContentPane().add(s, BorderLayout.CENTER);
		/*
		 * 设置复选框的触发事件 当用户勾选复选框时，获取对应的行数，并将所获得书籍名添加到向量v1
		 */
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int column = table.getSelectedColumn();
				Object obj = table.getValueAt(row, column);
				Object obj1 = true;
				Object obj2 = false;
				if (table.isCellSelected(row, column)) {
					System.out.println(obj);
					if (obj.equals(obj1)) {
						v1.add(row);
					}
					if (obj.equals(obj2)) {
						Vector v = new Vector();
						for (int i = 0; i < v1.size(); i++) {
							if (v1.indexOf(i) != row) {
								v.add(v1.indexOf(i));
							}
						}
						v1 = v;
					}
				}
			}
		});
		/*
		 * 设置三个按钮
		 */
		JButton b = new JButton("加入书单");
		panel.add(b);
		b.addActionListener(this);
		b = new JButton("我的书单");
		panel.add(b);
		b.addActionListener(this);
		b = new JButton("返回");
		panel.add(b);
		b.addActionListener(this);
		/*
		 * 设置选课界面的相关属性
		 */
		Container contentPane = f.getContentPane();
		contentPane.add(panel, BorderLayout.NORTH);
		contentPane.add(s, BorderLayout.CENTER);
		f.getContentPane().add(s, BorderLayout.CENTER);
		f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		f.setLocation(200, 200);
		f.setResizable(false);
		f.setTitle("虚拟图书馆");
		f.pack();
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});
	}

	/*
	 * 三个按钮对应的触发事件 “加入书单”：所选择的书籍将会添加到我的书单中 “返回”：跳转回选课主界面 “我的书单”：跳转到我的书单，查看所选的书籍信息
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("加入书单")) {
			for (int i = 0; i < v1.size(); i++) {
				int a = v1.indexOf(i);
			}
			for (int i = 0; i < v1.size(); i++) {
				int a = (int) v1.get(i);
				try {
					booksrv.addBook(bookName.get(a), booksrv.getUseraccount());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
		if (e.getActionCommand().equals("返回")) {
			new mainFrame(booksrv.getUseraccount(), booksrv.getSocket());
			f.dispose();
		}
		if (e.getActionCommand().equals("我的书单")) {
			new mybook(booksrv.getUseraccount(), booksrv.getSocket());
			f.dispose();
		}
	}
}

/*
 * MyTable18类继承了AbstractTableModel，
 * 并且实现了getColmunCount()、getRowCount()、getValueAt()方法
 */
class MyTable18 extends AbstractTableModel {
	public LibrarySrv booksrv;
	public Object[][] p = null;// 存储所要显示在表格中的数据

	public String[] n = { "书名", "出版商", "作者", "是否加入书单" };

	public MyTable18(LibrarySrv booksrv0) {

		super();
		booksrv = booksrv0;
		List<Book> booklist = new ArrayList<Book>();
		try {
			booklist = booksrv.getAllBook();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		p = new Object[booklist.size()][4];
		for (int i = 0; i < booklist.size(); i++) {
			p[i][0] = booklist.get(i).getBookName();
			p[i][1] = booklist.get(i).getBookPublisher();
			p[i][2] = booklist.get(i).getBookAuthor();
			p[i][3] = false;
		}
	}

	@Override
	public int getRowCount() {
		return p.length;
	}

	@Override
	public int getColumnCount() {
		return n.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return p[rowIndex][columnIndex];
	}

	@Override
	public String getColumnName(int column) {
		return n[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		p[rowIndex][columnIndex] = aValue;
		fireTableCellUpdated(rowIndex, columnIndex);
	}
}
