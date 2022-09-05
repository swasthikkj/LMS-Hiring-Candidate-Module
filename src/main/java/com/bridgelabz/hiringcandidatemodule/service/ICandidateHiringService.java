package com.bridgelabz.hiringcandidatemodule.service;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.hiringcandidatemodule.dto.HiringCandidateDTO;
import com.bridgelabz.hiringcandidatemodule.model.HiringCandidateModel;

public interface ICandidateHiringService {
	HiringCandidateModel addHireCandidate(HiringCandidateDTO hireCandidateDTO, String token);

	HiringCandidateModel updateHireCandidate(HiringCandidateDTO hireCandidateDTO, Long id, String token);

	Optional<HiringCandidateModel> getHireCandidateById(Long id, String token);

	List<HiringCandidateModel> getAllHireCandidates(String token);

	HiringCandidateModel deleteHireCandidate(Long id, String token);

}
