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
	public ArrayList<AdminProBean> selectArticleList(int page, int limit, int menu_code) {
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		String sql= "select * from product where menu_code= " + menu_code + " limit ?, " + limit;
		ArrayList<AdminProBean> articleList= new ArrayList<AdminProBean>();
		AdminProBean adminProBean= null;
		int startrow= (page - 1) * limit;	
		
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
	//전체 리뷰 
	public ArrayList<ReviewBean> getselectReview(int pro_code) {
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		ArrayList<ReviewBean> allReview= new ArrayList<ReviewBean>();
		ReviewBean review= null;	
		
		try {
			pstmt=con.prepareStatement("select * from review where pro_code= ?");
			pstmt.setInt(1, pro_code);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				review= new ReviewBean();
				review.setPro_code(rs.getInt("pro_code"));
				review.setTit_fg(rs.getInt("tit_fg"));
				review.setSub1_fg(rs.getInt("sub_fg1"));
				review.setSub2_fg(rs.getInt("sub_fg2"));
				review.setSub3_fg(rs.getInt("sub_fg3"));
				review.setCreate_dt(rs.getDate("create_dt"));
				review.setCreate_id(rs.getString("create_id"));
				allReview.add(review);
			}
		}catch(Exception e) {
			System.out.println("전체 리뷰에서 에러" + e);
		}finally{
			close(rs);
			close(pstmt);
		}
		return allReview;
	}
	//전체 리뷰 개수
	public int getReviewCount(int pro_code) {
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		int reviewCount= 0;	
		
		try {
			pstmt=con.prepareStatement("select count(*) from review where pro_code= ?");
			pstmt.setInt(1, pro_code);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				reviewCount= rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("전체 리뷰 개수에서 에러" + e);
		}finally{
			close(rs);
			close(pstmt);
		}
		
		return reviewCount;
	}
	//상품 개수
	public int selectList1Count() {
		int listCount= 0;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		try {
			pstmt=con.prepareStatement("select count(*) from product where menu_code= 1");
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				listCount= rs.getInt(1);
			}
		}catch(Exception ex) {
			System.err.println("사용자페이지 상품 개수 구하기에서 에러 발생 " + ex );
		}finally {
			close(con);
			close(rs);
		}
		return listCount;
	}

	public int selectList2Count() {
		int listCount= 0;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		try {
			pstmt=con.prepareStatement("select count(*) from product where menu_code= 2");
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				listCount= rs.getInt(1);
			}
		}catch(Exception ex) {
			System.err.println("사용자페이지 상품 개수 구하기에서 에러 발생 " + ex );
		}finally {
			close(con);
			close(rs);
		}
		return listCount;
	}
	public int selectList3Count() {
		int listCount= 0;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		try {
			pstmt=con.prepareStatement("select count(*) from product where menu_code= 3");
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				listCount= rs.getInt(1);
			}
		}catch(Exception ex) {
			System.err.println("사용자페이지 상품 개수 구하기에서 에러 발생 " + ex );
		}finally {
			close(con);
			close(rs);
		}
		return listCount;
	}
	public int selectList4Count() {
		int listCount= 0;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		try {
			pstmt=con.prepareStatement("select count(*) from product where menu_code= 4");
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				listCount= rs.getInt(1);
			}
		}catch(Exception ex) {
			System.err.println("사용자페이지 상품 개수 구하기에서 에러 발생 " + ex );
		}finally {
			close(con);
			close(rs);
		}
		return listCount;
	}
	public int selectList5Count() {
		int listCount= 0;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		try {
			pstmt=con.prepareStatement("select count(*) from product where menu_code= 5");
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				listCount= rs.getInt(1);
			}
		}catch(Exception ex) {
			System.err.println("사용자페이지 상품 개수 구하기에서 에러 발생 " + ex );
		}finally {
			close(con);
			close(rs);
		}
		return listCount;
	}
}
