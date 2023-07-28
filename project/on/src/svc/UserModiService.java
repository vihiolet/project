package svc;

import static db.JdbcUtil.*;
import dao.UsersDAO;
import vo.UserBean;
import java.sql.Connection;

public class UserModiService {

  //비번, 이름 수정
  public boolean userNamePassModi(String id, String name, String passwdSalt, String salt){
      UsersDAO userDAO= UsersDAO.getInstance();
		  Connection con= getConnection();
		  userDAO.setConnection(con);
		
		  boolean upateSuccess= false;
      int updateCount= userDAO.userNamePassModi(id, name, passwdSalt, salt);
      if(updateCount > 0)
        upateSuccess= true;
			  commit(con);
		  }else {
			  rollback(con);
		  }
		  close(con); 
      return upateSuccess;
  }
  //비번 수정
  public boolean userPassModi(String id, String passwdSalt, String salt){
      UsersDAO userDAO= UsersDAO.getInstance();
		  Connection con= getConnection();
		  userDAO.setConnection(con);
		
		  boolean upateSuccess= false;
      int updateCount= userDAO.userPassModi(id, passwdSalt, salt);
      if(updateCount > 0)
        upateSuccess= true;
			  commit(con);
		  }else {
			  rollback(con);
		  }
		  close(con); 
      return upateSuccess;
  }

  //이름 수정
  public boolean userNameModi(String id, String name){
      UsersDAO userDAO= UsersDAO.getInstance();
		  Connection con= getConnection();
		  userDAO.setConnection(con);
		
		  boolean upateSuccess= false;
      int updateCount= userDAO.userNamePassModi(id, name);
      if(updateCount > 0)
        upateSuccess= true;
			  commit(con);
		  }else {
			  rollback(con);
		  }
		  close(con); 
      return upateSuccess;
  }
}
