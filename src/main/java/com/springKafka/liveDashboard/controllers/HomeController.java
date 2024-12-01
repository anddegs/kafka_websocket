package com.springKafka.liveDashboard.controllers;

import com.springKafka.liveDashboard.services.KafkaProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {

	KafkaProducerService kafkaProducerService;

	public HomeController(KafkaProducerService kafkaProducerService) {
		this.kafkaProducerService = kafkaProducerService;
	}

	@GetMapping("/home")
	public String home(Model model) {
		return "home";
	}

	@GetMapping(value = "/send", produces = "application/json")
	@ResponseBody
	public ResponseMessage send() {
		kafkaProducerService.sendMessage();
		return new ResponseMessage("Hello, JSON from @Controller!");
	}

	public static class ResponseMessage {
		private String message;

		public ResponseMessage(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}
