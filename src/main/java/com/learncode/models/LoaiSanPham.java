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
public class LoaiSanPham {

	@Id
	@GeneratedValue(generator = "bigid")
    @GenericGenerator(name = "bigid",strategy = "com.learncode.config.IDGenerator")
	private long id;
	
	@Column(name = "maloaisanpham")
	@NotBlank(message = "không được để trống")
	private String maloaisanpham;
	
	@Column(name = "tenloaisanpham")
	@NotBlank(message = "không được để trống")
	private String tenloaisanpham;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date createday;
	
	@Column(name = "createby")
	private String createby;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date updateday;
	
	@Column(name = "updateby")
	private String updateby;
	
	@Column(name = "isdelete")
	private int isdelete;

	public LoaiSanPham() {
		super();
	}

	public LoaiSanPham(long id, @NotBlank(message = "không được để trống") String maloaisanpham,
			@NotBlank(message = "không được để trống") String tenloaisanpham, Date createday, String createby,
			Date updateday, String updateby, int isdelete) {
		super();
		this.id = id;
		this.maloaisanpham = maloaisanpham;
		this.tenloaisanpham = tenloaisanpham;
		this.createday = createday;
		this.createby = createby;
		this.updateday = updateday;
		this.updateby = updateby;
		this.isdelete = isdelete;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Date getCreateday() {
		return createday;
	}

	public void setCreateday(Date createday) {
		this.createday = createday;
	}

	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}

	public Date getUpdateday() {
		return updateday;
	}

	public void setUpdateday(Date updateday) {
		this.updateday = updateday;
	}

	public String getUpdateby() {
		return updateby;
	}

	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}

	public int getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}

	@Override
	public String toString() {
		return "LoaiSanPham [id=" + id + ", maloaisanpham=" + maloaisanpham + ", tenloaisanpham=" + tenloaisanpham
				+ ", createday=" + createday + ", createby=" + createby + ", updateday=" + updateday + ", updateby="
				+ updateby + ", isdelete=" + isdelete + "]";
	}

	
	
}
