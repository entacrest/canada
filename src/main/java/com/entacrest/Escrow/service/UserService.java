package com.entacrest.Escrow.service;

import com.entacrest.Escrow.DTO.ResponseMessage;
import com.entacrest.Escrow.model.Wallet;
import com.entacrest.Escrow.repository.UserRepository;
import com.entacrest.Escrow.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.entacrest.Escrow.model.User;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;  // This is fine, no need for redundant assignment
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public ResponseEntity<?> registerUser(User user) {
        try {
            Optional<User> existingEmail = userRepository.findByEmail(user.getEmail());
            Optional<User> existingPhone = userRepository.findByPhoneNumber(user.getPhoneNumber());

            if (existingEmail.isPresent()) {
                return ResponseEntity.badRequest().body(
                        new ResponseMessage("failed", "Email already in use")
                );
            } else if (existingPhone.isPresent()) {
                return ResponseEntity.badRequest().body(
                        new ResponseMessage("failed", "Phone already in use")
                );
            }
            // Encrypt password before saving user
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser = userRepository.save(user);

            // Create and associate a wallet with the newly registered user
            Wallet wallet = new Wallet();
            wallet.setUser(savedUser);
            wallet.setBalance(0.0); // Initialize with 0 balance (you can set another default if needed)
            walletRepository.save(wallet);

            return ResponseEntity.ok(savedUser);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    new ResponseMessage("failed", "An error occurred: " + e.getMessage())
            );
        }
    }
}
