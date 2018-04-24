package com.jpzhaof.guest.writer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.jpzhaof.guest.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Provider {
	public static final String NAME = "this_name";
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = ConnectionUtils.getConnection();
		Channel channel = connection.createChannel();
		//创建队列
		channel.queueDeclare(NAME, false, false, false, null);
		String mes = "this is message";
		channel.basicPublish("", NAME, null, mes.getBytes());
		System.out.println("rabbit发送消息");
		channel.close();
		connection.close();
		
	}
}
