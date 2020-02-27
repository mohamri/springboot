package com.vitechinc.mongo.sales.cognizantTest;

import com.vitechinc.mongo.sales.cognizantTest.exceptions.EmptyStuckException;

public class SequentialCog<Key, Value> {
	
	SeqNode head;
	
	public SeqNode getHead() {
		return head;
	}

	public void setHead(SeqNode head) {
		this.head = head;
	}

	public class SeqNode {
		Key key;
		Value value;
		SeqNode next;
		
		public Key getKey() {
			return key;
		}

		public void setKey(Key key) {
			this.key = key;
		}

		public Value getValue() {
			return value;
		}

		public void setValue(Value value) {
			this.value = value;
		}

		public SeqNode getNext() {
			return next;
		}

		public void setNext(SeqNode next) {
			this.next = next;
		}

		public SeqNode(Key key, Value value, SeqNode next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
	
	public boolean isEmpty() {
		if (head == null)
			return true;
		return false;
	}
	
	public Value getKey(Key key) throws EmptyStuckException {
		if(head == null)
			throw new EmptyStuckException("It is empty");
		for(SeqNode temp = head; temp != null; temp = temp.next) {
			if(temp.key.equals(key)) 
				return temp.value;
		}
		return null;
	}
	
	public void put(Key key, Value value) {
		if(head == null)
			head = new SeqNode(key, value, null);
		else {
			SeqNode temp = head;
			SeqNode last = temp;
			while(temp != null) {
				last = temp;
				temp = temp.getNext();
			}
			last.next = new SeqNode(key, value, null);

		}
	}
	
}
