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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.io.IOException;
import java.net.Socket;

import VC.client.bz.Impl.CourseSrvImpl;
import VC.common.Course;

public class mycourse extends JFrame implements ActionListener{
	DefaultTableModel defaultModel = null;
	JPanel panel = new JPanel();
	Vector v1 = new Vector();
	JFrame f = new JFrame();
	
	private String usrname;
	private Socket socket;
	public CourseSrvImpl coursesrv;
	
	public mycourse(String pusrname, Socket psocket) {
		
		this.setUsrname(pusrname);
		this.setSocket(psocket);
		
		Object[][] p = null;
		List<Course> mycourselist = new ArrayList<Course>();
		try {
			coursesrv = new CourseSrvImpl(pusrname, psocket);
			mycourselist = coursesrv.getallMyCourse();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		p = new Object[mycourselist.size()][5];

		for (int i = 0; i < mycourselist.size(); i++) {
			p[i][0] = new String(mycourselist.get(i).getCourseID());
			p[i][1] = new String(mycourselist.get(i).getCourseName());
			p[i][2] = new String(mycourselist.get(i).getCourseTeacher());
			p[i][3] = new String(mycourselist.get(i).getCredit());
		}
		
		
		String[] n = { "课程编号", "课程名字", "授课老师", "学分" };
		
		defaultModel = new DefaultTableModel(p, n);
		JTable table = new JTable(defaultModel);
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		
		JScrollPane s = new JScrollPane(table);
        f.getContentPane().add(s, BorderLayout.CENTER);
        
        JButton b = new JButton("返回");
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
        f.setTitle("虚拟校园选课界面我的课程");
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
		if (e.getActionCommand().equals("返回")) {
			new courseFrame(getUsrname(), getSocket());
			//f.setVisible(false);
			f.dispose();

		}
	}
	/*
	public static void main(String[] args) {
		new mycourse();
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