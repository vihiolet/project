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
	
	//상품 등록
	public int insertArticle(AdminProBean adminProBean) {
		int insertCount= 0;
		PreparedStatement pstmt= null;
//		ResultSet rs = null;
		String sql= "";
		
		try {			
			sql= "insert into product(pro_nm, menu_code, pro_company, pro_img, srch_code1, srch_nm1, create_dt, create_id) values(?, ?, ?, ?, ?, ?, now(), 01)";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, adminProBean.getPro_nm());
			pstmt.setInt(2, adminProBean.getMenu_code());
			pstmt.setString(3, adminProBean.getPro_company());
			pstmt.setString(4, adminProBean.getPro_img());
			pstmt.setInt(5, adminProBean.getSrch_code1());
			pstmt.setString(6, adminProBean.getSrch_nm1());
//			pstmt.setInt(7, adminProBean.getCreate_id());
			
			insertCount= pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Product Insert Error! :" + e);
			e.printStackTrace();
		}		
		return insertCount;
	}
	
	//검색점 등록
	public int insertKey(KeywordBean keywordBean) {
		
		int insertCount= 0;
		PreparedStatement pstmt= null;
		String sql= "";
		
		try {
			sql= "insert into keyword(srch_name ,create_dt, create_id) value(?, now(), ?)";
			pstmt= con.prepareStatement(sql);	// 이 코드가 없으면 catch문에 SQLException에서 빨간줄
			pstmt.setString(1, keywordBean.getSrch_name());
			pstmt.setInt(2, keywordBean.getCreate_id());
			insertCount= pstmt.executeUpdate();
			
		}catch (SQLException e) {
			System.out.println("Product Insert Error! :" + e);
			e.printStackTrace();
		}	
		
		return insertCount;
	}

	//검색점 목록 보기
	public int selectKeywordListCount() {
		int listCount= 0;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		try {
			pstmt=con.prepareStatement("select count(*) from keyword");
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

	public ArrayList<KeywordBean> selectKeywordList(int page, int limit) {
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		String admin_key_list_sql= "select * from keyword order by srch_code asc limit ?, 10";
		ArrayList<KeywordBean> keywordLsit= new ArrayList<KeywordBean>();
		KeywordBean keyword= null;
		int startrow= (page - 1) * 10;
		
		try {
			pstmt= con.prepareStatement(admin_key_list_sql);
			pstmt.setInt(1, startrow);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				keyword= new KeywordBean();
				keyword.setSrch_code(rs.getInt("srch_code"));
				keyword.setCreate_id(rs.getInt("create_id"));
				keyword.setSrch_name(rs.getString("srch_name"));
				keyword.setCreate_dt(rs.getDate("create_dt"));
				keywordLsit.add(keyword);
			}
		}catch(Exception e) {
			System.err.println("검색점 목록에서 오류 : " + e);
		}finally {
			close(rs);
			close(pstmt);
		}	
		return keywordLsit;
	}

}
