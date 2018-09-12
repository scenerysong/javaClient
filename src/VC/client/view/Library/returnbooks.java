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
import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import VC.client.view.Library.mainFrame;
import VC.client.bz.Impl.LibrarySrvImpl;
import VC.client.view.Library.mybook;
import VC.client.vo.LibrarySrv;
import VC.common.Book;
import java.util.List;
import java.util.ArrayList;

public class returnbooks extends JFrame implements ActionListener {

	DefaultTableModel defaultModel = null;
	JPanel panel = new JPanel();
	Vector v1 = new Vector();// 用来存储所归还的书籍名字
	JFrame f = new JFrame();
	public LibrarySrvImpl booksrv;
	List<String> bookName = new ArrayList<String>();

	public returnbooks(String pusrname, Socket psocket) {
		/*
		 * 接收从服务器端传来的我的书单的书籍名字
		 */
		booksrv = new LibrarySrvImpl(pusrname, psocket);
		List<Book> mybooklist = new ArrayList<Book>();
		try {
			mybooklist = booksrv.getallMyBook();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*
		 * 将我的书单中的书籍名称存入bookNmae中
		 */
		for (int i = 0; i < mybooklist.size(); i++) {
			bookName.add(mybooklist.get(i).getBookName());
			System.out.println("this is step " + mybooklist.get(i).getBookName());
		}
		/*
		 * 继承类MyTable1，建立一个表格 设置表格的属性，大小、滚动条、复选框
		 */
		MyTable1 mt1 = new MyTable1(booksrv);
		System.out.println("this is step of my table");
		final JTable table1 = new JTable(mt1);
		JCheckBox jc1 = new JCheckBox();
		table1.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(jc1));
		table1.setPreferredScrollableViewportSize(new Dimension(400, 150));
		JScrollPane s = new JScrollPane(table1);
		f.getContentPane().add(s, BorderLayout.CENTER);
		/*
		 * 设置复选框的触发事件 当用户勾选复选框时，获取对应的行数，并将所获得书籍名添加到向量v1
		 */
		table1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table1.getSelectedRow();
				int column = table1.getSelectedColumn();
				Object obj = table1.getValueAt(row, column);
				Object obj1 = true;
				Object obj2 = false;
				if (table1.isCellSelected(row, column)) {
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
		JButton b = new JButton("还书");
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
		f.setTitle("虚拟校园图书馆系统还书界面");
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
	 * 三个按钮对应的触发事件 “还书”：所选择的书籍将会从我的书单中删除 “返回”：跳转回图书馆主界面 “我的书单”：跳转到我的书单
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("还书")) {
			for (int i = 0; i < v1.size(); i++) {
				int a = (int) v1.get(i);
				System.out.println("this is returnbook part" + bookName.get(a));
				try {
					System.out.println("this is step returnbook");
					booksrv.returnBook(bookName.get(a));
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
 * MyTable1类继承了AbstractTableModel，
 * 并且实现了getColmunCount()、getRowCount()、getValueAt()方法
 */
class MyTable1 extends AbstractTableModel {
	public Object[][] p = null;// 存储要显示在表格中的数据
	public String[] n = { "书名", "出版商 ", "作者", "是否还书" };

	public MyTable1(LibrarySrv booksrv) {
		super();
		List<Book> booklist = new ArrayList<Book>();
		try {
			booklist = booksrv.getallMyBook();
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
