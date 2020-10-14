package com.learncode.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@Table(name="qtht_vaitro")
public class VaiTro implements Serializable{
	
	@Id
	private long id;
	
	@Column(name = "mavaitro")
	private String mavaitro;
	
	@Column(name = "tenvaitro")
	private String tenvaitro;
	
	@Column(name = "nguoitao")
	private String nguoitao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date createday;
	
	@Column(name = "nguoiupdate")
	private String nguoiupdate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date updateday;
	
	@Column(name="isdelete")
	private int isdelete;

	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinTable(name = "qtht_vaitrovachucnang",
				joinColumns = {@JoinColumn(name = "idvaitro", referencedColumnName = "id")},
				inverseJoinColumns = {@JoinColumn(name = "idchucnang", referencedColumnName = "id")})
	private List<ChucNang1> chucnang = new ArrayList<>();

	public VaiTro() {
		super();
	}

	public VaiTro(long id, String mavaitro, String tenvaitro, String nguoitao, Date createday, String nguoiupdate,
			Date updateday, int isdelete, List<ChucNang1> chucnang) {
		super();
		this.id = id;
		this.mavaitro = mavaitro;
		this.tenvaitro = tenvaitro;
		this.nguoitao = nguoitao;
		this.createday = createday;
		this.nguoiupdate = nguoiupdate;
		this.updateday = updateday;
		this.isdelete = isdelete;
		this.chucnang = chucnang;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getNguoitao() {
		return nguoitao;
	}

	public void setNguoitao(String nguoitao) {
		this.nguoitao = nguoitao;
	}

	public Date getCreateday() {
		return createday;
	}

	public void setCreateday(Date createday) {
		this.createday = createday;
	}

	public String getNguoiupdate() {
		return nguoiupdate;
	}

	public void setNguoiupdate(String nguoiupdate) {
		this.nguoiupdate = nguoiupdate;
	}

	public Date getUpdateday() {
		return updateday;
	}

	public void setUpdateday(Date updateday) {
		this.updateday = updateday;
	}

	public int getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}

	public List<ChucNang1> getChucnang() {
		return chucnang;
	}

	public void setChucnang(List<ChucNang1> chucnang) {
		this.chucnang = chucnang;
	}

	@Override
	public String toString() {
		return "VaiTro [id=" + id + ", mavaitro=" + mavaitro + ", tenvaitro=" + tenvaitro + ", nguoitao=" + nguoitao
				+ ", createday=" + createday + ", nguoiupdate=" + nguoiupdate + ", updateday=" + updateday
				+ ", isdelete=" + isdelete + ", chucnang=" + chucnang + "]";
	}
	
	
	
}
