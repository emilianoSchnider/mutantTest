package com.ml.mutantdetector.helper.iterator;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HorizontalIteratorMatrixTests {

	@Test(expected = IllegalArgumentException.class)
	public void whenEmptyMatrix_thenExceptionIsExpected() {
		HorizontalMatrixIterator horizontalIteratorMatrix = new HorizontalMatrixIterator();
		horizontalIteratorMatrix.init(null);
	}
	
	@Test(expected = ExceptionInInitializerError.class)
	public void whenTheClassNotInitialized_thenExceptionIsExpected() {
		HorizontalMatrixIterator horizontalIteratorMatrix = new HorizontalMatrixIterator();
		
		horizontalIteratorMatrix.hasNext();
	}
	
	@Test(expected = NoSuchElementException.class)
	public void whenInTheLasItem_thenToRequestNextItemExceptionIsExpected() {
		String[] matrix = {"AA","BB"};
		
		HorizontalMatrixIterator horizontalIteratorMatrix = new HorizontalMatrixIterator();
		horizontalIteratorMatrix.init(matrix);

		// Advance to the first item
		horizontalIteratorMatrix.next();
		
		// Advance to the second item
		horizontalIteratorMatrix.next();
		
		horizontalIteratorMatrix.next();
	}
	
	@Test
	public void whenInitMatrix_thenSameItemWillReturned() {
		String[] matrix = {"AA","BC"};
		
		HorizontalMatrixIterator horizontalIteratorMatrix = new HorizontalMatrixIterator();
		horizontalIteratorMatrix.init(matrix);
		
		assertTrue(horizontalIteratorMatrix.hasNext());
		assertEquals("AB", horizontalIteratorMatrix.next());	
		assertEquals("AC", horizontalIteratorMatrix.next());	
	}
	
	@Test
	public void whenNextItemExist_thenReturnHasNext() {
		String[] matrix = {"AA","BC"};
		
		HorizontalMatrixIterator horizontalIteratorMatrix = new HorizontalMatrixIterator();
		horizontalIteratorMatrix.init(matrix);
		
		assertTrue(horizontalIteratorMatrix.hasNext());
	}
	
	@Test
	public void whenInTheLasItem_thenReturnNotHasNext() {
		String[] matrix = {"AA","BB"};
		
		HorizontalMatrixIterator horizontalIteratorMatrix = new HorizontalMatrixIterator();
		horizontalIteratorMatrix.init(matrix);
		
		// Advance to the first item
		assertEquals("AB", horizontalIteratorMatrix.next());
		
		// Advance to the second item
		assertEquals("AB", horizontalIteratorMatrix.next());
		
		assertFalse(horizontalIteratorMatrix.hasNext());		
	}

}
