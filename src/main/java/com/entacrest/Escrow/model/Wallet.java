package com.entacrest.Escrow.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.management.ObjectName;
import java.time.LocalDateTime;
import java.util.Date;
import  java.util.UUID;

@Entity
@Data
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private Double balance;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private final Statuses status = Statuses.ACTIVE;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @PrePersist
    public void generateFullName() {
        this.createdOn = LocalDateTime.now();
        this.updatedOn = LocalDateTime.now();
    }
}
