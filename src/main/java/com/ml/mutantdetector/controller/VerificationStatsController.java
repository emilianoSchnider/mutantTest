package com.ml.mutantdetector.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ml.mutantdetector.dto.VerificationStatsResponse;
import com.ml.mutantdetector.service.VerificationStatsService;

/**
 * Verification stats controller.
 * Exposes rest service to get verification DNA stats.
 * 
 * @author eschnider
 */
@RestController
public class VerificationStatsController {
		
	VerificationStatsService verificationStatsService;
	
	@Autowired
	public VerificationStatsController(VerificationStatsService verificationStatsService) {
		this.verificationStatsService = verificationStatsService;
	}
	
	/**
	 * Return verification DNA stats.
	 * @return {@link VerificationStatsResponse} object with stats.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	public VerificationStatsResponse getVerficationStats() throws Exception {
		try {
			return this.verificationStatsService.getVerificationStats();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new Exception("An error occurred while processing the request.");
		}
	}
}
