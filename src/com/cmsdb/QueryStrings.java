package com.cmsdb;

public class QueryStrings {
	public static final String SELECT_VALIDATE_LOGIN = "select USER_ID, ROLE from USERS where USERNAME=? and PASSWORD=?";
	
	public static final String SELECT_STUDENT_DETAILS = "SELECT "+
														"	USER_ID, FIRSTNAME, LASTNAME, PHONE_NUMBER, ADDRESS, EMAIL, GPA, DEPARTMENT_NAME,"+
														"	(SELECT DESCRIPTION FROM LEVEL_CLASSIFICATION_LOOKUP WHERE ID = LEVEL_CLASSIFICATION) LEVEL_CLASSIFICATION,"+
														"	(SELECT DESCRIPTION FROM RESIDENCY_TYPE_LOOKUP WHERE ID = RESIDENCY_TYPE) RESIDENCY_TYPE"+
														" FROM"+
														" 	STUDENT, DEPARTMENT"+
														" WHERE"+
														"	DEPARTMENT_ID = DEPT_ID AND"+
														"	USER_ID=?";
	public static final String SELECT_STUDENT_CURRENT_COURSE_LIST = "SELECT "+
																	"   D.DEPARTMENT_NAME, C.COURSE_ID, C.COURSE_NAME,O.OFFERING_ID, L.ROOM_NO, L.BUILDING"+
																	" FROM"+
																	"   DEPARTMENT D, COURSE C, COURSE_OFFERING O, ENROLLED_IN E, STUDENT S, LOCATION L"+
																	" WHERE"+
																	"   E.USER_ID=? AND E.ENROLLMENT_STATUS=1 AND S.USER_ID = E.USER_ID AND"+
																	"   E.OFFERING_ID = O.OFFERING_ID AND"+
																	"   L.LOCATION_ID = O.LOCATION_ID AND"+
																	"   O.COURSE_ID = C.COURSE_ID AND"+
																	"   C.DEPARTMENT_ID = D.DEPARTMENT_ID";	
	
	public static final String SELECT_COURSE_SCHEDULE = "SELECT "+ 
														"	TO_CHAR(Sch.FROM_TIME, 'HH:MI AM'), TO_CHAR(Sch.TO_TIME, 'HH:MI AM'), TO_CHAR(NEXT_DAY(TO_DATE('01-JAN-1999'), Sch.DAY), 'Day')"+
														" FROM "+
														"	OFFERING_SCHEDULE Sch,COURSE_OFFERING O "+
														" WHERE "+
														"	O.OFFERING_ID =? AND O.OFFERING_ID = Sch.OFFERING_ID "; 
	
	public static final String SELECT_OFFERING_LOCATION =   "SELECT "+ 
															"	Loc.ROOM_NO, Loc.BUILDING "+
															" FROM "+
															"	LOCATION Loc,COURSE_OFFERING O "+
															" WHERE "+
															"	O.OFFERING_ID =? AND O.LOCATION_ID = Loc.LOCATION_ID";

	public static final String SELECT_COURSE_FACULTY =   "SELECT "+ 
														 "	Fac.FIRST_NAME, Fac.LAST_NAME "+
														 " FROM "+
														 "	FACULTY Fac, FACULTY_LIST Flist, COURSE_OFFERING O "+
														 " WHERE "+
														 "	O.OFFERING_ID =? AND Flist.OFFERING_ID = O.OFFERING_ID AND Flist.FACULTY_ID = Fac.FACULTY_ID ";
	
	public static final String SELECT_COURSE_FACULTY_2 =   "SELECT "+ 
															 "	Fac.FACULTY_ID, Fac.FIRST_NAME, Fac.LAST_NAME "+
															 " FROM "+
															 "	FACULTY Fac, FACULTY_LIST Flist, COURSE_OFFERING O "+
															 " WHERE "+
															 "	O.OFFERING_ID =? AND Flist.OFFERING_ID = O.OFFERING_ID AND Flist.FACULTY_ID = Fac.FACULTY_ID ";
	
	
	public static final String UPDATE_STUDENT_DETAILS = "UPDATE STUDENT Stu "+
														"SET Stu.FIRSTNAME=?, Stu.LASTNAME=?, Stu.EMAIL=?, "+ 
														" Stu.PHONE_NUMBER=?, Stu.ADDRESS=? "+
														"WHERE Stu.USER_ID =? ";

	public static final String SELECT_STUDENT_COMPLETED_COURSE_LIST =   "SELECT "+
																		" Dept.DEPARTMENT_NAME, C.COURSE_ID,C.COURSE_NAME, En.GRADE "+
																		"FROM "+
																		" DEPARTMENT Dept, COURSE C, COURSE_OFFERING O, ENROLLED_IN En, STUDENT S "+
																		"WHERE "+
																		" En.USER_ID=? AND En.ENROLLMENT_STATUS=3 AND En.USER_ID=S.USER_ID AND En.OFFERING_ID = O.OFFERING_ID AND O.COURSE_ID = C.COURSE_ID "+
																		" AND C.DEPARTMENT_ID =Dept.DEPARTMENT_ID";
	
