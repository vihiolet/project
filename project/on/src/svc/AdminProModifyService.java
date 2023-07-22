package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.AdminProDAO;
import vo.AdminProBean;

public class AdminProModifyService {
	
	//권한 확인
		public boolean isKeyWriter(String id) {
			boolean isAdminUser= false;
			Connection con= getConnection();
			AdminProDAO dao= AdminProDAO.getInstance();
			dao.setConnection(con);
			isAdminUser= dao.isAdminUser(id);
			close(con);
			return isAdminUser;
		}

		public boolean modifyPro(AdminProBean proBean) {
			boolean isModifySuccess= false;
			Connection con= getConnection();
			AdminProDAO dao= AdminProDAO.getInstance();
			dao.setConnection(con);
			int updateCount= dao.updatePro(proBean);
			if(updateCount > 0) {
				commit(con);
				isModifySuccess= true;
			}else {
				rollback(con);
			}
			close(con);
			return isModifySuccess;
		}
}
