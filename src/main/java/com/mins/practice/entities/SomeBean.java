package com.mins.practice.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

//@JsonIgnoreProperties(value= {"field1","field2"}) //1st way  static filter
@JsonFilter("SomeBeanFilter") // used in dynamic filtering
public class SomeBean {
	
   
	private String field1;
	private String field2;
	@JsonIgnore   // 2nd way This field is ignored or filtered and can not be seen (static filter)
	private String field3;
	
	public SomeBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}
	public SomeBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getField1() {
		return field1;
	}
	public void setField1(String field1) {
		this.field1 = field1;
	}
	public String getField2() {
		return field2;
	}
	public void setField2(String field2) {
		this.field2 = field2;
	}
	public String getField3() {
		return field3;
	}
	public void setField3(String field3) {
		this.field3 = field3;
	}
	
}
