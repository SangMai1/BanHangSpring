package com.learncode.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Repository;

import com.learncode.WebsiteBanHangThoiTrangApplication;
import com.learncode.models.MyItems;
import com.learncode.repository.BilldetailsRepository;

@Repository
public class ReportDAOImpl implements ReportDAO {

	@Autowired 
	BilldetailsRepository billdetailsRepository;
	
	@Override
	public List<MyItems> reportReceipt(Date date, int limit) {
        List<MyItems> list = new ArrayList<>();
        for (int i = limit - 1; i >= 0; i--) {
            Date d = subDays(date, i);
            String d1 = covertD2S(subDays(date, i));
            System.out.println("date" + d1);
            MyItems myItem = new MyItems();
            myItem.setTime(covertD2S(d));
            myItem.setValue(this.billdetailsRepository.thongke(toDate(d1)));
            list.add(myItem);
        }
        return list;
    }
	
	public static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static Date subDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);

        return cal.getTime();
    }

    private String covertD2S(Date date) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(date);
    }
    
    static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    public Date toDate(String date, String...pattern){
        try {
            if (pattern.length > 0) {
            DATE_FORMAT.applyPattern(pattern[0]);
            }
            
            return DATE_FORMAT.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
