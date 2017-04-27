package com.cmsdb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ncsu.cms.bean.AdminBean;
import com.ncsu.cms.bean.BillBean;
import com.ncsu.cms.bean.CompletedCoursesBean;
import com.ncsu.cms.bean.CourseBean;
import com.ncsu.cms.bean.CourseListBean;
import com.ncsu.cms.bean.CourseOfferingBean;
import com.ncsu.cms.bean.CourseOfferingListBean;
import com.ncsu.cms.bean.CurrentCourseBean;
import com.ncsu.cms.bean.DepartmentBean;
import com.ncsu.cms.bean.EnrolledBean;
import com.ncsu.cms.bean.ErrorBean;
import com.ncsu.cms.bean.FacultyBean;
import com.ncsu.cms.bean.FacultyMapBean;
import com.ncsu.cms.bean.LocationBean;
import com.ncsu.cms.bean.LocationListBean;
import com.ncsu.cms.bean.LoginBean;
import com.ncsu.cms.bean.LoginResultBean;
import com.ncsu.cms.bean.RequestBean;
import com.ncsu.cms.bean.RequestListBean;
import com.ncsu.cms.bean.ScheduleBean;
import com.ncsu.cms.bean.SemesterBean;
import com.ncsu.cms.bean.StudentBean;
import com.ncsu.cms.bean.StudentListBean;
import com.cmsdb.DBConnection;
import com.cmsdb.DAO;
import com.cmsdb.HashUtil;

public class DAOImpl implements DAO{
	static Connection conn = null;
	static {
        conn = DBConnection.getConnection();
    }
	public LoginResultBean validateLogin(LoginBean loginData){
		
		LoginResultBean validationResult = null;
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.SELECT_VALIDATE_LOGIN);

