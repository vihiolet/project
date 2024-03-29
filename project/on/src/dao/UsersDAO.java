package dao;
import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.AdminEmpBean;
import vo.AdminProBean;
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
			System.out.println("관리자정보 에러" + e);
		}finally {
			close(rs);
			close(pstmt);
		}	
		return empInfo;
	}
	
	//관리자 계정 확인
	public ArrayList<String> selectEmpId() {
		String sql= "select emp_id from emp";
		ArrayList<String> empIdList= new ArrayList<String>();
		String empId = null;
		try {
			pstmt=con.prepareStatement(sql);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				empId = new String();
				empId = rs.getString("emp_id");
				empIdList.add(empId);
			}
		}catch(Exception e) {
			System.out.println("관리자 계정 확인에서 에러" + e);
		}finally{
			close(rs);
			close(pstmt);
		}
		return empIdList;
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
		ArrayList<ReviewBean> review= new ArrayList<ReviewBean>();
		ReviewBean rb= null;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				rb= new ReviewBean();
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
	//내가 리뷰 쓴 제품
	public ArrayList<AdminProBean> myProReview(String id) {
		String sql= "select t2.pro_code, t2.pro_img from review t1 left join product t2 on t1.pro_code = t2.pro_code where t1.create_id= ? limit 4";
		ArrayList<AdminProBean> myReview= new ArrayList<AdminProBean>();
		AdminProBean pro= null;		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				pro= new AdminProBean();
				pro.setPro_code(rs.getInt("pro_code"));
				pro.setPro_img(rs.getString("pro_img"));
				myReview.add(pro);
			}
		}catch(Exception e) {
			System.out.println("내가 리뷰 단 제품에서 에러" + e);
		}finally{
			close(rs);
			close(pstmt);
		}
		return myReview;
	}
	//내가 쓴 리뷰
	public ArrayList<Object> getMyReview(String id, int page, int limit){
		String sql= "select t1.pro_code, review_code, tit_fg, sub_fg1, sub_fg2, sub_fg3, t1.create_dt, t1.create_id, t2.pro_img, t2.pro_nm"
				+ " from review t1 inner join product t2"
				+ " where t1.pro_code = t2.pro_code and t1.create_id= ? limit ?, " + limit;
		ArrayList<Object> myReview = new ArrayList<Object>();	
		ReviewBean review= null;
		int startrow= (page - 1) * limit;

		try{
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startrow);
			rs= pstmt.executeQuery();
			while(rs.next()){
				review= new ReviewBean();
				review.setPro_code(rs.getInt("pro_code"));
				review.setPro_nm(rs.getString("pro_nm"));
				review.setPro_img(rs.getString("pro_img"));
				review.setReview_code(rs.getInt("review_code"));
				review.setTit_fg(rs.getInt("tit_fg"));
				review.setSub1_fg(rs.getInt("sub_fg1"));
				review.setSub2_fg(rs.getInt("sub_fg2"));
				review.setSub3_fg(rs.getInt("sub_fg3"));
				review.setCreate_dt(rs.getDate("create_dt"));
				myReview.add(review);				
			}
		}catch(Exception e) {
			System.out.println("내가 쓴 리뷰에서 에러" + e);
		}finally{
			close(rs);
			close(pstmt);
		}
		
		return myReview;
			
	}

	//탈퇴하면 등록한 후기 삭제
	public void delUserContext(String id) {
		String sql = "delete from review where create_id= ?";
		
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("탈퇴 후 후기 삭제에서 오류" + e);
		}finally {
			close(pstmt);
		}
	}
	
	//내가 쓴 리뷰 삭제
	public int deleteReview(int[] intCodeArr) {
		String sql = "delete from review where review_code= ?";
		for(int i= 1; i< intCodeArr.length; i++) {
			sql += " or review_code= ?";
		}	
		
		int deleteCount= 0;
		try {
			pstmt= con.prepareStatement(sql);	
			
			for(int i= 1; i<= intCodeArr.length; i++) {					
				pstmt.setInt(i, intCodeArr[i-1]);
			}
			deleteCount= pstmt.executeUpdate();
		}catch(Exception e) {
			System.err.println("내가 쓴 후기 삭제에서 오류 : " + e);
		}finally {
			close(pstmt);
		}
		return deleteCount;
	}

	public int selectUserCount() {
		int listCount= 0;
		
		try {
			pstmt=con.prepareStatement("select count(*) from users");
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				listCount= rs.getInt(1);
			}
		}catch(Exception ex) {
			System.err.println("가입자 수 구하기에서 에러 발생 " + ex );
		}finally {
			close(con);
			close(rs);
		}
		return listCount;
	}	
}
