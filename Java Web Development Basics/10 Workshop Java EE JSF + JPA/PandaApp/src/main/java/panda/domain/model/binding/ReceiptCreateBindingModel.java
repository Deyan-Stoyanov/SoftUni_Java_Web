package panda.domain.model.binding;

import panda.domain.model.service.PackageServiceModel;
import panda.domain.model.service.UserServiceModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReceiptCreateBindingModel {
    private BigDecimal fee;
    private LocalDateTime issuedOn;
    private UserServiceModel recipient;
    private PackageServiceModel aPackage;

    public ReceiptCreateBindingModel() {
    }

    public ReceiptCreateBindingModel(BigDecimal fee, LocalDateTime issuedOn, UserServiceModel recipient, PackageServiceModel aPackage) {
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
}
