package com.vitechinc.mongo.sales.cognizantTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

public class LambdaComparator {
	
	 public static void main(String[] args) {
		 List<Person> listOfPerson = new ArrayList<Person>();
	        listOfPerson.add(new Person("abc", 27));
	        listOfPerson.add(new Person("mno", 26));
	        listOfPerson.add(new Person("pqr", 28));
	        listOfPerson.add(new Person("xyz", 27));
	        
	        Collections.sort(listOfPerson, (Person p1, Person p2) -> p1.getAge() - p2.getAge());
	        
	        //listOfPerson.forEach(person -> System.out.println(person.getName() + "||" + person.getAge()));
	        
	        List<PersonWrapper> wrappers = listOfPerson.parallelStream().
	        		map(p -> new PersonWrapper(p, p.getAge() * 1)).
	        		collect(Collectors.toList());
	        		
	        
	        for(Person person : listOfPerson) {
	        	//System.out.println(person.getAge());
	        }
	        
	        //List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
	        //long count = strings.stream().filter(s -> s.isEmpty()).count();
	        //System.out.println(count);
	        
	        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
	        System.out.println("Count empty strings");
	        System.out.println(strings.parallelStream().filter(s -> s.isEmpty()).count());
	        
	        System.out.println("Count strings of length 3");
	        System.out.println(strings.parallelStream().filter(s -> s.length() == 3).count());
	        
	        System.out.println("Eliminate empty strings and join using comma");
	        String nonEmptyList = strings.parallelStream().filter(s -> s.length() > 0).collect(Collectors.joining(", "));
	        System.out.println(nonEmptyList);
	        
	        System.out.println("List of squares of distinct numbers");
	        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
	        List<Double> squares = numbers.parallelStream().distinct().map(n -> Math.sqrt(n)).collect(Collectors.toList());
	        System.out.println(squares);
	        
	        System.out.println("Highest number in a list");
	        Optional<Integer> omax = numbers.parallelStream().max((x, y) -> x - y);
	        if(omax.isPresent()) 
	        	System.out.println("Max is : " + omax.get());
	        
	        System.out.println("Minimum number in a list");
	        Optional<Integer> omin = numbers.parallelStream().min((x, y) ->	x - y);
	        if(omin.isPresent()) 
	        	System.out.println("Max is : " + omin.get());
	        
	        
	        System.out.println("Sum number in a list");
	        Integer sum = numbers.parallelStream().reduce(0, (x, y) -> x + y);
	        System.out.println("Sum is : " + sum);
	        
	        System.out.println("Average number in a list");
	        List<Double> doubleNumbers = Arrays.asList(3.1, 2.2, 2.6, 3.6, 7.0, 3.1, 5.2);
	        IntSummaryStatistics summary = numbers.parallelStream().mapToInt(x -> x).summaryStatistics();
	        System.out.println(summary.getAverage());
	        
	        
	 }

	 
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
       super();
       this.name = name;
       this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

	class PersonWrapper {
		 Person p;
		 int wrapperId;  
		public PersonWrapper(Person p, int wrapperId) {
			super();
			this.p = p;
			this.wrapperId = wrapperId;
		       
		}
		public Person getP() {
			return p;
		}
		
		public void setP(Person p) {
			this.p = p;
		}
		
		public int getWrapperId() {
			return wrapperId;
		}
		
		public void setWrapperId(int wrapperId) {
			this.wrapperId = wrapperId;
		}
	}