	public static final String UPDATE_USER_PASSWORD = "UPDATE USERS U "+
			  										  "	SET U.PASSWORD=? "+
			  										  "WHERE "+ 
			  										  " U.USER_ID=?";
	
	public static final String SELECT_ADMIN_DETAILS= "SELECT "+
													 "  FIRSTNAME, LASTNAME, SSN "+
													 " FROM ADMIN "+
													 "WHERE USER_ID=? ";
	
	public static final String SELECT_BILL_AMOUNT = "SELECT BILL_AMOUNT"+
													" from ("+
													"   select bill.*, row_number() over (order by SEM_ID desc) as seqnum"+
													"   from BILL_PAYS bill"+
													"	WHERE USER_ID=?"+
													" ) bill"+
													" where seqnum = 1";
	
	public static final String UPDATE_BILL_AMOUNT =  "UPDATE "+
													 " BILL_PAYS Bill "+
													 "SET "+
													 " Bill.BILL_AMOUNT=? "+
													 "WHERE "+
													 " Bill.USER_ID=? ";
	public static final String SELECT_COURSE_OFFERING_LIST = 	" select "+
																"   offering.COURSE_ID,"+
																"   TO_CHAR(offering.OFFERING_ID,'000'),"+
																"   offering.COURSE_NAME,"+
																"   offering.MAX_CREDITS,"+
																"   offering.COURSE_DEPARTMENT,"+
																"   offering.FACULTY_LIST,"+
																"   offering.CLASS_SIZE,"+
																"   NVL(enrollment.ENROLLED_COUNT,0) ENROLLED_COUNT,"+
																"   offering.WAITLIST_SIZE,"+
																"   NVL(enrollment.WAITING_COUNT,0) WAITING_COUNT,"+
																"   offering.ROOM_NO,"+
																"   offering.BUILDING,"+
																"   offering.FROM_TIME,"+
																"   offering.TO_TIME,"+
																"   offering.DAY,"+
																"   offering.LEVEL_CLASSIFICATION_ID,"+
																"   offering.LEVEL_CLASSIFICATION,"+
																"   offering.COURSE_TYPE_LOOKUP_ID,"+
																"   offering.COURSE_TYPE"+
																" FROM"+
																"   (SELECT COURSE_ID,"+
																"     COURSE_NAME,"+
																"     MAX_CREDITS,"+
																"     DEPARTMENT_ID,"+
																"     COURSE_DEPARTMENT,"+
																"     OFFERING_ID,"+
																"     CLASS_SIZE,"+
																"     WAITLIST_SIZE,"+
																"     ROOM_NO,"+
																"     BUILDING,"+
																"     FROM_TIME,"+
																"     TO_TIME,"+
																"     DAY,"+
																"     LISTAGG( CASE WHEN FIRST_NAME IS NULL THEN NULL ELSE FIRST_NAME || ',' || LAST_NAME END, ';')"+
																"       WITHIN GROUP (ORDER BY FIRST_NAME) AS FACULTY_LIST,"+
																"     LEVEL_CLASSIFICATION_ID,"+
																"     LEVEL_CLASSIFICATION,"+
																"     COURSE_TYPE_LOOKUP_ID,"+
																"     COURSE_TYPE"+
																"     "+
																"   FROM("+
																"   SELECT COURSE.COURSE_ID,"+
																"     COURSE.COURSE_NAME,"+
																"     COURSE.MAX_CREDITS,"+
																"     DEPARTMENT.DEPARTMENT_ID,"+
																"     DEPARTMENT.DEPARTMENT_NAME COURSE_DEPARTMENT,"+
																"     COURSE_OFFERING.OFFERING_ID,"+
																"     COURSE_OFFERING.CLASS_SIZE,"+
																"     COURSE_OFFERING.WAITLIST_SIZE,"+
																"     LOCATION.ROOM_NO,"+
																"     LOCATION.BUILDING,"+
																"     TO_CHAR(OFFERING_SCHEDULE.FROM_TIME, 'HH:MI AM') FROM_TIME,"+
																"     TO_CHAR(OFFERING_SCHEDULE.TO_TIME, 'HH:MI AM') TO_TIME,"+
																"     LISTAGG(TRIM(TO_CHAR(NEXT_DAY(TO_DATE('01-JAN-1999'), OFFERING_SCHEDULE.DAY), 'DY')) , ',')"+
																"       WITHIN GROUP (ORDER BY OFFERING_SCHEDULE.DAY) AS DAY,"+
																"     FACULTY.FIRST_NAME,"+
																"     FACULTY.LAST_NAME,"+
																"     LEVEL_CLASSIFICATION_LOOKUP.ID LEVEL_CLASSIFICATION_ID,"+
																"     LEVEL_CLASSIFICATION_LOOKUP.DESCRIPTION LEVEL_CLASSIFICATION,"+
																"     COURSE_TYPE_LOOKUP.ID COURSE_TYPE_LOOKUP_ID,"+
																"     COURSE_TYPE_LOOKUP.DESCRIPTION AS COURSE_TYPE"+
																"   FROM COURSE"+
																"   INNER JOIN COURSE_OFFERING"+
																"   ON COURSE.COURSE_ID = COURSE_OFFERING.COURSE_ID"+
																"   INNER JOIN COURSE_TYPE_LOOKUP"+
																"   ON COURSE_TYPE_LOOKUP.ID = COURSE.COURSE_TYPE"+
																"   INNER JOIN LEVEL_CLASSIFICATION_LOOKUP"+
																"   ON LEVEL_CLASSIFICATION_LOOKUP.ID = COURSE.CLASSIFICATION_LEVEL"+
																"   LEFT JOIN OFFERING_SCHEDULE"+
																"   ON COURSE_OFFERING.OFFERING_ID = OFFERING_SCHEDULE.OFFERING_ID"+
																"   LEFT JOIN FACULTY_LIST"+
																"   ON COURSE_OFFERING.OFFERING_ID = FACULTY_LIST.OFFERING_ID"+
																"   LEFT JOIN FACULTY"+
																"   ON FACULTY.FACULTY_ID = FACULTY_LIST.FACULTY_ID"+
																"   INNER JOIN LOCATION"+
																"   ON LOCATION.LOCATION_ID = COURSE_OFFERING.LOCATION_ID"+
																"   INNER JOIN SEMESTER"+
																"   ON SEMESTER.SEMESTER_ID = COURSE_OFFERING.SEM_ID"+
																"   INNER JOIN DEPARTMENT"+
																"   ON DEPARTMENT.DEPARTMENT_ID = COURSE.DEPARTMENT_ID"+
																"   WHERE"+
																"   SEMESTER.COURSE_ADD_DEADLINE > SYSDATE"+
																"   group by (COURSE.COURSE_ID,"+
																"     COURSE.COURSE_NAME,"+
																"     COURSE.MAX_CREDITS,"+
																"     DEPARTMENT.DEPARTMENT_ID,"+
																"     DEPARTMENT.DEPARTMENT_NAME,"+
																"     COURSE_OFFERING.OFFERING_ID,"+
																"     COURSE_OFFERING.CLASS_SIZE,"+
																"     COURSE_OFFERING.WAITLIST_SIZE,"+
																"     LOCATION.ROOM_NO,"+
																"     LOCATION.BUILDING,"+
																"     TO_CHAR(OFFERING_SCHEDULE.FROM_TIME, 'HH:MI AM'),"+
																"     TO_CHAR(OFFERING_SCHEDULE.TO_TIME, 'HH:MI AM'),"+
																"     FACULTY.FIRST_NAME,"+
																"     FACULTY.LAST_NAME,"+
																"     LEVEL_CLASSIFICATION_LOOKUP.ID,"+
																"     LEVEL_CLASSIFICATION_LOOKUP.DESCRIPTION,"+
																"     COURSE_TYPE_LOOKUP.ID,"+
																"     COURSE_TYPE_LOOKUP.DESCRIPTION)"+
																"     ) COURSES"+
																"     group by"+
																"     COURSE_ID,"+
																"     COURSE_NAME,"+
																"     MAX_CREDITS,"+
																"     DEPARTMENT_ID,"+
																"     COURSE_DEPARTMENT,"+
																"     OFFERING_ID,"+
																"     CLASS_SIZE,"+
																"     WAITLIST_SIZE,"+
																"     ROOM_NO,"+
																"     BUILDING,"+
																"     FROM_TIME,"+
																"     TO_TIME,"+
																"     DAY,"+
																"     LEVEL_CLASSIFICATION_ID,"+
																"     LEVEL_CLASSIFICATION,"+
																"     COURSE_TYPE_LOOKUP_ID,"+
																"     COURSE_TYPE"+
																"   ) offering"+
																" LEFT JOIN"+
																"   ("+
																"     select * from("+
																"       select OFFERING_ID,ENROLLMENT_STATUS"+
																"       from ENROLLED_IN"+
																"       where ENROLLMENT_STATUS IN(1,2)"+
																"     )"+
																"     pivot("+
																"       count(*)"+
																"       for ENROLLMENT_STATUS in(1 ENROLLED_COUNT,2 WAITING_COUNT)"+
																"     )"+
																"   ) enrollment"+
																" ON"+
																"   offering.OFFERING_ID = enrollment.OFFERING_ID"+
																" WHERE"+
																"   offering.DEPARTMENT_ID like ?";
	
