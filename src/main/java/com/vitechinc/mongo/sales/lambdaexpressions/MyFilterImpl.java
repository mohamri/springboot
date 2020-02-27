package com.vitechinc.mongo.sales.lambdaexpressions;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;





public class MyFilterImpl {
	
	public static void main(String[] args) {
		
		File dir = new File("src/main/java");
		dir.list(new FilenameFilter() {
			
			public boolean accept(File dir, String name) {
				return name.endsWith("java");
			}
			
		});
		
		//Lamda
		dir.list((x, name) -> {
			return name.endsWith("java");
		});
		
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		Stream<Integer> s = list.stream().map(n -> n * 2);
		//s.forEach(System.out::println);
		
		IntStream.range(1, 8).filter(x -> x%2 == 0).map(x -> 3 * 2).forEach(Utils::divideByFour);
		
		List<String> listStr = Arrays.asList("str1", "str2", "str3");
		Collection coll = listStr.stream().collect(Collectors.toCollection(TreeSet::new));
		int l = 5;
	}

}
class Utils {
	public static void divideByFour(int x) {
		x = x * 4;
	}
}
