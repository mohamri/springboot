package com.vitechinc.mongo.sales.cognizantTest;

interface Average {
	double avg(int[] arr);
}

class LambdaExpressions {
	
	
	
	public static void main(String[] args) {
		
		Average av = (arr) -> {
			int len = arr.length;
			double sum = 0;
			for(int k = 0; k < len; k++) {
				sum += arr[k];
			}
			return sum / len;
		};
		
		System.out.println(av.avg(new int[] {1, 2, 3}));
	}

}


