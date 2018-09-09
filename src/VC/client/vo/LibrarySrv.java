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
	 * 
	 * @param bookname
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	List<Book> searchName(String bookname) throws IOException, ClassNotFoundException;

	List<Book> getAllBook() throws IOException, ClassNotFoundException;

	boolean addBook(String bookname, String username) throws ClassNotFoundException, IOException;

	boolean returnBook(String bookname, String username) throws ClassNotFoundException, IOException;

	List<Book> getallMyBook() throws IOException, ClassNotFoundException;

}