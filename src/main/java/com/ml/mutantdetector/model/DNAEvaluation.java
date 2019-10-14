package com.ml.mutantdetector.model;

import java.util.Arrays;
import java.util.List;

public class DNAEvaluation {	
	
	private List<String> dna;
	
	private Boolean isMutant;

	public List<String> getDna() {
		return dna;
	}

	public void setDna(List<String> dna) {
		this.dna = dna;
	}

	public Boolean getIsMutant() {
		return isMutant;
	}

	public void setIsMutant(Boolean isMutant) {
		this.isMutant = isMutant;
	}	
	
	public static String getKey(String[] dna) {
		return String.join("", dna);
	}

	public static String getKey(List<String> dna) {
		return String.join("", dna);
	}
	
	public DNAEvaluation() {}
	
	public DNAEvaluation(String[] dna, Boolean isMutant) {
		this.dna = Arrays.asList(dna);
		this.isMutant = isMutant;
	}
	
}
