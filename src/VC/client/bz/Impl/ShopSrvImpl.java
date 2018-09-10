package VC.client.bz.Impl;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import VC.client.vo.ShopSrv;
import VC.common.Goods;
import VC.common.GoodsMessage;
import VC.common.MessageType;

public class ShopSrvImpl extends ClientSrvImpl implements ShopSrv {

	public ShopSrvImpl() {
		super();
	}
	public ShopSrvImpl(String username) {
		super(username);
	}
	public ShopSrvImpl(String username, Socket socket) {
		super(username, socket);
	}

	/* (non-Javadoc)
	 * @see VC.client.bz.Impl.ShopSrv#getAllGoods()
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see VC.client.bz.Impl.ShopSrv#getAllMyGoods()
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see VC.client.bz.Impl.ShopSrv#buyAllGoods(java.lang.String)
	 */
	@Override
	public boolean buyAllGoods(String goodsname)throws ClassNotFoundException, IOException{
		boolean res = false;
		String type = MessageType.CMD_BUY_ALL_GOODS;
		GoodsMessage sendmsg = new GoodsMessage();
		sendmsg.setType(type);
		sendmsg.setProductName(goodsname);
		sendmsg.setID(getUseraccount());
		
		this.SendMessage(sendmsg);
		
		GoodsMessage rcvmsg = new GoodsMessage();
		rcvmsg = (GoodsMessage) this.ReceiveMessage();
		res = rcvmsg.isRes();
		return res;
	}
	/* (non-Javadoc)
	 * @see VC.client.bz.Impl.ShopSrv#addAllGoods(java.util.List, java.util.List)
	 */
	@Override
	public boolean addAllGoods(List<String> goodsName,List<String> Num)throws ClassNotFoundException, IOException{
		boolean res = false;
		String type = MessageType.CMD_ADD_ALL_GOODS;
		GoodsMessage sendmsg = new GoodsMessage();
		sendmsg.setType(type);
		sendmsg.setGoodsName(goodsName);
		sendmsg.setNum(Num);
		sendmsg.setID(getUseraccount());
		
		this.SendMessage(sendmsg);
		
		GoodsMessage rcvmsg = new GoodsMessage();
		rcvmsg = (GoodsMessage) this.ReceiveMessage();
		res = rcvmsg.isRes();
		return res;
	}
	
}
