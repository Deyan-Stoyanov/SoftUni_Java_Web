package panda.domain.model.view;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class ReceiptViewModel {
    private String id;
    private BigDecimal fee;
    private Date issuedOn;
    private UserListViewModel recipient;
    private PackageDetailsViewModel receiptPackage;

    public ReceiptViewModel() {
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Date getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(LocalDateTime issuedOn) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Instant instant = issuedOn.toInstant(ZoneOffset.UTC);
            Date date = Date.from(instant);
            this.issuedOn = sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public UserListViewModel getRecipient() {
        return recipient;
    }

    public void setRecipient(UserListViewModel recipient) {
        this.recipient = recipient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PackageDetailsViewModel getReceiptPackage() {
        return receiptPackage;
    }

    public void setReceiptPackage(PackageDetailsViewModel receiptPackage) {
        this.receiptPackage = receiptPackage;
    }
}
