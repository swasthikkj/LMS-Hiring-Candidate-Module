package com.bridgelabz.hiringcandidatemodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.hiringcandidatemodule.model.HiringCandidateModel;
@Repository
public interface HiringCandidateRepository extends JpaRepository<HiringCandidateModel, Long> {

}
