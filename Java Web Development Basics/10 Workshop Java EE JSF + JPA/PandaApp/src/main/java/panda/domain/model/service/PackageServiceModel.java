package panda.domain.model.service;

import panda.domain.enums.Status;

import java.time.LocalDateTime;

public class PackageServiceModel {
    private String id;
    private String description;
    private Double weight;
    private String shippingAddress;
    private Status status;
    private LocalDateTime deliveryDate;
    private UserServiceModel recipient;

    public PackageServiceModel() {
    }

    public PackageServiceModel(String description, Double weight, String shippingAddress, Status status, LocalDateTime deliveryDate, UserServiceModel recipient) {
        this.description = description;
        this.weight = weight;
        this.shippingAddress = shippingAddress;
        this.status = status;
        this.deliveryDate = deliveryDate;
        this.recipient = recipient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public UserServiceModel getRecipient() {
        return recipient;
    }

    public void setRecipient(UserServiceModel recipient) {
        this.recipient = recipient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
