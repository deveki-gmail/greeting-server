package com.example.greeting.server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GreetingServerApplication {

	static Map<Integer, String> map  = new HashMap<Integer, String>();
	
	static Logger logger = LoggerFactory.getLogger(GreetingServerApplication.class);
	
	String randomName;
	
	@Value("${greet.endpoint.sleep.in.seconds:2}")
	private int sleepTime;
	
	static{
		map.put(Integer.valueOf(1), "Good Morning");
		map.put(Integer.valueOf(2), "Good After Noon");
		map.put(Integer.valueOf(3), "Good Evening");
		map.put(Integer.valueOf(4), "Good Night");
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(GreetingServerApplication.class, args);
		try {
			logger.info("Greeting server stared : "+InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	
	@GetMapping("/greet")
	public String hello(@RequestParam(name = "name", required = false) String name){
		String tempName = ", everyone!";
		try {
			logger.info("Request processed at server "+InetAddress.getLocalHost()+" for name "+(name!=null && !name.trim().isEmpty() ? name : ""));
			
			if(sleepTime > 0) 
			{
				logger.info("Processing..... please wait for "+sleepTime+" seconds.");
			}
			Thread.sleep(sleepTime * 1000);
			
			if(randomName == null) 
			{
				randomName = names[new Random().nextInt(names.length - 1)];
			}
			
			logger.info("Today's hero (greeting server) : "+randomName);
			
			
			if(Objects.nonNull(name) && name.trim().length()!= 0) 
			{
				tempName = ", "+name+"!";
			}
			LocalTime time = LocalTime.now();
			int hour = time.getHour();
			if(hour < 12){
				
				return map.get(Integer.valueOf(1))+tempName;
			}
			if(hour > 12 && hour < 18){
				return map.get(Integer.valueOf(2))+tempName;
			}
			if(hour > 18){
				return map.get(Integer.valueOf(3))+tempName;
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map.get(Integer.valueOf(3))+tempName;
	}
	
	String[] names = {"Subhas Chandra Bose", 
			"Bhagat Singh",
			"Sardar Vallabhbhai Patel",
			"Rani Lakshmibai",
			"Bal Gangadhar Tilak",
			"Lala Lajpat Rai",
			"Chandrashekhar Azad",
			"Mangal Pandey",
			"Madam Bhikaji Cama",
			"Kittur Chennamma",
			"Kamaladevi Chattopadhyay",
			"Shri Ram",
			"Shri Krishna",
			"Deveki Nandan"
		 };
}
