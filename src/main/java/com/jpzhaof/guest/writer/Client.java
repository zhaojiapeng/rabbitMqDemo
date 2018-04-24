package com.jpzhaof.guest.writer;

import java.io.IOException;

import com.jpzhaof.guest.ConnectionUtils;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.AMQP.Queue.DeclareOk;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.rabbitmq.client.ShutdownSignalException;

public class Client {
	public static final String NAME = "this_name";
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		Connection connection = ConnectionUtils.getConnection();
		Channel channel = connection.createChannel();
		//创建队列
		channel.queueDeclare(NAME, false, false, false, null);
		DefaultConsumer consumer = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String mes = new String(body);
				System.out.println(mes);
			}
		};
		//创建监听
		channel.basicConsume(NAME, true,consumer);
	}

	
	public void methOne() throws Exception{
		Connection connection = ConnectionUtils.getConnection();
		Channel channel = connection.createChannel();
		//定义消费者
		QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
		//监听队列
		channel.basicConsume(NAME, true,queueingConsumer);
		while(true){
			Delivery nextDelivery = queueingConsumer.nextDelivery();
			String mes = new String(nextDelivery.getBody());
			System.out.println("接受结果："+ mes);
		}
	}
}
