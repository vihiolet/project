package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import java.util.ArrayList;
import vo.AdminProBean;

public class AdminProDAO {
	
	DataSource ds;
	Connection con;
	private static AdminProDAO adminProDAO;
	
	private AdminProDAO() {
		
	}

	public static AdminProDAO getInstance() {
		if(adminProDAO == null) {
			adminProDAO= new AdminProDAO();
		}
		return adminProDAO;
	}

	public void setConnection(Connection con) {
		this.con= con;
	}

	//글의 개수 구하기
	public int selectListCount() {
		int listCount= 0;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		try {
			pstmt=con.prepareStatement("select count(*) from product");
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				listCount= rs.getInt(1);
			}
		}catch(Exception ex) {
			System.err.println("글의 개수 구하기에서 에러 발생 " + ex );
		}finally {
			close(con);
			close(rs);
		}
		return listCount;
	}
	
	//글 목록 보기
	public ArrayList<AdminProBean> selectArticleList(int page, int limit) {
		
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		String AdminProList= "select * from product order by pro_code asc limit ?, 10";
		ArrayList<AdminProBean> articleList= new ArrayList<AdminProBean>();
		AdminProBean adminProBean= null;
		int startrow= (page-1) *10;
		
		try {
			pstmt= con.prepareStatement(AdminProList);
			pstmt.setInt(1, startrow);
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				adminProBean= new AdminProBean();
				adminProBean.setPro_code(rs.getInt("pro_code"));
				adminProBean.setPro_nm(rs.getString("pro_nm"));
				adminProBean.setMenu_code(rs.getInt("menu_code"));
				adminProBean.setPro_company(rs.getString("pro_company"));
				adminProBean.setPro_img(rs.getString("pro_img"));
				//adminProBean.get
				articleList.add(adminProBean);
			}
			
			}catch(Exception ex) {
				System.out.println("글 목록보기에서 에러 발생 " + ex);
			}finally {
				close(rs);
				close(pstmt);
			}
		
		return articleList;
	}

}
