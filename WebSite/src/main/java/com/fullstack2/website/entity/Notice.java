package com.fullstack2.webSite.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Notice extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long nno;
	private String title;
	private String admin;
	private String content;
	
	
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

    public String getAdmin() {
    	return admin;
    }
    
    public void setAdmin(String admin) {
    	this.admin = admin;
    }
    
}
