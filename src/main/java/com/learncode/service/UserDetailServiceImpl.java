package com.learncode.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;


import com.learncode.models.Nguoidung;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	NguoiDungService nguoiDungService;
	

	@Override
	public UserDetails loadUserByUsername(String tennguoidung) throws UsernameNotFoundException {

		Nguoidung nd = this.nguoiDungService.findByTen(tennguoidung);
		System.out.println("nguoi dung day" + nd);
		if (nd == null) {
			throw new UsernameNotFoundException("Could not find nguoi dung");
		};
		
		return new MyUserDetail(nd);
	}
}
