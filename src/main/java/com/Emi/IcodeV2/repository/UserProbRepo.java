package com.Emi.IcodeV2.repository;

import com.Emi.IcodeV2.model.UserProblem;
import com.Emi.IcodeV2.model.UserProblemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProbRepo extends JpaRepository<UserProblem,Integer> {
}
