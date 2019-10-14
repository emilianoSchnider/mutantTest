package com.ml.mutantdetector.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ml.mutantdetector.model.DNAEvaluation;
import com.ml.mutantdetector.model.DNAVerificationCounter;
import com.ml.mutantdetector.repository.DNAEvaluationRepository;
import com.ml.mutantdetector.repository.DNAValidationRepositoryImpl;
import com.ml.mutantdetector.repository.FirebaseDNAEvaluationRepository;
import com.ml.mutantdetector.repository.FirebaseDNAVerificationCounterRepository;

@RestController
public class WebController {
	@RequestMapping(value = "/sample", method = RequestMethod.POST )
	public String Sample(@RequestParam(value = "name",
	defaultValue = "Robot") String name) {
		
		//DNAValidationRepositoryImpl repository = new DNAValidationRepositoryImpl();
		//repository.guardar();
	
		DNAEvaluationRepository repository = new FirebaseDNAEvaluationRepository();
		
		String[] dna = {"AA","BB"};
		DNAEvaluation dnaEvaluation = new DNAEvaluation(dna, true);		
		
		repository.save(dnaEvaluation);
		
		try {
			DNAEvaluation k = repository.get("AABB");
			return k.toString();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Vamo loco";
	}
	
	@RequestMapping(value = "/counter", method = RequestMethod.POST )
	public String Counter(@RequestParam(value = "name", defaultValue = "mutant") String name) throws InterruptedException, ExecutionException {
		FirebaseDNAVerificationCounterRepository o = new FirebaseDNAVerificationCounterRepository();
		DNAVerificationCounter count = o.getCounter("mutant");
		return count == null? "LKa pyut" : String.valueOf(count.getCount());
	}
	
	@RequestMapping(value = "/increment", method = RequestMethod.POST )
	public String Increment(@RequestParam(value = "name", defaultValue = "mutant") String name) throws InterruptedException, ExecutionException {
		FirebaseDNAVerificationCounterRepository o = new FirebaseDNAVerificationCounterRepository();
		o.incrementCounter("mutant");
		return "ssss";
	}
}
