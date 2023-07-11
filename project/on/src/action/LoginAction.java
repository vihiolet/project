package action;

import static db.JdbcUtil.getConnection;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

import javax.management.RuntimeErrorException;

import dao.LoginDAO;
import svc.LoginService;
import vo.UserBean;

public class LoginAction {

	//암호회된 비번 반환
	public String getEncrypt(String pass, String salt) {
		
		UserBean loginMember = new UserBean();
		LoginService loginService= new LoginService();
		salt= loginService.getSaltById(loginMember.getId());
		pass= loginMember.getPass();
		
		String result= "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update((pass + salt).getBytes());
			byte[] passSalt= md.digest();
			
			StringBuffer sb= new StringBuffer();
			for(byte b : passSalt) {
				sb.append(String.format("%02x", b));
			}			
			result= sb.toString();
		}catch(NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	public UserBean LoginUser(String id, String pass) {
		LoginService loginService= new LoginService();
		UserBean loginUser= loginService.getLoginUser(id, pass);
		return loginUser;
	}

}
