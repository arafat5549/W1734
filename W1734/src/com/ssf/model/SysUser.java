package com.ssf.model;

import java.util.Date;

public class SysUser {

	//私有化属性，基本类型还是封装类型
	
	//封装类型的默认值为空，如果你的属性id数据库设置为不能为空NOT NULL，会报错，基本类型不会
	private Integer id;
	private String name;
	private Integer type;
	private Date createTime;
	private Date updateTime;
	
	//生成getter和setter方法
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "SysUser [id=" + id + ", name=" + name + ", type=" + type
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}
	
	
	
	
	
}
