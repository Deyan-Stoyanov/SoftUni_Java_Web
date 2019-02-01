package domain.entities;

import domain.enums.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Entity(name = "products")
public class Product extends BaseEntity {
    private String name;
    private String description;
    private Type type;

    public Product() {
    }

    public Product(String name, String description, Type type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    @Column(name = "name", unique = true)
    @NotNull
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
