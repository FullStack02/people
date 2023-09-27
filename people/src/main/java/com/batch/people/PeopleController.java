package com.batch.people;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeopleController {
	
	@RequestMapping("/people")  
	public String people() throws FileNotFoundException   
	{  
		//parsing a CSV file into Scanner class constructor  
		Scanner sc = new Scanner(new File("src/main/resources/people.csv"));  
		sc.useDelimiter(",");  
		while (sc.hasNext())  
		{  
		System.out.print(sc.next()); 
		}   
		sc.close();  //closes the scanner
	return "successfully deployed on tomcat."; 
	 
	} 
	
	

}
