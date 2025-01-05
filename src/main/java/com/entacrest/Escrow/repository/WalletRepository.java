package com.entacrest.Escrow.repository;

import com.entacrest.Escrow.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
}
