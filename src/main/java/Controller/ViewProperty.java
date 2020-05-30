package Controller;

import javafx.beans.property.*;
import javafx.scene.image.Image;

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

    public BooleanProperty TrumpCardVisibility;
    public BooleanProperty TrumpDeckClosedVisibility;
    public ObjectProperty<Image> TrumpCardImage;
    public BooleanProperty PlayerCanAct;

    public BooleanProperty CanSave;
    public StringProperty NewGameButtonText;
    public StringProperty QuitButtonText;

    public StringProperty PlayerTotalScoreText;
    public StringProperty CPUTotalScoreText;

    public BooleanProperty CPUHand1Visible;
    public BooleanProperty CPUHand2Visible;
    public BooleanProperty CPUHand3Visible;
    public BooleanProperty CPUHand4Visible;
    public BooleanProperty CPUHand5Visible;

    public ObjectProperty<Image> PlayerHand1;
    public ObjectProperty<Image> PlayerHand2;
    public ObjectProperty<Image> PlayerHand3;
    public ObjectProperty<Image> PlayerHand4;
    public ObjectProperty<Image> PlayerHand5;

    public BooleanProperty PlayerHand1Visible;
    public BooleanProperty PlayerHand2Visible;
    public BooleanProperty PlayerHand3Visible;
    public BooleanProperty PlayerHand4Visible;
    public BooleanProperty PlayerHand5Visible;

    public BooleanProperty PlayerPlayedImageVisible;
    public BooleanProperty CPUPlayedImageVisible;
    public ObjectProperty<Image> PlayerPlayedImage;
    public ObjectProperty<Image> CPUPlayedImage;

    public BooleanProperty PlayerWinVisible;
    public BooleanProperty CPUWinVisible;
    public BooleanProperty CanLoad;

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

        TrumpCardVisibility=new SimpleBooleanProperty();
        TrumpDeckClosedVisibility=new SimpleBooleanProperty();
        TrumpCardImage=new SimpleObjectProperty<Image>();
        PlayerCanAct=new SimpleBooleanProperty();

        CanSave=new SimpleBooleanProperty();
        NewGameButtonText=new SimpleStringProperty();
        QuitButtonText=new SimpleStringProperty();

        PlayerTotalScoreText=new SimpleStringProperty();
        CPUTotalScoreText=new SimpleStringProperty();
        PlayerPlayedImage=new SimpleObjectProperty<Image>();
        CPUPlayedImage=new SimpleObjectProperty<Image>();

        PlayerHand1=new SimpleObjectProperty<Image>();
        PlayerHand2=new SimpleObjectProperty<Image>();
        PlayerHand3=new SimpleObjectProperty<Image>();
        PlayerHand4=new SimpleObjectProperty<Image>();
        PlayerHand5=new SimpleObjectProperty<Image>();

        PlayerHand1Visible=new SimpleBooleanProperty();
        PlayerHand2Visible=new SimpleBooleanProperty();
        PlayerHand3Visible=new SimpleBooleanProperty();
        PlayerHand4Visible=new SimpleBooleanProperty();
        PlayerHand5Visible=new SimpleBooleanProperty();

        PlayerPlayedImageVisible=new SimpleBooleanProperty();
        CPUPlayedImageVisible=new SimpleBooleanProperty();
        CPUWinVisible= new SimpleBooleanProperty();
        PlayerWinVisible= new SimpleBooleanProperty();
        CanLoad=new SimpleBooleanProperty();
    }
}
