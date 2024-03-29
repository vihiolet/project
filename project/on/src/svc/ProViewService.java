package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminProDAO;
import dao.FrontProDAO;
import vo.AdminProBean;
import vo.ReviewBean;

public class ProViewService {

	public AdminProBean getProView(int pro_code) {
		
		Connection con= getConnection();
		AdminProDAO adminProDAO = AdminProDAO.getInstance();
		adminProDAO.setConnection(con);
		AdminProBean proBean= adminProDAO.selectPro(pro_code);
		close(con);
		return proBean;
	}

	//리뷰 최대 개수와 값
	public ReviewBean getMaxReviewCount(int pro_code) {
		Connection con= getConnection();
		AdminProDAO adminProDAO = AdminProDAO.getInstance();
		adminProDAO.setConnection(con);
		ReviewBean isMaxReview= adminProDAO.getReviewCount(pro_code);
		close(con);
		return isMaxReview;
	}	

	//tit_fg 개수 구하기 1
	public int getTit1Count(int pro_code) {
		Connection con= getConnection();
		AdminProDAO adminProDAO = AdminProDAO.getInstance();
		adminProDAO.setConnection(con);
		int tit1Count= adminProDAO.getTit1Count(pro_code);
		close(con);
		return tit1Count;
	}

	//tit_fg 표 개수 구하기 2
	public int getTit2Count(int pro_code) {
		Connection con= getConnection();
		AdminProDAO adminProDAO = AdminProDAO.getInstance();
		adminProDAO.setConnection(con);
		int tit2Count= adminProDAO.getTit2Count(pro_code);
		close(con);
		return tit2Count;
	}
	
	//tit_fg 표 개수 구하기 3
		public int getTit3Count(int pro_code) {
			Connection con= getConnection();
			AdminProDAO adminProDAO = AdminProDAO.getInstance();
			adminProDAO.setConnection(con);
			int tit3Count= adminProDAO.getTit3Count(pro_code);
			close(con);
			return tit3Count;
		}

	//전체 리뷰 
	public ArrayList<ReviewBean> getselectReview(int pro_code) {
		Connection con= getConnection();
		FrontProDAO frontDAO = FrontProDAO.getInstance();
		frontDAO.setConnection(con);
		ArrayList<ReviewBean> allReview= frontDAO.getselectReview(pro_code);
		close(con);
		return allReview;
	}

	//전체 리뷰 개수
	public int getReviewCount(int pro_code) {
		Connection con= getConnection();
		FrontProDAO frontDAO = FrontProDAO.getInstance();
		frontDAO.setConnection(con);
		int reviewCount= frontDAO.getReviewCount(pro_code);
		close(con);
		return reviewCount;
	}
}
