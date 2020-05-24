package Model;

public enum CardFace {
    Underknave(2, "Underknave"),
    Upperknave(3, "Upperknave"),
    King(4, "King"),
    Ten(10, "Ten"),
    Ace(11, "Ace");

    public final Integer value;
    public final String fileSegment;

    private CardFace(Integer value, String fileSegment) {
        this.value = value;
        this.fileSegment=fileSegment;
    }
}
