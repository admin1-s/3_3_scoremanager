package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {
    private String baseSql =
        "SELECT s.ENT_YEAR, s.NO, s.NAME, s.CLASS_NUM, t.POINT, t.NO AS TEST_NO " +
        "FROM STUDENT s " +
        "LEFT JOIN TEST t ON s.NO = t.STUDENT_NO AND s.SCHOOL_CD = t.SCHOOL_CD AND t.SUBJECT_CD = ? " +
        "WHERE s.ENT_YEAR = ? AND s.CLASS_NUM = ? AND s.SCHOOL_CD = ? " +
        "ORDER BY s.NO, t.NO";

    public List<TestListSubject> postFilter(ResultSet rSet) {
        Map<String, TestListSubject> map = new LinkedHashMap<>();
        try {
            while (rSet.next()) {
                String studentNo = rSet.getString("NO");
                TestListSubject subject = map.get(studentNo);

                if (subject == null) {
                    subject = new TestListSubject();
                    subject.setEntYear(rSet.getInt("ENT_YEAR"));
                    subject.setStudentNo(studentNo);
                    subject.setStudentName(rSet.getString("NAME"));
                    subject.setClassNum(rSet.getString("CLASS_NUM"));
                    map.put(studentNo, subject);
                }

                int point = rSet.getInt("POINT");
                if (!rSet.wasNull()) {
                    int testNo = rSet.getInt("TEST_NO");
                    subject.putPoint(testNo, point);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(map.values());
    }

    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) {
        List<TestListSubject> result = new ArrayList<>();
        try (
            Connection con = getConnection();
            PreparedStatement st = con.prepareStatement(baseSql)
        ) {
            st.setString(1, subject.getCd());
            st.setInt(2, entYear);
            st.setString(3, classNum);
            st.setString(4, school.getCd());

            try (ResultSet rs = st.executeQuery()) {
                result = postFilter(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
