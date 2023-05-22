package svc;

import java.sql.Connection;

import dao.AdminProDAO;
import vo.AdminProBean;
import static db.JdbcUtil.*;

public class AdminProRegService {

	public boolean registArticle(AdminProBean adminProBean) throws Exception{
		
		boolean isSuccess = false;
		Connection con = getConnection();
		AdminProDAO dao = AdminProDAO.getInstance();		//싱글톤 방식으로 DAO 클래스에서 생성된 인스턴스를 가져옴
		 dao.setConnection(con);
		 int insertCount = dao.insertArticle(adminProBean);
		 
		 if(insertCount > 0) {
			 commit(con);
			 isSuccess = true;			 
		 }else {
			 rollback(con);
		 }
		
		close(con);
		return isSuccess;
	}

}
