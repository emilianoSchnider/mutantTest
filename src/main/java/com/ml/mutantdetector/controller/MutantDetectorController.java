package com.ml.mutantdetector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ml.mutantdetector.dto.DNARequest;
import com.ml.mutantdetector.model.DNAEvaluation;
import com.ml.mutantdetector.service.MutantDetectorService;
import com.ml.mutantdetector.service.VerificationStatsService;

/**
 * Mutant detector controller.
 * Exposes rest service to detect mutants.
 * 
 * @author eschnider
 */
@RestController
public class MutantDetectorController {
	
	//@Autowired
	MutantDetectorService mutantDetectorService;
	
	//@Autowired
	VerificationStatsService verificationStatsService;
	
	@Autowired
	public MutantDetectorController(MutantDetectorService mutantDetectorService
			, VerificationStatsService verificationStatsService) {
		this.mutantDetectorService = mutantDetectorService;
		this.verificationStatsService = verificationStatsService;
	}
	
	/**
	 * Determine if the DNA is mutant.
	 * @param dnaRequest DNA sequence.
	 * @return HTTP 200-OK if it's mutant or 403 if it's human.
	 */
	@RequestMapping(value = "/mutant", method = RequestMethod.POST )
	public ResponseEntity isMutant(@RequestBody DNARequest dnaRequest) {
		
		this.mutantDetectorService.validate(dnaRequest);
		try {
			DNAEvaluation dnaEval = this.mutantDetectorService.getDNAEvaluation(dnaRequest);
		
			if (dnaEval != null) {
				return dnaEval.getIsMutant() 
						? ResponseEntity.status(HttpStatus.OK).build()
						: ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
		
			Boolean isMutant = this.mutantDetectorService.isMutant(dnaRequest);
		
			this.mutantDetectorService.saveDNARequestAndEvaluation(dnaRequest, isMutant);
		
			if (isMutant) {
				this.verificationStatsService.incrementMutantCounter();
				return ResponseEntity.status(HttpStatus.OK).build();
			}
		
			this.verificationStatsService.incrementHumanCounter();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();		
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
