package VC.client.vo;

import java.io.IOException;
import java.util.List;

import VC.common.Course;

/**
 * 选课模块功能实现,继承ClientSrv方法
 * @author Song
 *
 */
public interface CourseSrv {

	/**
	 * 返回所有课程信息,解析返回值得到相应课程
	 * @return List<Course>
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	List<Course> getAllCourse() throws IOException, ClassNotFoundException;

	/**
	 * 按照课程名称选课,用户为当前用户,默认没有重名课程
	 * @param coursename
	 * @return 是否成功选课
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	boolean addCourse(String coursename) throws ClassNotFoundException, IOException;

	/**
	 * 按照课程名称退课,用户为当前用户
	 * @param coursename
	 * @return 是否成功退课
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	boolean deleteCourse(String coursename) throws ClassNotFoundException, IOException;

	/**
	 * 返回当前用户所有已经选的课程,解析返回值的到课程
	 * @return List<Course>
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	List<Course> getallMyCourse() throws IOException, ClassNotFoundException;

}