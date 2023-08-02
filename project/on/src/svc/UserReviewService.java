package svc;
import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.UsersDAO;
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
  	//내가 투표한 리뷰
 	 public ArrayList getMyReview(String id){
    		UsersDAO userDAO= UsersDAO.getInstance();
		Connection con= getConnection();
		userDAO.setConnection(con);
    
    		ArrayList myReview= new ArrayList();
		myReview= userDAO.getMyReview();
    		close(con);		
		return myReview;
  }
}
