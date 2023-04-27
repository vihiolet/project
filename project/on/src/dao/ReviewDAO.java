package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vo.ReviewBean;

public class ReviewDAO {
	Connection con;
	private static ReviewDAO reviewDAO;

	public static ReviewDAO getInstance() {
		// TODO Auto-generated method stub
		if (reviewDAO == null) {
			reviewDAO = new ReviewDAO();

		}

		return reviewDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;

	}

	public int insertArticle(ReviewDAO reviewDAO2) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		int num = 0;
		String sql = "";
		int insertCount = 0;

		try {
//			pstmt = con.prepareStatement("select max(board_num) from board");
//			rs = pstmt.executeQuery();
//
//			if (rs.next())
//				num = rs.getInt(1) + 1;
//			else
//				num = 1;

			sql = "insert into review (pro_code, tit_fg, sub_fg1, sub_fg2, sub_fg3, create_dt ) values(?,?,?,?,?,now())";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, 0);
			pstmt.setInt(3, 0);
			pstmt.setInt(4, 0);
			pstmt.setInt(5, 0);

			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("boardInsert Error! :" + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}

}
