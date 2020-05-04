package com.Emi.IcodeV2.repository;

import com.Emi.IcodeV2.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepo extends JpaRepository<Problem,Integer> {
}
