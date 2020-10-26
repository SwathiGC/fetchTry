package com.forms.learn.core.servlets.pojo;

public class FirstBean {

	private String title;
	private String path;
	private String description;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public FirstBean(String title, String path, String description) {
		super();
		this.title = title;
		this.path = path;
		this.description = description;
	}
}