	public static final String UPDATE_ADMIN_DETAILS =   "UPDATE ADMIN A "+
														" SET A.FIRSTNAME=?, A.LASTNAME=?, A.SSN = ? "+
														"WHERE "+
														" A.USER_ID =? ";
	
	public static final String GET_STUDENT_LIST =   "  SELECT STUDENT.USER_ID,"+
													" 	(SELECT USERNAME FROM USERS WHERE USER_ID=STUDENT.USER_ID) USERNAME,"+
													"   STUDENT.FIRSTNAME,"+
													"   STUDENT.LASTNAME,"+
													"   STUDENT.EMAIL,"+
													"   STUDENT.ADDRESS,"+
													"   STUDENT.PHONE_NUMBER,"+
													"   (SELECT DEPARTMENT_NAME FROM DEPARTMENT WHERE DEPARTMENT_ID = STUDENT.DEPT_ID),"+
													"   STUDENT.GPA,"+
													"   (SELECT DESCRIPTION FROM RESIDENCY_TYPE_LOOKUP WHERE ID = STUDENT.RESIDENCY_TYPE) RESIDENCY_TYPE,"+
													"   (SELECT DESCRIPTION FROM LEVEL_CLASSIFICATION_LOOKUP WHERE ID = STUDENT.LEVEL_CLASSIFICATION) LEVEL_CLASSIFICATION,"+
													" 	(SELECT DOB FROM USERS WHERE USER_ID = STUDENT.USER_ID),"+
													" 	BILL.BILL_AMOUNT"+
													" FROM STUDENT,"+
													" (SELECT USER_ID, BILL_AMOUNT,"+
													"     rank() over (partition by user_id order by sem_id desc) rnk"+
													"     FROM"+
													"     BILL_PAYS) BILL"+
													" WHERE"+
													" BILL.RNK = 1 AND"+
													" STUDENT.USER_ID = BILL.USER_ID AND"+
													" STUDENT.USER_ID LIKE ?";
	
