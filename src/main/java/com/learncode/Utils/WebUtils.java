package com.learncode.Utils;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


public class WebUtils {

	public static String toString(User user) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("tennguoidung").append(user.getUsername());
		sb.append("password").append(user.getPassword());
		Collection<GrantedAuthority> authorities = user.getAuthorities();
		if (authorities != null && authorities.isEmpty()) {
			sb.append(" (");
			boolean first = true;
			for (GrantedAuthority a : authorities) {
				if (first) {
					sb.append(a.getAuthority());
					first = false;
				} else {
					sb.append(", ").append(a.getAuthority());
				}
			}
			sb.append(")");
		}
		return sb.toString();
	}
}
