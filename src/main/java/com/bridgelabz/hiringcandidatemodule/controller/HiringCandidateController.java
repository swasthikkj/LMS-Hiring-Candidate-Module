package com.bridgelabz.hiringcandidatemodule.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.hiringcandidatemodule.util.Response;
import com.bridgelabz.hiringcandidatemodule.dto.HiringCandidateDTO;
import com.bridgelabz.hiringcandidatemodule.model.HiringCandidateModel;
import com.bridgelabz.hiringcandidatemodule.service.ICandidateHiringService;
/**
 * Purpose:create hiring candidate controller
 * @version 4.15.1.RELEASE
 * @author Swasthik KJ
 */
@RestController
@RequestMapping("/hirecandidate")
public class HiringCandidateController {
	@Autowired
	ICandidateHiringService hireCandidateService;
	/**
	 * Purpose:add hiring candidate
	 * @Param token 
	 */
	@PostMapping("/addHireCandidate")
	public ResponseEntity<Response> addHireCandidate(@Valid @RequestBody HiringCandidateDTO hireCandidateDTO, @RequestHeader String token) {
		HiringCandidateModel hiringCandidateModel = hireCandidateService.addHireCandidate(hireCandidateDTO, token);
		Response response = new Response(200, "hirecandidate added successfully", hiringCandidateModel);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	/**
	 * Purpose:update hiring candidate by id
	 * @Param token and id
	 */
	@PutMapping("updateHireCandidate/{id}")
	public ResponseEntity<Response> updateHireCandidate(@Valid @RequestBody HiringCandidateDTO hireCandidateDTO, @PathVariable Long id, @RequestHeader String token) {
		HiringCandidateModel hiringCandidateModel = hireCandidateService.updateHireCandidate(hireCandidateDTO, id, token);
		Response response = new Response(200, "hirecandidate updated successfully", hiringCandidateModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	/**
	 * Purpose:get hiring candidate by id
	 * @Param token and id
	 */
	@GetMapping("/gethireCandidateData/{id}")
	public ResponseEntity<Response> getHireCandidateById(@PathVariable Long id, @RequestHeader String token) {
		Optional<HiringCandidateModel> hiringCandidateModel = hireCandidateService.getHireCandidateById(id, token);
		Response response = new Response(200, "hirecandidate fetched by id successfully", hiringCandidateModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	/**
	 * Purpose:get all hiring candidate
	 * @Param token 
	 */
	@GetMapping("/getAllhireCandidates")
	public ResponseEntity<Response> getAllHireCandidates(@RequestHeader String token) {
		List<HiringCandidateModel> hiringCandidateModel = hireCandidateService.getAllHireCandidates(token);
		Response response = new Response(200, "hirecandidate fetched successfully", hiringCandidateModel);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	/**
	 * Purpose:delete hiring candidate by id
	 * @Param token and id
	 */
	@DeleteMapping("/deletehireCandidate/{id}")
	public ResponseEntity<Response> deleteHireCandidate(@PathVariable Long id, @RequestHeader String token) {
		HiringCandidateModel hiringCandidateModel = hireCandidateService.deleteHireCandidate(id, token);
		Response response = new Response(200, "hirecandidate deleted successfully", hiringCandidateModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
