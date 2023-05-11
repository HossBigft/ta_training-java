package pageobjects.google.calculator.Menu;

public enum SsdQuantity {
    NONE(0),
    ZERO(0),
    ONE_375GB(1),
    TWO_375GB(2),
    THREE_375GB(3),
    FOUR_375GB(4),
    FIVE_375GB(5),
    SIX_375GB(6),
    SEVEN_375GB(7),
    EIGHT_375GB(8),
    SIXTEEN_375GB(16),
    TWENTY_FOUR_375GB(24);
    private final Integer value;

    private SsdQuantity(Integer val) {
        value = val;
    }

    public String getQuantityString() {
        return value.toString();
    }

}
