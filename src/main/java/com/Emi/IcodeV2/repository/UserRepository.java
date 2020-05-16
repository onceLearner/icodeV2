package com.Emi.IcodeV2.repository;

import com.Emi.IcodeV2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
   public User findUserByUsername(String username);
}
