package dao;
import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.AdminEmpBean;
import vo.ReviewBean;
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
		String sql= "insert into users values(?, ?, ?, ?, 0)";
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

	//로그인한 관리자 불러오기
	public AdminEmpBean selectUserInfo(String id) {
		
		String sql= "select emp_id, emp_name from emp where emp_id= ?";
		AdminEmpBean empInfo= new AdminEmpBean();
		
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				empInfo.setEmp_id(rs.getString("emp_id"));
				empInfo.setEmp_name(rs.getString("emp_name"));		
			}
		}catch(Exception e) {
			System.out.println("회원정보 에러" + e);
		}finally {
			close(rs);
			close(pstmt);
		}	
		return empInfo;
	}
	//로그인한 회원 불러오기 UserBean 타입
	public UserBean selectUserInfo2(String id) {
		
		String sql= "select id, name from users where id= ?";
		UserBean userInfo= new UserBean();
		
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				userInfo.setId(rs.getString("id"));
				userInfo.setName(rs.getString("name"));			
			}
		}catch(Exception e) {
			System.out.println("회원정보 에러" + e);
		}finally {
			close(rs);
			close(pstmt);
		}	
		return userInfo;
	}
	
	//id 중복체크
	public boolean duplicationIdChk(String id) {
		
		String sql= "select id from users where id= ?";
		boolean selectVal= false;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			if(rs.next()) selectVal= true;
		}catch(Exception e) {
			System.out.println("중복체크 에러");
		}finally {
			close(rs);
			close(pstmt);
		}
		return selectVal;
	}
	//회원 탈퇴
	public int delecteUsers(String id, String passwdSalt) {
		
		String sql = "delete from users where id=? and passwd= ?";
		int delectCount= 0;
		
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwdSalt);
			delectCount= pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("회원탈퇴에서 오류" + e);
		}finally {
			close(pstmt);
		}
		return delectCount;
	}
	
	//내가 단 리뷰 
	public ArrayList<ReviewBean> selectUserReview(String id) {
		
		String sql= "select * from review where id= ?";
		ArrayList<ReviewBean> review= null;
		ReviewBean rb= new ReviewBean();
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				review= new ArrayList<ReviewBean>();
				rb.setPro_code(rs.getInt("pro_code"));
				rb.setTit_fg(rs.getInt("tit_fg"));
				rb.setSub1_fg(rs.getInt("sub1_fg"));
				rb.setSub2_fg(rs.getInt("sub2_fg"));
				rb.setSub3_fg(rs.getInt("sub3_fg"));
				rb.setCreate_dt(rs.getDate("create_dt"));
				rb.setCreate_id(rs.getString("create_dt"));
				review.add(rb);
			}
		}catch(Exception e) {
			System.out.println("내가 단 리뷰에서 에러" + e);
		}finally{
			close(rs);
			close(pstmt);
		}
		
		return review;
	}
	
	//내가 쓴 리뷰 몇개?
	public int getReviewCount(String id) {
		String sql= "select count(*) from review where create_id= ?";
		int ReviewCount= 0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			
			//쿼리 출력
			//System.out.println(pstmt);
			
			if(rs.next()) ReviewCount= rs.getInt(1);			
		}catch(Exception e) {
			System.out.println("내가 단 리뷰 개수에서 에러" + e);
		}finally{
			close(rs);
			close(pstmt);
		}
		return ReviewCount;
	}
	
	//내 정보 접근 시 입력한 비밀번호와 비교
	public String getUserPasswd(String id) {
		String sql= "select passwd from users where id= ?";
		String isPasswd= null;
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			if(rs.next()) isPasswd= rs.getString(1);
		}catch(Exception e) {
			
		}finally{
			close(rs);
			close(pstmt);
		}
		return isPasswd;
	}

	//비번, 이름 수정
	public int userNamePassModi(String id, String name, String passwdSalt, String salt){
		String sql= "update users set name= ?, passwd= ?, salt= ? where id= ?";
		int upateSuccess= 0;
		
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, passwdSalt);
			pstmt.setString(3, salt);
			pstmt.setString(4, id);
			upateSuccess= pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("비번, 이름 수정 오류" + e);
		}finally {
			close(pstmt);
		}
		return upateSuccess;
	}
	//이름 수정
	public int userNamePassModi(String id, String name){
		String sql= "update users set name= ? where id= ?";
		int upateSuccess= 0;
		
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			
			upateSuccess= pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("이름 수정 오류 " + e);
		}finally {
			close(pstmt);
		}
		return upateSuccess;
	}
	//비번 수정
	public int userPassModi(String id, String passwdSalt, String salt){
		String sql= "update users set passwd= ?, salt= ? where id= ?";
		int upateSuccess= 0;
		
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, passwdSalt);
			pstmt.setString(2, salt);
			pstmt.setString(3, id);
			System.out.println(pstmt);
			upateSuccess= pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("비번 수정 오류" + e);
		}finally {
			close(pstmt);
		}
		return upateSuccess;
	}

	//관리자 로그인
	public String selectAdminLoginId(AdminEmpBean emp) {
		String loginId= null;
		try {
			pstmt= con.prepareStatement("select emp_id from emp where emp_id= ? and emp_pass= ?");
			pstmt.setString(1, emp.getEmp_id());
			pstmt.setString(2, emp.getEmp_pass());
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				loginId= rs.getString("emp_id");
			}
		}catch(Exception e) {
			System.out.println("로그인 에러" + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return loginId;
	}
}
