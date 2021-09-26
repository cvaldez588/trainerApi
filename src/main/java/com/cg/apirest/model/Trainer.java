package com.cg.apirest.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "trainers")
public class Trainer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trainer_seq")
    @GenericGenerator(
        name = "trainer_seq", 
        strategy = "com.cg.apirest.model.StringPrefixIdGenerator", 
        parameters = {
            @Parameter(name = StringPrefixIdGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = StringPrefixIdGenerator.VALUE_PREFIX_PARAMETER, value = "trainer-id-"),
            @Parameter(name = StringPrefixIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d")
        })
    private String id;
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String phone;
	
	@NotBlank
	private String first_name;
	
	@NotBlank
	private String last_name;
	
	@CreationTimestamp
	private Date createdAt;
	
	@UpdateTimestamp
	private Date updatedAt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
