package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.School;

public class SchoolDao extends Dao{
	/**
	 * getメソッド　学校コードを指定して学校インスタンスを一件取得する
	 */
	public School get(String cd) throws Exception{
		//学校インスタンスを初期化
		School school=new School();
		Connection con=getConnection();
		PreparedStatement st=null;

		try{
			st=con.prepareStatement("select * from school where cd=?");
			st.setString(1, cd);
			System.out.println("aiueo8");
			ResultSet rSet=st.executeQuery();

			System.out.println("aiue");
			if (rSet.next()){
				//リザルトセットが存在する場合
				//学校インスタンスに学校コードと学校名をセット
				school.setCd(rSet.getString("cd"));
				school.setName(rSet.getString("name"));
			} else{
				//存在しない場合
				school=null;
			}
			System.out.println("aiu");
		}catch (SQLException sqle){
			throw sqle;
		}finally{
			//プリペアードステートメントを閉じる
			if (st !=null){
				try{
					st.close();
					System.out.println("ai");
				} catch (SQLException sqle){
					throw sqle;
				}
			}
			//コネクションを閉じる
			if (con != null){
				try{
					con.close();
					System.out.println("a");
				}catch (SQLException sqle){
					throw sqle;
				}
			}
		}
		return school;
	}
}