	public static final String ADD_STUDENT = "INSERT "+
			 								 " INTO STUDENT "+
			 								 "(USER_ID, FIRSTNAME, LASTNAME, EMAIL, ADDRESS, PHONE_NUMBER, DEPT_ID, GPA, RESIDENCY_TYPE, LEVEL_CLASSIFICATION) "+
			 								 " VALUES (?,?,?,?,?,?,?,?,?,?)";
    
	public static final String ADD_USER = "INSERT "+
			 								 " INTO USERS "+
			 								 "(USER_ID, USERNAME, PASSWORD, ROLE) "+
			 								 " VALUES (?,?,?,?)";
	
	
	public static final String GET_COURSE_LIST =  	" SELECT COURSE.COURSE_ID,"+
													"   COURSE.COURSE_NAME,"+
													"	COURSE.DEPARTMENT_ID,"+
													"   (SELECT DEPARTMENT_NAME FROM DEPARTMENT WHERE DEPARTMENT_ID = COURSE.DEPARTMENT_ID),"+
													"   COURSE.MAX_CREDITS,"+
													"	COURSE.MIN_CREDITS,"+
													"	COURSE.COURSE_TYPE,"+
													"   (SELECT DESCRIPTION FROM COURSE_TYPE_LOOKUP WHERE ID = COURSE.COURSE_TYPE),"+	
													"	COURSE.CLASSIFICATION_LEVEL,"+
													"   (SELECT DESCRIPTION FROM LEVEL_CLASSIFICATION_LOOKUP WHERE ID = COURSE.CLASSIFICATION_LEVEL)"+
													" 	FROM COURSE"+
													" 	WHERE COURSE.COURSE_ID LIKE ?";
	
	public static final String ADD_COURSE = "INSERT "+
											" INTO COURSE "+
											"(COURSE_ID, COURSE_NAME, DEPARTMENT_ID, MAX_CREDITS, COURSE_TYPE, CLASSIFICATION_LEVEL,MIN_CREDITS) "+

											" VALUES (?,?,?,?,?,?,?)";
	
	public static final String GET_COURSE_OFFERING_LIST =  " SELECT COURSE_OFFERING.OFFERING_ID,"+
															"   COURSE_OFFERING.COURSE_ID,"+
															"   COURSE_OFFERING.CLASS_SIZE,"+
															"   COURSE_OFFERING.WAITLIST_SIZE,"+
															"   (SELECT SEMESTER_TYPE || ' ' || TO_CHAR(START_DATE, 'YYYY') FROM SEMESTER "+
															"	 WHERE SEMESTER_ID = COURSE_OFFERING.SEM_ID),"+
															"   (SELECT ROOM_NO || ' ' || BUILDING FROM LOCATION WHERE LOCATION_ID = COURSE_OFFERING.LOCATION_ID)"+
															" 	FROM COURSE_OFFERING"+
															" 	WHERE COURSE_OFFERING.OFFERING_ID LIKE ?";
	
	public static final String ADD_COURSE_OFFERING = "INSERT "+
													 " INTO COURSE_OFFERING "+
													 "(OFFERING_ID, COURSE_ID, CLASS_SIZE, WAITLIST_SIZE, SEM_ID, LOCATION_ID) "+
													 " VALUES (?,?,?,?,?,?)";
	
