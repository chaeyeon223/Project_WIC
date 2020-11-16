package kr.or.wic.service;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Base64;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class EncryptWrapper extends HttpServletRequestWrapper {

	public EncryptWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getParameter(String key) {
		String value="";
        if(key!=null && key.equals("pwd")) {
            value = super.getParameter("pwd");
            System.out.println("value :"+value);
            value = getSha512(value);
        }else if(key!=null && key.equals("password")) {
        	try {
				ServletInputStream sis = super.getInputStream();
				DataInputStream dis = new DataInputStream(sis);
				InputStreamReader isr = new InputStreamReader(dis);
				BufferedReader reader = new BufferedReader(isr);
				while(reader.readLine()!=null) {
					System.out.println(reader.readLine());
					System.out.println(reader.readLine().indexOf("pwd"));
					
				};
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
        else {
            //password가 아닌 값들은 특정한 처리를 하지않고 그냥 value에 넣는다.
            value = super.getParameter(key);
        }
        return value;
	}
	
	 public static String getSha512(String password) {
	        String encPwd ="";
	        
	        //sha512방식의 암호화 객체 생성, 암호화 하는 객체
	        MessageDigest md =null;
	        try {
	            md = MessageDigest.getInstance("SHA-512");
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        //암호화 하기전 패스워드를 바이트 단위로 먼저 쪼개주는 작업
	        byte[] bytes = password.getBytes(Charset.forName("UTF-8"));
	        //쪼개진 패스워드를 md의 update로 암호화 작업 진행
	        md.update(bytes);
	        //다시 String형으로 바꾸는 작업
	        encPwd = Base64.getEncoder().encodeToString(md.digest());
	        return encPwd;
	    }

}
