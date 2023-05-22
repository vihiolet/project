package svc;

import vo.ReviewBean;
import java.sql.Connection;
import dao.FrontProDAO;
import vo.ReviewBean;
import static db.JdbcUtil.*;


public class ReviewWriteProService {

	public static boolean registArticle(ReviewBean reviewBean) {
		boolean isWriteSuccess = false;
		 Connection con = null;
		 try {
			 con = getConnection();
			 FrontProDAO dao = FrontProDAO.getInstance();
			 dao.setConnection(con);
			 int insertCount = dao.insertArticle(reviewBean);
			 
			 if(insertCount > 0) {
				 commit(con);
				 isWriteSuccess = true;
			 }else { 
				 rollback(con);
			 }
		 }catch(Exception e) {
			 e.printStackTrace();
			 
		 }finally {
			 close(con);
		 }
		 return isWriteSuccess;
	}

}
