package com.learncode.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "qtht_loaisanpham")
public class LoaiSanPham extends AbstractDomainClass {

	@Column(name = "maloaisanpham")
	@NotBlank(message = "không được để trống")
	private String maloaisanpham;
	
	@Column(name = "tenloaisanpham")
	@NotBlank(message = "không được để trống")
	private String tenloaisanpham;

	public LoaiSanPham() {
		super();
	}

	public LoaiSanPham(@NotBlank(message = "không được để trống") String maloaisanpham,
			@NotBlank(message = "không được để trống") String tenloaisanpham) {
		super();
		this.maloaisanpham = maloaisanpham;
		this.tenloaisanpham = tenloaisanpham;
	}

	public String getMaloaisanpham() {
		return maloaisanpham;
	}

	public void setMaloaisanpham(String maloaisanpham) {
		this.maloaisanpham = maloaisanpham;
	}

	public String getTenloaisanpham() {
		return tenloaisanpham;
	}

	public void setTenloaisanpham(String tenloaisanpham) {
		this.tenloaisanpham = tenloaisanpham;
	}

	@Override
	public String toString() {
		return "LoaiSanPham [maloaisanpham=" + maloaisanpham + ", tenloaisanpham=" + tenloaisanpham + ", getId()="
				+ getId() + ", getNguoitao()=" + getNguoitao() + ", getNguoiupdate()=" + getNguoiupdate()
				+ ", getIsdelete()=" + getIsdelete() + ", getCreateday()=" + getCreateday() + ", getUpdateday()="
				+ getUpdateday() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
