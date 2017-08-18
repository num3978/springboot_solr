package com.hubert.springboot_solr.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hubert.springboot_solr.bean.SolrUser;
import com.hubert.springboot_solr.util.SolrJUtils;

@RestController
@RequestMapping("/index")
public class SolrController {
	
	@RequestMapping(value = "/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<SolrUser> query() throws SolrServerException, IOException{
		return SolrJUtils.query("name:tom");
	}
	

	@RequestMapping(value = "/insert", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String insert() throws SolrServerException, IOException{
		SolrUser user = new SolrUser();
		user.setId("20");
		user.setName("你好xiaoxiao");
		user.setKeywords("123456");
		user.setEmail("xiaoxiao@gmail.com");
		SolrJUtils.addOne(user);
		return "Y";
	}
	
}
