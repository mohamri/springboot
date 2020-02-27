package com.vitechinc.mongo.sales.cognizant;

import org.junit.Assert;
import org.junit.Test;

import com.vitechinc.mongo.sales.cognizantTest.SequentialCog;
import com.vitechinc.mongo.sales.cognizantTest.SequentialCog.SeqNode;
import com.vitechinc.mongo.sales.cognizantTest.exceptions.EmptyStuckException;

public class SequentialCogTest {
	
	
	@Test
	public void testIsEmptyReturnsTrueIfEmpty() {
		SequentialCog seq = new SequentialCog();
		Assert.assertEquals(true, seq.isEmpty());
	}
	
	@Test
	public void testIsEmptyReturnsFalseIfNotEmpty() {
		SequentialCog<String, String> seq = new SequentialCog<String, String>();
		SeqNode node = seq.new SeqNode("elet", "elet", null);
		seq.setHead(node);
		Assert.assertEquals(false,seq.isEmpty());
	}
	
	@Test
	public void testGetKey() throws EmptyStuckException {
		SequentialCog<String, String> seq = new SequentialCog<String, String>();
		
		SeqNode elet3 = seq.new SeqNode("key3",  "value3", null);
		SeqNode elet2 = seq.new SeqNode("key2",  "value2", elet3);
		SeqNode elet1 = seq.new SeqNode("key1",  "value1", elet2);
		seq.setHead(elet1);
		
		Assert.assertEquals("value1", seq.getKey("key1"));
		Assert.assertEquals("value2", seq.getKey("key2"));
		Assert.assertEquals("value3", seq.getKey("key3"));
	}
	
	@Test
	public void tetGetKeyThrowsExceptionIfEmpty() {
		SequentialCog<String, String> seq = new SequentialCog<String, String>();
		
		try {
			seq.getKey("key1");
			Assert.fail();
		} catch(EmptyStuckException exc) {
			Assert.assertTrue(true);
		}
		
	}
	

	@Test
	public void testPutKeyValueIncreaseSizeBy1() {
		SequentialCog<String, String> seq = new SequentialCog<String, String>();
		SeqNode elet3 = seq.new SeqNode("key3",  "value3", null);
		SeqNode elet2 = seq.new SeqNode("key2",  "value2", elet3);
		SeqNode elet1 = seq.new SeqNode("key1",  "value1", elet2);
		seq.setHead(elet1);
		seq.put("key1", "value1");
		int size = 0;
		for(SeqNode temp = elet1; temp != null; temp = temp.getNext()) {
			size ++;
		}
		Assert.assertEquals(4, size);
	}
	
	@Test
	public void testPutKeyValueIs1WhenEmpty() {
		SequentialCog<String, String> seq = new SequentialCog<String, String>();
		seq.put("key1", "value1");
		int size = 0;
 		for(SeqNode temp = seq.getHead(); temp != null; temp = temp.getNext()) {
 			size++;
 		}
 		Assert.assertEquals(1, size);
 		Assert.assertEquals("key1", seq.getHead().getKey());
 		Assert.assertEquals("value1", seq.getHead().getValue());
 		
	}
	
	@Test
	public void testPutElementByEnd() {
		
		SequentialCog<String, String> seq = new SequentialCog<String, String>();
		
		SeqNode elet3 = seq.new SeqNode("key3",  "value3", null);
		SeqNode elet2 = seq.new SeqNode("key2",  "value2", elet3);
		SeqNode elet1 = seq.new SeqNode("key1",  "value1", elet2);
		
		seq.put("lastKey", "lastValue");
		
		SeqNode temp = seq.getHead();
		SeqNode pred = temp;
		while(temp != null) {
			pred = temp;
			temp = temp.getNext();
		}
		Assert.assertEquals(pred.getKey(), "lastKey");
		Assert.assertEquals(pred.getValue(), "lastValue");
		
	}

}
