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

public class TestDao extends Dao {

    public List<Test> selectAll() throws Exception {
        List<Test> list = new ArrayList<>();
        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("SELECT * FROM TEST");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Test tes = new Test();

            Student s = new Student();
            s.setNo(rs.getString("STUDENT_NO"));
            tes.setStudent(s);

            Subject sub = new Subject();
            sub.setCd(rs.getString("SUBJECT_CD"));
            tes.setSubject(sub);

            School sch = new School();
            sch.setCd(rs.getString("SCHOOL_CD"));
            tes.setSchool(sch);

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

            Student s = new Student();
            s.setNo(rs.getString("STUDENT_NO"));
            test.setStudent(s);

            Subject sub = new Subject();
            sub.setCd(rs.getString("SUBJECT_CD"));
            test.setSubject(sub);

            School sch = new School();
            sch.setCd(rs.getString("SCHOOL_CD"));
            test.setSchool(sch);

            test.setNo(rs.getInt("NO"));
            test.setPoint(rs.getInt("POINT"));
            test.setClassNum(rs.getString("CLASS_NUM"));

            list.add(test);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }

    // 入学年度・クラス・科目による検索
    public List<Test> searchByCondition( String schoolCd,int entYear, String classNum, String subjectCd) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT t.*, s.name, s.ent_year, s.class_num " +
            "FROM test t " +
            "JOIN student s ON t.student_no = s.no " +
            "WHERE s.ent_year = ? AND s.class_num = ? " +
            "AND t.subject_cd = ? AND t.school_cd = ? " +
            "ORDER BY s.no, t.no"
        );

        st.setInt(1, entYear);
        st.setString(2, classNum);
        st.setString(3, subjectCd);
        st.setString(4, schoolCd);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Test test = new Test();

            Student s = new Student();
            s.setNo(rs.getString("student_no"));
            s.setName(rs.getString("name"));
            s.setEntYear(rs.getInt("ent_year"));
            s.setClassNum(rs.getString("class_num"));
            test.setStudent(s);

            Subject sub = new Subject();
            sub.setCd(rs.getString("subject_cd"));
            test.setSubject(sub);

            School sch = new School();
            sch.setCd(rs.getString("school_cd"));
            test.setSchool(sch);

            test.setNo(rs.getInt("no"));        // 1回目 or 2回目
            test.setPoint(rs.getInt("point"));  // 点数
            test.setClassNum(rs.getString("class_num"));

            list.add(test);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }


    // 学校による科目フィルター
    public List<Subject> filter(School school) throws Exception {
        List<Subject> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("SELECT * FROM subject WHERE school_cd = ?");
        st.setString(1, school.getCd());
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Subject s = new Subject();
            s.setCd(rs.getString("cd"));
            s.setName(rs.getString("name"));
            s.setSchool(school);
            list.add(s);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }

    public Test get(String studentNo, String subjectCd, School school) throws Exception {
        Test test = null;

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("SELECT * FROM test WHERE student_no = ? AND subject_cd = ? AND school_cd = ?");
        st.setString(1, studentNo);
        st.setString(2, subjectCd);
        st.setString(3, school.getCd());

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            test = new Test();
            test.setPoint(rs.getInt("point"));

            Student s = new Student();
            s.setNo(rs.getString("student_no"));
            s.setSchool(school);
            test.setStudent(s);

            Subject sub = new Subject();
            sub.setCd(rs.getString("subject_cd"));
            sub.setSchool(school);
            test.setSubject(sub);

            School sch = new School();
            sch.setCd(rs.getString("school_cd"));
            test.setSchool(sch);
        }

        rs.close();
        st.close();
        con.close();

        return test;
    }

    public boolean save(Test test) throws Exception {
        Connection con = getConnection();
        PreparedStatement st;
        ResultSet rs;

        // 成績の存在チェック
        st = con.prepareStatement("SELECT * FROM test WHERE student_no = ? AND subject_cd = ? AND school_cd = ?");
        st.setString(1, test.getStudent().getNo());
        st.setString(2, test.getSubject().getCd());
        st.setString(3, test.getSubject().getSchool().getCd());
        rs = st.executeQuery();

        if (rs.next()) {
            // 更新
            st = con.prepareStatement(
                "UPDATE test SET point = ? WHERE student_no = ? AND subject_cd = ? AND school_cd = ?"
            );
            st.setInt(1, test.getPoint());
            st.setString(2, test.getStudent().getNo());
            st.setString(3, test.getSubject().getCd());
            st.setString(4, test.getSubject().getSchool().getCd());
            st.executeUpdate();

        } else {
            // 挿入
        	st=con.prepareStatement(
        	"insert into test values(?, ?, ?, ?, ?, ?)");
        	st.setString(1, test.getStudent().getNo());
        	st.setString(2, test.getSubject().getCd());
        	st.setString(3, test.getSubject().getSchool().getCd());
        	st.setInt(4, test.getNo());
        	st.setInt(5, test.getPoint());
        	st.setString(6, test.getClassNum());

        }

        int line=st.executeUpdate();

        st.close();
        con.close();

        return line>0;
    }

    public boolean delete(Test test) throws Exception{
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("delete from test where student_no = ? and subject_cd = ? and school_cd = ? and no=?");
        st.setString(1, test.getStudent().getNo());
        st.setString(2, test.getSubject().getCd());
        st.setString(3, test.getSubject().getSchool().getCd());
        st.setInt(4, test.getNo());
		int result=st.executeUpdate();

		st.close();
		con.close();

		return result>0;

	}
}