package com.formacionbdi.springboot.app.producer.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
	
	@Autowired
	 private AmqpTemplate rabbitTemplate;
	 
	 @Value("${sample.rabbitmq.exchange}")
	 private String exchange;
	 
	 @Value("${sample.rabbitmq.routingkey}")
	 private String routingkey;
	 
	 @Value("${sample.rabbitmq.routingkey2}")
	 private String routingkey2;
	 
	 @Scheduled
	 public void send(String message) {
		 
	  String CustomMessage = "This is a message from sender"+ message;
	  
	  rabbitTemplate.convertAndSend(exchange, routingkey, CustomMessage);
	  System.out.println("Send msg to consumer= " + CustomMessage+" ");
	 
	 }
	 
	 @Scheduled
	 public void send2(String message) {
		 
	  String CustomMessage = "Mensaje Enviado n.n"+ message;
	  
	  rabbitTemplate.convertAndSend(exchange, routingkey2, CustomMessage);
	  System.out.println("Mensaje enviado al consumidor= " + CustomMessage+" ");
	 
	 }
}
