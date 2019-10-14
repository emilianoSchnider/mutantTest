package com.ml.mutantdetector.helper.iterator;

import java.util.NoSuchElementException;
import com.google.api.client.util.Strings;

/**
 * Iterate the matrix diagonally starting from the upper left corner to the lower right corner
 * @author eschnider
 */
public class DownwardDiagonalMatrixIterator implements MatrixIterator {

	private String[] matrix;
	private Integer currentIndex = 0;
	private Integer minSize = 1;
	private Integer matrixSize;
	
	@Override
	public void init(String[] initialMatrix) throws IllegalArgumentException {
		if (!MatrixIterator.isValidFormat(initialMatrix)) {
			throw new IllegalArgumentException();
		}
		
		this.matrix = initialMatrix;
		this.matrixSize = this.matrix.length;
	}

	/**
	 * Set the initial matrix to iterate and allows to configure 
	 * so that it only returns the rows with a minimum size.
	 * @param initialMatrix
	 * @param minSize
	 */
	public void init(String[] initialMatrix, Integer minSize) throws IllegalArgumentException {
		if ((initialMatrix != null)
				&& (minSize > 0)
				&& (minSize > initialMatrix.length)) {
			throw new IllegalArgumentException();
		}
		
		this.init(initialMatrix);
		this.minSize = minSize;
		this.currentIndex = minSize - 1;		
	}
	
	@Override
	public Boolean hasNext() throws ExceptionInInitializerError, NoSuchElementException {
		this.validateInitClass();
		
		// When currentIndex is vertical.
		if (this.currentIndex < this.matrixSize) {
			return !Strings.isNullOrEmpty(this.matrix[currentIndex]);
		}
			
		// Represents the maximum number of vertical iteration.
		Integer maxItemVerticalToIterate = this.matrixSize - (this.minSize - 1);
		
		// Same number of iteration except corner.
		Integer maxItemHorizontalToIterate = maxItemVerticalToIterate - 1;			
		
		return this.currentIndex < (this.matrixSize + maxItemHorizontalToIterate);		
	}

	@Override
	public String next() throws ExceptionInInitializerError, NoSuchElementException {
		this.validateInitClass();
		
		if (!this.hasNext()) {
			throw new NoSuchElementException();
		}
		
		String diagonal = (this.currentIndex < this.matrixSize) 
				? this.diagonal(this.currentIndex,  0)
				: this.diagonal(this.matrixSize - 1, this.currentIndex - (this.matrixSize -1));
		
		this.currentIndex++;
		return diagonal;
	}	
	
	private String diagonal(Integer startRowIndex, Integer startColumnIndex) {
		String diagonal = new String();	
		Integer initialRowIndex = startRowIndex;
				
		do {
			diagonal += this.matrix[startRowIndex--].charAt(startColumnIndex);			
		} while (initialRowIndex != startColumnIndex++);
		
		return diagonal;		
	}
	
	/**
	 * Validate that the class has been initialized 
	 */
	private void validateInitClass() throws ExceptionInInitializerError {
		if (matrix == null) {			
			throw new ExceptionInInitializerError("The initialization method should be invoked");
		}
	}
}
