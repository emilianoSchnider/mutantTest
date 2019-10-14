package com.ml.mutantdetector.helper.detector;

/**
 * Implement functions to validate allowed DNA format.
 * @author eschnider
 */
public class DNAValidator {
	
	public static void validate(String[] dna, Integer numOfLetterToBeEquals) throws IllegalArgumentException {
		Integer rowCount = dna.length;		
		
		if (dna.length < numOfLetterToBeEquals) {
			throw new IllegalArgumentException("The size of the matrix cannot be less than the amount of letter to analyze.");
		}
		
		for (String item: dna) {
			if (item.length() != rowCount) {
				throw new IllegalArgumentException("The number of rows and columns must be equal.");
			}
			
			if (!item.matches("[ATCGatcg]*")) {
				throw new IllegalArgumentException("Only A,T,C and G letter are allowed.");
			}
		}		
	}
	
}
