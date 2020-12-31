package com.learncode.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "qtht_nhomnguoidung")
public class NhomNguoiDung extends AbstractDomainClass {


	@Column(name = "manhom")
	@NotBlank(message = "không được để trống")
	private String manhom;

	@Column(name = "tennhom")
	@NotBlank(message = "không được để trống")
	private String tennhom;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinTable(name = "qtht_nhomnguoidungchucnang", joinColumns = {
			@JoinColumn(name = "idnhom", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "idchucnang", referencedColumnName = "id") })
	private Set<ChucNang1> chucnang = new HashSet<>();

	public NhomNguoiDung() {
		super();
	}

	public NhomNguoiDung(@NotBlank(message = "không được để trống") String manhom,
			@NotBlank(message = "không được để trống") String tennhom, String nguoitao, Set<ChucNang1> chucnang) {
		super();
		this.manhom = manhom;
		this.tennhom = tennhom;
		this.chucnang = chucnang;
	}

	public String getManhom() {
		return manhom;
	}

	public void setManhom(String manhom) {
		this.manhom = manhom;
	}

	public String getTennhom() {
		return tennhom;
	}

	public void setTennhom(String tennhom) {
		this.tennhom = tennhom;
	}

	public Set<ChucNang1> getChucnang() {
		return chucnang;
	}

	public void setChucnang(Set<ChucNang1> chucnang) {
		this.chucnang = chucnang;
	}

	
}
