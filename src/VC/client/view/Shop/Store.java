package VC.client.view.Shop;

import java.io.IOException;
import java.net.Socket;
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
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import VC.client.bz.Impl.ShopSrvImpl;
import VC.client.vo.ShopSrv;
import VC.common.Goods;

public class Store extends Application{
	public ShopSrv shopsrv;
	private ArrayList<TextField> tfs = new ArrayList<TextField>();
	/*
	 * 设置四个按钮，分别添加到相应界面
	 */
	private Button beSure = new Button("加入购物车");
	private Button buy =new Button("购买");
	private Button shoppingcart = new Button("我的购物车");
	private Button storepage= new Button("商店");
	private FlowPane pane = new FlowPane();	
	private DoubleProperty balance = new SimpleDoubleProperty();
	private String usrname;
	private Socket socket;
	public Store() {
		super();
	}
	public Store(String pusrname, Socket psocket) {
		super();
		this.setUsrname(pusrname);
		this.setSocket(psocket);
	}
	
	public void start(Stage Store) {
		shopsrv = new ShopSrvImpl(this.getUsrname(),this.getSocket());
		pane.setPadding(new Insets(50,50,50,50));
		pane.setHgap(15);
		pane.setVgap(10);
		/*
		 * 设置按钮“商店”的触发事件
		 */
		storepage.setOnAction(e->{
			try {
				show();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		pane.getChildren().addAll(storepage);
		/*
		 * 设置按钮“我的购物车”的触发事件
		 */
		shoppingcart.setOnAction(e->{
			try {
				storepageAction();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		pane.getChildren().addAll(shoppingcart);
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
            	primaryStage1.close();
            }
        });
		
		balance.addListener(new InvalidationListener() {
			public void invalidated(Observable ov) {
				Store.close();
			}
		});
		/*
		 * 设置商店界面的相关属性
		 */
		Scene scene = new Scene(pane,230,140);
		Store.setTitle("商店");
		Store.setScene(scene);
		Store.setResizable(false);
		Store.show();	
	}
	
	private Stage primaryStage = new Stage();
	
	private void show() throws ClassNotFoundException, IOException {
		/*
		 * 设置四个数组，存储来自数据库的数据信息
		 */
		List<String> goodsName = new ArrayList<String>();
		List<String> goodsID = new ArrayList<String>();
		List<String> goodsPrice = new ArrayList<String>();
		List<String> goodsNum = new ArrayList<String>();
		/*
		 * 接收来自数据库的数据信息，并且存储在相应的数组中
		 */
		shopsrv = new ShopSrvImpl(this.getUsrname(),this.getSocket());
		List<Goods> goodslist = shopsrv.getAllGoods();
		for(int i=0;i<goodslist.size();i++) {
			goodsName.add(i, goodslist.get(i).getProductName());
			goodsID.add(i, goodslist.get(i).getGoodsID());
			goodsPrice.add(i, goodslist.get(i).getValue());
			goodsNum.add(i,goodslist.get(i).getGoodsNum());
		}
		GridPane gridPane = new GridPane();
		gridPane.setHgap(20);
		gridPane.setVgap(20);
		/*
		 * 将数据库中商品信息显示在界面中
		 */
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
		/*
		 * 将按钮“购买”添加在商品界面上
		 */
		gridPane.add(beSure, 4, a+1);
		/*
		 * 设置按钮“购买”所触发的事件
		 */
		beSure.setOnAction(e->{
			try {
				join();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		/*
		 * 设置界面的相关属性
		 */
		GridPane.setHalignment(beSure, HPos.RIGHT);
		Scene scene = new Scene(gridPane,600,650);
		primaryStage.setTitle("Store");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	private Stage primaryStage1 = new Stage();
	private void storepageAction() throws ClassNotFoundException, IOException {
		/*
		 * 设置四个数组，存储数据库中的我的商品的相关信息
		 */
		List<String> GoodsName = new ArrayList<String>();
		List<String> GoodsID = new ArrayList<String>();
		List<String> GoodsPrice = new ArrayList<String>();
		List<String> NUM = new ArrayList<String>();
		/*
		 * 在数据库中的数据存在相应的数组
		 */
		shopsrv = new ShopSrvImpl(getUsrname(), getSocket());
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
		/*
		 * 将我所选的商品信息显示在界面上
		 */
		gridPane.add(new Label("商品编号"),0, 0);
		gridPane.add(new Label("商品名称"),2, 0);
		gridPane.add(new Label("商品价格"),4, 0 );
		gridPane.add(new Label("商品数目"),6, 0 );
		int a=GoodsID.size();
		for(int i=1;i<a+1;i++) {
			gridPane.add(new Label(GoodsID.get(i-1)),0, i);
			gridPane.add(new Label(GoodsName.get(i-1)),2, i);
			gridPane.add(new Label(GoodsPrice.get(i-1)),4, i);
			gridPane.add(new Label(NUM.get(i-1)),6, i);
		}
		Label money = new Label("余额");
        gridPane.add(money,4,a+1 );
        String balance = shopsrv.getMyBalance();
        gridPane.add(new Label(balance),6,a+1);
		gridPane.add(buy, 2, a+1);
		/*
		 * 设置按钮“购买”所触发的事件
		 */
		buy.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
            		for(int i=0;i<goodslist.size();i++) {
            			shopsrv = new ShopSrvImpl(getUsrname(), getSocket());
            			shopsrv.buyAllGoods(GoodsName.get(i));
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
		/*
		 * 设置界面的相关属性
		 */
		GridPane.setHalignment(beSure, HPos.RIGHT);
		Scene scene = new Scene(gridPane,600,650);
		primaryStage1.setTitle("Store");
		primaryStage1.setScene(scene);
		primaryStage1.show();
		
	}
	/*
	 * 点击按钮“加入购物车”，所选的商品添加到我的购物车中
	 */
	private void join() throws ClassNotFoundException, IOException {
		List<String> goodsName = new ArrayList<String>();
		shopsrv = new ShopSrvImpl(this.getUsrname(),this.getSocket());
		List<Goods> goodslist = shopsrv.getAllGoods();
		for(int i=0;i<goodslist.size();i++) {
			goodsName.add(i, goodslist.get(i).getProductName());
			System.out.println(goodslist.get(i).getProductName());
		}
		int b=tfs.size();
		List<String> NUM = new ArrayList<String>();
		List<String> NUM1 = new ArrayList<String>();
		List<String> Goodsname = new ArrayList<String>();
		for(int i=0;i<b;i++) {
			NUM.add(tfs.get(i).getText());
			System.out.println(tfs.get(i).getText());
		}
		try {
				for(int i=0;i<NUM.size();i++) {
					if(!NUM.get(i).equals("")) {
						Goodsname.add(goodsName.get(i));
						NUM1.add(NUM.get(i));
					}
				}
    			shopsrv.addAllGoods(Goodsname,NUM1);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
		}
	}	
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	public String getUsrname() {
		return usrname;
	}

	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
