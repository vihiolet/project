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

public class AdminEmpDAO {
	
	DataSource ds;
	Connection con;
	
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

	//글 개수 구하기
	public int selectEmpListCount() {
		int listCount= 0;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		try {
			pstmt=con.prepareStatement("select count(*) from Emp");
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
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		String empList_sql= "select srch_code, srch_name, create_dt, create_id, ifnull(remark, '') as remark from keyword order by srch_code asc limit ?, 10";
		ArrayList<AdminEmpBean> empLsit= new ArrayList<AdminEmpBean>();
		KeywordBean keyword= null;
		int startrow= (page - 1) * 10;
		
		try {
			pstmt= con.prepareStatement(empList_sql);
			pstmt.setInt(1, startrow);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				keyword= new KeywordBean();
				keyword.setSrch_code(rs.getInt("srch_code"));
				keyword.setCreate_id(rs.getInt("create_id"));
				keyword.setSrch_name(rs.getString("srch_name"));
				keyword.setCreate_dt(rs.getDate("create_dt"));
				keyword.setRemark(rs.getString("remark"));
				empLsit.add(keyword);
			}
		}catch(Exception e) {
			System.err.println("검색점 목록에서 오류 : " + e);
		}finally {
			close(rs);
			close(pstmt);
		}	
		return empLsit;
	}

}
