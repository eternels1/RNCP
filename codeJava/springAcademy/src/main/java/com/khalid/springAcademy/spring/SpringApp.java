package com.khalid.springAcademy.spring;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.khalid.springAcademy.beans.*;

public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        Scanner input = new Scanner(System.in);
        

        input.nextLine();
        System.out.println("--------------------------------------");
        
        IArtiste a1= ctx.getBean("amstrong",IArtiste.class);
        a1.faireNumero();
        
        IArtiste a2= ctx.getBean("catStevens",IArtiste.class);
        a2.faireNumero();
        
        IArtiste a3= ctx.getBean("jobleur1",IArtiste.class);
        a3.faireNumero();
        
        IArtiste a4= ctx.getBean("bobleponge",IArtiste.class);
        a4.faireNumero();
        
        input.nextLine();
		System.out.println("--------------------------------------");

		System.out.println("done...");
	}





}
