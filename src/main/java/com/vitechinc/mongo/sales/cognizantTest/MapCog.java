package com.vitechinc.mongo.sales.cognizantTest;

import com.vitechinc.mongo.sales.cognizant.SequentialCogTest;
import com.vitechinc.mongo.sales.cognizantTest.exceptions.EmptyStuckException;

public class MapCog<Key, Value> {

	SequentialCog<Key, Value>[] hashTable = (SequentialCog<Key, Value>[])new SequentialCog[1000];
	int size;
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Value get(Key key) throws EmptyStuckException {
		int index = (key.hashCode() & 0x7fffffff) % 1000;
		return hashTable[index].getKey(key);
		
	}
	

}
