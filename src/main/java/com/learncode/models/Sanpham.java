package com.learncode.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Proxy(lazy = false)
@Table(name = "qtht_sanpham")
public class Sanpham extends AbstractDomainClass{
	
	@Column(name = "masanpham")
	@NotBlank(message = "không được để trống")
	private String masanpham;
	
	@Column(name = "tensanpham")
	@NotBlank(message = "không được để trống")
	private String tensanpham;
	
	@Column(name = "image")
	private String image;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maloaisanpham")
	private LoaiSanPham loaisanpham;
	
	@Column(name = "xuatxu")
	@NotBlank(message = "không được để trống")
	private String xuatxu;
	
	@Column(name = "mota")
	@NotBlank(message = "không được để trống")
	private String mota;

	@Column(name = "highlight")
	private Integer highlight;
	
	public Sanpham() {
		super();
	}


	
	public Sanpham(@NotBlank(message = "không được để trống") String masanpham,
			@NotBlank(message = "không được để trống") String tensanpham, LoaiSanPham loaisanpham,
			@NotBlank(message = "không được để trống") String xuatxu,
			@NotBlank(message = "không được để trống") String mota, Integer highlight) {
		super();
		this.masanpham = masanpham;
		this.tensanpham = tensanpham;
		this.loaisanpham = loaisanpham;
		this.xuatxu = xuatxu;
		this.mota = mota;
		this.highlight = highlight;
	}



	public Sanpham(@NotBlank(message = "không được để trống") String masanpham,
			@NotBlank(message = "không được để trống") String tensanpham, String image, LoaiSanPham loaisanpham,
			@NotBlank(message = "không được để trống") String xuatxu,
			@NotBlank(message = "không được để trống") String mota, Integer isdelete, Integer highlight) {
		super();
		this.masanpham = masanpham;
		this.tensanpham = tensanpham;
		this.image = image;
		this.loaisanpham = loaisanpham;
		this.xuatxu = xuatxu;
		this.mota = mota;
		this.highlight = highlight;
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

	public Integer getHighlight() {
		return highlight;
	}

	public void setHighlight(Integer highlight) {
		this.highlight = highlight;
	}

	@Transient
	public String getLogoImagePath() {
		if (image == null) {
			return null;
		}
		return "/uploads/" + getId() + "/" + image;
	}


	
}