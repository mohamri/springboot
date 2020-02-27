package com.vitechinc.mongo.sales.cognizant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.vitechinc.mongo.sales.cognizantTest.Stuck;
import com.vitechinc.mongo.sales.cognizantTest.Stuck.Node;
import com.vitechinc.mongo.sales.cognizantTest.exceptions.EmptyStuckException;


public class StuckTest {
	
 	Stuck<Integer> stuck;

 	@Test
	public void testPush() throws EmptyStuckException {
		stuck = new Stuck<Integer>();
		stuck.push(stuck.new Node<Integer>(5));
		stuck.push(stuck.new Node<Integer>(7));
		stuck.push(stuck.new Node<Integer>(9));
		assertEquals(9, stuck.peek().intValue());
	}
	
	@Test
	public void testPeek() throws EmptyStuckException {
		Stuck stuck = new Stuck();
		Node head = stuck.new Node(5);
		stuck.setHead(head);
		assertEquals(5, stuck.peek());
	}
	
	@Test
	public void testPeekThrowExceptionIfEmptyStuck() {
		Stuck stuck = new Stuck();
		try {
			stuck.peek();
			fail();
			
		} catch (EmptyStuckException exc) {
			assert(true);
		}
	}
	
	@Test
	public void testEmptyRetrunsTrueIfEmpty() {
		Stuck stuck = new Stuck();
		assertEquals(true, stuck.isEmpty());
	}
	
	@Test
	public void testEmptyReturnsFalseIfNotEmpty() {
		Stuck<Integer> stuck = new Stuck<>();
		Node node = stuck.new Node<Integer>(5);
		stuck.setHead(node);
		assertEquals(false, stuck.isEmpty());
	}
	
	@Test
	public void testPopThrowExceptionIfStuckempty() {
		Stuck<Integer> stuck = new Stuck<Integer>();
		try {
			stuck.pop();
			fail();
			
		} catch(EmptyStuckException exc) {
			assert(true);
		}
	}
	
	
	@Test
	public void testPopAfterPush() throws EmptyStuckException {
		Stuck<Integer> stuck = new Stuck<Integer>();
		stuck.push(stuck.new Node(5));
		stuck.push(stuck.new Node(7));
		stuck.push(stuck.new Node(9));
		assertEquals(9, stuck.pop().intValue());
	}
	
}