			pstmt.setString(1, loginData.getUserName());
			pstmt.setString(2, HashUtil.generateSHA256Hash(loginData.getPassword()));
			
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
			{
				validationResult = new LoginResultBean(
						new ErrorBean(),
						rs.getString(1),
						rs.getString(2)
					);
			}
			else{
				validationResult = new LoginResultBean(
							new ErrorBean(ErrorBean.ERROR,"-1"),
							null,
							null
					);
			}
		} catch (SQLException e) {
			validationResult = new LoginResultBean();
			validationResult.setErrorData(new ErrorBean(ErrorBean.ERROR,"-1"));
			e.printStackTrace();
		}
		
		return validationResult;
	}
	public StudentBean getStudentDetails(int studentId){
		StudentBean student = null;
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.SELECT_STUDENT_DETAILS);

			pstmt.setInt(1, studentId);
			
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
			{
				student = new StudentBean(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getString(5),
							rs.getString(6),
							rs.getString(7),
							rs.getString(8),
							rs.getString(9),
							rs.getString(10)
						);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}
	
	public List<CurrentCourseBean> getCurrentCourses(int studentId){
		List<CurrentCourseBean> courseList = null;
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.SELECT_STUDENT_CURRENT_COURSE_LIST);

			pstmt.setInt(1, studentId);
			
			ResultSet rs = pstmt.executeQuery();

			courseList = new ArrayList<CurrentCourseBean>();
			
			while(rs.next())
			{
				CurrentCourseBean course  = new CurrentCourseBean(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							new LocationBean(
									rs.getString(5),
									rs.getString(6)
									),
							getCurrentSchedule(rs.getInt(4)),
							getCourseFaculty(rs.getInt(4))
						);
				courseList.add(course);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseList;
		
		
	}
	public List<ScheduleBean> getCurrentSchedule(int offeringId){
		List<ScheduleBean> scheduleList = null;
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.SELECT_COURSE_SCHEDULE);

			pstmt.setInt(1, offeringId);
			
			ResultSet rs = pstmt.executeQuery();

			scheduleList = new ArrayList<ScheduleBean>();
			
			while(rs.next())
			{
				ScheduleBean schedule  = new ScheduleBean(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3)
					);
				scheduleList.add(schedule);
			}

		} catch (SQLException e) {
			e.printStackTrace();}
		
		return scheduleList;
		
	}
	
	public LocationBean getCourseLocation(int offeringId){
		LocationBean location = null;
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.SELECT_OFFERING_LOCATION);

			pstmt.setInt(1, offeringId);
			
			ResultSet rs = pstmt.executeQuery();

			if(rs.next())
			{
				location = new LocationBean(
						rs.getString(1),
						rs.getString(2)						
						);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return location;
	}
	
	public List<FacultyBean> getCourseFaculty(int offeringId){
		List<FacultyBean> facultyList = null;
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.SELECT_COURSE_FACULTY_2);

			pstmt.setInt(1, offeringId);
			
			ResultSet rs = pstmt.executeQuery();

			facultyList = new ArrayList<FacultyBean>();
			
			while(rs.next())
			{
				FacultyBean faculty  = new FacultyBean(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3)
						);
				facultyList.add(faculty);
			}

		} catch (SQLException e) {
			e.printStackTrace();}
		
		return facultyList;
		
	}
	public void updateStudentDetails(String firstName, String lastName, String email, long phNo, String addr, int userid){
		try{
		PreparedStatement pstmt = conn.prepareStatement(QueryStrings.UPDATE_STUDENT_DETAILS);
		pstmt.setString(1, firstName);
		pstmt.setString(2, lastName); 
		pstmt.setString(3, email);
		pstmt.setLong(4, phNo);
		pstmt.setString(5, addr);
		pstmt.setInt(6, userid);
		
		
		int statusCode = pstmt.executeUpdate();
		
		
		
		System.out.println("ByeBro");
		System.out.println(statusCode);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void updateUserPassword(int studentId,String password){
		try{
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.UPDATE_USER_PASSWORD);
			pstmt.setString(1, password);
			pstmt.setInt(2, studentId);
			
			int statusCode = pstmt.executeUpdate();
			
			System.out.println(statusCode);
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void updateBillAmount(int studentId,int amount){
		try{
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.UPDATE_BILL_AMOUNT);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, studentId);
			System.out.println("Hi");
			int statusCode = pstmt.executeUpdate();
			System.out.println("Bye");
			
			System.out.println(statusCode);
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public List<CompletedCoursesBean> getCompletedCourses(int studentId){
		List<CompletedCoursesBean> courseList = null;
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.SELECT_STUDENT_COMPLETED_COURSE_LIST);

			pstmt.setInt(1, studentId);
			
			ResultSet rs = pstmt.executeQuery();

			courseList = new ArrayList<CompletedCoursesBean>();
			
			while(rs.next())
			{
				CompletedCoursesBean course  = new CompletedCoursesBean(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4)
						);
				courseList.add(course);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseList;
		
	}
	
	public BillBean getBill(int studentId){
        BillBean bill = null;
		
		try {
			
				PreparedStatement pstmt = conn.prepareStatement(QueryStrings.SELECT_BILL_AMOUNT);
	
				pstmt.setInt(1, studentId);
				
				ResultSet rs = pstmt.executeQuery();
	
				if(rs.next())
				{
					bill = new BillBean(
							rs.getString(1)
				     );
				}
		}
		
			catch (SQLException e) {
			e.printStackTrace();
		}
		return bill;
		
	}
	public List<CourseOfferingBean> getCourseOfferings(String departmentId){
		List<CourseOfferingBean> offeringList = null;
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.SELECT_COURSE_OFFERING_LIST);

			pstmt.setString(1, departmentId==null?"%":departmentId);
			
			ResultSet rs = pstmt.executeQuery();

			offeringList = new ArrayList<CourseOfferingBean>();
			
			while(rs.next())
			{
			    List<FacultyBean> fList = null;
			    
				String facultyList = rs.getString(6);
				
				if(facultyList!=null)
				{
					fList = new ArrayList<FacultyBean>();
					
				    System.out.println("flist=="+facultyList);
					for(String faculty: facultyList.split(";"))
					{	
						String [] fName = faculty.split(",");
					
						fList.add(new FacultyBean(fName[0], fName[1]));
					}
				}
				CourseOfferingBean course  = new CourseOfferingBean(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getString(5),
							fList,
							rs.getString(7),
							rs.getString(8),
							rs.getString(9),
							rs.getString(10),
							rs.getString(11),
							rs.getString(12),
							rs.getString(13),
							rs.getString(14),
							rs.getString(15),
							rs.getString(16),
							rs.getString(18)
						);
				offeringList.add(course);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return offeringList;
		
		
	}
	

	public List<CourseOfferingBean> getStudentCurrentCourseDetailsList(String studentId){
		List<CourseOfferingBean> offeringList = null;
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.SELECT_STUDENT_CURRENT_COURSE_DETAILS_LIST);

			pstmt.setString(1, studentId);
			
			ResultSet rs = pstmt.executeQuery();

			offeringList = new ArrayList<CourseOfferingBean>();
			
			while(rs.next())
			{
			    List<FacultyBean> fList = null;
			    
				String facultyList = rs.getString(6);
				
				if(facultyList!=null)
				{
					fList = new ArrayList<FacultyBean>();
					
				    System.out.println("flist=="+facultyList);
					for(String faculty: facultyList.split(";"))
					{	
						String [] fName = faculty.split(",");
					
						fList.add(new FacultyBean(fName[0], fName[1]));
					}
				}
				CourseOfferingBean course  = new CourseOfferingBean(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getString(5),
							fList,
							rs.getString(7),
							rs.getString(8),
							rs.getString(9),
							rs.getString(10),
							rs.getString(11),
							rs.getString(12),
							rs.getString(13),
							rs.getString(14),
							rs.getString(15)
						);
				offeringList.add(course);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return offeringList;
		
		
	}
	
	public AdminBean getAdminDetails(int adminid){
		
		AdminBean admin = null;
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.SELECT_ADMIN_DETAILS);

			pstmt.setInt(1, adminid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				admin  = new AdminBean(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3)
						);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
		
		
	}
	public void updateAdminDetails(int adminId, String firstName, String lastName, String ssn){
		try{
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.UPDATE_ADMIN_DETAILS);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, ssn);
			pstmt.setInt(4, adminId);
			System.out.println("Hi");
			int statusCode = pstmt.executeUpdate();
			System.out.println("Bye");
			
			System.out.println(statusCode);
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		
		
	}
	public List<StudentListBean> getStudentList(String studentId){
		List<StudentListBean> studentList = null;
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.GET_STUDENT_LIST);
			
			System.out.println("studentId="+studentId);
			pstmt.setString(1, studentId==null?"%":studentId);

			ResultSet rs = pstmt.executeQuery();

			studentList = new ArrayList<StudentListBean>();
			
			while(rs.next())
			{
				StudentListBean student  = new StudentListBean(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getString(5),
							rs.getString(6),
							rs.getString(7),
							rs.getString(8),
							rs.getString(9),
							rs.getString(10),
							rs.getString(11),
							rs.getString(12),
							rs.getString(13)
						);
				studentList.add(student);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studentList;
		
	}
	public void insertStudent(int userId, String firstName, String lastName,  String email , String address, long phoneNumber, int deptId, double gpa,int resType, int levelClassification, String username, String password, int role) {
		try{
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.ADD_STUDENT);
			pstmt.setInt(1, userId);
			pstmt.setString(2, firstName);
			pstmt.setString(3, lastName);
			pstmt.setString(4, email);
			pstmt.setString(5, address);
			pstmt.setLong(6, phoneNumber);
			pstmt.setInt(7, deptId);
			pstmt.setDouble(8, gpa);			
			pstmt.setInt(9, resType);			
			pstmt.setInt(10, levelClassification);
			insertUser(userId,username,HashUtil.generateSHA256Hash(password), role);
			System.out.println("Hi");
			int statusCode = pstmt.executeUpdate();
			System.out.println("Bye");
			
			System.out.println(statusCode);
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	public void insertUser(int userId, String userName,String password,int role){
		try{
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.ADD_USER);
			pstmt.setInt(1, userId);
			pstmt.setString(2, userName);
			pstmt.setString(3, password);
			pstmt.setInt(4, role);

			int statusCode = pstmt.executeUpdate();

			
			System.out.println(statusCode);
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	public void insertCourse(String courseId, String courseName, int deptID ,int maxCredits, int courseType, int classificationLevel,int minCredits ){
		try{
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.ADD_COURSE);
			pstmt.setString(1, courseId);
			pstmt.setString(2, courseName);
			pstmt.setInt(3, deptID);
			pstmt.setInt(4, maxCredits);
			pstmt.setInt(5, courseType);
			pstmt.setInt(6, classificationLevel);
			pstmt.setInt(7,minCredits);
			int statusCode = pstmt.executeUpdate();

			
			System.out.println(statusCode);
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	public List<CourseListBean>  getCourseList(String courseId){
		List<CourseListBean> courseList = null;
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.GET_COURSE_LIST);

			pstmt.setString(1, courseId==null?"%":courseId);
			
			ResultSet rs = pstmt.executeQuery();

			courseList = new ArrayList<CourseListBean>();
			
			while(rs.next())
			{
				CourseListBean course  = new CourseListBean(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getString(5),
							rs.getString(6),
							rs.getString(7),
							rs.getString(8),
							rs.getString(9),
							rs.getString(10)
						);
				courseList.add(course);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseList;
		
	}
	public List<CourseOfferingListBean> getCourseOfferingList(String courseOfferingId){
		List<CourseOfferingListBean> courseOfferingList = null;
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.GET_COURSE_OFFERING_LIST);

			
			pstmt.setString(1, courseOfferingId==null?"%":courseOfferingId);
			ResultSet rs = pstmt.executeQuery();

			courseOfferingList = new ArrayList<CourseOfferingListBean>();
			
			while(rs.next())
			{
				CourseOfferingListBean courseOffering  = new CourseOfferingListBean(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getString(5),
							rs.getString(6),
							getCourseFaculty(rs.getInt(1)),
							getCurrentSchedule(rs.getInt(1))
							
							
						);
				courseOfferingList.add(courseOffering);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseOfferingList;
		
	}
	public void insertCourseOffering(int courseOfferingId, String courseId,int classSize,int waitlistSize, int semId, int locationId){
		try{
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.ADD_COURSE_OFFERING);
			pstmt.setInt(1, courseOfferingId);
			pstmt.setString(2, courseId);
			pstmt.setInt(3, classSize);
			pstmt.setInt(4, waitlistSize);
			pstmt.setInt(5, semId);
			pstmt.setInt(6, locationId);
			int statusCode = pstmt.executeUpdate();

			
			System.out.println(statusCode);
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void editStudent(int userId,String userName,String firstName, String lastName,  String email , String address, long phoneNumber, int deptId,int resType, int levelClassification) {
		try{
			
		PreparedStatement pstmt = conn.prepareStatement(QueryStrings.EDIT_STUDENT1);
			//pstmt.setInt(1, userId);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, email);
			pstmt.setString(4, address);
			pstmt.setLong(5, phoneNumber);
			pstmt.setInt(6, deptId);
			//pstmt.setDouble(7, gpa);			
			pstmt.setInt(7, resType);			
			pstmt.setInt(8, levelClassification);
			pstmt.setInt(9, userId);
			
			int statusCode = pstmt.executeUpdate();
			

			pstmt = conn.prepareStatement(QueryStrings.EDIT_STUDENT2);
			pstmt.setString(1, userName);
			pstmt.setInt(2, userId);
			statusCode = pstmt.executeUpdate();
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public void editCourse(String courseId, String courseName, int deptID ,int maxCredits, int courseType, int classificationLevel,int minCredits){
		try{
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.EDIT_COURSE);
			
			
			pstmt.setString(1, courseName);
			pstmt.setInt(2, deptID);
			pstmt.setInt(3, maxCredits);
			pstmt.setInt(4, courseType);
			pstmt.setInt(5, classificationLevel);	
			pstmt.setInt(6, minCredits);
			pstmt.setString(7, courseId);
			int statusCode = pstmt.executeUpdate();

			//
			System.out.println(statusCode);
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public void editCourseOffering(int courseOfferingId, String courseId,int classSize,int waitlistSize, int semId, int locationId){
		try{
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.EDIT_COURSE_OFFERING);
			pstmt.setInt(1, courseOfferingId);
			pstmt.setString(2, courseId);
			pstmt.setInt(3, classSize);
			pstmt.setInt(4, waitlistSize);
			pstmt.setInt(5, semId);
			pstmt.setInt(6, locationId);
			pstmt.setInt(7, courseOfferingId);
			int statusCode = pstmt.executeUpdate();

			
			System.out.println(statusCode);
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	public List<DepartmentBean> getDepartmentList(){
		List<DepartmentBean> departmentList = null;
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.SELECT_DEPARTMENT_LIST);

			ResultSet rs = pstmt.executeQuery();

			departmentList = new ArrayList<DepartmentBean>();
			
			while(rs.next())
			{
				DepartmentBean department  = new DepartmentBean(
							rs.getString(1),
							rs.getString(2)				
						);
				departmentList.add(department);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return departmentList;
	}
	
	public ErrorBean enrollStudentToCourse(String studentId, String offeringId, String credits, String dropOfferingId){
		ErrorBean errorData = null;
		CallableStatement callableStatement = null;

		try {
			callableStatement = conn.prepareCall(QueryStrings.CALL_ENROLL_STUDENT_TO_COURSE);

			callableStatement.setInt(1, Integer.parseInt(studentId));
			callableStatement.setInt(2, Integer.parseInt(offeringId));
			callableStatement.setInt(3, Integer.parseInt(credits));
			if(dropOfferingId==null || dropOfferingId.equals(""))
				callableStatement.setNull(4, java.sql.Types.INTEGER);
			else
				callableStatement.setInt(4, Integer.parseInt(dropOfferingId));
			
			callableStatement.registerOutParameter(5, java.sql.Types.INTEGER);
			callableStatement.registerOutParameter(6, java.sql.Types.VARCHAR);
			

			callableStatement.executeUpdate();
			
			errorData = new ErrorBean(
					callableStatement.getString(6),
					callableStatement.getString(5));

		} catch (SQLException e) {
			errorData = new ErrorBean("There was error in enrolling the student", "-50");
			e.printStackTrace();
		}
		return errorData;
	}

	public List<CourseBean> getDropCourseList(String studentId){

		List<CourseBean> offeringList = null;
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.SELECT_STUDENT_DROP_COURSE_DETAILS_LIST);

			pstmt.setString(1, studentId);
			pstmt.setString(2, studentId);
			
			ResultSet rs = pstmt.executeQuery();

			offeringList = new ArrayList<CourseBean>();
			
			while(rs.next())
			{
				CourseBean course  = new CourseBean(
							null,
							rs.getString(1),
							rs.getString(2),
							rs.getString(3)
						);
				offeringList.add(course);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return offeringList;
	}
	public ErrorBean dropStudentCourse(String studentId, String offeringId){
		ErrorBean errorData = null;
		CallableStatement callableStatement = null;

		try {
			callableStatement = conn.prepareCall(QueryStrings.CALL_DROP_COURSE);

			callableStatement.setInt(1, Integer.parseInt(studentId));
			callableStatement.setInt(2, Integer.parseInt(offeringId));
			
			callableStatement.executeUpdate();
			
			errorData = new ErrorBean("Offering Id " + offeringId + " was dropped successfully!","0");
			
		} catch (SQLException e) {
			errorData = new ErrorBean("There was an error in dropping the course. Please try later.", "-50");
			e.printStackTrace();
		}
		return errorData;
	}


	public List<RequestListBean> getSPPERMRequestList(String studentId){

		List<RequestListBean> offeringList = null;
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.SELECT_SPPERM_REQUEST_LIST);

			pstmt.setString(1, studentId);
			
			ResultSet rs = pstmt.executeQuery();

			offeringList = new ArrayList<RequestListBean>();
			
			while(rs.next())
			{
				RequestListBean request  = new RequestListBean(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getString(5)
						);
				offeringList.add(request);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return offeringList;
	}
	public ErrorBean requestSpecialPermission(String studentId, String offeringId, String creditCount){

		ErrorBean errorData = null;
		try{
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.REQUEST_SPECIAL_PERMISSION);
			pstmt.setInt(1, Integer.parseInt(studentId));
			pstmt.setInt(2, Integer.parseInt(creditCount));
			pstmt.setInt(3, Integer.parseInt(offeringId));
			
			pstmt.execute();
			
			errorData = new ErrorBean("Special Permission Request has been placed successfully","0");
			
		} catch (SQLException e) {
			errorData = new ErrorBean("There was an error in requesting for permission. Please try later.", "-50");
			e.printStackTrace();
		}
		return errorData;
	}
	public List<DepartmentBean> getResidencyTypeList(){
		List<DepartmentBean> departmentList = null;
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.SELECT_DEPARTMENT_LIST);

			ResultSet rs = pstmt.executeQuery();

			departmentList = new ArrayList<DepartmentBean>();
			
			while(rs.next())
			{
				DepartmentBean department  = new DepartmentBean(
							rs.getString(1),
							rs.getString(2)				
						);
				departmentList.add(department);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return departmentList;
	}
	public List<DepartmentBean> getLevelClassificationList(){
		List<DepartmentBean> departmentList = null;
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.SELECT_DEPARTMENT_LIST);

			ResultSet rs = pstmt.executeQuery();

			departmentList = new ArrayList<DepartmentBean>();
			
			while(rs.next())
			{
				DepartmentBean department  = new DepartmentBean(
							rs.getString(1),
							rs.getString(2)				
						);
				departmentList.add(department);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return departmentList;
	}
	
	public List<RequestBean> getRequestDetails(){
		List<RequestBean> requestList = null;
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.GET_REQUEST_DETAILS);
			
			ResultSet rs = pstmt.executeQuery();
            
			requestList = new ArrayList<RequestBean>();
			
			while(rs.next())
			{
				RequestBean department  = new RequestBean(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getString(5),
							rs.getString(6),
							rs.getString(7),
							rs.getString(8),
							rs.getString(9)
						);
				requestList.add(department);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requestList;
		
	}
	public void approveRequest(int requestId, Date date, int adminId){
		CallableStatement callableStatement = null;

		try {
			callableStatement = conn.prepareCall(QueryStrings.APPROVE_REQUEST);

			callableStatement.setInt(1,requestId);
			callableStatement.setInt(2, adminId);

			callableStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void declineRequest(int requestId, Date date, int adminId){
		try{
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.DECLINE_REQUEST);
			
			pstmt.setDate(1, date);
			pstmt.setInt(2, adminId);
			pstmt.setInt(3,requestId); 
			
			
			int statusCode = pstmt.executeUpdate();
			
			
			
			System.out.println("ByeBro");
			System.out.println(statusCode);
			}
			catch(SQLException e){
				e.printStackTrace();
			}
	}
	public List<SemesterBean> getSemesterList(String semesterId) {
		
		List<SemesterBean> semesterList = null;
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.GET_SEMESTER_LIST);
			pstmt.setString(1, semesterId==null?"%":semesterId);
			ResultSet rs = pstmt.executeQuery();

			semesterList = new ArrayList<SemesterBean>();
			
			while(rs.next())
			{
				SemesterBean semester  = new SemesterBean(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getString(5),
							rs.getString(6)

						);
				semesterList.add(semester);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return semesterList;
	}
	public void enrollFromRequest(String userId, String offeringId, String creditCount){
		try{
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.ENROLL_STUDENT);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, offeringId);
			pstmt.setString(3, creditCount);

			int statusCode = pstmt.executeUpdate();

			
			System.out.println(statusCode);
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public LocationListBean getLocation(String offeringId){
		LocationListBean location = null;
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.SELECT_LOCATION);
			pstmt.setString(1, offeringId);
			ResultSet rs = pstmt.executeQuery();

		
			if(rs.next())
			{
				 location  = new LocationListBean(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3)

						);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return location;
	}
	

		
	public String getSemesterId(String courseOfferingId){
		String semesterId = null;
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.SELECT_LOCATION);
			pstmt.setString(1, courseOfferingId);
			ResultSet rs = pstmt.executeQuery();

		
			if(rs.next())
			{
				 semesterId = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return semesterId;
	}
	public void editCurrentSemester(String semesterId, String semesterType, String startDate, String endDate, String courseAddDeadline, String courseDropDeadline){
		try{
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.EDIT_CURRENT_SEMESTER);
			pstmt.setString(1, semesterType);
			pstmt.setString(2, startDate); 
			pstmt.setString(3, endDate);
			pstmt.setString(4, courseAddDeadline);
			pstmt.setString(5, courseDropDeadline);
			pstmt.setString(6, semesterId);
			
			
			int statusCode = pstmt.executeUpdate();
			
			System.out.println(statusCode);
			}
			catch(SQLException e){
				e.printStackTrace();
			}
	}
	
	public void insertSemester(String semesterId, String semesterType, String startDate, String endDate, String courseAddDeadline, String courseDropDeadline){
		
		try{
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.ADD_SEMESTER);
			pstmt.setString(1, semesterId);
			pstmt.setString(2, semesterType);
			pstmt.setString(3, startDate); 
			pstmt.setString(4, endDate);
			pstmt.setString(5, courseAddDeadline);
			pstmt.setString(6, courseDropDeadline);
			
			
			
			int statusCode = pstmt.executeUpdate();
			
			System.out.println(statusCode);
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		
		
	}
	public void insertPrerequisite(String courseId, String typeId, String details ){
		
		try{
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.ADD_PREREQUISITE);
			pstmt.setString(1, courseId);
			pstmt.setString(2, typeId);
			pstmt.setString(3, details==null?" ":details);
			System.out.println("!!!!!!!details"+details);
			int statusCode = pstmt.executeUpdate();

			System.out.println(statusCode);
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public List<EnrolledBean> getEnrolledDetails(String userId){
		
		List<EnrolledBean> enrolledList = null;
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.GET_ENROLLED_DETAILS);
			pstmt.setString(1,userId);
			ResultSet rs = pstmt.executeQuery();

			enrolledList = new ArrayList<EnrolledBean>();
			
			while(rs.next())
			{
				EnrolledBean enroll  = new EnrolledBean(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4)
						);
				enrolledList.add(enroll);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enrolledList;
		
		
	}
	public void updateGrade(String grade, String userId, String offeringId){
		try{
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.SAVE_GRADES);
			pstmt.setString(1, grade);
			pstmt.setString(2, userId); 	
			pstmt.setString(3, offeringId);
			int statusCode = pstmt.executeUpdate();
			
			
			
			System.out.println("ByeBro");
			System.out.println(statusCode);
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		
		
	}
	public List<FacultyMapBean> getFacultyFullNameList(){
		
		List<FacultyMapBean> facultyList = null;
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.LIST_OF_FACULTY);
			
			ResultSet rs = pstmt.executeQuery();

			facultyList = new ArrayList<FacultyMapBean>();
			
			while(rs.next())
			{
				FacultyMapBean faculty  = new FacultyMapBean(
							rs.getString(1),
							rs.getString(2)

						);
				facultyList.add(faculty);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return facultyList;
		
		
		
	}
	public void addFaculty(String offeringId, String facultyId){
		try{
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.ADD_FACULTY);
			pstmt.setString(1, offeringId);
			pstmt.setString(2, facultyId);

			int statusCode = pstmt.executeUpdate();

			
			System.out.println(statusCode);
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void deleteFaculty(String offeringId, String facultyId){
		try{
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.DELETE_FACULTY);
			pstmt.setString(1, facultyId);
			pstmt.setString(2, offeringId);

			int statusCode = pstmt.executeUpdate();

			
			System.out.println(statusCode);
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	
	public void addSchedule(String offeringId, String day, String fromTime, String toTime ){
		try{
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.ADD_SCHEDULE);
			pstmt.setString(1, offeringId);
			pstmt.setString(2, day);
			pstmt.setString(3, fromTime);
			pstmt.setString(4, toTime);

			int statusCode = pstmt.executeUpdate();

			
			System.out.println(statusCode);
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void enforceDropDeeadline(){
		try{
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.ENFORCE_DROP_DEADLINE);
			int statusCode = pstmt.executeUpdate();			
			System.out.println(statusCode);
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	public List<String> getFacultyForOffering(){
		List<String> facultyList = null;
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.SELECT_FACULTY_LIST_NAME);
			ResultSet rs = pstmt.executeQuery();

			facultyList = new ArrayList<String>();
			
			while(rs.next())
			{
				String faculty  = rs.getString(1);
				facultyList.add(faculty);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return facultyList;
	}
	public void updateBillAmountForSemester(String userId, int payValue){
		try{
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.UPDATE_BILL_AMOUNT2);
			pstmt.setInt(1, payValue);
			pstmt.setString(2, userId);
			

			int statusCode = pstmt.executeUpdate();

			
			System.out.println(statusCode);
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		
	}
	public void updateGPA(String offeringId, String userId){
		try{
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.UPDATE_GPA);
			pstmt.setString(1, offeringId);
			pstmt.setString(2, userId);
			pstmt.setString(3, userId);
			

			int statusCode = pstmt.executeUpdate();

			
			System.out.println(statusCode);
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public int getUserDetails(String userName){
		int userId = Integer.MIN_VALUE;
		try{
			PreparedStatement pstmt = conn.prepareStatement(QueryStrings.GET_USER_DETAILS);
			
			pstmt.setString(1, userName);
			

			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				userId = rs.getInt(1);
			}
				 
		
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return userId;
	}
	public static void main(String[] args) {
		//new DAOImpl().validateLogin(new LoginBean("ashis",HashUtil.generateSHA256Hash("root")));
		
		//System.out.println(new DAOImpl().getStudentDetails(6).getFirstName());
		
		//System.out.println(new DAOImpl().getCurrentCourses(6).get(0).getOfferingId());
		
		//System.out.println(new DAOImpl().getCurrentSchedule(1).get(0).getToTime()); 
		//System.out.println(new DAOImpl().getCourseLocation(3).getRoomNo());
		//System.out.println(new DAOImpl().getCourseFaculty(3).get(2).getFacultyFirstName());
		//new DAOImpl().updateStudentDetails("TankiBuoy", "Tanksali", "prtanki@ncsu.edu",120,"2516 Avent Ferry Rd",6);
		//System.out.println(new DAOImpl().getCompletedCourses(6).get(0).getGrade());
		//new DAOImpl().updateUserPassword(9, "hermoinie");
		//System.out.println(new DAOImpl().getBill(6).getBillAmount());
		//new DAOImpl().updateBillAmount(6,1800);
	     //new DAOImpl().updateAdminDetails(7, "Hugh \"Wolwerine\"","Jackman" , "63");
		//System.out.println(new DAOImpl().getStudentList().get(0).getMaxCredits());
		//new DAOImpl().insertStudent(200, "aairstName", "a", "email", "address", 2112, 1, 1, 1, 1, "aaa", "aa", 1);
		//System.out.println(new DAOImpl().getAdminDetails(7).getFirstName());

		//System.out.println(new DAOImpl().getAdminDetails(7).getFirstName());
		//System.out.println(new DAOImpl().getCourseOfferingList(null);
		/*Iterator<CourseOfferingListBean> it = new DAOImpl().getCourseOfferingList(null).iterator();
		while(it.hasNext())
			System.out.println(it.next().getCourseId());*/
		//new DAOImpl().editCourse("CSC517", courseName, deptID, creditCount, courseType, classificationLevel);
		//System.out.println(new DAOImpl().getRequestDetails().get(0).getStudentName());
		//System.out.println(new DAOImpl().getSemesterList("1").get(0).getCourseAddDeadline());
		//System.out.println(new DAOImpl().getCourseList(null).get(0).getClassificationLevel());
       // new DAOImpl().editCourse("CSC517", "OODD", 1, 2, 1, 1, 1);	
		//System.out.println(new DAOImpl().getCourseList("CS421").get(0).getCourseName());
		//new DAOImpl().insertPrerequisite("22", "CS515", "2", "CS510");
		//System.out.println(new DAOImpl().getEnrolledDetails("200").get(0).getUserId());
		//new DAOImpl().updateGrade("B-", "200");
		//System.out.println(new DAOImpl().getFacultyFullNameList().get(0));
		/*ErrorBean errorData = new DAOImpl().enrollStudentToCourse(5, 4, 3, -1);
		
		System.out.println("message=" +errorData.getErrorMessage());
		System.out.println("code="+errorData.getErrorCode());*/
		//ErrorBean errorData = new DAOImpl().enrollStudentToCourse("5", "7", "3", null);
		//ErrorBean errorData = new DAOImpl().dropStudentCourse(5, 8);
		//System.out.println("message=" +errorData.getErrorMessage());
		//System.out.println("code="+errorData.getErrorCode());
	    //System.out.println(new DAOImpl().getFacultyForOffering().get(1));
		//System.out.println(new DAOImpl().getCourseOfferingList(null).get(0).getFaculty().get(0).getFirstName());
		System.out.println(new DAOImpl().getCourseFaculty(1).get(1).getFacultyId());
		System.out.println(new DAOImpl().getUserDetails("alby"));
	}
	
}
