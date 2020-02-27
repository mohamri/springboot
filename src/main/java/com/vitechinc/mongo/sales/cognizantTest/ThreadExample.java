package com.vitechinc.mongo.sales.cognizantTest;

public class ThreadExample {
	
	 public static void main(String[] args) {
		  
		    //without lambda, Runnable implementation using anonymous class  
		    Runnable runnable = new Runnable() {
		        @Override
		        public void run() {
		            System.out.println(" Runnable example without lambda exp.");
		        }
		    };
		    Thread thread = new Thread(runnable);
		    thread.start();
		  
		     //with lambda 
		    Runnable withLambda = () -> System.out.println(" Runnable example with lambda exp.");
		    Thread thread1 = new Thread(withLambda);
		    thread1.start();
		    
		    Runnable t1 = () -> {
		    	System.out.println("t1");
		    };
		    
		    Runnable t2 = () -> {
		    	System.out.println("t2");
		    };
		    
		    Runnable t3 = () -> {
		    	System.out.println("t3");
		    };
		    
		    new Thread(t1).start();
		    new Thread(t2).start();
		    new Thread(t3).start();
		  }

}
