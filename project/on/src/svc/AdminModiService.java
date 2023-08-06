package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.AdminEmpDAO;
import vo.AdminEmpBean;

public class AdminModiService {
	//입력한 비밀번호가 맞는지 확인
	public String isPasswd(String id) {
		
		AdminEmpDAO empDAO= AdminEmpDAO.getInstance();
		Connection con= getConnection();
		empDAO.setConnection(con);		
		
		String isPasswd= empDAO.getAdminPasswd(id);
		close(con); 
		return isPasswd;
	}
	//비번, 이름 수정
	public boolean adminNamePassModi(String id, String name, String passwdSalt, String salt) {
		AdminEmpDAO empDAO= AdminEmpDAO.getInstance();
		Connection con= getConnection();
		empDAO.setConnection(con);
		boolean upateSuccess= false;
		int updateCount= empDAO.adminNamePassModi(id, name, passwdSalt, salt);
	    if(updateCount > 0) {
		   upateSuccess= true;
		   commit(con);
		}else {
			rollback(con);
		}
		close(con); 
		return upateSuccess;
	}
	//비번 수정
	public boolean adminPassModi(String id, String passwdSalt, String salt) {
		AdminEmpDAO empDAO= AdminEmpDAO.getInstance();
		Connection con= getConnection();
		empDAO.setConnection(con);
		boolean upateSuccess= false;
		int updateCount= empDAO.adminPassModi(id, passwdSalt, salt);
	    if(updateCount > 0) {
	        upateSuccess= true;
			commit(con);
		}else {
			rollback(con);
		}
		close(con); 
	    return upateSuccess;
	}
	//이름 수정
	public boolean adminNameModi(String id, String name) {
		AdminEmpDAO empDAO= AdminEmpDAO.getInstance();
		Connection con= getConnection();
		empDAO.setConnection(con);
		boolean upateSuccess= false;
	    int updateCount= empDAO.adminNamePassModi(id, name);
	    if(updateCount > 0) {
	       upateSuccess= true;
	       commit(con);
	    }else {
			rollback(con);
		}
	    	close(con); 
	    return upateSuccess;
	}
}
