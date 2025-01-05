package com.entacrest.Escrow.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @Column(columnDefinition = "boolean default false")
    private final Boolean active = false;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    @Override
    public String toString() {
        return this.name;
    }
}

