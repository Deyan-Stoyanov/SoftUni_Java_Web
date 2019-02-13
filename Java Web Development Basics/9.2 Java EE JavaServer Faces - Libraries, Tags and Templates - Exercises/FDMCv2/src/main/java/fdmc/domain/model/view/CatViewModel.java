package fdmc.domain.model.view;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CatViewModel {
    private String name;
    private String breed;
    private String color;
    private String gender;
    private BigDecimal price;
    private Date addedOn;

    public CatViewModel() {
    }

    public CatViewModel(String name, String breed, String color, String gender, BigDecimal price, Date addedOn) {
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.gender = gender;
        this.price = price;
        this.addedOn = addedOn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getAddedOn() {
        return this.addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }
}
