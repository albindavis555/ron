package com.rent.tool.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public class ToolEmbId {
	
	@Column(name = "name" )
	private String name;
	
	@Column(name = "Id" )
	private String id;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ToolEmbId(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}
	
	public ToolEmbId() {
		super();
		
	}

}
