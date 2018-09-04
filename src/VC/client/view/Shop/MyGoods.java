package VC.client.view.Shop;

import java.awt.GridLayout;
import java.awt.Container;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import VC.client.bz.Impl.ShopSrvImpl;
import VC.common.Goods;

public class MyGoods extends JFrame{
	
	public ShopSrvImpl shopsrv = new ShopSrvImpl("mike");
	
	public MyGoods() throws ClassNotFoundException, IOException {
		
		List<String> GoodsName = new ArrayList<String>();
		List<String> GoodsID = new ArrayList<String>();
		List<String> GoodsPrice = new ArrayList<String>();
		List<String> NUM = new ArrayList<String>();
		List<Goods> goodslist = shopsrv.getAllMyGoods();
		for(int i=0;i<goodslist.size();i++) {
			GoodsName.add(i, goodslist.get(i).getProductName());
			System.out.println(goodslist.get(i).getProductName());
			System.out.println(GoodsName.get(i));			
			GoodsID.add(i, goodslist.get(i).getGoodsID());
			GoodsPrice.add(i, goodslist.get(i).getValue());
			NUM.add(i, goodslist.get(i).getGoodsNum());
		}
		
				
		JButton jb = new JButton("返回");
		JButton jb1 = new JButton("购买");
		
		Container container = this.getContentPane();
		container.setLayout(new GridLayout(1,4,20,100));
		
		JPanel p1 = new JPanel(new GridLayout(GoodsName.size()+2,1));
		JPanel p2 = new JPanel(new GridLayout(GoodsName.size()+2,1));
		JPanel p3 = new JPanel(new GridLayout(GoodsName.size()+2,1));
		JPanel p4 = new JPanel(new GridLayout(GoodsName.size()+2,1));
		
		for(int i=0;i<GoodsID.size()+2;i++) {
			if(i==0){
				p1.add(new JLabel("商品编号"));
			}
			else if(i==GoodsID.size()+1) {
				p1.add(jb1);
			}
			else {
				p1.add(new JLabel(GoodsID.get(i-1)));
			}
			
		}
		for(int i=0;i<GoodsName.size()+1;i++) {
			if(i==0){
				p2.add(new JLabel("商品名称"));
			}
			else {
				p2.add(new JLabel(GoodsName.get(i-1)));
			}
		}
		for(int i=0;i<GoodsPrice.size()+1;i++) {
			if(i==0){
				p3.add(new JLabel("商品价格"));
			}
			else {
				p3.add(new JLabel(GoodsPrice.get(i-1)));
			}
		
		}
		for(int i=0;i<NUM.size()+2;i++) {
			if(i==0){
				p4.add(new JLabel("商品数量"));
			}
			else if(i==NUM.size()+1) {
				p4.add(jb);
			}
			else {
				p4.add(new JLabel(NUM.get(i-1)));
			}
		}
		
		jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					new ShopPage();
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
		
		jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
            		for(int i=0;i<goodslist.size();i++) {
            			shopsrv = new ShopSrvImpl("mike");
            			shopsrv.buyAllGoods(GoodsName.get(i), "mike");
            		}
            		
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	JOptionPane.showMessageDialog(null, "购买成功");
            	
            }
        });
		
		container.add(p1);
		container.add(p2);
		container.add(p3);
		container.add(p4);
		
		this.setVisible(true);
		this.setSize(500, 350);
		this.setTitle("虚拟校园商店购物车");
		this.setLocation(200, 200);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	
	}
	public static void main(String[] args) {
		try {
			new MyGoods();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
