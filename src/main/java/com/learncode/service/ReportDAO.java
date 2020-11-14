package com.learncode.service;

import java.util.Date;
import java.util.List;

import com.learncode.models.MyItems;

public interface ReportDAO {

	List<MyItems> reportReceipt(Date date, int limit);

}
