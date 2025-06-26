package enums;

public enum Item {
    SAUCE_LABS_BACKPACK("Sauce Labs Backpack"),
    SAUCE_LABS_BIKE_LIGHT("Sauce Labs Bike Light"),
    SAUCE_LABS_BOLT_TSHIRT("Sauce Labs Bolt T-Shirt"),
    SAUCE_LABS_FLEECE_JACKET("Sauce Labs Fleece Jacket"),
    SAUCE_LABS_ONESIE("Sauce Labs Onesie");

    private final String itemname;

    Item(String itemName) {
        this.itemname = itemName;
    }

    public String getItemName() {
        return itemname;
    }
}
