package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;


public class TestDao extends Dao{
	public List<Test> selectAll() throws Exception {
        List<Test> list = new ArrayList<>();
        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("SELECT * FROM TEST");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Test tes = new Test();
            tes.setStudentNo(rs.getString("STUDENT_NO"));
            tes.setSubjectNo(rs.getString("SUBJECT_CD"));
            tes.setSchoolCd(rs.getString("SCHOOL_CD"));
            tes.setNo(rs.getInt("NO"));
            tes.setPoint(rs.getInt("POINT"));
            tes.setClassNum(rs.getString("CLASS_NUM"));
            list.add(tes);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }


	// 学生番号による検索
	public List<Test> searchByStudentNo(String studentNo, String schoolCd) throws Exception {
	    List<Test> list = new ArrayList<>();
	    Connection con = getConnection();
	    PreparedStatement st = con.prepareStatement(
	        "SELECT * FROM test WHERE student_no = ? AND school_cd = ?"
	    );
	    st.setString(1, studentNo);
	    st.setString(2, schoolCd);
	    ResultSet rs = st.executeQuery();

	    while (rs.next()) {
	        Test test = new Test();
	        test.setStudentNo(rs.getString("student_no"));
	        test.setSubjectNo(rs.getString("subject_cd"));
	        test.setSchoolCd(rs.getString("school_cd"));
	        test.setNo(rs.getInt("no"));
	        test.setPoint(rs.getInt("point"));
	        test.setClassNum(rs.getString("class_num"));
	        list.add(test);
	    }

	    rs.close();
	    st.close();
	    con.close();

	    return list;
	}

	// 入学年度・クラス・科目による検索
	public List<Test> searchByCondition(int entYear, String classNum, String subjectCd, String schoolCd) throws Exception {
	    List<Test> list = new ArrayList<>();
	    Connection con = getConnection();
	    PreparedStatement st = con.prepareStatement(
	        "SELECT * FROM test t JOIN student s ON t.student_no = s.no " +
	        "WHERE s.ent_year = ? AND s.class_num = ? AND t.subject_cd = ? AND t.school_cd = ?"
	    );
	    st.setInt(1, entYear);
	    st.setString(2, classNum);
	    st.setString(3, subjectCd);
	    st.setString(4, schoolCd);
	    ResultSet rs = st.executeQuery();

	    while (rs.next()) {
	        Test test = new Test();
	        test.setStudentNo(rs.getString("student_no"));
	        test.setSubjectNo(rs.getString("subject_cd"));
	        test.setSchoolCd(rs.getString("school_cd"));
	        test.setNo(rs.getInt("no"));
	        test.setPoint(rs.getInt("point"));
	        test.setClassNum(rs.getString("class_num"));
	        list.add(test);
	    }

	    rs.close();
	    st.close();
	    con.close();

	    return list;
	}

//filter
public List<Subject> filter(School school)throws Exception{
	List<Subject> list=new ArrayList<>();
	Connection con=getConnection();
	PreparedStatement st=con.prepareStatement("select * from test");
	st.setString(1, school.getCd());
	ResultSet rs=st.executeQuery();

	while(rs.next()){
		Subject s=new Subject();
		s.setCd(rs.getString("cd"));
		s.setName(rs.getString("name"));
		s.setSchool(school);
		list.add(s);
	}

	st.close();
	con.close();

	return list;
}

	public Test getTest(String studentNo, String subjectCd, School school) throws Exception{
		Test test=null;

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
			"select * from test where student_no=? and subject_cd=? and school_cd=?");
		st.setString(1, studentNo);
		st.setString(2, subjectCd);
		st.setString(3, school.getCd());

		ResultSet rs=st.executeQuery();

		if (rs.next()){
			test=new Test();
			test.setPoint(rs.getInt("point"));

			Student s=new Student();
			s.setNo(rs.getString("student_no"));
			s.setSchool(school);
			test.setStudent(s);

			Subject sub=new Subject();
			sub.setCd(rs.getString("subject_cd"));
			sub.setSchool(school);
			test.setSubject(sub);
		}

		rs.close();
		st.close();

		return test;
	}

	public boolean save(Test test) throws Exception{
		Connection con=getConnection();
		PreparedStatement st;

		//新規挿入
		st=con.prepareStatement(
		"insert into test values(?, ?, ?, ?, ?, ?)");
		st.setString(1, test.getStudent().getNo());
		st.setString(2, test.getSubject().getCd());
		st.setString(3, test.getSubject().getSchool().getCd());
		st.setInt(4, test.getNo());
		st.setInt(5, test.getPoint());
		st.setString(6, test.getClassNum());

		int line=st.executeUpdate();


		st.close();
		con.close();

		return line>0;
	}
}
