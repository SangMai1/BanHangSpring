package com.learncode.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Proxy(lazy = false)
@Table(name = "ql_billdetails")
public class BillDetail {
	
	@Id
	@GeneratedValue(generator = "bigid")
    @GenericGenerator(name = "bigid",strategy = "com.learncode.config.IDGenerator")
	private long billdetail_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bills")
	private Bills bills;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sanphamvachitiet")
	private SanphamVaChitiet sanphamvachitiet;
	
	@Column(name = "billdetail_quantity")
	private Integer billdetail_quantity;
	
	@Column(name = "billdetail_price")
	private Float billdetail_price;
	
	@Column(name = "billdetail_sale")
	private Integer billdetail_sale;
	
	@Column(name = "billdetail_pay")
	private Integer billdetail_pay;
	
	@Column(name = "billdetail_status")
	private Integer billdetail_status;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date billdetail_date;

	public BillDetail() {
		super();
	}

	public BillDetail(long billdetail_id, Bills bills, SanphamVaChitiet sanphamvachitiet, Integer billdetail_quantity,
			Float billdetail_price, Integer billdetail_sale, Integer billdetail_pay, Integer billdetail_status,
			Date billdetail_date) {
		super();
		this.billdetail_id = billdetail_id;
		this.bills = bills;
		this.sanphamvachitiet = sanphamvachitiet;
		this.billdetail_quantity = billdetail_quantity;
		this.billdetail_price = billdetail_price;
		this.billdetail_sale = billdetail_sale;
		this.billdetail_pay = billdetail_pay;
		this.billdetail_status = billdetail_status;
		this.billdetail_date = billdetail_date;
	}

	public long getBilldetail_id() {
		return billdetail_id;
	}

	public void setBilldetail_id(long billdetail_id) {
		this.billdetail_id = billdetail_id;
	}

	public Bills getBills() {
		return bills;
	}

	public void setBills(Bills bills) {
		this.bills = bills;
	}

	public SanphamVaChitiet getSanphamvachitiet() {
		return sanphamvachitiet;
	}

	public void setSanphamvachitiet(SanphamVaChitiet sanphamvachitiet) {
		this.sanphamvachitiet = sanphamvachitiet;
	}

	public Integer getBilldetail_quantity() {
		return billdetail_quantity;
	}

	public void setBilldetail_quantity(Integer billdetail_quantity) {
		this.billdetail_quantity = billdetail_quantity;
	}

	public Float getBilldetail_price() {
		return billdetail_price;
	}

	public void setBilldetail_price(Float billdetail_price) {
		this.billdetail_price = billdetail_price;
	}

	public Integer getBilldetail_sale() {
		return billdetail_sale;
	}

	public void setBilldetail_sale(Integer billdetail_sale) {
		this.billdetail_sale = billdetail_sale;
	}

	public Integer getBilldetail_pay() {
		return billdetail_pay;
	}

	public void setBilldetail_pay(Integer billdetail_pay) {
		this.billdetail_pay = billdetail_pay;
	}

	public Integer getBilldetail_status() {
		return billdetail_status;
	}

	public void setBilldetail_status(Integer billdetail_status) {
		this.billdetail_status = billdetail_status;
	}

	public Date getBilldetail_date() {
		return billdetail_date;
	}

	public void setBilldetail_date(Date billdetail_date) {
		this.billdetail_date = billdetail_date;
	}

	@Override
	public String toString() {
		return "BillDetail [billdetail_id=" + billdetail_id + ", bills=" + bills + ", sanphamvachitiet="
				+ sanphamvachitiet + ", billdetail_quantity=" + billdetail_quantity + ", billdetail_price="
				+ billdetail_price + ", billdetail_sale=" + billdetail_sale + ", billdetail_pay=" + billdetail_pay
				+ ", billdetail_status=" + billdetail_status + ", billdetail_date=" + billdetail_date + "]";
	}
	
	
}
