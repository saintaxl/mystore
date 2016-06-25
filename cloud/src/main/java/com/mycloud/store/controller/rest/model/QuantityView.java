package com.mycloud.store.controller.rest.model;

import java.io.Serializable;

public class QuantityView implements Serializable{

	
    private static final long serialVersionUID = -5357447751860546135L;
    
    private Integer id;
	
	private String name;

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
	

}
