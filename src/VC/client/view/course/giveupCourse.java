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
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.IOException;
import java.net.Socket;

import VC.client.bz.Impl.CourseSrvImpl;
import VC.client.view.Library.borrowbook;
import VC.client.view.Library.mainFrame;
import VC.client.view.Library.mybook;
import VC.common.Course;

public class giveupCourse extends JFrame implements ActionListener{
	
	DefaultTableModel defaultModel = null;
	JPanel panel = new JPanel();
	Vector v1 = new Vector();
	JFrame f = new JFrame();

	List<String> courseName = new ArrayList<String>();

	private String usrname;
	private Socket socket;
	public CourseSrvImpl coursesrv;

	public giveupCourse(String pusrname, Socket psocket) {
		
		coursesrv = new CourseSrvImpl(pusrname, psocket);
		this.setUsrname(pusrname);
		this.setSocket(psocket);
		
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
		
		//JFrame f = new JFrame();
		MyTable1 mt1 = new MyTable1(coursesrv);
		
		final JTable table1 = new JTable(mt1);
		JCheckBox jc1 = new JCheckBox();
		table1.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(jc1));
		table1.setPreferredScrollableViewportSize(new Dimension(400, 150));
		
		
		//defaultModel = new DefaultTableModel(p, n);
        JScrollPane s = new JScrollPane(table1);
        f.getContentPane().add(s, BorderLayout.CENTER);
        
		table1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table1.getSelectedRow();

				int column = table1.getSelectedColumn();
				Object obj = table1.getValueAt(row, column);
				Object obj1 = true;
				if (table1.isCellSelected(row, column)) {
					//System.out.println(obj);
					if (obj.equals(obj1)) {
						//System.out.println(row);
						v1.add(row);
					}
				}
			}
		});

		// JPanel panel = new JPanel();
		JButton b = new JButton("退课");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("退课")) {
			for (int i = 0; i < v1.size(); i++) {
				int a = (int) v1.get(i);
				//System.out.println(courseName.get(a));
				// to do: the add course part
				// v1.size()
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
			//f.setVisible(false);
			//setVisible(false);
			f.dispose();
		}
		if (e.getActionCommand().equals("我的课程")) {
			new mycourse(getUsrname(), getSocket());
			//f.setVisible(false);
			f.dispose();
		}
		//table.revalidate();
	}

	/*
	public static void main(String[] args) {
		new giveupCourse(getUsrname(), getSocket());
	}
	*/

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

class MyTable1 extends AbstractTableModel {
	public CourseSrvImpl coursesrv;
	public Object[][] p = null;

	public String[] n = { "课程编号", "课程名字", "授课老师", "学分","是否选择" };

	public MyTable1(CourseSrvImpl coursesrv0) {
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

