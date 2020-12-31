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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false)
@Table(name = "qtht_sanphamvachitiet")
public class SanphamVaChitiet extends AbstractDomainClass {

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

	public SanphamVaChitiet() {
		super();
	}

	public SanphamVaChitiet(Sanpham sanphams, String kichthuoc, Integer soluong, Float giatien,
			Integer giamgia) {
		super();
		this.sanphams = sanphams;
		this.kichthuoc = kichthuoc;
		this.soluong = soluong;
		this.giatien = giatien;
		this.giamgia = giamgia;
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

	
}
