package model;

public class Property {
    private final String landName;
    private final int landPrice;

    public Property(String slotName, int landPrice) {
        this.landName = slotName;
        this.landPrice = landPrice;
    }


    public String getLandName() {
        return landName;
    }

    public int getLandPrice() {
        return landPrice;
    }

    public int getRentPrice() {
        return this.landPrice % 10;
    }


}
