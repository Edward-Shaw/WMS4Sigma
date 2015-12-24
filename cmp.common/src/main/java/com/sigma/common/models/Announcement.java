package com.sigma.common.models;

import java.sql.Timestamp;

import com.sigma.common.AnnouncementType;

public class Announcement {
	private int id = 0;

	private String title = "";

	private String content = "";

	private String type = AnnouncementType.GEPG.name();

	private Timestamp create;

	private String state = ResourceState.REMOVED.name();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getCreate() {
		return create;
	}

	public void setCreate(Timestamp create) {
		this.create = create;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


}
