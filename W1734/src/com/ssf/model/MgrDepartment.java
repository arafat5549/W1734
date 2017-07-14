package com.ssf.model;

public class MgrDepartment {

	private Integer id;
	private String name;
	private Integer parentId; // parent_id
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
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	@Override
	public String toString() {
		return "MgrDepartment [id=" + id + ", name=" + name + ", parentId="
				+ parentId + "]";
	}
	
	
}