	public static final String EDIT_STUDENT1 = 	" UPDATE STUDENT S"+
				 								 " SET S.FIRSTNAME=?, S.LASTNAME=?,S.EMAIL=?,S.ADDRESS=?,S.PHONE_NUMBER=?,S.DEPT_ID=?,S.RESIDENCY_TYPE=?,S.LEVEL_CLASSIFICATION=?"+
				 								 " WHERE "+
				 								 " S.USER_ID =?";
				 								 
			 								 
	public static final String EDIT_STUDENT2 = 	" UPDATE USERS U SET U.USERNAME=? WHERE U.USER_ID=?";
	
	public static final String SELECT_DEPARTMENT_LIST = " SELECT"+
														"  DEPARTMENT.DEPARTMENT_ID,"+
														"  DEPARTMENT.DEPARTMENT_NAME"+
														" FROM DEPARTMENT";
	
	public static final String SELECT_LOCATION = " SELECT"+
													  " LOCATION.LOCATION_ID,"+
													  " LOCATION.ROOM_NO,"+
													  " LOCATION.BUILDING"+
													  " FROM LOCATION, COURSE_OFFERING"+
													  " WHERE LOCATION.LOCATION_ID=COURSE_OFFERING.LOCATION_ID AND COURSE_OFFERING.OFFERING_ID=?";
	
	public static final String SELECT_SEMESTER = " SELECT"+
													" COURSE_OFFERING.SEM_ID"+
													" FROM"+
													" COURSE_OFFERING"+
													" WHERE COURSE_OFFERING.OFFERING_ID=?";
	
	public static final String EDIT_COURSE = 	 " UPDATE COURSE C"+
												 " SET"+
												 " C.COURSE_NAME=?,C.DEPARTMENT_ID=?,C.MAX_CREDITS=?,C.COURSE_TYPE=?,C.CLASSIFICATION_LEVEL=?,C.MIN_CREDITS=?"+
												 " WHERE "+
												 " C.COURSE_ID =?";
	
	public static final String EDIT_COURSE_OFFERING = 	" UPDATE COURSE_OFFERING O"+
														 " SET O.OFFERING_ID=?, O.COURSE_ID=?,O.CLASS_SIZE=?,O.WAITLIST_SIZE=?,O.SEM_ID=?,O.LOCATION_ID=?"+
														 " WHERE "+
														 " O.OFFERING_ID =? ";
	
			 
	public static final String GET_REQUEST_DETAILS = "SELECT "+
													 " R.REQ_ID, R.USER_ID, S.USERNAME,  R.CREDIT_COUNT, A.USERNAME, R.REQUEST_DATE, R.UPDATE_DATE, R.OFFERING_ID, R.STATUS "+
													 " FROM REQUEST R"+
													 " JOIN USERS S"+
													 " ON R.USER_ID=S.USER_ID"+
													 " LEFT JOIN USERS A "+
													 " ON R.ADMIN_ID = A.USER_ID";
	
	public static final String APPROVE_REQUEST = "{call APPROVE_REQUEST(?,?)}";
	
	public static final String DECLINE_REQUEST = "UPDATE REQUEST R "+
			 									 " SET R.STATUS='DECLINED', R.UPDATE_DATE=?, R.ADMIN_ID=? "+
			 									 "WHERE "+
			 									 " R.REQ_ID=?";
	
	public static final String ENROLL_STUDENT = "INSERT "+
												" INTO ENROLLED_IN "+
												" (USER_ID,OFFERING_ID,GRADE,WAITLIST_NO,ENROLLMENT_STATUS,DROP_OFFERING_ID,CREDIT_COUNT) "+
												" VALUES (?, ?, 'F', 0, 1, NULL, ?)";
			

	public static final String ADD_SEMESTER = "INSERT "+
											  " INTO SEMESTER "+
											  "(SEMESTER_ID,SEMESTER_TYPE,START_DATE,END_DATE,COURSE_ADD_DEADLINE,COURSE_DROP_DEADLINE) "+
											  " VALUES (?,?,TO_DATE(?,'YYYY-MM-DD'),TO_DATE(?,'YYYY-MM-DD'),TO_DATE(?,'YYYY-MM-DD'),TO_DATE(?,'YYYY-MM-DD'))";
	
	public static final String GET_SEMESTER_LIST =  " SELECT SEMESTER.SEMESTER_ID,"+													
													"   SEMESTER.SEMESTER_TYPE,"+
													"   TO_CHAR(SEMESTER.START_DATE, 'YYYY-MM-DD'),"+
													"   TO_CHAR(SEMESTER.END_DATE, 'YYYY-MM-DD'),"+
													"   TO_CHAR(SEMESTER.COURSE_ADD_DEADLINE, 'YYYY-MM-DD'),"+
													"   TO_CHAR(SEMESTER.COURSE_DROP_DEADLINE, 'YYYY-MM-DD')"+
													" 	FROM SEMESTER"+
													" 	WHERE SEMESTER.SEMESTER_ID LIKE ?";
	
