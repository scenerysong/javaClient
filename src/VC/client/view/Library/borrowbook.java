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

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

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


public class borrowbook extends JFrame implements ActionListener {
	
	//DefaultTableModel defaultModel = null;
	JTable table = null;
	JPanel panel = new JPanel();
	Vector v1 = new Vector();
	JFrame f = new JFrame();
	public LibrarySrvImpl booksrv = new LibrarySrvImpl("mike");

	//String[] bookName = { "History", "Science", "Future", "Policy", "Furture" };
	Vector<String> bookName = new Vector<String>();
	public borrowbook() {
	
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
		
		for (int i = 0; i < booklist.size(); i++) {
			bookName.add(booklist.get(i).getBookName());
		}
		//JFrame f = new JFrame();
		MyTable18 mt = new MyTable18();
		final JTable table = new JTable(mt);
		JCheckBox jc1 = new JCheckBox();
		table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(jc1));
		table.setPreferredScrollableViewportSize(new Dimension(400, 150));
		/*
		Object[][] p = {
                { "History", "987", "Mike",false },
                { "Science", "124", "Dan", false }, 
                {"Future","246","Peter",false},
                {"Policy","768","Markus",false},
                {"Furture","111","Song",false},};
		
		String[] n = { "娑旓箑鎮�", "閸戣櫣澧楅崯锟�, "娴ｆ粏锟�", "閺勵垰鎯侀崝鐘插弳娑旓箑宕�" };
		*/

		//defaultModel = new DefaultTableModel(p, n);
        JScrollPane s = new JScrollPane(table);
        f.getContentPane().add(s, BorderLayout.CENTER);
		// 閿熸枻鎷烽敓鏂ゆ嫹涓�閿熸枻鎷烽粯閿熻緝鐨勬唻鎷烽敓渚ワ綇鎷烽敓锟�

		//defaultModel = new DefaultTableModel(p, n);
		//table = new JTable(defaultModel);
		//table.setPreferredScrollableViewportSize(new Dimension(400, 80));
        
        table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();

				int column = table.getSelectedColumn();
				Object obj = table.getValueAt(row, column);
				Object obj1 = true;
				if (table.isCellSelected(row, column)) {
					System.out.println(obj);
					if (obj.equals(obj1)) {
						//System.out.println(row);
						v1.add(row);
					}
				}
			}
		});

		// JPanel panel = new JPanel();
		JButton b = new JButton("鍔犲叆涔﹀崟");
		panel.add(b);
		b.addActionListener(this);
		b = new JButton("鎴戠殑涔﹀崟");
		panel.add(b);
		b.addActionListener(this);
		b = new JButton("杩斿洖");
		panel.add(b);
		b.addActionListener(this);

		Container contentPane = f.getContentPane();
		contentPane.add(panel, BorderLayout.NORTH);
		contentPane.add(s, BorderLayout.CENTER);
		f.getContentPane().add(s, BorderLayout.CENTER);
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setLocation(200, 200);
		f.setResizable(false);
        f.setTitle("铏氭嫙鏍″洯鍥句功棣嗙郴缁熷�熶功鐣岄潰");
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
		if (e.getActionCommand().equals("鍔犲叆涔﹀崟")) {
			for (int i = 0; i < v1.size(); i++) {
				int a = v1.indexOf(i);
				System.out.println(bookName.get(a));
				// 閿熺殕鐚存嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷疯彉閿燂拷
			
			}
			for (int i = 0; i < v1.size(); i++) {
				int a = (int) v1.get(i);
				//System.out.println(courseName.get(a));
				// to do: the add course part
				// v1.size()
				try {
					booksrv = new LibrarySrvImpl("mike");
					booksrv.addBook(bookName.get(a), "mike");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}
		if (e.getActionCommand().equals("杩斿洖")) {
			new mainFrame();
			f.setVisible(false);
			//setVisible(false);
		}
		if (e.getActionCommand().equals("鎴戠殑涔﹀崟")) {
			new mybook();
			f.setVisible(false);
		}
		//table.revalidate();
	}

	public static void main(String[] args) {
		new borrowbook();
	}

}

class MyTable18 extends AbstractTableModel {
	public LibrarySrvImpl booksrv = new LibrarySrvImpl("mike");
	public Object[][] p = null;
	/*
	Object[][] p = {
            { "History", "987", "Mike",false },
            { "Science", "124", "Dan", false }, 
            {"Future","246","Peter",false},
            {"Policy","768","Markus",false},
            {"Furture","111","Song",false},};
*/
	public String[] n = { "涔﹀悕", "鍑虹増鍟� ","浣滆��", "鏄惁鍔犲叆" };

	public MyTable18() {
		super();
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
