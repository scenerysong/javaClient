package VC.client.view.course;

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

import VC.client.bz.Impl.CourseSrvImpl;
import VC.client.view.Library.borrowbook;
import VC.client.view.Library.mainFrame;
import VC.client.view.Library.mybook;
import VC.common.Course;

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

public class chooseCourse extends JFrame implements ActionListener {
	JTable table = null;
	JPanel panel = new JPanel();
	Vector v1 = new Vector();
	JFrame f = new JFrame();

	List<String> courseName = new ArrayList<String>();
	// String[] courseName = { "History", "Science", "Policy" };

	public CourseSrvImpl coursesrv;

	public chooseCourse(String pusrname, Socket psocket) {

		coursesrv = new CourseSrvImpl(pusrname, psocket);
		
		String[] n = { "课程编号", "课程名字", "授课老师", "学分", "是否选择" };

		List<Course> courselist = new ArrayList<Course>();
		try {
			courselist = coursesrv.getAllCourse();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (int i = 0; i < courselist.size(); i++) {
			courseName.add(courselist.get(i).getCourseName());
			System.out.println(courseName.get(i));
		}

		MyTable18 mt = new MyTable18(coursesrv);
		final JTable table = new JTable(mt);
		JCheckBox jc1 = new JCheckBox();
		table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(jc1));
		table.setPreferredScrollableViewportSize(new Dimension(400, 150));
		JScrollPane s = new JScrollPane(table);
		f.getContentPane().add(s, BorderLayout.CENTER);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();

				int column = table.getSelectedColumn();
				Object obj = table.getValueAt(row, column);
				Object obj1 = true;
				if (table.isCellSelected(row, column)) {
					if (obj.equals(obj1)) {
						v1.add(row);
					}
				}
			}
		});

		// JPanel panel = new JPanel();
		JButton b = new JButton("加入课程");
		panel.add(b);
		b.addActionListener(this);
		b = new JButton("我的课程");
		panel.add(b);
		b.addActionListener(this);
		b = new JButton("返回");
		panel.add(b);
		b.addActionListener(this);

		Container contentPane = f.getContentPane();
		contentPane.add(panel, BorderLayout.NORTH);
		contentPane.add(s, BorderLayout.CENTER);
		f.getContentPane().add(s, BorderLayout.CENTER);
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setLocation(200, 200);
		f.setResizable(false);
		f.setTitle("虚拟校园图书馆借书界面");
		f.pack();
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("加入课程")) {
			for (int i = 0; i < v1.size(); i++) {
				int a = (int) v1.get(i);
				System.out.println(a);
				try {
					coursesrv = new CourseSrvImpl("mike");
					System.out.println(courseName.get(a));
					coursesrv.addCourse(courseName.get(a), "mike");
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
			new courseFrame(coursesrv.getUseraccount(), coursesrv.getSocket());
			f.setVisible(false);
			// setVisible(false);
		}
		if (e.getActionCommand().equals("我的课程")) {
			new mycourse(coursesrv.getUseraccount(),coursesrv.getSocket());
			f.setVisible(false);
		}
	}

	/*
	public static void main(String[] args) {
		new chooseCourse();
	}
	*/

}

class MyTable18 extends AbstractTableModel {

	public CourseSrvImpl coursesrv;
	public Object[][] p = null;

	public String[] n = { "课程编号", "课程名字", "授课老师", "学分", "是否选择" };

	public MyTable18(CourseSrvImpl coursesrv0) {
		super();
		coursesrv = coursesrv0;
		List<Course> courselist = new ArrayList<Course>();
		try {
			courselist = coursesrv.getAllCourse();
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
			p[i][3] = courselist.get(i).getCourseNum();
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
