package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao{

	//取得したクラス番号、学生情報をもとにクラス情報を返すメソッド
	public ClassNum get(String class_num, School school)throws Exception{
		//クラス番号インスタンスを初期化
		ClassNum classNum=new ClassNum();
		Connection con=getConnection();
		PreparedStatement st=null;

		try{
			st=con.prepareStatement("select * from class_num where class_num=? and school_cd=?");
			st.setString(1, class_num);
			st.setString(2, school.getCd());
			ResultSet rSet=st.executeQuery();
			//学校Daoを初期化
			SchoolDao sDao=new SchoolDao();
			if (rSet.next()){
				//リザルトセットが存在する場合
				//クラス番号インスタンスに検索結果をセット
				classNum.setClassNum(rSet.getString("class_num"));
				classNum.setSchoolCd(sDao.get(rSet.getString("school_cd")));
			} else{
				//リザルトセットが存在しない場合
				//クラス番号インスタンスにnullをセット
				classNum=null;
			}
		}catch (Exception e){
			throw e;
		}finally{
			//プリペアードステートメントを閉じる
			if (st !=null){
				try{
					st.close();
				} catch (SQLException sqle){
					throw sqle;
				}
			}
			//コネクションを閉じる
			if (con != null){
				try{
					con.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}
		}
		return classNum;
	}


	//学校を指定してクラス番号の一覧を取得するメソッド
	public List<String> filter(School school)throws Exception{
		//リストを初期化
		List<String> list=new ArrayList<>();
		Connection con=getConnection();

		PreparedStatement st =con.prepareStatement("select class_num from class_num where school_cd=? order by class_num");
		st.setString(1, school.getCd());
		ResultSet rSet=st.executeQuery();

		//リザルトセットを全権走査
		while (rSet.next()){
			//リストにクラス番号を追加
			list.add(rSet.getString("class_num"));
		}
		//プリペアードステートメントを閉じる
		st.close();
		//コネクションを閉じる
		con.close();

		return list;
	}

	//クラス番号の取得
	public List<ClassNum> getAllClassNums() throws Exception{
		List<ClassNum> list=new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("select * from class_num order by class_num");
		ResultSet rs=st.executeQuery();

		while (rs.next()){
			ClassNum c=new ClassNum();
			c.setClassNum(rs.getString("class_num"));
			list.add(c);
		}
		con.close();
		st.close();

		return list;
	}

	public List<ClassNum> getClassNum(School school) throws Exception{
		List<ClassNum> list=new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement("select * from class_num where school_cd=? order by class_num");
		st.setString(1, school.getCd());

		System.out.println(school.getCd());

		ResultSet rs=st.executeQuery();

		while (rs.next()){
			ClassNum classNum=new ClassNum();
			School sch=new School();
			sch.setCd(rs.getString("school_cd"));
			System.out.println(rs.getString("school_cd"));
			classNum.setSchool(school);
			classNum.setClassNum(rs.getString("class_num"));
			System.out.println(rs.getString("class_num"));

			list.add(classNum);
		}

		rs.close();
		st.close();

		return list;


	}

}
