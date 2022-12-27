package dev.noemontes.server.chat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BaseController {

	@GetMapping("/test")
	public String test() {
		return "Rest controller is running";
	}
}
