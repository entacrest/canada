package com.entacrest.Escrow.repository;

import com.entacrest.Escrow.model.User;
import com.entacrest.Escrow.model.UserAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountTypeRepository extends JpaRepository<UserAccountType, Integer> {
}

