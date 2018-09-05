package VC.client.view.Shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import VC.client.bz.Impl.ShopSrvImpl;
import VC.common.Goods;


public class Store extends Application{
	
	public ShopSrvImpl shopsrv = new ShopSrvImpl();
	private ArrayList<TextField> tfs = new ArrayList<TextField>();
	private String[] NUM ;
	private Button beSure = new Button("加入购物车");
	private Button buy =new Button("购买");
	private Button shoppingcart = new Button("我的购物车");
	private Button storepage= new Button("商店");
	
	private FlowPane pane = new FlowPane();	
	private DoubleProperty balance = new SimpleDoubleProperty();
	
	public void start(Stage Store) {
		pane.setPadding(new Insets(50,50,50,50));
		pane.setHgap(15);
		pane.setVgap(10);
		
		storepage.setOnAction(e->{
			try {
				show();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		pane.getChildren().addAll(storepage);
		
		shoppingcart.setOnAction(e->{
			try {
				storepageAction();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		pane.getChildren().addAll(shoppingcart);

		balance.addListener(new InvalidationListener() {
			public void invalidated(Observable ov) {
				Store.close();
			}
		});
		
		Scene scene = new Scene(pane,230,140);
		Store.setTitle("虚拟校园商店");
		Store.setScene(scene);
		Store.setResizable(false);
		Store.show();	
	}
	
	private Stage primaryStage = new Stage();
	
	private void show() throws ClassNotFoundException, IOException {
		List<String> goodsName = new ArrayList<String>();
		List<String> goodsID = new ArrayList<String>();
		List<String> goodsPrice = new ArrayList<String>();
		List<String> goodsNum = new ArrayList<String>();
		
		List<Goods> goodslist = shopsrv.getAllGoods();
		
		for(int i=0;i<goodslist.size();i++) {
			goodsName.add(i, goodslist.get(i).getProductName());
			goodsID.add(i, goodslist.get(i).getGoodsID());
			goodsPrice.add(i, goodslist.get(i).getValue());
			goodsNum.add(i,goodslist.get(i).getGoodsNum());
		}

		Pane pane = new Pane();
		GridPane gridPane = new GridPane();
		gridPane.setHgap(20);
		gridPane.setVgap(20);
		
		gridPane.add(new Label("商品编号"),0, 0);
		gridPane.add(new Label("商品名称"),2, 0);
		gridPane.add(new Label("商品价格"),4,0 );
		gridPane.add(new Label("商品数目"),6, 0 );
		gridPane.add(new Label("购买数量"),8, 0);
		
		int a=goodsID.size();

		for(int i=1;i<a+1;i++) {
			gridPane.add(new Label(goodsID.get(i-1)),0, i);
			gridPane.add(new Label(goodsName.get(i-1)),2, i);
			gridPane.add(new Label(goodsPrice.get(i-1)),4, i);
			gridPane.add(new Label(goodsNum.get(i-1)),6, i);
			TextField goodsBuyNum = new TextField();
			tfs.add(goodsBuyNum);
			gridPane.add(goodsBuyNum, 8, i);
			
		}

		gridPane.add(beSure, 4, a+1);
	    
		int b=tfs.size();
		
		for(int i=0;i<b;i++) {
			NUM[i]=tfs.get(i).getText();
		}
		
		beSure.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
            		for(int i=0;i<b;i++) {
            			if(Integer.valueOf(NUM[i])!=0) {
            				shopsrv.addAllGoods(goodsName.get(i),NUM[i],"wls");
            			}
            			
            		}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				
				}
			}
        });
	
		GridPane.setHalignment(beSure, HPos.RIGHT);
		
		
		Scene scene = new Scene(gridPane,600,650);
		primaryStage.setTitle("Store");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	private void storepageAction() throws ClassNotFoundException, IOException {
		List<String> GoodsName = new ArrayList<String>();
		List<String> GoodsID = new ArrayList<String>();
		List<String> GoodsPrice = new ArrayList<String>();
		List<String> NUM = new ArrayList<String>();
		List<Goods> goodslist = shopsrv.getAllMyGoods();
		for(int i=0;i<goodslist.size();i++) {
			GoodsName.add(i, goodslist.get(i).getProductName());
			GoodsID.add(i, goodslist.get(i).getGoodsID());
			GoodsPrice.add(i, goodslist.get(i).getValue());
			NUM.add(i, goodslist.get(i).getGoodsNum());
		}
		
		GridPane gridPane = new GridPane();
		gridPane.setHgap(20);
		gridPane.setVgap(20);
		
		gridPane.add(new Label("商品编号"),0, 0);
		gridPane.add(new Label("商品名称"),2, 0);
		gridPane.add(new Label("商品价格"),4,0 );
		gridPane.add(new Label("商品数目"),6, 0 );
		gridPane.add(new Label("购买数量"),8, 0);
		
		int a=GoodsID.size();

		for(int i=1;i<a+1;i++) {
			gridPane.add(new Label(GoodsID.get(i-1)),0, i);
			gridPane.add(new Label(GoodsName.get(i-1)),2, i);
			gridPane.add(new Label(GoodsPrice.get(i-1)),4, i);
			gridPane.add(new Label(NUM.get(i-1)),6, i);
		}

		gridPane.add(buy, 2, a+1);
	
	    
		buy.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
            		for(int i=0;i<goodslist.size();i++) {
            			shopsrv.buyAllGoods(GoodsName.get(i), "wls");
            		}
            		
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
        });
	
		GridPane.setHalignment(beSure, HPos.RIGHT);
		
		
		Scene scene = new Scene(gridPane,600,650);
		primaryStage.setTitle("Store");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
