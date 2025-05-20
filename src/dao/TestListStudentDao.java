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


    public List<TestListStudent> postFilter(ResultSet rSet,Student student) {
        List<TestListStudent> result = new ArrayList<>();
        try {
            while (rSet.next()) {
                TestListStudent ts = new TestListStudent();
                ts.setSubjectName(rSet.getString("name"));
                ts.setSubjectCd(rSet.getString("subject_cd"));
                ts.setNum(rSet.getInt("no"));
                ts.setPoint(rSet.getInt("point"));
                result.add(ts);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<TestListStudent> filter(Student student) throws Exception{
            Connection con = getConnection();
            PreparedStatement st = con.prepareStatement("select b.name, a.subject_cd, a.no, a.point "
            		+ "from test as a "
            		+ "join subject as b "
            		+"on a.subject_cd = b.cd "
            		+"where a.student_no = ? ");

            st.setString(1, student.getNo());
            ResultSet rs=st.executeQuery();

            List<TestListStudent> list=postFilter(rs, student);
            st.close();
            con.close();
        return list;
    }

}
