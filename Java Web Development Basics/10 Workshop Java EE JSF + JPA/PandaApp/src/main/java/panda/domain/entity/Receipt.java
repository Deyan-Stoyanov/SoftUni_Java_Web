package panda.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "receipts")
public class Receipt extends BaseEntity {
    private BigDecimal fee;
    private LocalDateTime issuedOn;
    private User recipient;
    private Package aPackage;

    public Receipt() {
    }

    public Receipt(BigDecimal fee, LocalDateTime issuedOn, User recipient, Package aPackage) {
        this.fee = fee;
        this.issuedOn = issuedOn;
        this.recipient = recipient;
        this.aPackage = aPackage;
    }

    @Column(name = "fee")
    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Column(name = "issuedOn")
    public LocalDateTime getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(LocalDateTime issuedOn) {
        this.issuedOn = issuedOn;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "recipient", referencedColumnName = "id")
    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    @ManyToOne(targetEntity = Package.class)
    @JoinColumn(name = "package", referencedColumnName = "id")
    public Package getPackage() {
        return aPackage;
    }

    public void setPackage(Package aPackage) {
        this.aPackage = aPackage;
    }
}
