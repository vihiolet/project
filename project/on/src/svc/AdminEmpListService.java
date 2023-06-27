package svc;
import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminEmpDAO;
import vo.AdminEmpBean;

public class AdminEmpListService {

	public int getListCount() throws Exception{
		int listCount= 0;
		Connection con= getConnection();
		AdminEmpDAO adminEmpDAO = AdminEmpDAO.getInstance();
		adminEmpDAO.setConnection(con);
		listCount= adminEmpDAO.selectEmpListCount();
		close(con);
		return listCount;
	}

	public ArrayList<AdminEmpBean> getEmpList(int page, int limit) {
		ArrayList<AdminEmpBean> EmpList= null;
		Connection con= getConnection();
		AdminEmpDAO adminEmpDAO = AdminEmpDAO.getInstance();
		adminEmpDAO.setConnection(con);
		EmpList= adminEmpDAO.selectEmpList(page, limit);
		close(con);
		return EmpList;
	}

}
