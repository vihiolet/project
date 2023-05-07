package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.AdminProBean;
import vo.ReviewBean;

public class DAO {
	Connection con;
	private static DAO dao;

	public static DAO getInstance() {
		if (dao == null) {
			dao = new DAO();		//DAO 인스턴스 생성
		}
		return dao;
	}

	public void setConnection(Connection con) {
		this.con = con;

	}
	
	//리뷰 등록
	public int insertArticle(ReviewBean reviewBean) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		int num = 0;
		String sql = "";
		int insertCount = 0;

		try {
//			pstmt = con.prepareStatement("select max(board_num) from board");
//			rs = pstmt.executeQuery();
//
//			if (rs.next())
//				num = rs.getInt(1) + 1;
//			else
//				num = 1;

			sql = "insert into review (pro_code, tit_fg, sub_fg1, sub_fg2, sub_fg3, create_dt ) values(?,?,?,?,?,now())";
			
			pstmt = con.prepareStatement(sql);			
			pstmt.setInt(1, reviewBean.getPro_code());			
			pstmt.setInt(2, reviewBean.getTit_fg());
			pstmt.setInt(3, reviewBean.getSub1_fg());
			pstmt.setInt(4, reviewBean.getSub2_fg());
			pstmt.setInt(5, reviewBean.getSub3_fg());

			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("boardInsert Error! :" + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}
	
	//상품 등록
	public int insertArticle(AdminProBean adminProBean) {
		int insertCount = 0;
		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		String sql = "";
		
		try {			
			sql = "insert into product(pro_nm, menu_code, pro_company, pro_img, srch_code1, srch_nm1, create_dt, create_id) values(?, ?, ?, ?, ?, ?, now(), 01)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, adminProBean.getPro_nm());
			pstmt.setInt(2, adminProBean.getMenu_code());
			pstmt.setString(3, adminProBean.getPro_company());
			pstmt.setString(4, adminProBean.getPro_img());
			pstmt.setInt(5, adminProBean.getSrch_code1());
			pstmt.setString(6, adminProBean.getSrch_nm1());
//			pstmt.setInt(7, adminProBean.getCreate_id());
			
			insertCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Product Insert Error! :" + e);
			e.printStackTrace();
		}		
		return insertCount;
	}

}
