package com.learncode.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "qtht_vaitro")
public class VaiTro extends AbstractDomainClass {

	@Column(name = "mavaitro")
	@NotBlank(message = "không được để trống")
	private String mavaitro;

	@Column(name = "tenvaitro")
	@NotBlank(message = "không được để trống")
	private String tenvaitro;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinTable(name = "qtht_vaitrovachucnang", joinColumns = {
			@JoinColumn(name = "idvaitro", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "idchucnang", referencedColumnName = "id") })
	private List<ChucNang1> chucnang = new ArrayList<>();

	public VaiTro() {
		super();
	}

	public VaiTro(@NotBlank(message = "không được để trống") String mavaitro,
			@NotBlank(message = "không được để trống") String tenvaitro, List<ChucNang1> chucnang) {
		super();
		
		this.mavaitro = mavaitro;
		this.tenvaitro = tenvaitro;
		this.chucnang = chucnang;
	}

	public String getMavaitro() {
		return mavaitro;
	}

	public void setMavaitro(String mavaitro) {
		this.mavaitro = mavaitro;
	}

	public String getTenvaitro() {
		return tenvaitro;
	}

	public void setTenvaitro(String tenvaitro) {
		this.tenvaitro = tenvaitro;
	}

	public List<ChucNang1> getChucnang() {
		return chucnang;
	}

	public void setChucnang(List<ChucNang1> chucnang) {
		this.chucnang = chucnang;
	}

}
