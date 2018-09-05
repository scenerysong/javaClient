package VC.client.bz.Impl;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import VC.common.Goods;
import VC.common.GoodsMessage;
import VC.common.MessageType;

public class ShopSrvImpl extends ClientSrvImpl {

	public ShopSrvImpl() {
		super();
	}
	public ShopSrvImpl(String username) {
		super(username);
	}
	public ShopSrvImpl(String username, Socket socket) {
		super(username, socket);
	}

	public List<Goods> getAllGoods() throws ClassNotFoundException, IOException {

		List<Goods> retGoodslist = new ArrayList<Goods>();
		String type = MessageType.CMD_GET_ALL_GOODS;
		GoodsMessage sendmsg = new GoodsMessage();
		sendmsg.setType(type);
		sendmsg.setID(getUseraccount());
		
		this.SendMessage(sendmsg);

		GoodsMessage rcvmsg = new GoodsMessage();
		rcvmsg = (GoodsMessage) this.ReceiveMessage();
		retGoodslist = rcvmsg.getGoodslist();

		return retGoodslist;
	}

	public List<Goods> getAllMyGoods() throws ClassNotFoundException, IOException {

		List<Goods> myGoodslist = new ArrayList<Goods>();
		String type = MessageType.CMD_GET_ALL_MYGOODS;
		GoodsMessage sendmsg = new GoodsMessage();
		sendmsg.setType(type);
		sendmsg.setID(getUseraccount());

		this.SendMessage(sendmsg);

		GoodsMessage rcvmsg = new GoodsMessage();
		rcvmsg = (GoodsMessage) this.ReceiveMessage();
		myGoodslist = rcvmsg.getGoodslist();

		return myGoodslist;
	}
	
	public boolean buyAllGoods(String goodsname,String username)throws ClassNotFoundException, IOException{
		boolean res = false;
		String type = MessageType.CMD_BUY_ALL_GOODS;
		GoodsMessage sendmsg = new GoodsMessage();
		sendmsg.setSender(username);
		sendmsg.setType(type);
		sendmsg.setProductName(goodsname);
		sendmsg.setID(getUseraccount());
		
		this.SendMessage(sendmsg);
		
		GoodsMessage rcvmsg = new GoodsMessage();
		rcvmsg = (GoodsMessage) this.ReceiveMessage();
		res = rcvmsg.isRes();
		return res;
	}
	public boolean addAllGoods(String mygoodsname,String num,String username)throws ClassNotFoundException, IOException{
		boolean res = false;
		String type = MessageType.CMD_ADD_ALL_GOODS;
		GoodsMessage sendmsg = new GoodsMessage();
		sendmsg.setSender(username);
		sendmsg.setType(type);
		sendmsg.setProductName(mygoodsname);
		sendmsg.setGoodsNum(num);
		sendmsg.setID(getUseraccount());
		
		this.SendMessage(sendmsg);
		
		GoodsMessage rcvmsg = new GoodsMessage();
		rcvmsg = (GoodsMessage) this.ReceiveMessage();
		res = rcvmsg.isRes();
		return res;
	}
}
