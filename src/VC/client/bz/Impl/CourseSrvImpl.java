package VC.client.bz.Impl;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import VC.common.Course;
import VC.common.CourseMessage;
import VC.common.MessageType;

public class CourseSrvImpl extends ClientSrvImpl{
	
	public CourseSrvImpl() {
		super();
	}
	
	public CourseSrvImpl(String user) {
		super(user);
	}
	public CourseSrvImpl(String user, Socket socket) {
		super(user, socket);
	}
	
	public List<Course> getAllCourse() throws IOException, ClassNotFoundException{
		
		List<Course> retCourselist = new ArrayList<Course>();
		String type = MessageType.CMD_GET_ALL_COURSE;
		CourseMessage sendmsg = new CourseMessage();
		sendmsg.setType(type);
		
		this.SendMessage(sendmsg);
		
		CourseMessage rcvmsg = new CourseMessage();
		rcvmsg = (CourseMessage) this.ReceiveMessage();
		retCourselist = rcvmsg.getCourselist();
		
		return retCourselist;
	}
	
	public boolean addCourse(String coursename) throws ClassNotFoundException, IOException {
		
		boolean res = false;
		String type = MessageType.CMD_ADD_ALL_COURSE;
		CourseMessage sendmsg = new CourseMessage();
		sendmsg.setType(type);
		sendmsg.setID(getUseraccount());
		sendmsg.setCourseName(coursename);
		
		this.SendMessage(sendmsg);
		
		CourseMessage rcvmsg = new CourseMessage();
		rcvmsg = (CourseMessage) this.ReceiveMessage();
		res = rcvmsg.isRes();
		return res;
	}
	
	public boolean deleteCourse(String coursename) throws ClassNotFoundException, IOException{
		boolean res = false;
		String type = MessageType.CMD_DELETE_ALL_COURSE;
		CourseMessage sendmsg = new CourseMessage();
		sendmsg.setType(type);
		sendmsg.setID(getUseraccount());
		sendmsg.setCourseName(coursename);

		//System.out.println("start send message");
		this.SendMessage(sendmsg);
		
		CourseMessage rcvmsg = new CourseMessage();
		rcvmsg = (CourseMessage) this.ReceiveMessage();
		res = rcvmsg.isRes();
		return res;
		
	}
	
	public List<Course> getallMyCourse() throws IOException, ClassNotFoundException{
		List<Course> myCourselist = new ArrayList<Course>();
		String type = MessageType.CMD_GET_ALL_MYCOURSE;
		CourseMessage sendmsg = new CourseMessage();
		sendmsg.setType(type);
		sendmsg.setID(this.getUseraccount());
		
		this.SendMessage(sendmsg);
		
		CourseMessage rcvmsg = new CourseMessage();
		rcvmsg = (CourseMessage) this.ReceiveMessage();
		myCourselist = rcvmsg.getCourselist();
		
		return myCourselist;
	}
}
