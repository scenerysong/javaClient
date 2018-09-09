package VC.client.vo;

import java.io.IOException;
import java.util.List;

import VC.common.Goods;

public interface ShopSrv {

	List<Goods> getAllGoods() throws ClassNotFoundException, IOException;

	List<Goods> getAllMyGoods() throws ClassNotFoundException, IOException;

	boolean buyAllGoods(String goodsname) throws ClassNotFoundException, IOException;

	boolean addAllGoods(List<String> goodsName, List<String> Num) throws ClassNotFoundException, IOException;

}