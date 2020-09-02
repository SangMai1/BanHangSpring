package com.learncode.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "qtht_sanpham")
public class Sanpham {
	
	@Id
	private long id;
	
	@Column(name = "masanpham")
	private String masanpham;
	
	@Column(name = "tensanpham")
	private String tensanpham;
	
	@Column(name = "image")
	private String image;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maloaisanpham")
	private LoaiSanPham loaisanpham;
	
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
	
	@Column(name = "xuatxu")
	private String xuatxu;
	
	@Column(name = "mota")
	private String mota;
	
	@Column(name = "isdelete")
	private Integer isdelete;
	
	public Sanpham() {
		super();
	}

	public Sanpham(long id, String masanpham, String tensanpham, String image, LoaiSanPham loaisanpham, Date createday,
			String createby, Date updateday, String updateby, String xuatxu, String mota, Integer isdelete) {
		super();
		this.id = id;
		this.masanpham = masanpham;
		this.tensanpham = tensanpham;
		this.image = image;
		this.loaisanpham = loaisanpham;
		this.createday = createday;
		this.createby = createby;
		this.updateday = updateday;
		this.updateby = updateby;
		this.xuatxu = xuatxu;
		this.mota = mota;
		this.isdelete = isdelete;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMasanpham() {
		return masanpham;
	}

	public void setMasanpham(String masanpham) {
		this.masanpham = masanpham;
	}

	public String getTensanpham() {
		return tensanpham;
	}

	public void setTensanpham(String tensanpham) {
		this.tensanpham = tensanpham;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public LoaiSanPham getLoaisanpham() {
		return loaisanpham;
	}

	public void setLoaisanpham(LoaiSanPham loaisanpham) {
		this.loaisanpham = loaisanpham;
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

	public String getXuatxu() {
		return xuatxu;
	}

	public void setXuatxu(String xuatxu) {
		this.xuatxu = xuatxu;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public Integer getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

	@Transient
	public String getLogoImagePath() {
		if (image == null) {
			return null;
		}
		return "/uploads/" + id + "/" + image;
	}
}
