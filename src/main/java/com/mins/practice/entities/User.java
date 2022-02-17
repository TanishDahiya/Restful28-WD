package com.mins.practice.entities;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

//@ApiModel(description="All details about the user.")  //used with swagger
public class User {
	
	private Integer id;
	@Size(min=2,message="Name should have min 2 characters")
	private String name;
	@Past
//	@ApiModelProperty(notes="Birthdate should be in past")// used with swagger
	private Date birthDate;

	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	
	

}
