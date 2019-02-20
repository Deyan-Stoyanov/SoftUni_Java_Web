package panda.domain.model.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReceiptServiceModel {
    private String id;
    private BigDecimal fee;
    private LocalDateTime issuedOn;
    private UserServiceModel recipient;
    private PackageServiceModel aPackage;

    public ReceiptServiceModel() {
    }

    public ReceiptServiceModel(BigDecimal fee, LocalDateTime issuedOn, UserServiceModel recipient, PackageServiceModel aPackage) {
        this.fee = fee;
        this.issuedOn = issuedOn;
        this.recipient = recipient;
        this.aPackage = aPackage;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public LocalDateTime getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(LocalDateTime issuedOn) {
        this.issuedOn = issuedOn;
    }

    public UserServiceModel getRecipient() {
        return recipient;
    }

    public void setRecipient(UserServiceModel recipient) {
        this.recipient = recipient;
    }

    public PackageServiceModel getPackage() {
        return aPackage;
    }

    public void setPackage(PackageServiceModel aPackage) {
        this.aPackage = aPackage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
