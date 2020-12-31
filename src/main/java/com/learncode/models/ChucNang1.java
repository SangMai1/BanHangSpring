package com.learncode.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "qtht_chucnang")
public class ChucNang1 extends AbstractDomainClass {

	@Column(name = "machucnang")
	@NotBlank(message = "không được để trống")
	private String machucnang;

	@Column(name = "tenchucnang")
	@NotBlank(message = "không được để trống")
	private String tenchucnang;

	@Column(name = "maapi")
	@NotBlank(message = "không được để trống")
	private String maapi;

	@Column(name = "parentid")
	private long parentid;

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

	public long getParentid() {
		return parentid;
	}

	public void setParentid(long parentid) {
		this.parentid = parentid;
	}

	
}
