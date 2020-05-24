package Model;

public enum CardColour {
    Spring("Spring"),
    Summer("Summer"),
    Autumn("Autumn"),
    Winter("Winter");

    public final String fileSegment;

    private CardColour(String fileSegment) {
        this.fileSegment=fileSegment;
    }
}
