package com.learncode.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "qtht_chucnang")
public class ChucNang1 {

	@Id
	@GeneratedValue(generator = "bigid")
	@GenericGenerator(name = "bigid", strategy = "com.learncode.config.IDGenerator")
	private long id;

	@Column(name = "machucnang")
	@NotBlank(message = "không được để trống")
	private String machucnang;

	@Column(name = "tenchucnang")
	@NotBlank(message = "không được để trống")
	private String tenchucnang;

	@Column(name = "maapi")
	@NotBlank(message = "không được để trống")
	private String maapi;

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

	@Column(name = "parentid")
	private long parentid;

	@Column(name = "isdelete")
	private int isdelete;

	public ChucNang1() {
		super();
	}

	public ChucNang1(long id, String machucnang, @NotBlank(message = "không được để trống") String tenchucnang,
			String maapi, Date createday, String nguoitao, Date updateday, String nguoiupdate, long parentid,
			int isdelete) {
		super();
		this.id = id;
		this.machucnang = machucnang;
		this.tenchucnang = tenchucnang;
		this.maapi = maapi;
		this.createday = createday;
		this.nguoitao = nguoitao;
		this.updateday = updateday;
		this.nguoiupdate = nguoiupdate;
		this.parentid = parentid;
		this.isdelete = isdelete;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMachucnang() {
		return machucnang;
	}

	public void setMachucnang(String machucnang) {
		this.machucnang = machucnang;
	}

	public String getTenchucnang() {
		return tenchucnang;
	}

	public void setTenchucnang(String tenchucnang) {
		this.tenchucnang = tenchucnang;
	}

	public String getMaapi() {
		return maapi;
	}

	public void setMaapi(String maapi) {
		this.maapi = maapi;
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

	public long getParentid() {
		return parentid;
	}

	public void setParentid(long parentid) {
		this.parentid = parentid;
	}

	public int getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}


	
}
