package VC.client.vo;

import java.io.IOException;
import java.util.List;

import VC.common.Book;

/**
 * 
 * @author song
 * 图书馆模块功能实现,继承ClientSrv方法
 */
public interface LibrarySrv {

	/**
	 * 按书名查询书
	 * @param bookname
	 * @return 符合名称的所有书
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	List<Book> searchName(String bookname) throws IOException, ClassNotFoundException;

	/**
	 * 得到图书馆的所有书
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	List<Book> getAllBook() throws IOException, ClassNotFoundException;

	/**
	 * 按书名和用户名借书
	 * @param bookname
	 * @param username
	 * @return 是否借书成功
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	boolean addBook(String bookname) throws ClassNotFoundException, IOException;

	/**
	 * 按书名和用户名还书
	 * @param bookname
	 * @param username
	 * @return 是否还书成功
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	boolean returnBook(String bookname) throws ClassNotFoundException, IOException;

	/**
	 * 得到我借的所有书
	 * @return List
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	List<Book> getallMyBook() throws IOException, ClassNotFoundException;

}