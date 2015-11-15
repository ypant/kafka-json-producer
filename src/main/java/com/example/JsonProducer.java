package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class JsonProducer {
	static Logger defaultLogger = LoggerFactory.getLogger(JsonProducer.class);
	static Logger kafkaLogger = LoggerFactory.getLogger("com.example.kafkaLogger");

	public static void main(String args[]) {

		JsonProducer obj = new JsonProducer();

		String str = obj.getJsonObjAsString();

		// Use the logger
		kafkaLogger.info(str);

		try {
			// Construct and send message
			obj.constructAndSendMessage();
		} catch (InterruptedException e) {
			defaultLogger.error("Caught interrupted exception " + e);
		} catch (ExecutionException e) {
			defaultLogger.error("Caught execution exception " + e);
		}	
	}

	private String getJsonObjAsString() {
		JSONObject obj = new JSONObject();
		obj.put("name", "John");
		obj.put("age", new Integer(55));
		obj.put("address", "123 MainSt, Palatine, IL");

		JSONArray list = new JSONArray();
		list.add("msg 1");
		list.add("msg 2");
		list.add("msg 3");

		obj.put("messages", list);

		return obj.toJSONString();
	}

	private void constructAndSendMessage() throws InterruptedException, ExecutionException {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

		boolean sync = false;
		String topic = "kafkatopic";
		String key = "mykey";
		String value = "myvalue1 mayvalue2 myvalue3";
		ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic, key, value);
		if (sync) {
			producer.send(producerRecord).get();
		} else {
			producer.send(producerRecord);
		}
		producer.close();
	}
}