	public static final String EDIT_CURRENT_SEMESTER =" UPDATE SEMESTER"+
												" SET"+
												" SEMESTER.SEMESTER_TYPE=?, SEMESTER.START_DATE=TO_DATE(?,'YYYY-MM-DD'), SEMESTER.END_DATE=TO_DATE(?,'YYYY-MM-DD'),"+
												" SEMESTER.COURSE_ADD_DEADLINE=TO_DATE(?,'YYYY-MM-DD'), SEMESTER.COURSE_DROP_DEADLINE=TO_DATE(?,'YYYY-MM-DD')"+
												" WHERE"+
												" SEMESTER.SEMESTER_ID=?";
	
	public static String SEARCH_COURSE ="SELECT * FROM COURSE WHERE COURSE.COURSE_ID=?";
	
	public static String ADD_PREREQUISITE = "INSERT "+
											" INTO PREREQUISITE "+
											"(COURSE_ID, TYPE_ID, DETAILS) "+
											" VALUES (?,?,?)";
	
	
		public static String GET_ENROLLED_DETAILS = 
												" SELECT OFFERING_ID, CREDIT_COUNT, GRADE, USER_ID " +
												"FROM ENROLLED_IN " + 
												"WHERE USER_ID=? AND ENROLLMENT_STATUS=1";

		public static final String SAVE_GRADES = 
												"UPDATE ENROLLED_IN" + 
												" SET  GRADE=?" + 
												" WHERE USER_ID=? AND OFFERING_ID=?";
		
		public static final String UPDATE_GPA=  "UPDATE STUDENT SET GPA = "+ 
												" (SELECT AVG(GRADE_LOOKUP.GRADE_POINTS) "+
												" FROM ENROLLED_IN "+
												" INNER JOIN GRADE_LOOKUP "+
												" ON GRADE_LOOKUP.GRADE_LETTER = ENROLLED_IN.GRADE "+
												" INNER JOIN COURSE_OFFERING COURSE_OFFERING1 "+
												" ON COURSE_OFFERING1.OFFERING_ID = ENROLLED_IN.OFFERING_ID "+
												" INNER JOIN COURSE_OFFERING "+
												" ON COURSE_OFFERING1.SEM_ID = COURSE_OFFERING.SEM_ID "+
												" WHERE "+
												" COURSE_OFFERING.OFFERING_ID =? AND "+
												" ENROLLED_IN.USER_ID =?) "+
												" WHERE "+
												" USER_ID =? ";
		
		public static final String LIST_OF_FACULTY = " SELECT FACULTY.FIRST_NAME || ' ' || FACULTY.LAST_NAME, FACULTY.FACULTY_ID "+
											   " FROM FACULTY";
	
	
        public static final String ADD_FACULTY =  "INSERT "+
        									" INTO FACULTY_LIST "+
        									"(OFFERING_ID,FACULTY_ID) "+
        									" VALUES (?,?)";
        
        public static final String DELETE_FACULTY = " DELETE FROM FACULTY_LIST "+
        											" WHERE "+
        											" FACULTY_ID=? AND OFFERING_ID=? ";
        
        
        public static final String ADD_SCHEDULE = "INSERT "+
        									" INTO OFFERING_SCHEDULE "+
        									" (SCHEDULE_ID, OFFERING_ID, DAY, FROM_TIME, TO_TIME) "+
        									" VALUES((SELECT MAX(SCHEDULE_ID)+1 FROM OFFERING_SCHEDULE),"+
        									" ?,?,TO_TIMESTAMP(?, 'YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP(?, 'YYYY-MM-DD HH24:MI:SS')) ";
        
      public static final String ENFORCE_DROP_DEADLINE=" DELETE from ENROLLED_IN "+
    		  											" where user_id in (select B.USER_ID "+
    		  											" FROM BILL_PAYS B JOIN STUDENT S ON  B.USER_ID = S.USER_ID "+
    		  											" JOIN ENROLLED_IN E ON E.USER_ID = B.USER_ID "+
    		  											" WHERE B.BILL_AMOUNT > 0) ";
        
