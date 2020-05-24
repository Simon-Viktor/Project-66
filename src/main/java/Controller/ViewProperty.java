package Controller;

import javafx.beans.property.*;

public class ViewProperty {
    public BooleanProperty Spring20Visible;
    public BooleanProperty Summer20Visible;
    public BooleanProperty Autumn20Visible;
    public BooleanProperty Winter20Visible;

    public BooleanProperty Call20Visible;
    public BooleanProperty DeckVisible;
    public BooleanProperty GameScoreVisible;
    public StringProperty GameScore;

    public BooleanProperty CPUScoredVisible;
    public BooleanProperty PlayerScoredVisible;

    public BooleanProperty CPUHand1Visible;
    public BooleanProperty CPUHand2Visible;
    public BooleanProperty CPUHand3Visible;
    public BooleanProperty CPUHand4Visible;
    public BooleanProperty CPUHand5Visible;

    public ViewProperty()
    {
        Spring20Visible=new SimpleBooleanProperty();
        Summer20Visible=new SimpleBooleanProperty();
        Autumn20Visible=new SimpleBooleanProperty();
        Winter20Visible=new SimpleBooleanProperty();

        Call20Visible=new SimpleBooleanProperty();
        DeckVisible=new SimpleBooleanProperty();
        GameScoreVisible=new SimpleBooleanProperty();
        GameScore=new SimpleStringProperty();

        CPUScoredVisible=new SimpleBooleanProperty();
        PlayerScoredVisible=new SimpleBooleanProperty();

        CPUHand1Visible=new SimpleBooleanProperty();
        CPUHand2Visible=new SimpleBooleanProperty();
        CPUHand3Visible=new SimpleBooleanProperty();
        CPUHand4Visible=new SimpleBooleanProperty();
        CPUHand5Visible=new SimpleBooleanProperty();
    }


    public void Update(ViewProperty other)
    {
        //TODO - Assign the values from other into the values of this. Only do once everything else is done.
        //TODO - Alternatively, add a reference into the main game model and just manipulate things from there.
    }
}
