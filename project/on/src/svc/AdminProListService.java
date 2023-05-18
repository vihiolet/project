package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;


import vo.AdminProBean;

public class AdminProListService {

	public int getListCount() throws Exception {
		
		int listCount= 0;
		Connection con= getConnection();
		
		

		return 0;
	}

	public ArrayList<AdminProBean> getArticleList(int page, int limit) {
		
		return null;
	}

}
