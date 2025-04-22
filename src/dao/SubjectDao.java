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
		PreparedStatement st=con.prepareStatement("select * from student where school_cd=?");
		st.setString(1,cd);
		ResultSet rs = st.executeQuery();

		while (rs.next()){
			s.setCd(rs.getString("cd"));
			s.setName(rs.getString("name"));
			s.setSchool(school);
		}

		st.close();
		con.close();

		return s;
	}

	public List<Subject> filter(School school)throws Exception{
		List<Subject> list=new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("select * from subject where school_cd=?");
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

	public boolean save(Subject subject){

	}

	public boolean delete(Subject subject){

	}

}
