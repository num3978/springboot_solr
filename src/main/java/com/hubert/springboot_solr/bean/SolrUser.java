package com.hubert.springboot_solr.bean;

import org.apache.solr.client.solrj.beans.Field;

public class SolrUser {
	
	@Field("id")                     //在schema.xml配置必需有url这个field
	private String id;
	
	@Field("name")
	private String name;
	
	@Field("password")
	private String password;
	
	@Field("email")
	private String email;
	
	@Field("keywords")
	private String keywords;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	
}
