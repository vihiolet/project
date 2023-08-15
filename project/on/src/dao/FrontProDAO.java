package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.AdminEmpBean;
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

	//index에 제품 출력1
	public AdminProBean selectPro1(int menu_code1) {
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		AdminProBean proBean = new AdminProBean();
		try {
			pstmt= con.prepareStatement("select pro_code, pro_nm, pro_img, pro_company from product where menu_code= " + menu_code1 + " limit 1");
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				proBean.setPro_code(rs.getInt("pro_code"));
				proBean.setPro_nm(rs.getString("pro_nm"));
				proBean.setPro_img(rs.getString("pro_img"));
				proBean.setPro_company(rs.getString("pro_company"));
			}
		}catch(Exception e) {
			System.out.println("메인페이지 상품 출력 에러" + e);
		}finally {
			close(rs);
			close(pstmt);
		}	
		return proBean;
	}
	//index에 제품 출력1
		public AdminProBean selectPro2(int menu_code2) {
			PreparedStatement pstmt = null;
			ResultSet rs= null;
			AdminProBean proBean = new AdminProBean();
			try {
				pstmt= con.prepareStatement("select pro_code, pro_nm, pro_img, pro_company from product where menu_code= " + menu_code2 + " limit 1");
				rs= pstmt.executeQuery();
				
				if(rs.next()) {
					proBean.setPro_code(rs.getInt("pro_code"));
					proBean.setPro_nm(rs.getString("pro_nm"));
					proBean.setPro_img(rs.getString("pro_img"));
					proBean.setPro_company(rs.getString("pro_company"));
				}
			}catch(Exception e) {
				System.out.println("메인페이지 상품 출력 에러" + e);
			}finally {
				close(rs);
				close(pstmt);
			}	
			return proBean;
		}
		//index에 제품 출력3
		public AdminProBean selectPro3(int menu_code3) {
			PreparedStatement pstmt = null;
			ResultSet rs= null;
			AdminProBean proBean = new AdminProBean();
			try {
				pstmt= con.prepareStatement("select pro_code, pro_nm, pro_img, pro_company from product where menu_code= " + menu_code3 + " limit 1");
				rs= pstmt.executeQuery();
				
				if(rs.next()) {
					proBean.setPro_code(rs.getInt("pro_code"));
					proBean.setPro_nm(rs.getString("pro_nm"));
					proBean.setPro_img(rs.getString("pro_img"));
					proBean.setPro_company(rs.getString("pro_company"));
				}
			}catch(Exception e) {
				System.out.println("메인페이지 상품 출력 에러" + e);
			}finally {
				close(rs);
				close(pstmt);
			}	
			return proBean;
		}
		//index에 제품 출력4
		public AdminProBean selectPro4(int menu_code4) {
			PreparedStatement pstmt = null;
			ResultSet rs= null;
			AdminProBean proBean = new AdminProBean();
			try {
				pstmt= con.prepareStatement("select pro_code, pro_nm, pro_img, pro_company from product where menu_code= " + menu_code4 + " limit 1");
				rs= pstmt.executeQuery();
				
				if(rs.next()) {
					proBean.setPro_code(rs.getInt("pro_code"));
					proBean.setPro_nm(rs.getString("pro_nm"));
					proBean.setPro_img(rs.getString("pro_img"));
					proBean.setPro_company(rs.getString("pro_company"));
				}
			}catch(Exception e) {
				System.out.println("메인페이지 상품 출력 에러" + e);
			}finally {
				close(rs);
				close(pstmt);
			}	
			return proBean;
		}
		//index에 제품 출력5
		public AdminProBean selectPro5(int menu_code5) {
			PreparedStatement pstmt = null;
			ResultSet rs= null;
			AdminProBean proBean = new AdminProBean();
			try {
				pstmt= con.prepareStatement("select pro_code, pro_nm, pro_img, pro_company from product where menu_code= " + menu_code5 + " limit 1");
				rs= pstmt.executeQuery();
				
				if(rs.next()) {
					proBean.setPro_code(rs.getInt("pro_code"));
					proBean.setPro_nm(rs.getString("pro_nm"));
					proBean.setPro_img(rs.getString("pro_img"));
					proBean.setPro_company(rs.getString("pro_company"));
				}
			}catch(Exception e) {
				System.out.println("메인페이지 제품 출력 에러" + e);
			}finally {
				close(rs);
				close(pstmt);
			}	
			return proBean;
		}

		public AdminProBean selectProSrch1(int srch_code) {
			PreparedStatement pstmt = null;
			ResultSet rs= null;
			AdminProBean proBean = new AdminProBean();
			try {
				pstmt= con.prepareStatement("select pro_code, pro_nm, pro_img, pro_company from product where menu_code= 1 and srch_code1= "+ srch_code + " limit 1");
				rs= pstmt.executeQuery();
				
				if(rs.next()) {
					proBean.setPro_code(rs.getInt("pro_code"));
					proBean.setPro_nm(rs.getString("pro_nm"));
					proBean.setPro_img(rs.getString("pro_img"));
					proBean.setPro_company(rs.getString("pro_company"));
				}
			}catch(Exception e) {
				System.out.println("메인페이지 검색점 조건 제품 출력 에러" + e);
			}finally {
				close(rs);
				close(pstmt);
			}	
			return proBean;
		}
		public AdminProBean selectProSrch2(int srch_code) {
			PreparedStatement pstmt = null;
			ResultSet rs= null;
			AdminProBean proBean = new AdminProBean();
			try {
				pstmt= con.prepareStatement("select pro_code, pro_nm, pro_img, pro_company from product where menu_code= 2 and srch_code1= " + srch_code + " limit 1");
				rs= pstmt.executeQuery();
				
				if(rs.next()) {
					proBean.setPro_code(rs.getInt("pro_code"));
					proBean.setPro_nm(rs.getString("pro_nm"));
					proBean.setPro_img(rs.getString("pro_img"));
					proBean.setPro_company(rs.getString("pro_company"));
				}
			}catch(Exception e) {
				System.out.println("메인페이지 검색점 조건 제품 출력 에러" + e);
			}finally {
				close(rs);
				close(pstmt);
			}	
			return proBean;
		}
		public AdminProBean selectProSrch3(int srch_code) {
			PreparedStatement pstmt = null;
			ResultSet rs= null;
			AdminProBean proBean = new AdminProBean();
			try {
				pstmt= con.prepareStatement("select pro_code, pro_nm, pro_img, pro_company from product where menu_code= 3 and srch_code1= " + srch_code  + " limit 1");
				rs= pstmt.executeQuery();
				
				if(rs.next()) {
					proBean.setPro_code(rs.getInt("pro_code"));
					proBean.setPro_nm(rs.getString("pro_nm"));
					proBean.setPro_img(rs.getString("pro_img"));
					proBean.setPro_company(rs.getString("pro_company"));
				}
			}catch(Exception e) {
				System.out.println("메인페이지 검색점 조건 제품 출력 에러" + e);
			}finally {
				close(rs);
				close(pstmt);
			}	
			return proBean;
		}
		public AdminProBean selectProSrch4(int srch_code) {
			PreparedStatement pstmt = null;
			ResultSet rs= null;
			AdminProBean proBean = new AdminProBean();
			try {
				pstmt= con.prepareStatement("select pro_code, pro_nm, pro_img, pro_company from product where menu_code= 4 and srch_code1= " + srch_code  + " limit 1");
				rs= pstmt.executeQuery();
				
				if(rs.next()) {
					proBean.setPro_code(rs.getInt("pro_code"));
					proBean.setPro_nm(rs.getString("pro_nm"));
					proBean.setPro_img(rs.getString("pro_img"));
					proBean.setPro_company(rs.getString("pro_company"));
				}
			}catch(Exception e) {
				System.out.println("메인페이지 검색점 조건 제품 출력 에러" + e);
			}finally {
				close(rs);
				close(pstmt);
			}	
			return proBean;
		}

		//검색으로 나온 제품 개수(검색점)
		public int selectMainSrchProCount(String srch_nm) {
			int listCount= 0;
			PreparedStatement pstmt= null;
			ResultSet rs= null;
			
			try {
				pstmt=con.prepareStatement("select count(*) from product where srch_code1= (select srch_code from keyword where srch_name= ?)");
				pstmt.setString(1, srch_nm);
				rs= pstmt.executeQuery();
				
				if(rs.next()) {
					listCount= rs.getInt(1);
				}
			}catch(Exception ex) {
				System.err.println("메인 검색으로 나온 개수 구하기에서 에러 발생 " + ex );
			}finally {
				close(con);
				close(rs);
			}
			return listCount;
		}

		//검색으로 나온 제품 개수(제품명)
		public int selectMainNameProCount(String pro_nm) {
			int listCount= 0;
			PreparedStatement pstmt= null;
			ResultSet rs= null;
			try {
				pstmt=con.prepareStatement("select count(*) from product where pro_nm= ?");
				pstmt.setString(1, pro_nm);
				rs= pstmt.executeQuery();
				
				if(rs.next()) {
					listCount= rs.getInt(1);
				}
			}catch(Exception ex) {
				System.err.println("메인 검색으로 나온 개수 구하기에서 에러 발생 " + ex );
			}finally {
				close(con);
				close(rs);
			}
			return listCount;
		}
		
		//검색으로 나온 제품 개수(검색점)
				public int selectSrchProCount(String srch_nm, int menu_code) {
					int listCount= 0;
					PreparedStatement pstmt= null;
					ResultSet rs= null;
					
					try {
						pstmt=con.prepareStatement("select count(*) from product where srch_code1= (select srch_code from keyword where srch_name= ?) and menu_code=" + menu_code);
						pstmt.setString(1, srch_nm);
						rs= pstmt.executeQuery();
						
						if(rs.next()) {
							listCount= rs.getInt(1);
						}
					}catch(Exception ex) {
						System.err.println("검색으로 나온 개수 구하기에서 에러 발생 " + ex );
					}finally {
						close(con);
						close(rs);
					}
					return listCount;
				}

				//검색으로 나온 제품 개수(제품명)
				public int selectNameProCount(String pro_nm, int menu_code) {
					int listCount= 0;
					PreparedStatement pstmt= null;
					ResultSet rs= null;
					try {
						pstmt=con.prepareStatement("select count(*) from product where pro_nm= ? and menu_code=" + menu_code);
						pstmt.setString(1, pro_nm);
						rs= pstmt.executeQuery();
						
						if(rs.next()) {
							listCount= rs.getInt(1);
						}
					}catch(Exception ex) {
						System.err.println("검색으로 나온 개수 구하기에서 에러 발생 " + ex );
					}finally {
						close(con);
						close(rs);
					}
					return listCount;
				}
		
		//메뉴에서 검색하여 나온 제품
		public ArrayList<AdminProBean> selectSrchProList(int page, int limit, int menu_code, String srch_nm) {
			PreparedStatement pstmt= null;
			ResultSet rs= null;
			String sql= "select * from product where menu_code= " + menu_code + " and srch_code1= (select srch_code from keyword where srch_name= ?) limit ?, " + limit;
			
			ArrayList<AdminProBean> articleList= new ArrayList<AdminProBean>();
			AdminProBean adminProBean= null;
			int startrow= (page - 1) * limit;	
			
			try {
				pstmt= con.prepareStatement(sql);
				pstmt.setString(1, srch_nm);
				pstmt.setInt(2, startrow);	
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
					System.out.println("메뉴에서 검색하여 나온 제품에서 에러 발생 " + ex);
				}finally {
					close(rs);
					close(pstmt);
				}
			
			return articleList;
		}

		public ArrayList<AdminProBean> selectMainSrchProList(int page, int limit, String srch_nm) {
			PreparedStatement pstmt= null;
			ResultSet rs= null;
			String sql= "select * from product where srch_code1= (select srch_code from keyword where srch_name= ?) limit ?, " + limit;
			ArrayList<AdminProBean> articleList= new ArrayList<AdminProBean>();
			AdminProBean adminProBean= null;
			int startrow= (page - 1) * limit;	
			
			try {
				pstmt= con.prepareStatement(sql);
				pstmt.setString(1, srch_nm);
				pstmt.setInt(2, startrow);	
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
					System.out.println("메뉴에서 검색하여 나온 제품에서 에러 발생 " + ex);
				}finally {
					close(rs);
					close(pstmt);
				}
			
			return articleList;
		}

		public ArrayList<AdminProBean> selectNameProList(int page, int limit, int menu_code, String pro_nm) {
			PreparedStatement pstmt= null;
			ResultSet rs= null;
			String sql= "select * from product where menu_code= " + menu_code + " and pro_nm= ? limit ?, " + limit;
			ArrayList<AdminProBean> articleList= new ArrayList<AdminProBean>();
			AdminProBean adminProBean= null;
			int startrow= (page - 1) * limit;	
			
			try {
				pstmt= con.prepareStatement(sql);
				pstmt.setString(1, pro_nm);
				pstmt.setInt(2, startrow);	
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
					System.out.println("메뉴에서 검색하여 나온 제품(제품명/메뉴코드 있음)에서 에러 발생 " + ex);
				}finally {
					close(rs);
					close(pstmt);
				}
			
			return articleList;
		}

		public ArrayList<AdminProBean> selectMainNameProList(int page, int limit, String pro_nm) {
			PreparedStatement pstmt= null;
			ResultSet rs= null;
			String sql= "select * from product where pro_nm= ? limit ?, " + limit;
			ArrayList<AdminProBean> articleList= new ArrayList<AdminProBean>();
			AdminProBean adminProBean= null;
			int startrow= (page - 1) * limit;	
			
			try {
				pstmt= con.prepareStatement(sql);
				pstmt.setString(1, pro_nm);
				pstmt.setInt(2, startrow);	
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
					System.out.println("메뉴에서 검색하여 나온 제품(제품명/메뉴코드 없음)에서 에러 발생 " + ex);
				}finally {
					close(rs);
					close(pstmt);
				}
			
			return articleList;
		}
}
