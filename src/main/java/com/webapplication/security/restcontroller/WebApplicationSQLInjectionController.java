package com.webapplication.security.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapplication.security.domain.User;
import com.webapplication.security.restcontroller.repository.MySQLRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name= "SQL Injection and Prevention")
public class WebApplicationSQLInjectionController {

	@Autowired
	MySQLRepository repository;
	
	@Operation(summary = "This API uses simple connection to database, which has risk of SQL injection vulnerability", description = "Adding userId value and \"' or 1=1 -- \" string exploits SQL Injection vulnerability")
	@GetMapping("vulnerable/getDetails/{userId}")
	public List<User> getUserDetailsVulnerable(@PathVariable("userId") String userId) {
		return repository.getUnsecureUserDeatils(userId);
	}

	@GetMapping("nonvulnerable/getDetails/{userId}")
	@Operation(summary = "This API uses prepared connection to database, which is precompile statement, which is safe from SQL Injection vulnerability", description = "Adding userId value and \"' or 1=1 -- \" string does not exploit SQL Injection vulnerability")

	public List<User> getUserDetailsNonVulnerable(@PathVariable("userId") String userId) {
		return repository.getSecureUserDeatils(userId);
	}

}
