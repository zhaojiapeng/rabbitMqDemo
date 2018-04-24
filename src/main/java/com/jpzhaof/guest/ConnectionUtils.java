package com.jpzhaof.guest;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtils {
	
	public static Connection getConnection(){
		ConnectionFactory  faction = new ConnectionFactory();
		faction.setHost("127.0.0.1");
		faction.setPort(5672);
		faction.setVirtualHost("/virtual_db");
		faction.setUsername("jpzhaof");
		faction.setPassword("jpzhaof");
		try {
			return faction.newConnection();
		} catch (Exception e) {
			
		};
		return null;
	}

}
