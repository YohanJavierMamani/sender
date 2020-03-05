package com.formacionbdi.springboot.app.producer.controller;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.producer.service.RabbitMQSender;


@RestController
@RequestMapping(value = "/rabbitmq/")
public class clasecontroller {
	
	@Autowired
	 RabbitMQSender rabbitMQSender;
	 
	 @PostMapping(value = "/producer")
	 public String producer(@RequestParam(value="message") String message) throws IOException { 
	 
	  rabbitMQSender.send(message);
	  return "Message sent to the RabbitMQ Successfully";
	 }
	 
	 @PostMapping(value = "/producer2")
	 public String producer2(@RequestParam(value="message") String message) throws IOException { 
	 
	  rabbitMQSender.send2(message);
	  return "Message sent to the RabbitMQ funcionó :O";
	 }
}
