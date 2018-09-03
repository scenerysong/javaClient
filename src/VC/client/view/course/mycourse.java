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
import VC.client.bz.Impl.CourseSrvImpl;
import VC.common.Course;

public class mycourse extends JFrame implements ActionListener{
	DefaultTableModel defaultModel = null;
	JPanel panel = new JPanel();
	Vector v1 = new Vector();
	JFrame f = new JFrame();
	public CourseSrvImpl coursesrv = new CourseSrvImpl("mike");
	
	public mycourse() {
		Object[][] p = null;
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
		p = new Object[mycourselist.size()][5];
		
		for (int i = 0; i < mycourselist.size(); i++) {
			p[i][0] = mycourselist.get(i).getCourseID();
			p[i][1] = mycourselist.get(i).getCourseName();
			p[i][2] = mycourselist.get(i).getCourseTeacher();
			p[i][3] = mycourselist.get(i).getCourseNum();
		}
		
		
		String[] n = { "课程编号", "课程名字", "授课老师", "学分","是否选择" };
		
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
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
			new courseFrame();
			f.setVisible(false);

		}
	}
	public static void main(String[] args) {
		new mycourse();
	}


}