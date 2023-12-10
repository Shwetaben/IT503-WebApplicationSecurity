package com.webapplication.security.restcontroller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapplication.security.exception.XSSAttackFoundException;

import io.swagger.v3.oas.annotations.Hidden;

@RestController
@RequestMapping("/api")
@Hidden
public class WebApplicationXSSDemoController {

	@GetMapping("vulnerable/getXSSresponse/{name}")
	public String getGreetingMessages(@PathVariable("name") String name) {

		String s = "<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<title>Page Title</title>\r\n" + "</head>\r\n"
				+ "<body>\r\n" +

				"<p>Welcome,</p>\r\n" + name + "\r\n" +

				"</body>\r\n" + "</html>";
		return s;
	}

	@GetMapping("nonvulnerable/getXSSresponse/{name}")
	public String getGreetingMessages2(@PathVariable("name") String name) {

		if (isValidString(name)) {
			XSSAttackFoundException ex = new XSSAttackFoundException();
			throw ex;
		}

		String s = "<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<title>Page Title</title>\r\n" + "</head>\r\n"
				+ "<body>\r\n" +

				"<p>Welcome,</p>\r\n" + name + "\r\n" +

				"</body>\r\n" + "</html>";
		return s;
	}

	private boolean isValidString(String srt) {
		Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
		Matcher match = pattern.matcher(srt);
		return match.find();
	}
}
