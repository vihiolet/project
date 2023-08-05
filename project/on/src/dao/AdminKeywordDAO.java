package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.util.ArrayList;

import vo.KeywordBean;

public class AdminKeywordDAO {
	
	DataSource ds;
	Connection con;
	
	private static AdminKeywordDAO adminKeywordDAO;
	
	private AdminKeywordDAO() {
		
	}
	public static AdminKeywordDAO getInstance() {
		if(adminKeywordDAO == null) {
			adminKeywordDAO = new AdminKeywordDAO();
		}
		return adminKeywordDAO;
	}
	
	public void setConnection(Connection con){
		this.con= con;
	}
	
	//검색점 등록
		public int insertKey(KeywordBean keywordBean) {
			
			int insertCount= 0;
			PreparedStatement pstmt= null;
			String keywordReg_sql= "";
			
			try {
				keywordReg_sql= "insert into keyword(srch_name ,create_dt, create_id, remark) value(?, now(), ?, ?)";
				pstmt= con.prepareStatement(keywordReg_sql);	// 이 코드가 없으면 catch문에 SQLException에서 빨간줄
				pstmt.setString(1, keywordBean.getSrch_name());
				pstmt.setString(2, keywordBean.getCreate_id());
				pstmt.setString(3, keywordBean.getRemark());
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
			String keywordList_sql= "select srch_code, srch_name, create_dt, create_id, ifnull(remark, '') as remark from keyword order by srch_code asc limit ?, 10";
			ArrayList<KeywordBean> keywordLsit= new ArrayList<KeywordBean>();
			KeywordBean keyword= null;
			int startrow= (page - 1) * 10;
			
			try {
				pstmt= con.prepareStatement(keywordList_sql);
				pstmt.setInt(1, startrow);
				rs= pstmt.executeQuery();
				while(rs.next()) {
					keyword= new KeywordBean();
					keyword.setSrch_code(rs.getInt("srch_code"));
					keyword.setCreate_id(rs.getString("create_id"));
					keyword.setSrch_name(rs.getString("srch_name"));
					keyword.setCreate_dt(rs.getDate("create_dt"));
					keyword.setRemark(rs.getString("remark"));
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
		
		//검색점 수정
		public int updateKeyword(KeywordBean keywordBean) {
			int updateCount= 0;
			return updateCount;
		}
		
		//검색점 삭제
		public int deleteKeyword(int[] intCodeArr) {
			
			PreparedStatement pstmt= null;
			String keywortDel_sql= "delete from keyword where srch_code= ?";
			for(int i= 1; i< intCodeArr.length; i++) {
				keywortDel_sql += " or srch_code= ?";
			}	
			
			int deleteCount= 0;
			try {
				pstmt= con.prepareStatement(keywortDel_sql);	
				
				for(int i= 1; i<= intCodeArr.length; i++) {					
					pstmt.setInt(i, intCodeArr[i-1]);
				}
				deleteCount= pstmt.executeUpdate();
			}catch(Exception e) {
				System.err.println("검색점 삭제에서 오류 : " + e);
			}finally {
				close(pstmt);
			}
			return deleteCount;
		}
		
		//수정시 관리자 확인
		public boolean isAdminUser(String id) {
			// TODO Auto-generated method stub
			return false;
		}
		
		//검색점명 수정
		public int updateKekword(KeywordBean keyword) {
			int updateCount= 0;
			PreparedStatement pstmt= null;
			String sql= "update keyword set srch_name= ? where srch_code= ?";
			
			try {
				pstmt= con.prepareStatement(sql);
				pstmt.setString(1, keyword.getSrch_name());
				pstmt.setInt(2, keyword.getSrch_code());
				
				updateCount= pstmt.executeUpdate();
				
			}catch(Exception e) {
				System.err.println("검색점 수정에서 오류 : " + e);
			}finally {
				close(pstmt);
			}
			return updateCount;
		}
		
		//검색점명 수정
		public int updateAllKeyword(KeywordBean keyword) {
			int updateCount= 0;
			PreparedStatement pstmt= null;
			String sql= "update keyword set srch_name= ?, remark= ? where srch_code= ?";
			
			try {
				pstmt= con.prepareStatement(sql);
				pstmt.setString(1, keyword.getSrch_name());
				pstmt.setString(2, keyword.getRemark());
				pstmt.setInt(3, keyword.getSrch_code());
				
				updateCount= pstmt.executeUpdate();
				
			}catch(Exception e) {
				System.err.println("검색점 수정에서 오류 : " + e);
			}finally {
				close(pstmt);
			}
			return updateCount;
		}
		
		public KeywordBean getkeyword(int srch_code) {
			PreparedStatement pstmt= null;
			ResultSet rs= null;
			KeywordBean keyword = null;
			try {
				pstmt= con.prepareStatement("select * from keyword where srch_code= "+ srch_code);
				rs= pstmt.executeQuery();
				if(rs.next()) {
					keyword = new KeywordBean();
					keyword.setSrch_code(rs.getInt("srch_code"));
					keyword.setCreate_id(rs.getString("create_id"));
					keyword.setSrch_name(rs.getString("srch_name"));
					keyword.setRemark(rs.getString("remark"));
				}
			}catch(Exception e) {
				System.err.println("검색점 수정 form에서 오류 : " + e);
			}
			return keyword;
		}
}
