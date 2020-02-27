package com.vitechinc.mongo.sales.lambdaexpressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class GeneratingStreams {
	
	public static void main(String[] args) {
		
		Stream<String> strStream = Stream.of("str1", "str2", "str3");
		//strStream.map(x -> x + "_mapped").forEach(System.out::println);
		
		Stream<String> arrStream = Stream.of(new String[] {"stream", "from", "an", "array", "of", "objects"});
		
		//From array
		String[] arr = new String[] {"We", "can", "convert", "to", "a", "stream", "using", "arrays", "as", "well"};
		Stream<String> arrSream = Arrays.stream(arr);
		
		//From Collection
		Collection<Double> doubleCol = new ArrayList<Double>();
		for(int i = 1; i < 1000; i++) {
			doubleCol.add(Math.random());
		}
		Stream<Double> seqStream = doubleCol.stream();
		Stream<Double> parallelStream = doubleCol.parallelStream();
		Optional<Double> res = parallelStream.map(x -> x * 2).reduce((x, y) -> x + y);
		if(res.isPresent()) {
			//System.out.println(res.get());
		}
		
		//From Map
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("one", 1);
		map.put("two", 2);
		map.put("three", 3);
		
		//map.entrySet().stream().forEach(x -> System.out.println(x));
		//map.keySet().stream().forEach(k -> System.out.println(k));
		
		//Primitive types
		//IntStream.rangeClosed(1, 10).forEach(x -> System.out.println(x));
		LongStream.rangeClosed(1, 10);
		//DoubleStream.of(1.0, 2.0, 3.0, 4.0).forEach(p -> System.out.println(p));
		
		//Generate stream using supplier
		Supplier<Integer> intSupplier = () -> new Random().nextInt(10);
		//Stream.generate(intSupplier).limit(10).forEach(x -> System.out.println(x));
		
		//Using iterate
		//Stream.iterate(0, n -> n + 1).limit(10).forEach(n -> System.out.println(n));
		Stream.iterate(new int[] {0,  1}, x-> new int[] {x[1], x[0] + x[1]}).limit(10).map(n -> n[0]).forEach(x -> System.out.println(x));
		
		Stream<String> stringStream = Stream.of("str1", "str22", "str333", "str4444", "str55555");
		//stringStream.map(s -> s.length()).peek(action)
		
		
		
	}
}
