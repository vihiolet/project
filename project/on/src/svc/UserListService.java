package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.UsersDAO;
import vo.ReviewBean;
import vo.UserBean;

public class UserListService {

	public UserBean getUserInfo2(String id) {
		UsersDAO userDAO= UsersDAO.getInstance();
		Connection con= getConnection();
		userDAO.setConnection(con);
		
		UserBean userInfo= new UserBean();
		userInfo= userDAO.selectUserInfo2(id);
		
		close(con);		
		return userInfo;
	}

	public ArrayList<ReviewBean> getUserReview(String id) {
		UsersDAO userDAO= UsersDAO.getInstance();
		Connection con= getConnection();
		userDAO.setConnection(con);
		
		ArrayList<ReviewBean> review= new ArrayList<ReviewBean>();
		review= userDAO.selectUserReview(id);
		
		close(con);
		return review;
	}
	//내가 쓴 리뷰 몇개? 
	public int getReviewCount(String id) {
		UsersDAO userDAO= UsersDAO.getInstance();
		Connection con= getConnection();
		userDAO.setConnection(con);
		int reviewCount= userDAO.getReviewCount(id);
		close(con);
		return reviewCount;
	}

}
