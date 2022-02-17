package com.mins.practice.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.mins.practice.Bean.HelloWorldBean;

@RestController
public class HelloController {
	
	@Autowired
	private MessageSource messageSource;
	
	// 1st basic and simple method 
	@GetMapping("/home")
	public String home() {
		return "Welcome to the Blog Website";
	}
	
	//2nd method instead of creating a simple string, we create a bean and return it back
	@GetMapping("/home2")
	public HelloWorldBean home2() {
		return new HelloWorldBean("HELLO WORLD BEAN");
	}
	
	//3rd method using path variable
	//String.format convert the arguments into the string 
	// %s is replaced by {name}  for character we use %c and so on
	@GetMapping("/home3/{name}")
	public HelloWorldBean home3(@PathVariable String name) {
		return new HelloWorldBean(String.format("HELLO WORLD BEAN, %s",name));
	}
	
	@GetMapping("/home-internationalisation")
	public String homeInternationalisation() {
		return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
	}
	

}
