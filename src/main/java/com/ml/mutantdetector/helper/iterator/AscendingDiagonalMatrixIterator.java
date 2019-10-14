package com.ml.mutantdetector.helper.iterator;

import java.util.NoSuchElementException;

/**
 * Iterate the matrix diagonally starting from the bottom left corner to the upper right corner.
 * @author eschnider
 */
public class AscendingDiagonalMatrixIterator implements MatrixIterator {

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
		this.currentIndex = this.matrixSize - 1;
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
		this.currentIndex = this.matrixSize - this.minSize;
	}
	
	@Override
	public Boolean hasNext() throws ExceptionInInitializerError, NoSuchElementException {
		this.validateInitClass();
		
		Integer maxIndexSize = this.matrixSize - this.minSize;

		return (Math.abs(this.currentIndex) <= maxIndexSize);				
	}

	@Override
	public String next() throws ExceptionInInitializerError, NoSuchElementException {
		this.validateInitClass();
		
		if (!this.hasNext()) {
			throw new NoSuchElementException();
		}
		
		String diagonal = (this.currentIndex >= 0) 
				? this.diagonal(this.currentIndex,  0)
				: this.diagonal(0, Math.abs(this.currentIndex));
		
		this.currentIndex--;
		return diagonal;
	}

	private String diagonal(Integer startRowIndex, Integer startColumnIndex) {
		String diagonal = new String();			
		Integer diagonalSize = this.matrixSize - Math.abs(this.currentIndex);
				
		for (int i = 0; i < diagonalSize; i++) {
			diagonal += this.matrix[startRowIndex++].charAt(startColumnIndex++);	
		}
		
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
