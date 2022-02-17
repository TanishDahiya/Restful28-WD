package com.mins.practice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.mins.practice.entities.SomeBean;

@RestController
public class FilteringController {
	
	
	@GetMapping("/filtering")
	public SomeBean retrieveSomeBean() {
		return new SomeBean("value1","value2","value3");
	}
	//STATIC FILTERING
	@GetMapping("/filtering-list")
	public List<SomeBean> retrieveListofSomeBean() {
		return Arrays.asList(new SomeBean("value1","value2","value3"),
		 new SomeBean("value11","value12","value13"));
	}
	
	//DYNAMIC FILTERING
	@GetMapping("/filtering-new")
	public MappingJacksonValue retrievenewSomeBean() {
		SomeBean someBean= new SomeBean("value11","value12","value13");
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		return mapping;
	}
	
	@GetMapping("/filtering-newlist")
	public MappingJacksonValue retrievenewListofSomeBean() {
		List<SomeBean> list=  Arrays.asList(new SomeBean("value1","value2","value3"),
				 new SomeBean("value11","value12","value13"));
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		return mapping;
	}

}
