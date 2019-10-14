package com.ml.mutantdetector.helper.iterator;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test method to DownwardDiagonalIteratorMatrix class.
 * 
 * @author eschnider
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DownwardDiagonalIteratorMatrixTests {

	@Test(expected = IllegalArgumentException.class)
	public void whenEmptyMatrix_thenExceptionIsExpected() {
		DownwardDiagonalMatrixIterator downwardDiagonalIteratorMatrix = new DownwardDiagonalMatrixIterator();
		downwardDiagonalIteratorMatrix.init(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenMinSizeIsGreatherThanMatrixSize_thenExceptionIsExpected() {
		String[] matrix = {"AA","BB","CC"};
		DownwardDiagonalMatrixIterator downwardDiagonalIteratorMatrix = new DownwardDiagonalMatrixIterator();
		downwardDiagonalIteratorMatrix.init(matrix, 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenMatrizSizeIsIncorrect_thenExceptionIsExpected() {
		String[] matrix = {"AA","BB","CC"};
		
		DownwardDiagonalMatrixIterator downwardDiagonalIteratorMatrix = new DownwardDiagonalMatrixIterator();
		downwardDiagonalIteratorMatrix.init(matrix);
	}
	
	@Test(expected = ExceptionInInitializerError.class)
	public void whenTheClassNotInitialized_thenExceptionIsExpected() {
		DownwardDiagonalMatrixIterator downwardDiagonalIteratorMatrix = new DownwardDiagonalMatrixIterator();
		
		downwardDiagonalIteratorMatrix.hasNext();
	}
	
	@Test(expected = NoSuchElementException.class)
	public void whenInTheLasItem_thenToRequestNextItemExceptionIsExpected() {
		String[] matrix = {"AB","CD"};
		
		DownwardDiagonalMatrixIterator downwardDiagonalIteratorMatrix = new DownwardDiagonalMatrixIterator();
		downwardDiagonalIteratorMatrix.init(matrix);

		// Advance to the first item
		assertEquals("A", downwardDiagonalIteratorMatrix.next());
		
		// Advance to the second item
		assertEquals("CB", downwardDiagonalIteratorMatrix.next());

		// Advance to the third item
		assertEquals("D", downwardDiagonalIteratorMatrix.next());

		downwardDiagonalIteratorMatrix.next();
	}
	
	@Test
	public void whenValidMatrix_thenAllDownwardDiagonalAreValid() {	
		String[] matrix = {"ABC","DEF","GHI"};
		
		DownwardDiagonalMatrixIterator downwardDiagonalIteratorMatrix = new DownwardDiagonalMatrixIterator();
		downwardDiagonalIteratorMatrix.init(matrix);
		
		assertEquals("A", downwardDiagonalIteratorMatrix.next());
		assertEquals("DB", downwardDiagonalIteratorMatrix.next());
		assertEquals("GEC", downwardDiagonalIteratorMatrix.next());
		assertEquals("HF", downwardDiagonalIteratorMatrix.next());
		assertEquals("I", downwardDiagonalIteratorMatrix.next());
	}
	
	@Test
	public void whenValidMatrix_thenAllDownwardDiagonalAreValid2() {	
		String[] matrix = {"ABBBA","BCCCB","CDDDC","DEEED","EFFFE"};
		
		DownwardDiagonalMatrixIterator downwardDiagonalIteratorMatrix = new DownwardDiagonalMatrixIterator();
		downwardDiagonalIteratorMatrix.init(matrix, 4);
		
		assertEquals("DDCB", downwardDiagonalIteratorMatrix.next());
		assertEquals("EEDCA", downwardDiagonalIteratorMatrix.next());
		assertEquals("FEDB", downwardDiagonalIteratorMatrix.next());
	}
	
	@Test
	public void whenValidMatrixWithMinimunRowSizeIsSet_thenAllDownwardDiagonalAreValid() {
		String[] matrix = {"ABC","DEF","GHI"};
		
		DownwardDiagonalMatrixIterator downwardDiagonalIteratorMatrix = new DownwardDiagonalMatrixIterator();
		downwardDiagonalIteratorMatrix.init(matrix, 3);
				
		assertEquals("GEC", downwardDiagonalIteratorMatrix.next());		
	}
	
	@Test
	public void whenMinimumRowSizeIsSet_thenOnlyDiagonalsWithThatSizeOrMoreAreExpected() {	
		String[] matrix = {"ABC","DEF","GHI"};
		
		DownwardDiagonalMatrixIterator downwardDiagonalIteratorMatrix = new DownwardDiagonalMatrixIterator();
		downwardDiagonalIteratorMatrix.init(matrix,2);
		
		Integer rowCount = 0;
		while (downwardDiagonalIteratorMatrix.hasNext()) {
			downwardDiagonalIteratorMatrix.next();
			rowCount++;					
		}
		
		assertEquals(3, rowCount.longValue());
	}
	
	@Test
	public void whenNextItemExist_thenReturnHasNext() {
		String[] matrix = {"AA","BB"};
		
		DownwardDiagonalMatrixIterator downwardDiagonalIteratorMatrix = new DownwardDiagonalMatrixIterator();
		downwardDiagonalIteratorMatrix.init(matrix);
		
		assertTrue(downwardDiagonalIteratorMatrix.hasNext());
	}
	
	@Test
	public void whenInTheLasItem_thenReturnNotHasNext() {
		String[] matrix = {"AA","BB"};
		
		DownwardDiagonalMatrixIterator downwardDiagonalIteratorMatrix = new DownwardDiagonalMatrixIterator();
		downwardDiagonalIteratorMatrix.init(matrix);
		
		// Advance to the first item
		assertEquals("A", downwardDiagonalIteratorMatrix.next());
		
		// Advance to the second item
		assertEquals("BA", downwardDiagonalIteratorMatrix.next());
		
		// Advance to the third item
		assertEquals("B", downwardDiagonalIteratorMatrix.next());
		
		assertFalse(downwardDiagonalIteratorMatrix.hasNext());
	}
}
