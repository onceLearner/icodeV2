package com.Emi.IcodeV2.repository;

import com.Emi.IcodeV2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
