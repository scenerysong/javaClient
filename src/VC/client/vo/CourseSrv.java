package VC.client.vo;

import java.io.IOException;
import java.util.List;

import VC.common.Course;

/**
 * ѡ��ģ�鹦��ʵ��,�̳�ClientSrv����
 * @author Song
 *
 */
public interface CourseSrv {

	/**
	 * �������пγ���Ϣ,��������ֵ�õ���Ӧ�γ�
	 * @return List<Course>
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	List<Course> getAllCourse() throws IOException, ClassNotFoundException;

	/**
	 * ���տγ�����ѡ��,�û�Ϊ��ǰ�û�,Ĭ��û�������γ�
	 * @param coursename
	 * @return �Ƿ�ɹ�ѡ��
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	boolean addCourse(String coursename) throws ClassNotFoundException, IOException;

	/**
	 * ���տγ������˿�,�û�Ϊ��ǰ�û�
	 * @param coursename
	 * @return �Ƿ�ɹ��˿�
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	boolean deleteCourse(String coursename) throws ClassNotFoundException, IOException;

	/**
	 * ���ص�ǰ�û������Ѿ�ѡ�Ŀγ�,��������ֵ�ĵ��γ�
	 * @return List<Course>
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	List<Course> getallMyCourse() throws IOException, ClassNotFoundException;

}