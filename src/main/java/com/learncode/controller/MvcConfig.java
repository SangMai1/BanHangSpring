package com.learncode.controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path brandUploadDir = Paths.get("./uploads/");
		String brandUploadPath = brandUploadDir.toFile().getAbsolutePath();
		
		registry.addResourceHandler("/uploads/**").addResourceLocations("file:/" + brandUploadPath + "/");
	}
	
}
