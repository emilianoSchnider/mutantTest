package com.ml.mutantdetector.helper.iterator;

import java.util.NoSuchElementException;

import com.google.api.client.util.Strings;

public class VerticalIteratorMatrix implements MatrixIterator {

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
		
		return matrix.length > currentIndex
				&& !Strings.isNullOrEmpty(matrix[currentIndex]); 
	}

	@Override
	public String next() throws ExceptionInInitializerError, NoSuchElementException {
		this.validateInitClass();
		
		if (this.hasNext()) {
			return matrix[currentIndex++];
		}
		
		throw new NoSuchElementException();
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
