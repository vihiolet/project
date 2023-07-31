package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.AdminProBean;
import vo.ReviewBean;

public class FrontProDAO {
	Connection con;
	private static FrontProDAO dao;

	public static FrontProDAO getInstance() {
		if (dao == null) {
			dao = new FrontProDAO();		//DAO 인스턴스 생성
		}
		return dao;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//리뷰 등록
	public int insertArticle(ReviewBean reviewBean) {
		PreparedStatement pstmt = null;
		String sql = "";
		int insertCount = 0;

		try {
			sql = "insert into review (pro_code, tit_fg, sub_fg1, sub_fg2, sub_fg3, create_dt, create_id) values(?,?,?,?,?,now(), ?)";
			
			pstmt = con.prepareStatement(sql);			
			pstmt.setInt(1, reviewBean.getPro_code());			
			pstmt.setInt(2, reviewBean.getTit_fg());
			pstmt.setInt(3, reviewBean.getSub1_fg());
			pstmt.setInt(4, reviewBean.getSub2_fg());
			pstmt.setInt(5, reviewBean.getSub3_fg());
			pstmt.setString(6, reviewBean.getCreate_id());
			insertCount = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("boardInsert Error! :" + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return insertCount;
	}

	//제품 리스트
	public ArrayList<AdminProBean> selectArticleList(int page, int limit) {
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		String sql= "select * from product t1 inner join keyword t2 on t1.srch_code1 = t2.srch_code limit ?, 10";
		ArrayList<AdminProBean> articleList= new ArrayList<AdminProBean>();
		AdminProBean adminProBean= null;
		int startrow= (page-1) *10;	
		
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setInt(1, startrow);			
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				adminProBean= new AdminProBean();
				adminProBean.setPro_code(rs.getInt("pro_code"));
				adminProBean.setPro_nm(rs.getString("pro_nm"));
				adminProBean.setMenu_code(rs.getInt("menu_code"));
				adminProBean.setPro_company(rs.getString("pro_company"));
				adminProBean.setPro_img(rs.getString("pro_img"));
				adminProBean.setCreate_id(rs.getString("create_id"));
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
