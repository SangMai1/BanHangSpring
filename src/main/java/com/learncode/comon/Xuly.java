package com.learncode.comon;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

public class Xuly {

	public static Map<String, String> getTextFile(String file) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(new File(file));
			String line;
			while (scanner.hasNext()) {
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
		if (null == st || st.equals("")) {
			return "";
		} else {
			st = st.toLowerCase();
			String[] arr = st.split(" ");
			String search = "";
			String result = "";
			for (int i = 0; i < arr.length; i++) {
				String temp = (search + " " + arr[i]).trim();
				System.out.println("temp" + temp);
				if (mapFile.containsKey(temp)) {
					search = temp;
				} else {
					search = search.replaceAll(" ", "&").trim();
					result = result + search;
					search = temp;
					System.out.println("search" + search);
				}
			}
			search = search.replaceAll(" ", "&").trim();
			result = search;
			System.out.println("resutlt" + result);
			return result;
		}
	}

	public static String giaiMd5(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}
	
	public static boolean checkMd5(String p1, String p2) {
		return BCrypt.checkpw(p1, p2);
	}

}
