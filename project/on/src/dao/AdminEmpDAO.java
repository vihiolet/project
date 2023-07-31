package dao;

import db.JdbcUtil.*;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;

import vo.AdminEmpBean;
import vo.KeywordBean;
import vo.UserBean;

public class AdminEmpDAO {
	
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	private AdminEmpDAO() {}
	
	private static AdminEmpDAO adminEmpDAO;

	public static AdminEmpDAO getInstance() {
		if(adminEmpDAO == null) {
			adminEmpDAO= new AdminEmpDAO();
		}
		return adminEmpDAO;
	}
	
	public void setConnection(Connection con) {
		this.con= con;
	}

	//개수 구하기
	public int selectEmpListCount() {
		int listCount= 0;
		
		try {
			pstmt=con.prepareStatement("select count(*) from emp");
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				listCount= rs.getInt(1);
			}
		}catch(Exception ex) {
			System.err.println("글 개수 구하기에서 에러 발생 " + ex );
		}finally {
			close(con);
			close(rs);
		}
		return listCount;
	}
	//목록 보기
	public ArrayList<AdminEmpBean> selectEmpList(int page, int limit) {

		String empList_sql= "select emp_code, emp_id, emp_name, create_dt, create_id from emp order by emp_code asc limit ?, 10";
		ArrayList<AdminEmpBean> empLsit= new ArrayList<AdminEmpBean>();
		AdminEmpBean emp= null;
		int startrow= (page - 1) * 10;
		
		try {
			pstmt= con.prepareStatement(empList_sql);
			pstmt.setInt(1, startrow);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				emp= new AdminEmpBean();
				emp.setEmp_code(rs.getInt("emp_code"));
				emp.setEmp_id(rs.getString("emp_id"));
				emp.setEmp_name(rs.getString("emp_name"));
				emp.setCreate_dt(rs.getDate("create_dt"));
				emp.setCreate_id(rs.getString("create_id"));
				
				empLsit.add(emp);
			}
		}catch(Exception e) {
			System.err.println("emp 목록에서 오류 : " + e);
		}finally {
			close(rs);
			close(pstmt);
		}	
		return empLsit;
	}
	//수정
	//삭제
	
	//id로 salt값 가져오기
	public String selectEmpSalt(String id) {	
		String result= "";
		
		try {
			pstmt= con.prepareStatement("select salt from emp where emp_id= ?");
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
	//아이디 중복체크
	public boolean duplicationIdChk(String id) {
		String sql= "select emp_id from emp where emp_id= ?";
		boolean selectVal= false;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			if(rs.next()) selectVal= true;
		}catch(Exception e) {
			System.out.println("중복체크 에러" + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return selectVal;
	}
	//관리자 등록
	public int insertAdminEmp(AdminEmpBean emp) {
		String sql= "insert into emp(emp_pass, emp_name, create_dt, create_id, emp_id, salt) values(?, ?, now(), ?, ?, ?)";
		int insertCount= 0;
		
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, emp.getEmp_pass());
			pstmt.setString(2, emp.getEmp_name());
			pstmt.setString(3, emp.getCreate_id());
			pstmt.setString(4, emp.getEmp_id());
			pstmt.setString(5, emp.getSalt());
			
			insertCount= pstmt.executeUpdate();			
		}catch(Exception e) {
			System.out.println("회원가입 에러 " + e);
		}finally {
			close(pstmt);
		}
		return insertCount;
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