	public static final String SELECT_STUDENT_CURRENT_COURSE_DETAILS_LIST = "  SELECT COURSE_ID,"+
																			"     TO_CHAR(OFFERING_ID,'000'),"+
																			"     COURSE_NAME,"+
																			"     MAX_CREDITS,"+
																			"     COURSE_DEPARTMENT,"+
																			"     LISTAGG( CASE WHEN FIRST_NAME IS NULL THEN NULL ELSE FIRST_NAME || ',' || LAST_NAME END, ';')"+
																			"       WITHIN GROUP (ORDER BY FIRST_NAME) AS FACULTY_LIST,"+
																			"     ROOM_NO,"+
																			"     BUILDING,"+
																			"     FROM_TIME,"+
																			"     TO_TIME,"+
																			"     DAY,"+
																			"     LEVEL_CLASSIFICATION,"+
																			"     COURSE_TYPE,"+
																			"     ENROLLMENT_STATUS ENROLLMENT_STATUS_ID,"+
																			"     (SELECT DESCRIPTION FROM ENROLLMENT_STATUS_LOOKUP WHERE ID = ENROLLMENT_STATUS)  ||"+
																			"     (CASE ENROLLMENT_STATUS WHEN 2 THEN ' (' || WAITLIST_NO || '/' || WAITLIST_SIZE || ')' ELSE '' END) ENROLLMENT_STATUS"+
																			"   FROM("+
																			"   SELECT COURSE.COURSE_ID,"+
																			"     COURSE.COURSE_NAME,"+
																			"     COURSE.MAX_CREDITS,"+
																			"     DEPARTMENT.DEPARTMENT_ID,"+
																			"     DEPARTMENT.DEPARTMENT_NAME COURSE_DEPARTMENT,"+
																			"     COURSE_OFFERING.OFFERING_ID,"+
																			"     LOCATION.ROOM_NO,"+
																			"     LOCATION.BUILDING,"+
																			"     TO_CHAR(OFFERING_SCHEDULE.FROM_TIME, 'HH:MI AM') FROM_TIME,"+
																			"     TO_CHAR(OFFERING_SCHEDULE.TO_TIME, 'HH:MI AM') TO_TIME,"+
																			"     LISTAGG(TRIM(TO_CHAR(NEXT_DAY(TO_DATE('01-JAN-1999'), OFFERING_SCHEDULE.DAY), 'DY')) , ',')"+
																			"       WITHIN GROUP (ORDER BY OFFERING_SCHEDULE.DAY) AS DAY,"+
																			"     FACULTY.FIRST_NAME,"+
																			"     FACULTY.LAST_NAME,"+
																			"     LEVEL_CLASSIFICATION_LOOKUP.ID LEVEL_CLASSIFICATION_ID,"+
																			"     LEVEL_CLASSIFICATION_LOOKUP.DESCRIPTION LEVEL_CLASSIFICATION,"+
																			"     COURSE_TYPE_LOOKUP.ID COURSE_TYPE_LOOKUP_ID,"+
																			"     COURSE_TYPE_LOOKUP.DESCRIPTION AS COURSE_TYPE,"+
																			"     ENROLLED_IN.ENROLLMENT_STATUS,"+
																			"     ENROLLED_IN.WAITLIST_NO,"+
																			"     COURSE_OFFERING.WAITLIST_SIZE"+
																			"   FROM COURSE"+
																			"   INNER JOIN COURSE_OFFERING"+
																			"   ON COURSE.COURSE_ID = COURSE_OFFERING.COURSE_ID"+
																			"   INNER JOIN COURSE_TYPE_LOOKUP"+
																			"   ON COURSE_TYPE_LOOKUP.ID = COURSE.COURSE_TYPE"+
																			"   INNER JOIN LEVEL_CLASSIFICATION_LOOKUP"+
																			"   ON LEVEL_CLASSIFICATION_LOOKUP.ID = COURSE.CLASSIFICATION_LEVEL"+
																			"   LEFT JOIN OFFERING_SCHEDULE"+
																			"   ON COURSE_OFFERING.OFFERING_ID = OFFERING_SCHEDULE.OFFERING_ID"+
																			"   LEFT JOIN FACULTY_LIST"+
																			"   ON COURSE_OFFERING.OFFERING_ID = FACULTY_LIST.OFFERING_ID"+
																			"   LEFT JOIN FACULTY"+
																			"   ON FACULTY.FACULTY_ID = FACULTY_LIST.FACULTY_ID"+
																			"   INNER JOIN LOCATION"+
																			"   ON LOCATION.LOCATION_ID = COURSE_OFFERING.LOCATION_ID"+
																			"   INNER JOIN SEMESTER"+
																			"   ON SEMESTER.SEMESTER_ID = COURSE_OFFERING.SEM_ID"+
																			"   INNER JOIN DEPARTMENT"+
																			"   ON DEPARTMENT.DEPARTMENT_ID = COURSE.DEPARTMENT_ID"+
																			"   INNER JOIN ENROLLED_IN ON"+
																			"   ENROLLED_IN.OFFERING_ID = COURSE_OFFERING.OFFERING_ID"+
																			"   WHERE"+
																			"   ENROLLED_IN.USER_ID = ? AND"+
																			"   ENROLLED_IN.ENROLLMENT_STATUS IN(1,2)  "+
																			"   group by (COURSE.COURSE_ID,"+
																			"     COURSE.COURSE_NAME,"+
																			"     COURSE.MAX_CREDITS,"+
																			"     DEPARTMENT.DEPARTMENT_ID,"+
																			"     DEPARTMENT.DEPARTMENT_NAME,"+
																			"     COURSE_OFFERING.OFFERING_ID,"+
																			"     LOCATION.ROOM_NO,"+
																			"     LOCATION.BUILDING,"+
																			"     TO_CHAR(OFFERING_SCHEDULE.FROM_TIME, 'HH:MI AM'),"+
																			"     TO_CHAR(OFFERING_SCHEDULE.TO_TIME, 'HH:MI AM'),"+
																			"     FACULTY.FIRST_NAME,"+
																			"     FACULTY.LAST_NAME,"+
																			"     LEVEL_CLASSIFICATION_LOOKUP.ID,"+
																			"     LEVEL_CLASSIFICATION_LOOKUP.DESCRIPTION,"+
																			"     COURSE_TYPE_LOOKUP.ID,"+
																			"     COURSE_TYPE_LOOKUP.DESCRIPTION,"+
																			"     ENROLLED_IN.ENROLLMENT_STATUS,"+
																			"     ENROLLED_IN.WAITLIST_NO,   "+
																			"     COURSE_OFFERING.WAITLIST_SIZE)"+
																			"     ) COURSES"+
																			"     group by"+
																			"     COURSE_ID,"+
																			"     COURSE_NAME,"+
																			"     MAX_CREDITS,"+
																			"     DEPARTMENT_ID,"+
																			"     COURSE_DEPARTMENT,"+
																			"     OFFERING_ID,"+
																			"     ROOM_NO,"+
																			"     BUILDING,"+
																			"     FROM_TIME,"+
																			"     TO_TIME,"+
																			"     DAY,"+
																			"     LEVEL_CLASSIFICATION_ID,"+
																			"     LEVEL_CLASSIFICATION,"+
																			"     COURSE_TYPE_LOOKUP_ID,"+
																			"     COURSE_TYPE,"+
																			"     ENROLLMENT_STATUS,"+
																			"     WAITLIST_NO,"+
																			"     WAITLIST_SIZE";
	public static final String CALL_ENROLL_STUDENT_TO_COURSE = "{call ENROLL_STUDENT(?,?,?,?,?,?)}";
	public static final String CALL_DROP_COURSE = "{call DELETE_ENROLLMENT(?,?)}";
	public static final String REQUEST_SPECIAL_PERMISSION = " INSERT INTO REQUEST(USER_ID, CREDIT_COUNT, REQUEST_DATE, OFFERING_ID, STATUS)"+
															" VALUES ( ?, ?, SYSDATE, ?, 'PENDING')";
	

