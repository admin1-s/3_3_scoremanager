package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;


public class SubjectDao extends Dao{

	public Subject get(String cd, School school) throws Exception{
		Subject s=new Subject();
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("select * from subject where cd=?");
		st.setString(1,cd);
		ResultSet rs = st.executeQuery();

		while(rs.next()){
			s.setCd(rs.getString("cd"));
			s.setName(rs.getString("name"));
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

	public Subject findByCd(String cd) throws Exception{
		Subject s=new Subject();
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("select * from subject where cd=?");
		st.setString(1,cd);
		ResultSet rs = st.executeQuery();

		while(rs.next()){
			s.setCd(rs.getString("cd"));
			s.setName(rs.getString("name"));

			School school=new School();
			school.setCd(rs.getString("school_cd"));
			s.setSchool(school);
		}

		st.close();
		con.close();

		return s;
	}

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

	public boolean save(Subject subject) throws Exception{
		Connection con=getConnection();
		String sql="insert into subject (cd, name, school_cd) values(?, ?, ?)";
		PreparedStatement st=con.prepareStatement(sql);
		st.setString(1, subject.getCd());
		st.setString(2, subject.getName());
		st.setString(3, subject.getSchool().getCd());
		int result=st.executeUpdate();

		st.close();
		con.close();

		return  result>0;

	}

	public boolean update(Subject subject)throws Exception{
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("update subject set name=? where cd=? and school_cd=?");
		st.setString(1, subject.getName());
		st.setString(2, subject.getCd());
		st.setString(3, subject.getSchool().getCd());
		int result=st.executeUpdate();

		st.close();
		con.close();

		return result>0;
	}

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
