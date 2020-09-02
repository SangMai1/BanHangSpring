package com.learncode.models;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "qtht_nguoidung")
public class Nguoidung implements Serializable{
	
	@Id
	private long id;
	
	@Column(name = "manguoidung")
	private String manguoidung;
	
	@Column(name = "tennguoidung")
	private String tennguoidung;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "gender")
	private int gender;
	
	@Column(name = "phone")
	private String phone;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date createday;
	
	@Column(name = "nguoitao")
	private String nguoitao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date updateday;
	
	@Column(name = "nguoiupdate")
	private String nguoiupdate;
	
	@Column(name = "isdelete")
	private int isdelete;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "qtht_nguoidungvanhomnguoidung",
				joinColumns = {@JoinColumn(name = "idnguoidung", referencedColumnName = "id")},
				inverseJoinColumns = {@JoinColumn(name = "idnhom", referencedColumnName = "id")})
	private List<NhomNguoiDung> nhomnguoidung = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "qtht_nguoidungvachucnang",
				joinColumns = {@JoinColumn(name = "idnguoidung", referencedColumnName = "id")},
				inverseJoinColumns = {@JoinColumn(name = "idchucnang", referencedColumnName = "id")})
	private List<ChucNang1> chucnang = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "qtht_nguoidungvavaitro",
				joinColumns = {@JoinColumn(name = "idnguoidung", referencedColumnName = "id")},
				inverseJoinColumns = {@JoinColumn(name = "idvaitro", referencedColumnName = "id")})
	private List<VaiTro> vaitro = new ArrayList<>();

	public Nguoidung() {
		super();
	}

	public Nguoidung(long id, String manguoidung, String tennguoidung, String password, String email, int gender,
			String phone, Date createday, String nguoitao, Date updateday, String nguoiupdate, int isdelete,
			List<NhomNguoiDung> nhomnguoidung, List<ChucNang1> chucnang, List<VaiTro> vaitro) {
		super();
		this.id = id;
		this.manguoidung = manguoidung;
		this.tennguoidung = tennguoidung;
		this.password = password;
		this.email = email;
		this.gender = gender;
		this.phone = phone;
		this.createday = createday;
		this.nguoitao = nguoitao;
		this.updateday = updateday;
		this.nguoiupdate = nguoiupdate;
		this.isdelete = isdelete;
		this.nhomnguoidung = nhomnguoidung;
		this.chucnang = chucnang;
		this.vaitro = vaitro;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Date getCreateday() {
		return createday;
	}

	public void setCreateday(Date createday) {
		this.createday = createday;
	}

	public String getNguoitao() {
		return nguoitao;
	}

	public void setNguoitao(String nguoitao) {
		this.nguoitao = nguoitao;
	}

	public Date getUpdateday() {
		return updateday;
	}

	public void setUpdateday(Date updateday) {
		this.updateday = updateday;
	}

	public String getNguoiupdate() {
		return nguoiupdate;
	}

	public void setNguoiupdate(String nguoiupdate) {
		this.nguoiupdate = nguoiupdate;
	}

	public int getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}

	public List<NhomNguoiDung> getNhomnguoidung() {
		return nhomnguoidung;
	}

	public void setNhomnguoidung(List<NhomNguoiDung> nhomnguoidung) {
		this.nhomnguoidung = nhomnguoidung;
	}

	public List<ChucNang1> getChucnang() {
		return chucnang;
	}

	public void setChucnang(List<ChucNang1> chucnang) {
		this.chucnang = chucnang;
	}

	public List<VaiTro> getVaitro() {
		return vaitro;
	}

	public void setVaitro(List<VaiTro> vaitro) {
		this.vaitro = vaitro;
	}

	
	
}
