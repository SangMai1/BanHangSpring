package com.learncode.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ql_slides")
public class Slides {

	@Id
	@GeneratedValue(generator = "bigid")
	@GenericGenerator(name = "bigid", strategy = "com.learncode.config.IDGenerator")
	private long id;

	@Column(name = "maslides")
	@NotBlank(message = "không được để trống")
	private String maslides;

	@Column(name = "tenslides")
	@NotBlank(message = "không được để trống")
	private String tenslides;

	@Column(name = "createby")
	private String createby;

	@Column(name = "images")
	private String images;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date createday;

	@Column(name = "updateby")
	private String updateby;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date updateday;

	@Column(name = "isdelete")
	private Integer isdelete;

	public Slides() {
		super();
	}

	public Slides(long id, @NotBlank(message = "không được để trống") String maslides,
			@NotBlank(message = "không được để trống") String tenslides) {
		super();
		this.id = id;
		this.maslides = maslides;
		this.tenslides = tenslides;
	}

	public Slides(long id, @NotBlank(message = "không được để trống") String maslides,
			@NotBlank(message = "không được để trống") String tenslides, String createby, String images, Date createday,
			String updateby, Date updateday, Integer isdelete) {
		super();
		this.id = id;
		this.maslides = maslides;
		this.tenslides = tenslides;
		this.createby = createby;
		this.images = images;
		this.createday = createday;
		this.updateby = updateby;
		this.updateday = updateday;
		this.isdelete = isdelete;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMaslides() {
		return maslides;
	}

	public void setMaslides(String maslides) {
		this.maslides = maslides;
	}

	public String getTenslides() {
		return tenslides;
	}

	public void setTenslides(String tenslides) {
		this.tenslides = tenslides;
	}

	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Date getCreateday() {
		return createday;
	}

	public void setCreateday(Date createday) {
		this.createday = createday;
	}

	public String getUpdateby() {
		return updateby;
	}

	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}

	public Date getUpdateday() {
		return updateday;
	}

	public void setUpdateday(Date updateday) {
		this.updateday = updateday;
	}

	public Integer getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

	@Transient
	public String getSlidesImagePath() {
		if (images == null) {
			return null;
		}
		return "/slides/" + id + "/" + images;
	}

}
