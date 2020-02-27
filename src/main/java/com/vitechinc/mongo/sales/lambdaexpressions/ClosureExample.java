package com.vitechinc.mongo.sales.lambdaexpressions;

import java.util.function.Function;

public class ClosureExample {
	
	public static Function<Integer, Integer> closure() {
		int a = 3;
		Function<Integer, Integer> function = t -> {
			return t * a;
		};
		
		return function;
	}
}
