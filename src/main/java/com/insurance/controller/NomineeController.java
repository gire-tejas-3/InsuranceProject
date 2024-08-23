package com.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.model.Nominee;
import com.insurance.service.NomineeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/nominee")
public class NomineeController {

	@Autowired
	private NomineeService nomineeService;
	
	@PostMapping
	public ResponseEntity<Nominee> saveNominee(@Valid @RequestBody Nominee nominee) {
		Nominee createdNominee = nomineeService.saveNominee(nominee);
		return new ResponseEntity<>(createdNominee,HttpStatus.CREATED );
	
		}
	@PutMapping("{/id}")
	public ResponseEntity<Nominee> updateNominee(@Valid @PathVariable Nominee nominee) {
		Nominee updatedNominee = nomineeService.saveNominee(nominee);
		return new ResponseEntity<>(updatedNominee,HttpStatus.OK);
	}
	@GetMapping("{/id}")
	 public ResponseEntity<Nominee> getNomineeById(@PathVariable int id) {
		 Nominee nomineeList=  nomineeService.getNomineeById(id);
		return new ResponseEntity<Nominee>(nomineeList, HttpStatus.OK);
	 }
	 @DeleteMapping("{/id}")
	 public ResponseEntity<Void> deleteNominee(@PathVariable int id) {
		 nomineeService.deleteNominee(id);
		 return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	 }
	
}
