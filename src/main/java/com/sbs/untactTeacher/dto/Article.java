package com.sbs.untactTeacher.dto;

public class Article {
	private int id;
	private String regDate;
	private String title;
	private String body;
	private String updateDate;

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public Article(int id, String regDate, String title, String body, String updateDate) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", regDate=" + regDate + ", title=" + title + ", body=" + body + "updateDate = " + updateDate + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
