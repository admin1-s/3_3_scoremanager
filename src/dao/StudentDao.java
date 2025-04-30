package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {

    // 全件取得
    public List<Student> selectAll() throws Exception {
        List<Student> list = new ArrayList<>();
        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("SELECT * FROM STUDENT");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Student stu = new Student();
            stu.setNo(rs.getString("NO"));
            stu.setName(rs.getString("NAME"));
            stu.setEntYear(rs.getInt("ENT_YEAR"));
            stu.setClassNum(rs.getString("CLASS_NUM"));
            stu.setAttend("TRUE".equalsIgnoreCase(rs.getString("IS_ATTEND")));
            list.add(stu);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }

    // 入学年度の一覧を取得
    public List<Integer> selectEntYears() throws Exception {
        List<Integer> list = new ArrayList<>();
        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT DISTINCT ENT_YEAR FROM STUDENT ORDER BY ENT_YEAR DESC");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            list.add(rs.getInt("ENT_YEAR"));
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }

    // クラス番号の一覧を取得
    public List<String> selectClassNums() throws Exception {
        List<String> list = new ArrayList<>();
        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT DISTINCT CLASS_NUM FROM STUDENT ORDER BY CLASS_NUM");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            list.add(rs.getString("CLASS_NUM"));
        }

        rs.close();
        st.close();
        con.close();
        return list;
    }

    // 条件検索
    public List<Student> search(Integer entYear, String classNum, Boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        Connection con = getConnection();

        String sql = "SELECT * FROM STUDENT WHERE 1=1";
        List<Object> params = new ArrayList<>();

        if (entYear != null) {
            sql += " AND ENT_YEAR = ?";
            params.add(entYear);
        }
        if (classNum != null) {
            sql += " AND CLASS_NUM = ?";
            params.add(classNum);
        }
        if (isAttend != null) {
            sql += " AND IS_ATTEND = ?";
            params.add(isAttend ? "TRUE" : "FALSE");
        }

        PreparedStatement st = con.prepareStatement(sql);

        for (int i = 0; i < params.size(); i++) {
            st.setObject(i + 1, params.get(i));
        }

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Student stu = new Student();
            stu.setNo(rs.getString("NO"));
            stu.setName(rs.getString("NAME"));
            stu.setEntYear(rs.getInt("ENT_YEAR"));
            stu.setClassNum(rs.getString("CLASS_NUM"));
            stu.setAttend("TRUE".equalsIgnoreCase(rs.getString("IS_ATTEND")));
            list.add(stu);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }

    // 学生を追加するメソッド
    public int insert(Student student) throws Exception {
        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "INSERT INTO STUDENT (ENT_YEAR, NO, NAME, CLASS_NUM, IS_ATTEND) VALUES (?, ?, ?, ?, ?)"
        );
        st.setInt(1, student.getEntYear());
        st.setString(2, student.getNo());
        st.setString(3, student.getName());
        st.setString(4, student.getClassNum());
        st.setString(5, student.isAttend() ? "TRUE" : "FALSE");

        int line = st.executeUpdate();

        st.close();
        con.close();
        return line;
    }

    // 新規追加：Schoolを使わない filter メソッド
    public List<Student> filter(Integer entYear, String classNum, Boolean isAttend) throws Exception {
        return search(entYear, classNum, isAttend);
    }

    // 既存の School パラメータを含む filter メソッド（未使用でも一応残す）
    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
        return search(entYear, classNum, isAttend);
    }

    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
        return search(entYear, null, isAttend);
    }

    public List<Student> filter(School school, boolean isAttend) throws Exception {
        return search(null, null, isAttend);
    }


    //藤川追加
    public Student findByNo(String no) throws Exception{
		Student s=new Student();
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("select * from student where no=?");
		st.setString(1,no);
		ResultSet rs = st.executeQuery();

		while(rs.next()){
			s.setNo(rs.getString("no"));
			s.setName(rs.getString("name"));
			s.setEntYear(rs.getInt("ent_year"));
			s.setClassNum(rs.getString("class_num"));
			s.setAttend(rs.getBoolean("is_attend"));

			School school=new School();
			school.setCd(rs.getString("school_cd"));
			s.setSchool(school);
		}

		st.close();
		con.close();

		return s;
	}

}
