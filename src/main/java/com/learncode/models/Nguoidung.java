package com.learncode.models;

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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Proxy(lazy = false)
@Table(name = "qtht_nguoidung")
public class Nguoidung extends AbstractDomainClass {


	@Column(name = "manguoidung")
	@NotBlank(message = "không được để trống")
	private String manguoidung;

	@Column(name = "tennguoidung")
	@NotBlank(message = "không được để trống")
	private String tennguoidung;

	@Column(name = "password")
	@NotBlank(message = "không được để trống")
	private String password;

	@Column(name = "email")
	@NotBlank(message = "không được để trống")
	@Email(message = "vui lòng nhập định dạng email hợp lệ")
	private String email;

	@Column(name = "gender")
	private int gender;

	@Column(name = "phone")
	@NotBlank(message = "không được để trống")
	@Length(min = 1, max = 11, message = "bạn phải nhập đủ 11 số")
	private String phone;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "qtht_nguoidungvanhomnguoidung", joinColumns = {
			@JoinColumn(name = "idnguoidung", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "idnhom", referencedColumnName = "id") })
	private List<NhomNguoiDung> nhomnguoidung = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinTable(name = "qtht_nguoidungvavaitro", joinColumns = {
			@JoinColumn(name = "idnguoidung", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "idvaitro", referencedColumnName = "id") })
	private List<VaiTro> vaitro = new ArrayList<>();

	public Nguoidung() {
		super();
	}

	public Nguoidung(@NotBlank(message = "không được để trống") String manguoidung,
			@NotBlank(message = "không được để trống") String tennguoidung,
			@NotBlank(message = "không được để trống") String password,
			@NotBlank(message = "không được để trống") @Email(message = "vui lòng nhập định dạng email hợp lệ") String email,
			int gender,
			@NotBlank(message = "không được để trống") @Length(min = 1, max = 11, message = "bạn phải nhập đủ 11 số") String phone,
			List<NhomNguoiDung> nhomnguoidung, List<VaiTro> vaitro) {
		super();
		this.manguoidung = manguoidung;
		this.tennguoidung = tennguoidung;
		this.password = password;
		this.email = email;
		this.gender = gender;
		this.phone = phone;
		this.nhomnguoidung = nhomnguoidung;
		this.vaitro = vaitro;
	}


	public String getManguoidung() {
		return manguoidung;
	}

	public void setManguoidung(String manguoidung) {
		this.manguoidung = manguoidung;
	}

	public String getTennguoidung() {
		return tennguoidung;
	}

	public void setTennguoidung(String tennguoidung) {
		this.tennguoidung = tennguoidung;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<NhomNguoiDung> getNhomnguoidung() {
		return nhomnguoidung;
	}

	public void setNhomnguoidung(List<NhomNguoiDung> nhomnguoidung) {
		this.nhomnguoidung = nhomnguoidung;
	}

	public List<VaiTro> getVaitro() {
		return vaitro;
	}

	public void setVaitro(List<VaiTro> vaitro) {
		this.vaitro = vaitro;
	}




	
}
