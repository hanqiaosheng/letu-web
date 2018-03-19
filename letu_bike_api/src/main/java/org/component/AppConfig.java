package org.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("AppConfig")
public class AppConfig {
	@Value("${app_id}")
	private String app_id;
	@Value("${app_secret}")
	private String app_secret;
	@Value("${app_domain}")
	private String app_domain;
	@Value("${base_path_web}")
	private String base_path_web;
	@Value("${base_path_weixin}")
	private String base_path_weixin;
	@Value("${base_path_admin}")
	private String base_path_admin;
	@Value("${app_token}")
	private String app_token;
	@Value("${app_partner}")
	private String app_partner;
	@Value("${app_partnerkey}")
	private String app_partnerkey;
	@Value("${upload_path}")
	private String upload_path;
	@Value("${template_color}")
	private String template_color;
	@Value("${page_size_web}")
	private Integer page_size_web;
	@Value("${page_size_weixin}")
	private Integer page_size_weixin;
	@Value("${tcpserver_port}")
	private Integer tcpserver_port;
	@Value("${min_app_id}")
	private String min_app_id;
	@Value("${min_app_secret}")
	private String min_app_secret;
	@Value("${guide_small_app_id}")
	private String guide_small_app_id;
	@Value("${guide_small_app_secret}")
	private String guide_small_app_secret;
	@Value("${app_certificate}")
	private String app_certificate;
	@Value("${base_path_report}")
	private String base_path_report;
	@Value("${base_path_resource}")
	private String base_path_resource;
	
	public String getApp_id() {
		return app_id;
	}
	public String getApp_secret() {
		return app_secret;
	}
	public String getApp_domain() {
		return app_domain;
	}
	public String getBase_path_web() {
		return base_path_web;
	}
	public String getBase_path_weixin() {
		return base_path_weixin;
	}
	public String getApp_token() {
		return app_token;
	}
	public String getApp_partner() {
		return app_partner;
	}
	public String getApp_partnerkey() {
		return app_partnerkey;
	}
	public String getUpload_path() {
		return upload_path;
	}
	public String getTemplate_color() {
		return template_color;
	}
	public Integer getPage_size_web() {
		return page_size_web;
	}
	public Integer getPage_size_weixin() {
		return page_size_weixin;
	}
	public String getBase_path_admin() {
		return base_path_admin;
	}
	public Integer getTcpserver_port() {
		return tcpserver_port;
	}
	public String getMin_app_id() {
		return min_app_id;
	}
	public String getMin_app_secret() {
		return min_app_secret;
	}
	public String getGuide_small_app_id() {
		return guide_small_app_id;
	}
	public String getGuide_small_app_secret() {
		return guide_small_app_secret;
	}
	public String getApp_certificate() {
		return app_certificate;
	}
	public String getBase_path_report() {
		return base_path_report;
	}
	public String getBase_path_resource(){return base_path_resource;}
}
