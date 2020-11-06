package com.learncode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WebsiteBanHangThoiTrangApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsiteBanHangThoiTrangApplication.class, args);
	}

}
