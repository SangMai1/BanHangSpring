package com.learncode.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Proxy(lazy = false)
@Table(name = "ql_bill")
public class Bills{

	@Id
	@GeneratedValue(generator = "bigid")
	@GenericGenerator(name = "bigid", strategy = "com.learncode.config.IDGenerator")
	private long bill_id;

	@Column(name = "bill_name")
	private String bill_name;

	@Column(name = "bill_password")
	private String bill_password;
	
	@Column(name = "bill_email")
	private String bill_email;

	@Column(name = "bill_phone")
	private String bill_phone;

	@Column(name = "bill_address")
	private String bill_address;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date bill_date;

	@Column(name = "bill_status")
	private Integer bill_status;

	public Bills() {
		super();
	}

	public Bills(long bill_id, String bill_name, String bill_password, String bill_email, String bill_phone,
			String bill_address, Date bill_date, Integer bill_status) {
		super();
		this.bill_id = bill_id;
		this.bill_name = bill_name;
		this.bill_password = bill_password;
		this.bill_email = bill_email;
		this.bill_phone = bill_phone;
		this.bill_address = bill_address;
		this.bill_date = bill_date;
		this.bill_status = bill_status;
	}

	public long getBill_id() {
		return bill_id;
	}

	public void setBill_id(long bill_id) {
		this.bill_id = bill_id;
	}

	public String getBill_name() {
		return this.bill_name;
	}

	public void setBill_name(String bill_name) {
		this.bill_name = bill_name;
	}

	public String getBill_password() {
		return bill_password;
	}

	public void setBill_password(String bill_password) {
		this.bill_password = bill_password;
	}

	public String getBill_email() {
		return bill_email;
	}

	public void setBill_email(String bill_email) {
		this.bill_email = bill_email;
	}

	public String getBill_phone() {
		return bill_phone;
	}

	public void setBill_phone(String bill_phone) {
		this.bill_phone = bill_phone;
	}

	public String getBill_address() {
		return bill_address;
	}

	public void setBill_address(String bill_address) {
		this.bill_address = bill_address;
	}

	public Date getBill_date() {
		return bill_date;
	}

	public void setBill_date(Date bill_date) {
		this.bill_date = bill_date;
	}

	public Integer getBill_status() {
		return bill_status;
	}

	public void setBill_status(Integer bill_status) {
		this.bill_status = bill_status;
	}

	@Override
	public String toString() {
		return "Bills [bill_id=" + bill_id + ", bill_name=" + bill_name + ", bill_password=" + bill_password
				+ ", bill_email=" + bill_email + ", bill_phone=" + bill_phone + ", bill_address=" + bill_address
				+ ", bill_date=" + bill_date + ", bill_status=" + bill_status + "]";
	}

	
}
