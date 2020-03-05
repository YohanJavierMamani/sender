package com.formacionbdi.springboot.app.producer.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	
	@Value("${sample.rabbitmq.queue}")
	String queueName;

	@Value("${sample.rabbitmq.queue2}")
	String queueName2;	
	
	@Value("${sample.rabbitmq.exchange}")
	 String exchange;
	
	@Value("${sample.rabbitmq.routingkey}")
	 private String routingkey;

	@Value("${sample.rabbitmq.routingkey2}")
	 private String routingkey2;	
	
	@Bean
	 Queue queue() {
	  return new Queue(queueName, false);
	 }

	@Bean
	 Queue queue2() {
	  return new Queue(queueName2, false);
	 }	
	
	@Bean
	 DirectExchange exchange() {
	  return new DirectExchange(exchange);
	 }
	
	@Bean
	 Binding binding(@Qualifier("queue") Queue queue, DirectExchange exchange) {
	  return BindingBuilder.bind(queue).to(exchange).with(routingkey);
	 }

	@Bean
	 Binding binding2(@Qualifier("queue2") Queue queue, DirectExchange exchange) {
	  return BindingBuilder.bind(queue).to(exchange).with(routingkey2);
	 }	
	
	
	@Bean
	 public MessageConverter jsonMessageConverter() {
	  return new Jackson2JsonMessageConverter();
	 }
	
	@Bean
	 public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
	  final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	  rabbitTemplate.setMessageConverter(jsonMessageConverter());
	  return rabbitTemplate;
	 }

}
