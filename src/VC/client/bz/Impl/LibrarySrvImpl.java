package VC.client.bz.Impl;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import VC.common.BookMessage;
import VC.common.MessageType;
import VC.client.vo.LibrarySrv;
import VC.common.Book;

public class LibrarySrvImpl extends ClientSrvImpl implements LibrarySrv {

	public LibrarySrvImpl() {
		super();
	}
	public LibrarySrvImpl(String name) {
		super(name);
	}
	public LibrarySrvImpl(String name, Socket sock) {
		super(name,sock);
	}

	/* (non-Javadoc)
	 * @see VC.client.bz.Impl.LibrarySrv#searchName(java.lang.String)
	 */
	@Override
	public List<Book> searchName(String bookname) throws IOException, ClassNotFoundException {

		List<Book> retBooklist = new ArrayList<Book>();
		String type = MessageType.CMD_QUY_BOOK_BOOKNAME;
		BookMessage sendmsg = new BookMessage();
		sendmsg.setBookname(bookname);
		sendmsg.setType(type);

		this.SendMessage(sendmsg);

		BookMessage rcvmsg = new BookMessage();
		rcvmsg = (BookMessage) this.ReceiveMessage();
		retBooklist = rcvmsg.getBooklist();

		return retBooklist;
	}

	/* (non-Javadoc)
	 * @see VC.client.bz.Impl.LibrarySrv#getAllBook()
	 */
	@Override
	public List<Book> getAllBook() throws IOException, ClassNotFoundException {

		List<Book> Booklist = new ArrayList<Book>();
		String type = MessageType.CMD_GET_ALL_BOOK;
		BookMessage sendmsg = new BookMessage();
		sendmsg.setType(type);

		this.SendMessage(sendmsg);

		BookMessage rcvmsg = new BookMessage();
		rcvmsg = (BookMessage) this.ReceiveMessage();
		Booklist = rcvmsg.getBooklist();

		return Booklist;
	}

	/* (non-Javadoc)
	 * @see VC.client.bz.Impl.LibrarySrv#addBook(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean addBook(String bookname) throws ClassNotFoundException, IOException {

		boolean res = false;
		String type = MessageType.CMD_ADD_ALL_BOOK;
		BookMessage sendmsg = new BookMessage();
		sendmsg.setType(type);
		sendmsg.setID(this.getUseraccount());
		sendmsg.setBookname(bookname);

		this.SendMessage(sendmsg);

		BookMessage rcvmsg = new BookMessage();
		rcvmsg = (BookMessage) this.ReceiveMessage();
		res = rcvmsg.isRes();
		return res;
	}

	/* (non-Javadoc)
	 * @see VC.client.bz.Impl.LibrarySrv#returnBook(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean returnBook(String bookname) throws ClassNotFoundException, IOException {
		boolean res = false;
		String type = MessageType.CMD_DELETE_ALL_BOOK;
		BookMessage sendmsg = new BookMessage();
		sendmsg.setType(type);
		sendmsg.setID(this.getUseraccount());
		sendmsg.setBookname(bookname);

		this.SendMessage(sendmsg);

		BookMessage rcvmsg = new BookMessage();
		rcvmsg = (BookMessage) this.ReceiveMessage();
		res = rcvmsg.isRes();
		return res;

	}

	/* (non-Javadoc)
	 * @see VC.client.bz.Impl.LibrarySrv#getallMyBook()
	 */
	@Override
	public List<Book> getallMyBook() throws IOException, ClassNotFoundException {
		List<Book> myBooklist = new ArrayList<Book>();
		String type = MessageType.CMD_GET_ALL_MYBOOK;
		BookMessage sendmsg = new BookMessage();
		sendmsg.setType(type);
		sendmsg.setID(this.getUseraccount());

		this.SendMessage(sendmsg);

		BookMessage rcvmsg = new BookMessage();
		rcvmsg = (BookMessage) this.ReceiveMessage();
		myBooklist = rcvmsg.getBooklist();

		return myBooklist;
	}
}
