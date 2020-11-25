package com.learncode.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Repository;

import com.learncode.WebsiteBanHangThoiTrangApplication;
import com.learncode.models.Items;
import com.learncode.models.MyItems;
import com.learncode.repository.BilldetailsRepository;

@Repository
public class ReportDAOImpl implements ReportDAO {

	@Autowired 
	BilldetailsRepository billdetailsRepository;
	
	//thống kê số lượng đơn hàng từng tháng
	@Override
	public List<MyItems> reportReceiptThang(Date date, int limit) {
        List<MyItems> list = new ArrayList<>();
        for (int i = limit - 1; i > 0; i--) {
            MyItems myItem = new MyItems();
            myItem.setTime(String.valueOf(i));
            myItem.setValue(this.billdetailsRepository.thongkeThang(i));
            list.add(myItem);
        }
        return list;
    }
	
	//thống kê doanh thu năm
	@Override
	public List<MyItems> reportReceiptDoanhThuThang(Date date, int limit) {
        List<MyItems> list = new ArrayList<>();
        for (int i = limit - 1; i > 0; i--) {
            MyItems myItem = new MyItems();
            myItem.setTime(String.valueOf(i));
            myItem.setValue(this.billdetailsRepository.doanhThuThongkeThang(i));
            list.add(myItem);
        }
        return list;
    }
	
	@Override
	public List<MyItems> reportReceiptThongKeDoanhThuTungNgayTrongThang(int year, int month) {
        List<MyItems> list = new ArrayList<>();
        int days = subDays1(year, month);
        for (int i = days; i > 0; i--) {
            MyItems myItem = new MyItems();
            String ngaythangnam = year + "-" + month + "-" + i;
            myItem.setTime(ngaythangnam);
            myItem.setValue(this.billdetailsRepository.thongkeDoanhThuTungNgayTrongThang(toDate1(ngaythangnam)));
            list.add(myItem);
        }
        return list;
    }
	
	
	// thống kê người đăng kí mới
	
	@Override
	public List<MyItems> reportReceiptThongKeNguoiDangKiThang(Date date, int limit) {
        List<MyItems> list = new ArrayList<>();
        for (int i = limit - 1; i > 0; i--) {
            MyItems myItem = new MyItems();
            myItem.setTime(String.valueOf(i));
            myItem.setValue(this.billdetailsRepository.thongkeNguoiDangKiThang(i));
            list.add(myItem);
        }
        return list;
    }
	
	
	@Override
	public List<MyItems> reportReceiptThongKeNguoiDangKiTungThang(int year, int month) {
        List<MyItems> list = new ArrayList<>();
        int days = subDays1(year, month);
        for (int i = days; i > 0; i--) {
            MyItems myItem = new MyItems();
            String ngaythangnam = year + "-" + month + "-" + i;
            myItem.setTime(ngaythangnam);
            myItem.setValue(this.billdetailsRepository.thongkeNguoiDangKiTungNgayTrongThang(toDate1(ngaythangnam)));
            list.add(myItem);
        }
        return list;
    }
	
	
	//thống kê số lượng sản phẩm bán ra
	
	@Override
	public List<MyItems> reportReceiptThongKeSoLuongSanPhamBanRa(Date date, int limit) {
        List<MyItems> list = new ArrayList<>();
        for (int i = limit - 1; i > 0; i--) {
            MyItems myItem = new MyItems();
            myItem.setTime(String.valueOf(i));
            myItem.setValue(this.billdetailsRepository.thongKeSoLuongSanPham(i));
            list.add(myItem);
        }
        return list;
    }
	
	@Override
	public List<MyItems> reportReceiptThongKeSoLuongSanPhamTungThang(int year, int month) {
        List<MyItems> list = new ArrayList<>();
        int days = subDays1(year, month);
        for (int i = days; i > 0; i--) {
            MyItems myItem = new MyItems();
            String ngaythangnam = year + "-" + month + "-" + i;
            myItem.setTime(ngaythangnam);
            myItem.setValue(this.billdetailsRepository.thongkeSoLuongSanPhamTrongThang(toDate1(ngaythangnam)));
            list.add(myItem);
        }
        return list;
    }
	
	//thống kê số lượng đơn hàng
	
	@Override
	public List<MyItems> reportReceiptThongKeSoLuongDonHang(Date date, int limit) {
        List<MyItems> list = new ArrayList<>();
        for (int i = limit - 1; i > 0; i--) {
            MyItems myItem = new MyItems();
            myItem.setTime(String.valueOf(i));
            myItem.setValue(this.billdetailsRepository.thongKeSoLuongDonHang(i));
            list.add(myItem);
        }
        return list;
    }
	
	@Override
	public List<MyItems> reportReceiptThongKeSoLuongDonHangTungThang(int year, int month) {
        List<MyItems> list = new ArrayList<>();
        int days = subDays1(year, month);
        for (int i = days; i > 0; i--) {
            MyItems myItem = new MyItems();
            String ngaythangnam = year + "-" + month + "-" + i;
            myItem.setTime(ngaythangnam);
            myItem.setValue(this.billdetailsRepository.thongkeSoLuongDonHangTrongThang(toDate1(ngaythangnam)));
            list.add(myItem);
        }
        return list;
    }
	
	// so sanh 2 nam
	
	@Override
	public List<Items> reportReceiptSoSanh2Nam(int nam, int thang) {
        List<Items> list = new ArrayList<>();
        for (int i = 1; i <= thang; i++) {
            Items myItem = new Items();
            myItem.setTime(String.valueOf(i));
            myItem.setSanpham(this.billdetailsRepository.sosanh2NamSanPham(nam, i));
            myItem.setSoluong(this.billdetailsRepository.sosanh2NamSoLuong(nam, i));
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
        cal.add(Calendar.DATE,-days);
        return cal.getTime();
    }
    
    public static Integer subDays1(Integer year, Integer month) {
        YearMonth yearMonthObject = YearMonth.of(year, month);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        return daysInMonth;
    }
    
    public static Integer subDays2(Integer year) {
        Year y = Year.of(year);
        int ye = y.length();
        return ye;
    }

    private String covertD2S(Date date) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(date);
    }
    
    private String covertD2S1(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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
    static final SimpleDateFormat DATE_FORMAT1 = new SimpleDateFormat("yyyy-MM-dd");
    public Date toDate1(String date, String...pattern){
        try {
            if (pattern.length > 0) {
            DATE_FORMAT1.applyPattern(pattern[0]);
            }
            
            return DATE_FORMAT1.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

	@Override
	public List<MyItems> reportReceipt(Date date, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
    
   
    
}
