package com.learncode.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "qtht_sanphamvachitiet")
public class SanphamVaChitiet {

	@Id
	@GeneratedValue(generator = "bigid")
	@GenericGenerator(name = "bigid", strategy = "com.learncode.config.IDGenerator")
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idsanpham")
	private Sanpham sanphams;

	@Column(name = "kichthuoc")
	private String kichthuoc;

	@Column(name = "soluong")
	private Integer soluong;

	@Column(name = "giatien")
	private Float giatien;

	@Column(name = "giamgia")
	private Integer giamgia;

	@Column(name = "isdelete")
	private Integer isdelete;

	public SanphamVaChitiet() {
		super();
	}

	public SanphamVaChitiet(long id, Sanpham sanphams, String kichthuoc, Integer soluong, Float giatien,
			Integer giamgia, Integer isdelete) {
		super();
		this.id = id;
		this.sanphams = sanphams;
		this.kichthuoc = kichthuoc;
		this.soluong = soluong;
		this.giatien = giatien;
		this.giamgia = giamgia;
		this.isdelete = isdelete;
	}

	
	public SanphamVaChitiet(Sanpham sanphams, String kichthuoc, Integer soluong, Float giatien, Integer giamgia,
			Integer isdelete) {
		super();
		this.sanphams = sanphams;
		this.kichthuoc = kichthuoc;
		this.soluong = soluong;
		this.giatien = giatien;
		this.giamgia = giamgia;
		this.isdelete = isdelete;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Sanpham getSanphams() {
		return sanphams;
	}

	public void setSanphams(Sanpham sanphams) {
		this.sanphams = sanphams;
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

	public Integer getGiamgia() {
		return giamgia;
	}

	public void setGiamgia(Integer giamgia) {
		this.giamgia = giamgia;
	}

	public Integer getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

	@Override
	public String toString() {
		return "SanphamVaChitiet [id=" + id + ", sanphams=" + sanphams + ", kichthuoc=" + kichthuoc + ", soluong="
				+ soluong + ", giatien=" + giatien + ", giamgia=" + giamgia + ", isdelete=" + isdelete + "]";
	}

	
}
