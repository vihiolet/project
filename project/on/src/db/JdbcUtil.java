package db;
import java.sql.*;


public class JdbcUtil {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://mydatabase.c3guwtchkl85.ap-northeast-2.rds.amazonaws.com:3306/project?serverTimezone=Asia/Seoul&characterEncoding=UTF-8";
    private static final String USER = "admin";
    private static final String PW = "ajastudio45";
    public static Statement stmt;

	public static Connection getConnection() {	    
		Connection con = null;

		try {			
			//드라이버 연결
            Class.forName(DRIVER);

            //접속 URL, mysql 유저 아이디, 비밀번호로 접속
            con = DriverManager.getConnection(URL, USER, PW);

			//접속성공 메세지
            System.out.println("Database connection established");			

		}catch(Exception e) {
			System.err.println("Cannot connect to database server");
            System.err.println(e.getMessage());
			e.printStackTrace();
		}		
		return con; 
	}

	public static void close(Connection con) {
		try {
			con.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(Statement stmt) {
		try {
			stmt.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs) {
		try {
			rs.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void commit(Connection con) {
		try {

			con.commit();
			System.out.println("commit Success");

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection con) {
			try {

				con.rollback();
				System.out.println("rollback Success");

			}catch(Exception e) {
				e.printStackTrace();
			}
		}
}