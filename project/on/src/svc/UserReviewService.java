package svc;
import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.UsersDAO;
import vo.ReviewBean;
import vo.UserBean;

public class UserReviewService {

	public UserBean getUserInfo2(String id) {
		UsersDAO userDAO= UsersDAO.getInstance();
		Connection con= getConnection();
		userDAO.setConnection(con);
		
		UserBean userInfo= new UserBean();
		userInfo= userDAO.selectUserInfo2(id);
		
		close(con);		
		return userInfo;
	}
  	//내가 투표한 리뷰
 	 public ArrayList<Object> getMyReview(String id, int page, int limit){
    	UsersDAO userDAO= UsersDAO.getInstance();
		Connection con= getConnection();
		userDAO.setConnection(con);
    
		ArrayList<Object> myReview = new ArrayList<Object>();
		myReview= userDAO.getMyReview(id, page, limit);
    	close(con);	
    	
		return myReview;
  }
 	 
 	//내가 투표한 리뷰 개수
	public int getReviewCount(String id) {
		UsersDAO userDAO= UsersDAO.getInstance();
		Connection con= getConnection();
		userDAO.setConnection(con);
		int listCount= userDAO.getReviewCount(id);
		close(con);	
		return listCount;
	}
}
