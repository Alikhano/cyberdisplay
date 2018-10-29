package ru.alikhano.cyberdisplay.ejb.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import ru.alikhano.cyberdisplay.service.ManageTopProductsService;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Stateless
public class Listener {
	
	private static final Logger logger = LogManager.getLogger(Listener.class);
	private static final String QUEUENAME = "productsTopQueue";
	
	private Channel channel;
	private Connection connection;
	
	@EJB
	ManageTopProductsService manageTopProductsService;
	
	
	/**
	 * starts the RabbitMQ listener to receive notifications from server side 
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public void start() throws IOException, TimeoutException {
		logger.info("listener has started");
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		connection = connectionFactory.newConnection();
		channel = connection.createChannel();
		channel.queueDeclare(QUEUENAME, false, false, false, null);	
		Consumer consumer = new DefaultConsumer(channel) {
		
		@Override
		public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws UnsupportedEncodingException {
			String message = new String(body, "UTF-8");
			logger.info("Message received from server: " + message);
			manageTopProductsService.getReadyforUpdate();
			
		}
		
		};
		
		channel.basicConsume(QUEUENAME, true, consumer);
	}
	
	/**
	 * closes the listening channel on application shutdown
	 * @throws IOException
	 * @throws TimeoutException
	 */
	@PreDestroy
	public void close() throws IOException, TimeoutException {
		channel.close();
		connection.close();
	}

}
