package com.example.greeting.server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GreetingServerApplication {

	static Map<Integer, String> map  = new HashMap<Integer, String>();
	
	String randomName;
	
	static{
		map.put(Integer.valueOf(1), "Good Morning");
		map.put(Integer.valueOf(2), "Good After Noon");
		map.put(Integer.valueOf(3), "Good Evening");
		map.put(Integer.valueOf(4), "Good Night");
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(GreetingServerApplication.class, args);
		try {
			System.out.println("Greeting server stared : "+InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	
	@GetMapping("/greet")
	public String hello(){
		try {
			System.out.println("Request processed at server "+InetAddress.getLocalHost());
			
			if(randomName == null) 
			{
				randomName = names[new Random().nextInt(names.length - 1)];
			}
			System.out.println("Today's hero (greeting server) : "+randomName);
			
			LocalTime time = LocalTime.now();
			int hour = time.getHour();
			if(hour < 12){
				return map.get(Integer.valueOf(1));
			}
			if(hour > 12 && hour < 18){
				return map.get(Integer.valueOf(2));
			}
			if(hour > 18){
				return map.get(Integer.valueOf(3));
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map.get(Integer.valueOf(3));
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
