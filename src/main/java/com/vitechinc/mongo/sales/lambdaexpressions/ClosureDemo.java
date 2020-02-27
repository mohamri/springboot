package com.vitechinc.mongo.sales.lambdaexpressions;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;



public class ClosureDemo {
	
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		Function<Integer, Integer> closure = ClosureExample.closure();
		list.stream().map(closure).forEach(n -> System.out.println(n + ""));
		
		List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
		integerList.stream().collect(Collectors.toCollection(TreeSet::new));
		
		TreeSet<Integer> treeSet =  integerList.stream().collect(Collectors.toCollection(TreeSet::new));
		for(Integer elet : treeSet) {
			System.out.println(elet);
		}
		
	}
}
