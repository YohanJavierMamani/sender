package com.formacionbdi.springboot.app.receiver.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class RabbitMQ {
	
	 @RabbitListener(queues = "#{'${sample.rabbitmq.queue}'.split(',')}")
	 public void recievedMessage(String incomingMessage) {
	  System.out.println("El mensaje recibido es el siguiente: " + incomingMessage);
	 }
}





