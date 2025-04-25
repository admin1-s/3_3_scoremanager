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


public class TestDao extends Dao{
	public Test get(Student student,Subject subject,School school,int no) throws Exception{
		Student s=new Student();
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("select * from test");

		con.close();
		}


//filter
public List<Subject> filter(School school)throws Exception{
	List<Subject> list=new ArrayList<>();
	Connection con=getConnection();
	PreparedStatement st=con.prepareStatement("select * from test");
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
}