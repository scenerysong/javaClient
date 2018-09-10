package VC.client.vo;

import java.io.IOException;
import java.util.List;

import VC.common.Goods;

/**
 * 
 * @author song
 * 商店功能实现,继承ClientSrv类
 */
public interface ShopSrv {

	/**
	 * 得到所有商品,使用父类内置用户名
	 * @return List<Goods>
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	List<Goods> getAllGoods() throws ClassNotFoundException, IOException;

	/**
	 * 得到用户购物车所有商品,使用父类内置用户名
	 * @return List<Goods>
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	List<Goods> getAllMyGoods() throws ClassNotFoundException, IOException;

	/**
	 * 按商品名称清空购物车中的某种商品
	 * @param goodsname
	 * @return 是否清空成功
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	boolean buyAllGoods(String goodsname) throws ClassNotFoundException, IOException;

	/**
	 * 按商品名称及数量添加某种商品进入购物车
	 * @param goodsName
	 * @param Num
	 * @return 是否添加成功
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	boolean addAllGoods(List<String> goodsName, List<String> Num) throws ClassNotFoundException, IOException;

}