package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {
    private String baseSql =
        "SELECT subj.NAME AS subject_name, subj.CD AS subject_cd, t.NO AS num, t.POINT AS point " +
        "FROM TEST t " +
        "JOIN SUBJECT subj ON t.SUBJECT_CD = subj.CD AND t.SCHOOL_CD = subj.SCHOOL_CD " +
        "WHERE t.STUDENT_NO = ? " +
        "AND t.SCHOOL_CD = ? " +
        "AND t.CLASS_NUM = ? " +
        "AND EXISTS ( " +
        "    SELECT 1 FROM STUDENT s " +
        "    WHERE s.NO = t.STUDENT_NO " +
        "    AND s.ENT_YEAR = ? " +
        ")";

    public List<TestListStudent> postFilter(ResultSet rSet) {
        List<TestListStudent> result = new ArrayList<>();
        try {
            while (rSet.next()) {
                TestListStudent student = new TestListStudent();
                student.setSubjectName(rSet.getString("subject_name"));
                student.setSubjectCd(rSet.getString("subject_cd"));
                student.setNum(rSet.getInt("num"));
                student.setPoint(rSet.getInt("point"));
                result.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<TestListStudent> filter(Student student) {
        List<TestListStudent> result = new ArrayList<>();
        try (
            Connection con = getConnection();
            PreparedStatement st = con.prepareStatement(baseSql)
        ) {
            st.setString(1, student.getNo());
            st.setString(2, student.getSchool().getCd());
            st.setString(3, student.getClassNum());
            st.setInt(4, student.getEntYear());

            try (ResultSet rs = st.executeQuery()) {
                result = postFilter(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