	public static final String SELECT_STUDENT_DROP_COURSE_DETAILS_LIST = 	" SELECT C.COURSE_ID, C.COURSE_NAME, E.OFFERING_ID"+
																			" FROM"+
																			" ENROLLED_IN E,"+
																			" COURSE_OFFERING O,"+
																			" COURSE C"+
																			" WHERE"+
																			" C.COURSE_ID = O.COURSE_ID AND"+
																			" O.OFFERING_ID = E.OFFERING_ID AND"+
																			" E.OFFERING_ID NOT IN (SELECT DROP_OFFERING_ID "+
																			" 						FROM ENROLLED_IN WHERE USER_ID = ? AND"+
																			"						ENROLLMENT_STATUS = 2 AND"+
																			"						DROP_OFFERING_ID IS NOT NULL) AND"+
																			" E.ENROLLMENT_STATUS = 1 AND"+
																			" E.USER_ID = ?";
	
	public static final String SELECT_SPPERM_REQUEST_LIST = " SELECT REQUEST.REQ_ID,"+
															"   COURSE_OFFERING.COURSE_ID,"+
															"   REQUEST.REQUEST_DATE,"+
															"   REQUEST.OFFERING_ID,"+
															"   REQUEST.STATUS"+
															" FROM REQUEST"+
															" INNER JOIN COURSE_OFFERING"+
															" ON COURSE_OFFERING.OFFERING_ID = REQUEST.OFFERING_ID WHERE"+
															" REQUEST.USER_ID = ?";
	
	public static final String SELECT_FACULTY_LIST_NAME =  " SELECT FACULTY.LAST_NAME || ' ' || FACULTY.FIRST_NAME "+
															" FROM FACULTY, FACULTY_LIST"+
															" WHERE FACULTY.FACULTY_ID = FACULTY_LIST.FACULTY_ID ";
	
	public static final String UPDATE_BILL_AMOUNT2 = " UPDATE BILL_PAYS "+
													 " SET BILL_AMOUNT = BILL_AMOUNT-? "+
													 " WHERE "+
													 " USER_ID =? AND "+
													 " SEM_ID = (SELECT MAX(SEM_ID) FROM SEMESTER) ";
	
	public static final String GET_USER_DETAILS = " SELECT USERS.USER_ID, USERS.ROLE, USERS.DOB "+
												  " FROM USERS "+
			                                      " WHERE "+
												  " USERS.USERNAME=? ";
	
	
}
