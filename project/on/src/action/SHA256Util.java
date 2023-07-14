package action;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SHA256Util {
	
	public String getSalt() {
		//salt 만들기
		SecureRandom sr= new SecureRandom();
		byte[] salt= new byte[20];
		sr.nextBytes(salt);
		StringBuffer sb= new StringBuffer();
		for(byte b : salt) {
			sb.append(String.format("%02x", b));
		}
		
		return sb.toString();
	}
	
	public String getEncrypt(String passwd, String salt) {
		//SHA-256알고리즘 적용
		String result= "";
		try {
			MessageDigest md= MessageDigest.getInstance("SHA-256");
			
			md.update((passwd + salt).getBytes());
			byte[] passwdSalt= md.digest();
			
			StringBuffer sb= new StringBuffer();
			for(byte b : passwdSalt) {
				sb.append(String.format("%02x", b));
			}
			result= sb.toString();
		}catch(NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
}
