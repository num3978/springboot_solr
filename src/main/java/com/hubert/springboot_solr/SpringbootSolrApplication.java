package com.hubert.springboot_solr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringbootSolrApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSolrApplication.class, args);
	}
}
