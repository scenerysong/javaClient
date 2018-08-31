package VC.client.view.Shop;


import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Container;
import java.net.URL;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import VC.client.bz.Impl.ShopSrvImpl;
import VC.common.Goods;

public class ShopPage extends JFrame{
	
	
	public ShopSrvImpl shopsrv = new ShopSrvImpl();
	
	JButton jb1 = new JButton("加入购物车");
	JButton jb2 = new JButton("我的购物车");
	
	ArrayList<JTextField> jtfs = new ArrayList<>();
	
	public ShopPage() throws ClassNotFoundException, IOException {
		
		
		List<String> GoodsName = new ArrayList<String>();
		List<String> GoodsID = new ArrayList<String>();
		List<String> GoodsPrice = new ArrayList<String>();
		//List<String> NUM = new ArrayList<String>();
		List<Goods> goodslist = shopsrv.getAllGoods();
		
		for(int i=0;i<goodslist.size();i++) {
			GoodsName.add(i, goodslist.get(i).getProductName());
			GoodsID.add(i, goodslist.get(i).getGoodsID());
			GoodsPrice.add(i, goodslist.get(i).getValue());
			//NUM.add(i, goodslist.get(i).getGoodsNum());
		}
		
		JPanel p1 = new JPanel(new GridLayout(GoodsName.size()+1,1));
		JPanel p2 = new JPanel(new GridLayout(GoodsName.size()+1,1));
		JPanel p3 = new JPanel(new GridLayout(GoodsName.size()+1,1));
		JPanel p4 = new JPanel(new GridLayout(GoodsName.size()+1,1));
		
		Container container = this.getContentPane();
		container.setLayout(new GridLayout(1,4,20,100));
		
		for(int i=0;i<GoodsID.size()+1;i++) {
			if(i!=GoodsID.size()) {
				p1.add(new JLabel(GoodsID.get(i)));
			}
			else {
				p1.add(jb1);
			}
			
		}
		for(int i=0;i<GoodsName.size();i++) {
			p2.add(new JLabel(GoodsName.get(i)));
		}
		for(int i=0;i<GoodsPrice.size()+1;i++) {
			if(i!=GoodsPrice.size()) {
				p3.add(new JLabel(GoodsPrice.get(i)));
			}
			else {
				p3.add(jb2);
			}
		}
		
		//ArrayList<JTextField> jtfs = new ArrayList<>();
		
		for(int i=0;i<GoodsID.size();i++) {
			if(i==0) {
				p4.add(new JLabel("商品数量"));
			}
			else {
				JTextField jtf= new JTextField("");
				jtfs.add(jtf);
				p4.add(jtf);
			}
		}
		
		int a=jtfs.size();
		String[] NUM = new String[a];
		for(int i=0;i<a;i++) {
			NUM[i]=jtfs.get(i).getText();
		}
		
		
		jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(null, "添加成功");
            	
            }
        });
		
		jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	//for (int i = 0; i < jtfs.size(); i++) {
                    //String temp = jtfs.get(i).getText();
            	try {
					new MyGoods();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	setVisible(false);
            	//}
            }
         });
		
		
		container.add(p1);
		container.add(p2);
		container.add(p3);
		container.add(p4);
		
		this.setVisible(true);
		this.setSize(500, 350);
		this.setTitle("虚拟校园商店");
		this.setLocation(200, 200);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	public static void main (String[] args) {
		try {
			new ShopPage();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
