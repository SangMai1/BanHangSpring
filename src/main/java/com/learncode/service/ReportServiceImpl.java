package com.learncode.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learncode.models.MyItems;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportDAO reportDAO;
	
	@Override
	public List<MyItems> reportReceipt(Date date, int limit){
		return reportDAO.reportReceipt(date, limit);
	}
}
