package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao {

    private Dao dao;

    public ClassNumDao() {
        this.dao = new Dao();
    }

    public List<ClassNum> findAll() throws Exception {
        List<ClassNum> list = new ArrayList<>();
        String sql = "SELECT * FROM CLASS_NUM";

        try (Connection conn = dao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

        	while (rs.next()) {
        	    String schoolCd = rs.getString("SCHOOL_CD");
        	    School school = new School();
        	    school.setCd(schoolCd);

        	    ClassNum classNum = new ClassNum();
        	    classNum.setSchoolCd(school); // ここは ClassNum の setter
        	    classNum.setClassNum(rs.getString("CLASS_NUM"));
        	    list.add(classNum);
        	}


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
