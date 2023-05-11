package pageobjects.google.calculator.Menu;

public enum CommitedUsage {
    NONE(0),
    ONE_YEAR(1),
    THREE_YEARS(3);
    private Integer value;

    private CommitedUsage(Integer value){
        this.value =value;
    }

    public String getValueString() {
        return value.toString();
    }
}
