package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import java.util.ArrayList;
import vo.AdminProBean;
import vo.KeywordBean;
import vo.ReviewBean;

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
	
	//상품 목록 보기
	public ArrayList<AdminProBean> selectArticleList(int page, int limit) {
		
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		String admin_pro_list_sql= "select * from product order by pro_code asc limit ?, 10";
		ArrayList<AdminProBean> articleList= new ArrayList<AdminProBean>();
		AdminProBean adminProBean= null;
		int startrow= (page-1) *10;
		
		try {
			pstmt= con.prepareStatement(admin_pro_list_sql);
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
	
	//상품 등록
	public int insertArticle(AdminProBean adminProBean) {
		int insertCount= 0;
		PreparedStatement pstmt= null;
		String sql= "";
		
		try {			
			sql= "insert into product(pro_nm, menu_code, pro_company, pro_img, srch_code1, srch_nm1, create_dt, create_id) values(?, ?, ?, ?, ?, ?, now(), ?)";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, adminProBean.getPro_nm());
			pstmt.setInt(2, adminProBean.getMenu_code());
			pstmt.setString(3, adminProBean.getPro_company());
			pstmt.setString(4, adminProBean.getPro_img());
			pstmt.setInt(5, adminProBean.getSrch_code1());
			pstmt.setString(6, adminProBean.getSrch_nm1());
			pstmt.setString(7, adminProBean.getCreate_id());
			
			insertCount= pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Product Insert Error! :" + e);
			e.printStackTrace();
		}		
		return insertCount;
	}
	//삭제
	public int deleteProduct(int[] intProCodeArr) {
		PreparedStatement pstmt= null;
		String proDel_sql= "delete from product where pro_code= ?";
		for(int i= 1; i< intProCodeArr.length; i++) {
			proDel_sql += " or pro_code= ?";
		}	
		
		int deleteCount= 0;
		try {
			pstmt= con.prepareStatement(proDel_sql);	
			
			for(int i= 1; i<= intProCodeArr.length; i++) {					
				pstmt.setInt(i, intProCodeArr[i-1]);
			}
			deleteCount= pstmt.executeUpdate();
		}catch(Exception e) {
			System.err.println("제품 삭제에서 오류 : " + e);
		}finally {
			close(pstmt);
		}
		return deleteCount;
	}
	//권한 확인
	public boolean isAdminUser(String id) {
		PreparedStatement pstmt= null;
		return false;
	}

	//수정
	public int updatePro(AdminProBean proBean, int pro_code) {
		PreparedStatement pstmt= null;
		String sql= "update product set pro_nm= ?, menu_code= ?, pro_company= ?, pro_img= ?, srch_code1= ?, srch_nm1= ? where pro_code= " +pro_code;
		int updateCount= 0;
		try{			
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, proBean.getPro_nm());
			pstmt.setInt(2, proBean.getMenu_code());
			pstmt.setString(3, proBean.getPro_company());
			pstmt.setString(4, proBean.getPro_img());
			pstmt.setInt(5, proBean.getSrch_code1());
			pstmt.setString(6, proBean.getSrch_nm1());
			
			updateCount= pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Product Insert Error! :" + e);
			e.printStackTrace();
		}
		return updateCount;
	}

	//뷰 페이지
	public AdminProBean selectPro(int pro_code) {
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		AdminProBean adminPro= null;
		
		try {
			pstmt= con.prepareStatement("select * from product where pro_code= " + pro_code);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				adminPro = new AdminProBean();
				adminPro.setPro_code(rs.getInt("pro_code"));
				adminPro.setPro_nm(rs.getString("pro_nm"));
				adminPro.setPro_company(rs.getString("pro_company"));
				adminPro.setPro_img(rs.getString("pro_img"));
				adminPro.setSrch_code1(rs.getInt("srch_code1"));
				adminPro.setSrch_nm1(rs.getString("srch_nm1"));
				adminPro.setPro_explain(rs.getString("pro_explain"));	
				adminPro.setMenu_code(rs.getInt("menu_code"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return adminPro;
	}

	//리뷰 최대 개수
	public ReviewBean getReviewCount(int pro_code) {
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		ReviewBean review= null;
		String sql= "select t1.pro_code, tit_fg, max(titCount) as titCount from product t1 inner join "
				+ "(select pro_code, tit_fg, count(tit_fg) as titCount from review where pro_code= " + pro_code
				+ " group by tit_fg having count(tit_fg) >= 1) t2 on t1.pro_code = t2.pro_code where t1.pro_code = " + pro_code;
		
		try {
			pstmt= con.prepareStatement(sql);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				review= new ReviewBean();
				review.setPro_code(rs.getInt("pro_code"));
				review.setTit_fg(rs.getInt("tit_fg"));
				review.setReviewCount(rs.getInt("titCount"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return review;
	}
	
	//tit_fg 표 개수 구하기 1
	public int getTit1Count(int pro_code) {
		
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		String sql= "select t1.pro_code, ifnull(count(tit_fg), 0) from product t1 inner join review t2 "
				+ "on t1.pro_code= t2.pro_code where tit_fg= 1 and t1.pro_code =" + pro_code;
		int tit1Count= 0;
		
		try {
			pstmt= con.prepareStatement(sql);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				tit1Count= rs.getInt(2);
			}			
		}catch(Exception e) {
			System.out.println("리뷰1 개수 에러 발생 " + e);
		}finally {
			close(pstmt);
			close(rs);
		}
		return tit1Count;
	}

	//tit_fg 표 개수 구하기 2
	public int getTit2Count(int pro_code) {
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		String sql= "select t1.pro_code, ifnull(count(tit_fg), 0) from product t1 inner join review t2 "
				+ "on t1.pro_code= t2.pro_code where tit_fg=2 and t1.pro_code= " + pro_code;
		int tit2Count= 0;
		
		try {
			pstmt= con.prepareStatement(sql);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				tit2Count= rs.getInt(2);
			}	
		}catch(Exception e) {
			System.out.println("리뷰1 개수 에러 발생 " + e);
		}finally {
			close(pstmt);
			close(rs);
		}
		return tit2Count;
	}

	//tit_fg 표 개수 구하기 3
	public int getTit3Count(int pro_code) {
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		String sql= "select t1.pro_code, ifnull(count(tit_fg), 0) from product t1 inner join review t2 "
				+ "on t1.pro_code= t2.pro_code where tit_fg= 3 and t1.pro_code =" + pro_code;
		int tit3Count= 0;
		
		try {
			pstmt= con.prepareStatement(sql);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				tit3Count= rs.getInt(2);
			}			
		}catch(Exception e) {
			System.out.println("리뷰1 개수 에러 발생 " + e);
		}finally {
			close(pstmt);
			close(rs);
		}
		return tit3Count;
	}
}
