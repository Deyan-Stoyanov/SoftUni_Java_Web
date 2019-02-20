package panda.domain.entity;

import panda.domain.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "packages")
public class Package extends BaseEntity{
    private String description;
    private Double weight;
    private String shippingAddress;
    private Status status;
    private LocalDateTime deliveryDate;
    private User recipient;

    public Package() {
    }

    public Package(String description, Double weight, String shippingAddress, Status status, LocalDateTime deliveryDate, User recipient) {
        this.description = description;
        this.weight = weight;
        this.shippingAddress = shippingAddress;
        this.status = status;
        this.deliveryDate = deliveryDate;
        this.recipient = recipient;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "weight")
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Column(name = "shippingAddress")
    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name = "deliveryDate")
    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @ManyToOne(targetEntity = User.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "recipient", referencedColumnName = "id")
    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }
}
