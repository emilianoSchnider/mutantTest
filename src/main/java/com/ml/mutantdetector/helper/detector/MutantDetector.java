package com.ml.mutantdetector.helper.detector;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import com.ml.mutantdetector.helper.iterator.AscendingDiagonalMatrixIterator;
import com.ml.mutantdetector.helper.iterator.DownwardDiagonalMatrixIterator;
import com.ml.mutantdetector.helper.iterator.HorizontalMatrixIterator;
import com.ml.mutantdetector.helper.iterator.MatrixIterator;
import com.ml.mutantdetector.helper.iterator.VerticalIteratorMatrix;

/**
 * Implements the logic to detect mutant.
 * 
 * @author eschnider
 */
public class MutantDetector {
	
	private static final int NUM_OF_SEQUENCES_TO_BE_MUTANT = 2;
	private static final int NUM_OF_CONSECUTIVE_EQUAL_LETTERS_TO_BE_MUTANT = 4;
	
	public void validate(String[] dna) throws IllegalArgumentException {
		DNAValidator.validate(dna, NUM_OF_CONSECUTIVE_EQUAL_LETTERS_TO_BE_MUTANT);
	}
	
	/**
	 * Analyze if it is mutant.
	 * @param dna
	 * @return if it is mutant.
	 */
	public Boolean isMutant(String[] dna) 
			throws IllegalArgumentException, ExceptionInInitializerError, NoSuchElementException {
					
		List<MatrixIterator> iteratorDirections = new ArrayList<MatrixIterator>();
		
		HorizontalMatrixIterator horizontalMatrixIterator = new HorizontalMatrixIterator();
		horizontalMatrixIterator.init(dna);
		iteratorDirections.add(horizontalMatrixIterator);
		
		VerticalIteratorMatrix verticalIteratorMatrix = new VerticalIteratorMatrix();
		verticalIteratorMatrix.init(dna);
		iteratorDirections.add(verticalIteratorMatrix);
		
		DownwardDiagonalMatrixIterator downwardDiagonalMatrixIterator = new DownwardDiagonalMatrixIterator();
		downwardDiagonalMatrixIterator.init(dna, NUM_OF_CONSECUTIVE_EQUAL_LETTERS_TO_BE_MUTANT);
		iteratorDirections.add(downwardDiagonalMatrixIterator);
		
		AscendingDiagonalMatrixIterator ascendingDiagonalMatrixIterator = new AscendingDiagonalMatrixIterator();
		ascendingDiagonalMatrixIterator.init(dna, NUM_OF_CONSECUTIVE_EQUAL_LETTERS_TO_BE_MUTANT);
		iteratorDirections.add(ascendingDiagonalMatrixIterator);		
		
		Integer mutantSequenceCount = 0;
		
		for(MatrixIterator iterator : iteratorDirections)  {
			while (iterator.hasNext()) {
				if (isMutantSequence(iterator.next())) {
					mutantSequenceCount += 1;
				}
				
				if (mutantSequenceCount >= NUM_OF_SEQUENCES_TO_BE_MUTANT) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Use a regular expression to determine if there are 4 equal characters in a row.
	 * @param seq
	 * @return if there are 4 consecutive equal characters.
	 */
	private Boolean isMutantSequence(String seq) {
		String pattern = String.format("(.)\\1{%d,}", (NUM_OF_CONSECUTIVE_EQUAL_LETTERS_TO_BE_MUTANT - 1));
		
		return Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(seq).find();
	}
}
