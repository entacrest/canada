package com.entacrest.Escrow.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import com.entacrest.Escrow.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class UserAccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "account_type", referencedColumnName = "id")  // Foreign key in AccountType table
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private final Statuses status = Statuses.PENDING;

    @Column(columnDefinition = "boolean default false")
    private final Boolean active = false;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    // Default constructor
    public UserAccountType() {
        this.createdOn = new Date();
        this.updatedOn = new Date();
    }


//    @Override
//    public String toString() {
//        return this.id + ": " + this.user + ": " + this.accountType;
//    }
}


