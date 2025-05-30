package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;


public class SubjectDao extends Dao{

	//cdに基づく科目情報の取得
	public Subject get(String cd, School school) throws Exception{
		Subject s=new Subject();
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("select * from subject where cd=?");
		st.setString(1,cd);
		ResultSet rs = st.executeQuery();

		while(rs.next()){
			s.setCd(rs.getString("cd"));
			s.setName(rs.getString("name"));
			school.setCd(rs.getString("school_cd"));
			s.setSchool(school);
		}

		st.close();
		con.close();

		return s;
	}

	//科目名一覧取得
	public List<Subject> getAllSubjects() throws Exception{
		List<Subject> list=new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("select * from subject order by cd");
		ResultSet rs=st.executeQuery();

		while (rs.next()){
			Subject s=new Subject();
			s.setCd(rs.getString("cd"));
			s.setName(rs.getString("name"));
			list.add(s);
		}

		con.close();
		st.close();
		return list;
	}

	//subject_cdに基づく科目情報の一覧取得
	public List<Subject> filter(School school)throws Exception{
		if (school == null) {
	        throw new IllegalArgumentException("school is null");
	    }

		List<Subject> list=new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("select * from subject where school_cd=? order by cd");
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

	//与えられた科目情報の更新または挿入
	public boolean save(Subject subject) throws Exception {
	    Connection con = getConnection();
	    PreparedStatement st;
	    ResultSet rs;

	    // 科目の存在チェック
	    st = con.prepareStatement("select * from subject where cd = ? and school_cd = ?");
	    st.setString(1, subject.getCd());
	    st.setString(2, subject.getSchool().getCd());
	    rs = st.executeQuery();

	    int line;

	    if (rs.next()) {
	        // 更新
	        st = con.prepareStatement("update subject set name = ? where cd = ? and school_cd = ?");
	        st.setString(1, subject.getName());
	        st.setString(2, subject.getCd());
	        st.setString(3, subject.getSchool().getCd());
	        line = st.executeUpdate();
	    } else {
	        // 追加
	        st = con.prepareStatement("insert into subject (cd, name, school_cd) values (?, ?, ?)");
	        st.setString(1, subject.getCd());
	        st.setString(2, subject.getName());
	        st.setString(3, subject.getSchool().getCd());
	        line = st.executeUpdate();
	    }

	    rs.close();
	    st.close();
	    con.close();

	    return line > 0;
	}

	//与えられた科目情報の削除
	public boolean delete(Subject subject) throws Exception{
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("delete from subject where name=? and cd=? and school_cd=?");
		st.setString(1, subject.getName());
		st.setString(2, subject.getCd());
		st.setString(3, subject.getSchool().getCd());
		int result=st.executeUpdate();

		st.close();
		con.close();

		return result>0;

	}

}
