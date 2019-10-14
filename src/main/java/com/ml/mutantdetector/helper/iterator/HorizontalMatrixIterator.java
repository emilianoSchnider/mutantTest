package com.ml.mutantdetector.helper.iterator;

import java.util.NoSuchElementException;

public class HorizontalMatrixIterator implements MatrixIterator {

	private String[] matrix;
	private Integer currentIndex = 0;
	
	@Override
	public void init(String[] initialMatrix) throws IllegalArgumentException {
		if (!MatrixIterator.isValidFormat(initialMatrix)) {
			throw new IllegalArgumentException();
		}
		
		matrix = initialMatrix;
	}

	@Override
	public Boolean hasNext() throws ExceptionInInitializerError, NoSuchElementException {
		this.validateInitClass();
		
		return (matrix[0].length() > currentIndex);				
	}

	@Override
	public String next() throws ExceptionInInitializerError, NoSuchElementException {
		this.validateInitClass();
		
		if (!this.hasNext()) {
			throw new NoSuchElementException();
		}
		
		StringBuffer horizantalSequence = new StringBuffer();
		for (String row : this.matrix) {
			horizantalSequence.append(row.charAt(currentIndex));
		}
		
		currentIndex++;
		return horizantalSequence.toString();
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
