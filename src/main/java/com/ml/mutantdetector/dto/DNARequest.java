package com.ml.mutantdetector.dto;

/**
 * DTO to represent DNA request. 
 * 
 * @author eschnider
 */
public class DNARequest {
	private String[] dna;

	public String[] getDna() {
		return dna;
	}

	public void setDna(String[] dna) {
		this.dna = dna;
	}	
}
