package com.bridgelabz.hiringcandidatemodule.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.hiringcandidatemodule.dto.HiringCandidateDTO;
import com.bridgelabz.hiringcandidatemodule.exception.CustomNotFoundException;
//import com.bridgelabz.hiringcandidatemodule.model.AdminModel;
//import com.bridgelabz.hiringcandidatemodule.model.BankDetailsModel;
import com.bridgelabz.hiringcandidatemodule.model.HiringCandidateModel;
//import com.bridgelabz.hiringcandidatemodule.repository.AdminRepository;
//import com.bridgelabz.hiringcandidatemodule.repository.BankDetailsRepository;
import com.bridgelabz.hiringcandidatemodule.repository.HiringCandidateRepository;
import com.bridgelabz.hiringcandidatemodule.util.TokenUtil;

@Service
public class CandidateHiringService implements ICandidateHiringService {
	@Autowired
	HiringCandidateRepository hireCandidateRepository;

	//	@Autowired
	//	TokenUtil tokenUtil;

	//	@Autowired
	//	AdminRepository adminRepository;

	@Autowired
	MailService mailService;

	//	@Autowired
	//	BankDetailsRepository bankDetailsRepository;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public HiringCandidateModel addHireCandidate(HiringCandidateDTO hireCandidateDTO, String token) {
		//		Long hireCandidateId = tokenUtil.decodeToken(token);
		//		Optional<AdminModel> isTokenPresent = adminRepository.findById(hireCandidateId);
		//		if(isTokenPresent.isPresent()) {
		//			Optional<BankDetailsModel> isBankAccountPresent = bankDetailsRepository.findById(bankId);
		//			
		//			if(isBankAccountPresent.isPresent()) {
		//				model.setBankDetails(isBankAccountPresent.get());
		//			}
		boolean isUserPresent = restTemplate.getForObject("http://LMS-AdminModule:8069/adminmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			HiringCandidateModel model = new HiringCandidateModel(hireCandidateDTO);
			hireCandidateRepository.save(model);
			String body = "Hiring candidate added successfully with bank Id" + model.getId();
			String subject = "hiring candidate added Successfully";
			mailService.send(model.getEmail(), subject, body);
			return model;
		}
		throw new CustomNotFoundException(400, "Token not found");
	}

	@Override
	public HiringCandidateModel updateHireCandidate(HiringCandidateDTO hireCandidateDTO, Long id, String token) {
		//		Long hireCandidateId = tokenUtil.decodeToken(token);
		//		Optional<AdminModel> isTokenPresent = adminRepository.findById(hireCandidateId);
		//		if(isTokenPresent.isPresent()) {
		boolean isUserPresent = restTemplate.getForObject("http://LMS-AdminModule:8069/adminmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<HiringCandidateModel> isIdPresent = hireCandidateRepository.findById(id);
			if(isIdPresent.isPresent()) {
				isIdPresent.get().setCicId(hireCandidateDTO.getCicId());
				isIdPresent.get().setFullName(hireCandidateDTO.getFullName());
				isIdPresent.get().setEmail(hireCandidateDTO.getEmail());
				isIdPresent.get().setMobileNum(hireCandidateDTO.getMobileNum());
				isIdPresent.get().setHiredDate(hireCandidateDTO.getHiredDate());
				isIdPresent.get().setAggrPer(hireCandidateDTO.getAggrPer());
				isIdPresent.get().setCity(hireCandidateDTO.getCity());
				isIdPresent.get().setState(hireCandidateDTO.getState());
				isIdPresent.get().setJobLocation(hireCandidateDTO.getJobLocation());
				isIdPresent.get().setPassedOutYear(hireCandidateDTO.getPassedOutYear());
				isIdPresent.get().setCreatorUser(hireCandidateDTO.getCreatorUser());
				isIdPresent.get().setCandidateStatus(hireCandidateDTO.getCandidateStatus());
				isIdPresent.get().setUpdatedTimeStamp(LocalDateTime.now());
				hireCandidateRepository.save(isIdPresent.get());
				String body = "Hiring candidate updated successfully with bank Id" + isIdPresent.get().getId();
				String subject = "hiring candidate updated Successfully";
				mailService.send(isIdPresent.get().getEmail(), subject, body);
				return isIdPresent.get();
			}
			throw new CustomNotFoundException(400, "Hiring candidate details not present");
		}
		throw new CustomNotFoundException(400, "Token Invalid");
	}

	@Override
	public Optional<HiringCandidateModel> getHireCandidateById(Long id, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://LMS-AdminModule:8069/adminmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			return hireCandidateRepository.findById(id);		
		}
		throw new CustomNotFoundException(400, "Token Invalid");
	}

	@Override
	public List<HiringCandidateModel> getAllHireCandidates(String token) {
		//		Long hireCandidateId = tokenUtil.decodeToken(token);
		boolean isUserPresent = restTemplate.getForObject("http://LMS-AdminModule:8069/adminmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			List<HiringCandidateModel> getAllHireCandidates = hireCandidateRepository.findAll();
			if(getAllHireCandidates.size() > 0) {
				return getAllHireCandidates;
			} else {
				throw new CustomNotFoundException(400, "Candidate not present");
			}
		}
		throw new CustomNotFoundException(400, "Token Invalid");
	}

	@Override
	public HiringCandidateModel deleteHireCandidate(Long id, String token) {
		//		Long hireCandidateId = tokenUtil.decodeToken(token);
		boolean isUserPresent = restTemplate.getForObject("http://LMS-AdminModule:8069/adminmodule/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<HiringCandidateModel> isHireCandidatePresent = hireCandidateRepository.findById(id);
			if(isHireCandidatePresent.isPresent()) {
				hireCandidateRepository.delete(isHireCandidatePresent.get());
				String body = "Hiring candidate updated successfully with bank Id" + isHireCandidatePresent.get().getId();
				String subject = "hiring candidate updated Successfully";
				mailService.send(isHireCandidatePresent.get().getEmail(), subject, body);
				return isHireCandidatePresent.get();
			}
			throw new CustomNotFoundException(400, "Candidate not found");	
		}
		throw new CustomNotFoundException(400, "Token Invalid");
	}
}
