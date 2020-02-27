package com.vitechinc.mongo.sales.cognizantTest;

import com.vitechinc.mongo.sales.cognizantTest.exceptions.EmptyStuckException;

public class Stuck<T> {
	
	Node head;
	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	int count;
	public Stuck() {
		head = null;
		count = 0;
	}
	
	public class Node<T> {
		
		T value;
		Node next;
		
		public Node(T value) {
			this.value = value;
		}
	}
	
	/**
	 * 
	 * @param node
	 */
	public void push(Node node) {
		if(head == null)
			head = null;
		node.next = head;
		head = node;
	}
	
	public T peek() throws EmptyStuckException {
		if(head == null) {
			throw new EmptyStuckException("Empty stuck");
		}
		return (T)head.value;
	}
	
	public boolean isEmpty() {
		if(head == null)
			return true;
		return false;
	}
	
	
	public T pop() throws  EmptyStuckException {
		if(head == null)
			throw new EmptyStuckException("Empty stuck");
		Node retval = head;
		head = head.next;
		
		return (T)retval.value;
	}
	
}
