package com.learncode.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "qtht_sanphamvachitiet")
public class SanphamVaChitiet {

	@Id
	private long id;
	
	@Column(name = "idsanpham")
	private long idsanpham;
	
	@Column(name = "kichthuoc")
	private String kichthuoc;
	
	@Column(name = "soluong")
	private Integer soluong;
	
	@Column(name = "giatien")
	private Float giatien;
	
	private Integer isdelete;

	public SanphamVaChitiet() {
		super();
	}

	public SanphamVaChitiet(long id, long idsanpham, String kichthuoc, Integer soluong, Float giatien,
			Integer isdelete) {
		super();
		this.id = id;
		this.idsanpham = idsanpham;
		this.kichthuoc = kichthuoc;
		this.soluong = soluong;
		this.giatien = giatien;
		this.isdelete = isdelete;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdsanpham() {
		return idsanpham;
	}

	public void setIdsanpham(long idsanpham) {
		this.idsanpham = idsanpham;
	}

	public String getKichthuoc() {
		return kichthuoc;
	}

	public void setKichthuoc(String kichthuoc) {
		this.kichthuoc = kichthuoc;
	}

	public Integer getSoluong() {
		return soluong;
	}

	public void setSoluong(Integer soluong) {
		this.soluong = soluong;
	}

	public Float getGiatien() {
		return giatien;
	}

	public void setGiatien(Float giatien) {
		this.giatien = giatien;
	}

	public Integer getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
	
	
}
