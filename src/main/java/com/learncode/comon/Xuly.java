package com.learncode.comon;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.SpringApplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learncode.models.ChucNang1;
import com.learncode.models.Nguoidung;


public class Xuly {
	
	public static Map<String, String> getTextFile(String file){
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(new File(file));
			String line;
			while(scanner.hasNext()) {
				line = scanner.nextLine();
				String[] value = line.split(":");
				map.put(value[0].trim(), value[1].trim());
			}
			scanner.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}
	
	public static String xuLySearch(String st) {
		Map<String, String> mapFile = getTextFile("data.txt");
		if(null == st || st.equals("")) {
			return "";
		} else {
			st = st.toLowerCase().trim();
			String[] arr = st.split(" ");
			String search = "";
			String result = "";
			for(int i = 0; i < arr.length; i++) {
				String temp = (search + " " + arr[i]).trim();
				if (mapFile.containsKey(temp)) {
					search = temp;
				} else {
					search = search.replace(" ", "&").trim();
					result = result + search ;
					search = arr[i];
				}
			}
			search = search.replace(" ", "&").trim();
			result = result + search;
			return result;
		}
	}
	
	public static String CredAndPass(String password) {
		try {
			password = "abc" + password;
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(password.getBytes());
			byte[] digest = md5.digest();
			StringBuilder sb = new StringBuilder(32);
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			return sb.toString();
		} catch (Exception e) {
			System.out.println("Exception thrown"
	                + " for incorrect algorithm: " + e);
	        return null;
		}
	}
	
	public static String giaiMd5(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}
	
	public static boolean checkMd5(String p1, String p2) {
		return BCrypt.checkpw(p1, p2);
	}

	public static boolean checkUrl(Nguoidung nd, String url) {
		for (ChucNang1 chucNang1 : nd.getChucnang()) {
			if (chucNang1.getMaapi().equals(url)) {
				return true;
			}
		}
		return false;
	}
}
