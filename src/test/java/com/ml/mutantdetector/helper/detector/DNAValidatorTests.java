package com.ml.mutantdetector.helper.detector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DNAValidatorTests {
	
	@Test(expected = NullPointerException.class)
	public void whenMatrixIsNull_thenExceptionIsExpected() {
		DNAValidator.validate(null, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenNumberOfLetterToBeEqualsAreLargerThanMatrixSize_thenExceptionIsExpected() {
		String[] dna = {"TTT", "TTT", "TTT"};
		
		DNAValidator.validate(dna, 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenNumberOfRowsAndColumnsAreDifferent_thenExceptionIsExpected() {
		String[] dna = {"TTT","TTT"};
		
		DNAValidator.validate(dna, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenMatrixContainsInvalidLetter_thenExceptionIsExpected() {
		String[] dna = {"BBB", "NNN", "AAA"};
		
		DNAValidator.validate(dna, 2);
	}
	
	@Test
	public void whenValidMatrix_thenNoExceptionIsExpected() {
		String[] dna = {"AAAA","TTTT","GGGG","CCCC"};
		
		DNAValidator.validate(dna, 3);
	}
	
}
