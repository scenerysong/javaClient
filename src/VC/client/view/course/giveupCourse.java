package VC.client.view.course;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.io.IOException;
import java.net.Socket;
import VC.client.bz.Impl.CourseSrvImpl;
import VC.client.vo.CourseSrv;
import VC.common.Course;

public class giveupCourse extends JFrame implements ActionListener {
	DefaultTableModel defaultModel = null;
	JPanel panel = new JPanel();
	Vector v1 = new Vector();// 用来存储所选择的要退的课程名字
	JFrame f = new JFrame();
	List<String> courseName = new ArrayList<String>();// 存储我的课程表中的课程名
	private String usrname;
	private Socket socket;
	public CourseSrv coursesrv;

	public giveupCourse(String pusrname, Socket psocket) {
		coursesrv = new CourseSrvImpl(pusrname, psocket);
		this.setUsrname(pusrname);
		this.setSocket(psocket);
		/*
		 * 接收从服务器端传来的我的课程的课程
		 */
		List<Course> mycourselist = new ArrayList<Course>();
		try {
			mycourselist = coursesrv.getallMyCourse();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i = 0; i < mycourselist.size(); i++) {
			courseName.add(mycourselist.get(i).getCourseName());
		}
		/*
		 * 继承类MyTable1，建立一个表格 设置表格的属性，大小、滚动条、复选框
		 */
		MyTable1 mt1 = new MyTable1(coursesrv);
		final JTable table1 = new JTable(mt1);
		JCheckBox jc1 = new JCheckBox();
		table1.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(jc1));
		table1.setPreferredScrollableViewportSize(new Dimension(400, 150));
		JScrollPane s = new JScrollPane(table1);
		f.getContentPane().add(s, BorderLayout.CENTER);
		/*
		 * 设置复选框的触发事件 当用户勾选复选框时，获取对应的行数，并将所获得课程名添加到向量v1
		 */
		table1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table1.getSelectedRow();
				int column = table1.getSelectedColumn();
				Object obj = table1.getValueAt(row, column);
				Object obj1 = true;
				if (table1.isCellSelected(row, column)) {
					if (obj.equals(obj1)) {
						v1.add(row);
					}
				}
			}
		});
		/*
		 * 设置三个按钮
		 */
		JButton b = new JButton("退课");
		panel.add(b);
		b.addActionListener(this);
		b = new JButton("我的课程");
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
		f.setTitle("虚拟校园选课界面退课");
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
	 * 三个按钮对应的触发事件 “退课”：所选择的课程将会从我的课程表中删除 “返回”：跳转回选课主界面 “我的课程”：跳转到我的课程表
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("退课")) {
			for (int i = 0; i < v1.size(); i++) {
				int a = (int) v1.get(i);
				System.out.println(courseName.get(a));
				try {
					System.out.println("start step1");
					coursesrv = new CourseSrvImpl(getUsrname(), getSocket());
					coursesrv.deleteCourse(courseName.get(a));
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
			new courseFrame(getUsrname(), getSocket());
			f.dispose();
		}
		if (e.getActionCommand().equals("我的课程")) {
			new mycourse(getUsrname(), getSocket());
			f.dispose();
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getUsrname() {
		return usrname;
	}

	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}

}

/*
 * MyTable1类继承了AbstractTableModel，
 * 并且实现了getColmunCount()、getRowCount()、getValueAt()方法
 */
class MyTable1 extends AbstractTableModel {
	public CourseSrv coursesrv;
	public Object[][] p = null;// 存储要显示在表格中的数据
	public String[] n = { "课程编号", "课程名字", "授课老师", "学分", "是否选择" };

	public MyTable1(CourseSrv coursesrv0) {
		super();
		coursesrv = coursesrv0;
		List<Course> courselist = new ArrayList<Course>();
		try {
			courselist = coursesrv.getallMyCourse();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		p = new Object[courselist.size()][5];
		for (int i = 0; i < courselist.size(); i++) {
			p[i][0] = courselist.get(i).getCourseID();
			p[i][1] = courselist.get(i).getCourseName();
			p[i][2] = courselist.get(i).getCourseTeacher();
			p[i][3] = courselist.get(i).getCredit();
			p[i][4] = false;
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
		return getValueAt(0, columnIndex) == null ? null : getValueAt(0, columnIndex).getClass();
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
