package com.example.codingevents.models;


import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class EventDetails extends AbstractEntity {

    @Size(max = 500, message = "Description is too long!")
    private String description;
    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email. Try again!")
    private String contactEmail;
    @NotNull(message = "Location is required.")
    @NotBlank(message = "Location is required.")
    private String address;

    @OneToOne(mappedBy = "eventDetails")
    private Event event;

    public EventDetails(String description, String contactEmail, String address) {
        this.description = description;
        this.contactEmail = contactEmail;
        this.address = address;
    }
    public EventDetails() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
