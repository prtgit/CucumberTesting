package com.cmsdb;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
	public static String generateSHA256Hash(String source)
	{
		String hashString = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			md.update(source.getBytes(), 0, source.length());

	        byte[] mdbytes = md.digest();

	        //convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < mdbytes.length; i++) {
	          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        
	        hashString = sb.toString();
	        
	        //System.out.println("Hex format : " + sb.toString());

	       //convert the byte to hex format method 2
	        /*StringBuffer hexString = new StringBuffer();
	    	for (int i=0;i<mdbytes.length;i++) {
	    	  hexString.append(Integer.toHexString(0xFF & mdbytes[i]));
	    	}

	    	System.out.println("Hex format : " + hexString.toString());*/
	        
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hashString;
	}
	
	public static void main(String[] args) {
		System.out.println(new HashUtil().generateSHA256Hash("password"));
		
	}
}
