package sbojgb.domain.enums;

public enum Sector {
    Medicine, Car, Food, Domestic, Security;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
