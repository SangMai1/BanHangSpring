package com.learncode.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false)
@Table(name = "ql_kho")
public class Kho {

	@Id
	@GeneratedValue(generator = "bigid")
	@GenericGenerator(name = "bigid", strategy = "com.learncode.config.IDGenerator")
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "billdetails_id")
	private BillDetail billdetails_id;
	
	@Column(name = "trangthai")
	private int trangthai;
	
	@Column(name = "isdelete")
	private int isdelete;

	public Kho() {
		super();
	}

	public Kho(long id, BillDetail billdetails_id, int trangthai, int isdelete) {
		super();
		this.id = id;
		this.billdetails_id = billdetails_id;
		this.trangthai = trangthai;
		this.isdelete = isdelete;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BillDetail getBilldetails_id() {
		return billdetails_id;
	}

	public void setBilldetails_id(BillDetail billdetails_id) {
		this.billdetails_id = billdetails_id;
	}

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}

	public int getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}
	
	
	
	
}
