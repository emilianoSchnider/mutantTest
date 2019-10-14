package com.ml.mutantdetector.helper.iterator;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VerticalIteratorMatrixTests {

	@Test(expected = IllegalArgumentException.class)
	public void whenEmptyMatrix_thenExceptionIsExpected() {
		VerticalIteratorMatrix verticalIteratorMatrix = new VerticalIteratorMatrix();
		verticalIteratorMatrix.init(null);
	}
	
	@Test(expected = ExceptionInInitializerError.class)
	public void whenTheClassNotInitialized_thenExceptionIsExpected() {
		VerticalIteratorMatrix verticalIteratorMatrix = new VerticalIteratorMatrix();
		
		verticalIteratorMatrix.hasNext();
	}
	
	@Test(expected = NoSuchElementException.class)
	public void whenInTheLasItem_thenToRequestNextItemExceptionIsExpected() {
		String[] matrix = {"AA","BB"};
		
		VerticalIteratorMatrix verticalIteratorMatrix = new VerticalIteratorMatrix();
		verticalIteratorMatrix.init(matrix);

		// Advance to the first item
		assertEquals("AA", verticalIteratorMatrix.next());
		
		// Advance to the second item
		assertEquals("BB", verticalIteratorMatrix.next());
		
		verticalIteratorMatrix.next();
	}
	
	@Test
	public void whenInitMatrix_thenSameItemWillReturned() {
		String[] matrix = {"AA","BB"};
		
		VerticalIteratorMatrix verticalIteratorMatrix = new VerticalIteratorMatrix();
		verticalIteratorMatrix.init(matrix);
		
		assertTrue(verticalIteratorMatrix.hasNext());
		assertEquals("AA", verticalIteratorMatrix.next());	
		assertEquals("BB", verticalIteratorMatrix.next());	
	}
	
	@Test
	public void whenNextItemExist_thenReturnHasNext() {
		String[] matrix = {"AA","BB"};
		
		VerticalIteratorMatrix verticalIteratorMatrix = new VerticalIteratorMatrix();
		verticalIteratorMatrix.init(matrix);
		
		assertTrue(verticalIteratorMatrix.hasNext());
	}
	
	@Test
	public void whenInTheLasItem_thenReturnNotHasNext() {
		String[] matrix = {"AA","BB"};
		
		VerticalIteratorMatrix verticalIteratorMatrix = new VerticalIteratorMatrix();
		verticalIteratorMatrix.init(matrix);
		
		// Advance to the first item
		assertEquals("AA", verticalIteratorMatrix.next());
		
		// Advance to the second item
		assertEquals("BB", verticalIteratorMatrix.next());
		
		assertFalse(verticalIteratorMatrix.hasNext());
	}
	
}
