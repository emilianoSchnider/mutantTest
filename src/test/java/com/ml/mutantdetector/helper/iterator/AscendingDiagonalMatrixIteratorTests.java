package com.ml.mutantdetector.helper.iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test method to AscendingDiagonalMatrixIterator class.
 *  
 * @author eschnider
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AscendingDiagonalMatrixIteratorTests {
	
	@Test(expected = IllegalArgumentException.class)
	public void whenEmptyMatrix_thenExceptionIsExpected() {
		AscendingDiagonalMatrixIterator ascendingDiagonalMatrixIterator = new AscendingDiagonalMatrixIterator();
		ascendingDiagonalMatrixIterator.init(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenMinSizeIsGreatherThanMatrixSize_thenExceptionIsExpected() {
		String[] matrix = {"AA","BB","CC"};
		AscendingDiagonalMatrixIterator ascendingDiagonalMatrixIterator = new AscendingDiagonalMatrixIterator();
		ascendingDiagonalMatrixIterator.init(matrix, 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenMatrizSizeIsIncorrect_thenExceptionIsExpected() {
		String[] matrix = {"AA","BB","CC"};
		
		AscendingDiagonalMatrixIterator ascendingDiagonalMatrixIterator = new AscendingDiagonalMatrixIterator();
		ascendingDiagonalMatrixIterator.init(matrix);
	}
	
	@Test(expected = ExceptionInInitializerError.class)
	public void whenTheClassNotInitialized_thenExceptionIsExpected() {
		AscendingDiagonalMatrixIterator ascendingDiagonalMatrixIterator = new AscendingDiagonalMatrixIterator();
		
		ascendingDiagonalMatrixIterator.hasNext();
	}
	
	@Test(expected = NoSuchElementException.class)
	public void whenInTheLasItem_thenToRequestNextItemExceptionIsExpected() {
		String[] matrix = {"AB","CD"};
		
		AscendingDiagonalMatrixIterator ascendingDiagonalMatrixIterator = new AscendingDiagonalMatrixIterator();
		ascendingDiagonalMatrixIterator.init(matrix);

		// Advance to the first item
		assertEquals("C", ascendingDiagonalMatrixIterator.next());
		
		// Advance to the second item
		assertEquals("AD", ascendingDiagonalMatrixIterator.next());

		// Advance to the third item
		assertEquals("B", ascendingDiagonalMatrixIterator.next());

		ascendingDiagonalMatrixIterator.next();
	}
	
	@Test
	public void whenValidMatrix_thenAllAscendingDiagonalAreValid() {	
		String[] matrix = {"ABC","DEF","GHI"};
		
		AscendingDiagonalMatrixIterator ascendingDiagonalMatrixIterator = new AscendingDiagonalMatrixIterator();
		ascendingDiagonalMatrixIterator.init(matrix);
		
		assertEquals("G", ascendingDiagonalMatrixIterator.next());
		assertEquals("DH", ascendingDiagonalMatrixIterator.next());
		assertEquals("AEI", ascendingDiagonalMatrixIterator.next());
		assertEquals("BF", ascendingDiagonalMatrixIterator.next());
		assertEquals("C", ascendingDiagonalMatrixIterator.next());
	}
	
	@Test
	public void whenValidMatrix_thenAllDownwardDiagonalAreValid2() {	
		String[] matrix = {"ABBBA","BCCCB","CDDDC","DEEED","EFFFE"};
		
		AscendingDiagonalMatrixIterator ascendingDiagonalMatrixIterator = new AscendingDiagonalMatrixIterator();
		ascendingDiagonalMatrixIterator.init(matrix, 4);
		
		assertEquals("BDEF", ascendingDiagonalMatrixIterator.next());
		assertEquals("ACDEE", ascendingDiagonalMatrixIterator.next());
		assertEquals("BCDD", ascendingDiagonalMatrixIterator.next());
	}
	
	@Test
	public void whenValidMatrixWithMinimunRowSizeIsSet_thenAllAscendingDiagonalAreValid() {
		String[] matrix = {"ABC","DEF","GHI"};
		
		AscendingDiagonalMatrixIterator ascendingDiagonalMatrixIterator = new AscendingDiagonalMatrixIterator();
		ascendingDiagonalMatrixIterator.init(matrix, 3);
				
		assertEquals("AEI", ascendingDiagonalMatrixIterator.next());		
	}
	
	@Test
	public void whenMinimumRowSizeIsSet_thenOnlyDiagonalsWithThatSizeOrMoreAreExpected() {	
		String[] matrix = {"ABC","DEF","GHI"};
		
		AscendingDiagonalMatrixIterator ascendingDiagonalMatrixIterator = new AscendingDiagonalMatrixIterator();
		ascendingDiagonalMatrixIterator.init(matrix,2);
		
		Integer rowCount = 0;
		while (ascendingDiagonalMatrixIterator.hasNext()) {
			ascendingDiagonalMatrixIterator.next();
			rowCount++;					
		}
		
		assertEquals(3, rowCount.longValue());
	}
	
	@Test
	public void whenNextItemExist_thenReturnHasNext() {
		String[] matrix = {"AA","BB"};
		
		AscendingDiagonalMatrixIterator ascendingDiagonalMatrixIterator = new AscendingDiagonalMatrixIterator();
		ascendingDiagonalMatrixIterator.init(matrix);
		
		assertTrue(ascendingDiagonalMatrixIterator.hasNext());
	}
	
	@Test
	public void whenInTheLasItem_thenReturnNotHasNext() {
		String[] matrix = {"AA","BB"};
		
		AscendingDiagonalMatrixIterator ascendingDiagonalMatrixIterator = new AscendingDiagonalMatrixIterator();
		ascendingDiagonalMatrixIterator.init(matrix);
		
		// Advance to the first item
		assertEquals("B", ascendingDiagonalMatrixIterator.next());
		
		// Advance to the second item
		assertEquals("AB", ascendingDiagonalMatrixIterator.next());
		
		// Advance to the third item
		assertEquals("A", ascendingDiagonalMatrixIterator.next());
		
		assertFalse(ascendingDiagonalMatrixIterator.hasNext());
	}
}
