package com.khalid.springKamelot.spring;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.khalid.springKamelot.beans.*;

public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        Scanner input = new Scanner(System.in);
        

        input.nextLine();
        System.out.println("--------------------------------------");
        
        IChevalier c1 = ctx.getBean("jacquouuille", IChevalier.class);
        System.out.println(c1);
        c1.partirEnQuete();
        
        IChevalier c2 = ctx.getBean("goedefrois", IChevalier.class);
        System.out.println(c2);
        c2.partirEnQuete(); 
        
        IChevalier c3 = ctx.getBean("link", IChevalier.class);
        System.out.println(c3);
        c3.partirEnQuete();
        
        input.nextLine();
		System.out.println("--------------------------------------");

		System.out.println("done...");
	}





}
