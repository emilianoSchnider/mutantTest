package com.ml.mutantdetector.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ml.mutantdetector.model.DNAVerificationCounter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FirebaseDNAVerificationCounterRepositoryTests {

	@Test
	public void whenCounterTypeExists_thenCountValueIsExpected() {
		DNAVerificationCounterRepository repository = new FirebaseDNAVerificationCounterRepository();
		DNAVerificationCounter counter;
		
		try {
			counter = repository.getCounter("mutant");
			
			assertNotNull(counter);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	@Test
	public void whenCounterTypeNotExists_thenNullCounterIsExpected() throws Exception {
		DNAVerificationCounterRepository repository = new FirebaseDNAVerificationCounterRepository();
		DNAVerificationCounter counter;
		
		try {
			counter = repository.getCounter("animal");
			
			assertNull(counter);
		} catch (InterruptedException | ExecutionException e) {			
			e.printStackTrace();
			throw e;
		}	
	}
	
	@Test
	public void whenIncrementeCounter_thenNextValueIsExpected() throws Exception {
		DNAVerificationCounterRepository repository = new FirebaseDNAVerificationCounterRepository();
		DNAVerificationCounter previus,next;
		
		try {
			String counterType = "mutant";
			
			previus = repository.getCounter(counterType);				
			
			repository.incrementCounter(counterType);
			next = repository.getCounter(counterType);
			
			assertTrue(next.getCount() > previus.getCount());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw e;
		}	
	}
}
