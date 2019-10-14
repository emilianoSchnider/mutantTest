/**
 * 
 */
package com.ml.mutantdetector.helper.iterator;

import java.util.NoSuchElementException;

/**
 * Define methods used to iterate the matrix.
 * 
 * @author eschnider
 */
public interface MatrixIterator {

	/**
	 * Set the initial matrix to iterate.
	 * 
	 * @param matrix to iterate.
	 */
	public abstract void init(String[] matrix) throws IllegalArgumentException;
	
	/**
	 * Check if exist items to iterate.
	 * 
	 * @return if exist items to iterate.
	 */
	public abstract Boolean hasNext() throws ExceptionInInitializerError, NoSuchElementException;
	
	/**
	 * Gets next item.
	 * 
	 * @return next item. 
	 */
	public abstract String next() throws ExceptionInInitializerError, NoSuchElementException;
	
	/**
	 * Validate that the number of rows and characters of each are equal. 
	 * @param matrix
	 * @return if the number of rows and characters of each are equal.
	 */
	public static Boolean isValidFormat(String[] matrix) {
		if (matrix == null) {
			return false;
		}
		
		Integer rowCount = matrix.length;
		
		// Each row should have the same number of characters
		for (String row : matrix) {
			if (row.length() != rowCount) {
				return false;
			}
		}
		
		return true;
	}

}
