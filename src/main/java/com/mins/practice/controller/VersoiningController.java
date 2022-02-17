package com.mins.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mins.practice.versoining.Name;
import com.mins.practice.versoining.Personv1;
import com.mins.practice.versoining.Personv2;

@RestController
public class VersoiningController {
	
	//This is also known as URI versioning and used by Twitter
	@GetMapping("/personv1")
	public Personv1 personv1() {
		return new Personv1("Tanish Dahiya");
	}
	
	@GetMapping("/personv2")
	public Personv2 personv2() {
		return new Personv2(new Name("Tanish", "Dahiya"));
	}
	
	
	//get using params
	//http://localhost:8089/person/param?version=1
	//This is also known as Request paramater versioning and used by AMAZON
	@GetMapping(value="/person/param",params="version=1")
	public Personv2 personv2new() {
		return new Personv2(new Name("Hello", "World"));
	}
	
	//get using header
	//http://localhost:8089/person/header
	//in header-->API_VERSION   and in value-->2
	//This is  also known as header versioning used by MICROSOFT
	@GetMapping(value="/person/header",headers="API_VERSION=2")
	public Personv2 personv2newheader() {
		return new Personv2(new Name("Header", "Method"));
	}
	
	//get using produces
	//http://localhost:8089/person/produces
	//Accept on in key and in value->application/vnd.company.app-v1+json
	//This is also know as Media type versioning a.k.a "content negotiation" or accept header used by GITHUB
	@GetMapping(value="/person/produces",produces="application/vnd.company.app-v1+json")
	public Personv2 personv2newproduces() {
		return new Personv2(new Name("Hey", "There"));
	}
	


}
