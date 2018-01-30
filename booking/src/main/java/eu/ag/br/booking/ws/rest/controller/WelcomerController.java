package eu.ag.br.booking.ws.rest.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author codeprime
 *
 */
public class WelcomerController {
	
	
	
	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", "Hello world.");
		
		return "welcome";
	}
	

}
