package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Test;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    private String baseSql =
        "SELECT t.NO AS TEST_NO, t.POINT, sub.NAME AS SUBJECT_NAME, sub.CD AS SUBJECT_CD " +
        "FROM TEST t " +
        "JOIN SUBJECT sub ON t.SUBJECT_CD = sub.CD AND t.SCHOOL_CD = sub.SCHOOL_CD " +
        "WHERE t.STUDENT_NO = ? AND t.SCHOOL_CD = ? " +
        "ORDER BY t.SUBJECT_CD, t.NO";

    public List<TestListStudent> postFilter(ResultSet rSet) {
        List<TestListStudent> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                TestListStudent bean = new TestListStudent();
                bean.setSubjectName(rSet.getString("SUBJECT_NAME"));
                bean.setSubjectCd(rSet.getString("SUBJECT_CD"));
                bean.setNum(rSet.getInt("TEST_NO"));
                bean.setPoint(rSet.getInt("POINT"));
                list.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<TestListStudent> filter(Student student) {
        List<TestListStudent> result = new ArrayList<>();
        try (
            Connection con = getConnection();
            PreparedStatement st = con.prepareStatement(baseSql)
        ) {
            st.setString(1, student.getNo());
            st.setString(2, student.getSchool().getCd());

            try (ResultSet rs = st.executeQuery()) {
                result = postFilter(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Test> searchByStudentNo(String studentNo, String schoolCd) throws Exception {
        List<Test> list = new ArrayList<>();
        try (
            Connection con = getConnection();
            PreparedStatement st = con.prepareStatement(
                "SELECT * FROM test WHERE student_no = ? AND school_cd = ?"
            )
        ) {
            st.setString(1, studentNo);
            st.setString(2, schoolCd);
            try (ResultSet rs = st.executeQuery()) {
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
            }
        }
        return list;
    }

    public List<Test> searchByCondition(int entYear, String classNum, String subjectCd, String schoolCd) throws Exception {
        List<Test> list = new ArrayList<>();
        try (
            Connection con = getConnection();
            PreparedStatement st = con.prepareStatement(
                "SELECT * FROM test t JOIN student s ON t.student_no = s.no " +
                "WHERE s.ent_year = ? AND s.class_num = ? AND t.subject_cd = ? AND t.school_cd = ?"
            )
        ) {
            st.setInt(1, entYear);
            st.setString(2, classNum);
            st.setString(3, subjectCd);
            st.setString(4, schoolCd);

            try (ResultSet rs = st.executeQuery()) {
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
            }
        }
        return list;
    }
}
