package com.entacrest.Escrow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password field is required")
    private String password;

    @Pattern(regexp = "^\\+?[0-9. ()-]{7,15}$", message = "Invalid phone number")
    @NotBlank(message = "Phone number is required")
    @Column(unique = true)
    private String phoneNumber;

    private String address;

    @Column(columnDefinition = "boolean default false")
    private final Boolean isActive = false;

    @Column(columnDefinition = "boolean default false")
    private final Boolean isStaff = false;

    @Column(columnDefinition = "boolean default false")
    private final Boolean isAdmin = false;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    @Column(name = "full_name")
    private String fullName;

    // Generate full name during creation and update
    @PrePersist
    public void generateFullName() {
        this.fullName = this.firstName + " " + this.lastName;
        this.createdOn = LocalDateTime.now();
        this.updatedOn = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.fullName = this.firstName + " " + this.lastName;
        this.updatedOn = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return this.id + ": " + this.firstName + ": " + this.lastName;
    }

    // Ensure fullName is updated when firstName or lastName is changed
    public void setFirstName(String firstName) {
        this.firstName = firstName;
        this.fullName = this.firstName + " " + this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        this.fullName = this.firstName + " " + this.lastName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}



