package dao;
import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.UserBean;

public class UsersDAO {
	
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static UsersDAO usersDAO;
	private UsersDAO(){ }

	public static UsersDAO getInstance() {
		if(usersDAO == null) {
			usersDAO= new UsersDAO();
		}
		return usersDAO;
	}

	public void setConnection(Connection con) {
		this.con= con;
		
	}

	public int insertAdminUsers(UserBean users) {
		String sql= "insert into users values(?, ?, ?, ?)";
		int insertCount= 0;
		
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, users.getId());
			pstmt.setString(2, users.getPass());
			pstmt.setString(3, users.getSalt());
			pstmt.setString(4, users.getName());
			insertCount= pstmt.executeUpdate();			
		}catch(Exception e) {
			System.out.println("회원가입 에러 " + e);
		}finally {
			close(pstmt);
		}
		return insertCount;
	}
	
	//id로 salt값 가져오기
	public String selectLoginSalt(String id) {
		UserBean user= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String result= "";
		try {
			pstmt= con.prepareStatement("select salt from users where id= ?");
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				result= rs.getString("salt");
			}
		}catch(Exception e) {
				e.printStackTrace();
		}finally {
			try {
				close(rs);
				close(pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}		
		return result;
	}

	//로그인 시 id 확인
	public String selectLoginId(UserBean user) {
		String loginId= null;
		try {
			pstmt= con.prepareStatement("select id from users where id= ? and passwd= ?");
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPass());
			rs= pstmt.executeQuery();
			//System.out.println(pstmt);
			if(rs.next()) {
				loginId= rs.getString("id");
			}
		}catch(Exception e) {
			System.out.println("로그인 에러" + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return loginId;
	}

	//로그인한 회원 불러오기
	public ArrayList<UserBean> selectUserInfo(String id) {
		
		String sql= "selec id, name from users where id= ?";
		ArrayList<UserBean> userInfo= new ArrayList<UserBean>(); 
		UserBean ub= new UserBean();
		
		try {
			pstmt= con.prepareStatement(sql);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				ub.setId(rs.getString("id"));
				ub.setName(rs.getString("name"));
				userInfo.add(ub);
			}
		}catch(Exception e) {
			System.out.println("회원정보 에러" + e);
		}finally {
			close(rs);
			close(pstmt);
		}		
		return userInfo;
	}

}
