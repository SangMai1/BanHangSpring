package com.learncode.models;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class AbstractDomainClass implements DomainObject {

	@Id
	@GeneratedValue(generator = "bigid")
	@GenericGenerator(name = "bigid", strategy = "com.learncode.config.IDGenerator")
	private long id;
	
	private Date createday;
	private Date updateday;
	
	private String nguoitao;
	private String nguoiupdate;
	
	private Integer isdelete;
	
	@Override
	public long getId() {
		return id;
	}
	
	@Override
	public void setId(long id) {
		this.id = id;
	}

	
	
	public String getNguoitao() {
		return nguoitao;
	}

	public void setNguoitao(String nguoitao) {
		this.nguoitao = nguoitao;
	}

	public String getNguoiupdate() {
		return nguoiupdate;
	}

	public void setNguoiupdate(String nguoiupdate) {
		this.nguoiupdate = nguoiupdate;
	}

	public Integer getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

	public Date getCreateday() {
		return createday;
	}

	public Date getUpdateday() {
		return updateday;
	}
	
	@PreUpdate
	@PrePersist
	public void updateTimeTamps() {
		updateday = new Date();
		if (createday == null) {
			createday = new Date(); 
		}
	}
}
