package com.ml.mutantdetector.helper.detector;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MutantDetectorTests {

	@Test
	public void whenDNAContainsMoreThanOneSequenceOfSameLetter_thenMutantIsReturned() {
		String[] dna = {"ATTTT","CCCCA","TCTCT","AAGGC","CCCAA"};		
		String[] dna1 = {"ATCGA","AACGG","ATCTG","ACATG","TCGTG"};		
		String[] dna2 = {"ATTTA","ACCCA","TATTA","ACATA","TCGAG"};
		String[] dna3 = {"AACTA","ACTCA","TTAAA","TCATA","TCGAG"};
		
		MutantDetector mutantDetector = new MutantDetector();
		
		assertTrue(mutantDetector.isMutant(dna));
		assertTrue(mutantDetector.isMutant(dna1));
		assertTrue(mutantDetector.isMutant(dna2));
		assertTrue(mutantDetector.isMutant(dna3));
	}

	@Test
	public void whenDNANoContainsMoreThanOneSequenceOfSameLetter_thenNotMutantIsReturned() {
		String[] dna = {"ATTGT","CCCAA","GGTTT","AAGGC","CCCAA"};		
		String[] dna1 = {"ATCGA","AACGG","TTGGC","ACATG","TCGTG"};	
		
		MutantDetector mutantDetector = new MutantDetector();
		
		assertFalse(mutantDetector.isMutant(dna));
		assertFalse(mutantDetector.isMutant(dna1));
	}
	
	@Test
	public void whenDNAContainsOnlyOneSequenceOfSameLetter_thenNotMutantIsReturned() {
		String[] dna = {"ATTTT","CCCAA","TCTCT","AAGGC","CCCAA"};		
		String[] dna1 = {"ATCGA","AACGG","ATCTG","ACATG","TCGTC"};	
		
		MutantDetector mutantDetector = new MutantDetector();
		
		assertFalse(mutantDetector.isMutant(dna));
		assertFalse(mutantDetector.isMutant(dna1));
	}
	
}
