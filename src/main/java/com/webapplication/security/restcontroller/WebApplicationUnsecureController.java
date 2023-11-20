package com.webapplication.security.restcontroller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapplication.security.domain.User;
import com.webapplication.security.restcontroller.repository.MySQLRepository;

@RestController
@RequestMapping("/api/v1")
public class WebApplicationUnsecureController {
	
	@Autowired
	MySQLRepository repository;
	
	@GetMapping("/getDetails/{userId}")
	public List<User> getDetails(@PathVariable("userId") String userId) {
		System.out.println("ÄPI hit");
		return repository.getUnsecureUserDeatils(userId);
	}
	
	

}
