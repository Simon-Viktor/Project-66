package Controller;

import Model.CardColour;
import Model.GameModel;
import Model.MenuModel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class GameController {


    @FXML
    private ImageView Spring20Button;
    @FXML
    private ImageView Summer20Button;
    @FXML
    private ImageView Autumn20Button;
    @FXML
    private ImageView Winter20Button;
    @FXML
    private Button PairButton;
    @FXML
    private ImageView deckView;
    @FXML
    private Label PlayerGameScore;
    @FXML
    private ImageView CPUScoredCards;
    @FXML
    private ImageView PlayerScoredCards;
    @FXML
    private ImageView CPUHand1;
    @FXML
    private ImageView CPUHand2;
    @FXML
    private ImageView CPUHand3;
    @FXML
    private ImageView CPUHand4;
    @FXML
    private ImageView CPUHand5;

    @FXML
    private ImageView trumpView;
    @FXML
    private ImageView trumpDeckClosed;

    @FXML
    private Button NewGameButton;
    @FXML
    private Button SurrenderAndQuitButton;
    @FXML
    private Button SaveAndQuitButton;
    @FXML
    private Button SaveButton;
    @FXML
    private Label PlayerTotalScoreLabel;
    @FXML
    private Label CPUTotalScoreLabel;


    @FXML
    private ImageView PlayerPlayedView;
    @FXML
    private ImageView CPUPlayedView;
    @FXML
    private ImageView PlayerHand1;
    @FXML
    private ImageView PlayerHand2;
    @FXML
    private ImageView PlayerHand3;
    @FXML
    private ImageView PlayerHand4;
    @FXML
    private ImageView PlayerHand5;


    ViewProperty viewProperty;
    GameModel gameModel;
    MenuModel menuModel;

    @FXML
    private Pane PlayerActionBlockerPane1;
    @FXML
    private Pane PlayerActionBlockerPane2;
    @FXML
    private Pane PlayerActionBlockerPane3;

    @FXML
    private void initialize()
    {
        viewProperty=new ViewProperty();
        setupStaticViews();
        handleBindings();
        gameModel=new GameModel(viewProperty);
        menuModel=new MenuModel(gameModel);
        viewProperty.DeckVisible.setValue(true);
        viewProperty.NewGameButtonText.setValue("New Game");
        viewProperty.QuitButtonText.setValue("Quit");
    }

    private void setupStaticViews()
    {
        Spring20Button.setImage(new Image((getClass().getResource("/Images/"+CardColour.Spring.fileSegment+".jpg").toExternalForm())));
        Summer20Button.setImage(new Image((getClass().getResource("/Images/"+CardColour.Summer.fileSegment+".jpg").toExternalForm())));
        Autumn20Button.setImage(new Image((getClass().getResource("/Images/"+CardColour.Autumn.fileSegment+".jpg").toExternalForm())));
        Winter20Button.setImage(new Image((getClass().getResource("/Images/"+CardColour.Winter.fileSegment+".jpg").toExternalForm())));
        deckView.setImage(new Image((getClass().getResource("/Images/CardBack.jpg").toExternalForm())));
        PlayerScoredCards.setImage(new Image((getClass().getResource("/Images/CardBack.jpg").toExternalForm())));
        CPUScoredCards.setImage(new Image((getClass().getResource("/Images/CardBack.jpg").toExternalForm())));

        CPUHand1.setImage(new Image((getClass().getResource("/Images/CardBack.jpg").toExternalForm())));
        CPUHand2.setImage(new Image((getClass().getResource("/Images/CardBack.jpg").toExternalForm())));
        CPUHand3.setImage(new Image((getClass().getResource("/Images/CardBack.jpg").toExternalForm())));
        CPUHand4.setImage(new Image((getClass().getResource("/Images/CardBack.jpg").toExternalForm())));
        CPUHand5.setImage(new Image((getClass().getResource("/Images/CardBack.jpg").toExternalForm())));

        trumpDeckClosed.setImage(new Image((getClass().getResource("/Images/CardBack.jpg").toExternalForm())));
    }

    private void handleBindings()
    {
        Bindings.bindBidirectional(this.Spring20Button.visibleProperty(), viewProperty.Spring20Visible);
        Bindings.bindBidirectional(this.Summer20Button.visibleProperty(), viewProperty.Summer20Visible);
        Bindings.bindBidirectional(this.Autumn20Button.visibleProperty(), viewProperty.Autumn20Visible);
        Bindings.bindBidirectional(this.Winter20Button.visibleProperty(), viewProperty.Winter20Visible);

        Bindings.bindBidirectional(this.PairButton.visibleProperty(), viewProperty.Call20Visible);
        Bindings.bindBidirectional(this.deckView.visibleProperty(), viewProperty.DeckVisible);
        Bindings.bindBidirectional(this.PlayerGameScore.visibleProperty(), viewProperty.GameScoreVisible);
        Bindings.bindBidirectional(this.PlayerGameScore.textProperty(), viewProperty.GameScore);

        Bindings.bindBidirectional(this.PlayerScoredCards.visibleProperty(), viewProperty.PlayerScoredVisible);
        Bindings.bindBidirectional(this.CPUScoredCards.visibleProperty(), viewProperty.CPUScoredVisible);

        Bindings.bindBidirectional(this.CPUHand1.visibleProperty(), viewProperty.CPUHand1Visible);
        Bindings.bindBidirectional(this.CPUHand2.visibleProperty(), viewProperty.CPUHand2Visible);
        Bindings.bindBidirectional(this.CPUHand3.visibleProperty(), viewProperty.CPUHand3Visible);
        Bindings.bindBidirectional(this.CPUHand4.visibleProperty(), viewProperty.CPUHand4Visible);
        Bindings.bindBidirectional(this.CPUHand5.visibleProperty(), viewProperty.CPUHand5Visible);

        Bindings.bindBidirectional(this.trumpDeckClosed.visibleProperty(), viewProperty.TrumpDeckClosedVisibility);
        Bindings.bindBidirectional(this.trumpView.visibleProperty(), viewProperty.TrumpCardVisibility);
        Bindings.bindBidirectional(this.PlayerActionBlockerPane1.visibleProperty(), viewProperty.PlayerCanAct);
        Bindings.bindBidirectional(this.PlayerActionBlockerPane2.visibleProperty(), viewProperty.PlayerCanAct);
        Bindings.bindBidirectional(this.PlayerActionBlockerPane3.visibleProperty(), viewProperty.PlayerCanAct);

        Bindings.bindBidirectional(this.SaveAndQuitButton.visibleProperty(), viewProperty.CanSave);
        Bindings.bindBidirectional(this.SaveButton.visibleProperty(), viewProperty.CanSave);
        Bindings.bindBidirectional(this.NewGameButton.textProperty(), viewProperty.NewGameButtonText);
        Bindings.bindBidirectional(this.SurrenderAndQuitButton.textProperty(), viewProperty.QuitButtonText);

        Bindings.bindBidirectional(this.PlayerTotalScoreLabel.textProperty(), viewProperty.PlayerTotalScoreText);
        Bindings.bindBidirectional(this.CPUTotalScoreLabel.textProperty(), viewProperty.CPUTotalScoreText);

        Bindings.bindBidirectional(this.PlayerHand1.imageProperty(), viewProperty.PlayerHand1);
        Bindings.bindBidirectional(this.PlayerHand2.imageProperty(), viewProperty.PlayerHand2);
        Bindings.bindBidirectional(this.PlayerHand3.imageProperty(), viewProperty.PlayerHand3);
        Bindings.bindBidirectional(this.PlayerHand4.imageProperty(), viewProperty.PlayerHand4);
        Bindings.bindBidirectional(this.PlayerHand5.imageProperty(), viewProperty.PlayerHand5);

        Bindings.bindBidirectional(this.PlayerHand1.visibleProperty(), viewProperty.PlayerHand1Visible);
        Bindings.bindBidirectional(this.PlayerHand2.visibleProperty(), viewProperty.PlayerHand2Visible);
        Bindings.bindBidirectional(this.PlayerHand3.visibleProperty(), viewProperty.PlayerHand3Visible);
        Bindings.bindBidirectional(this.PlayerHand4.visibleProperty(), viewProperty.PlayerHand4Visible);
        Bindings.bindBidirectional(this.PlayerHand5.visibleProperty(), viewProperty.PlayerHand5Visible);

        Bindings.bindBidirectional(this.PlayerPlayedView.imageProperty(), viewProperty.PlayerPlayedImage);
        Bindings.bindBidirectional(this.PlayerPlayedView.visibleProperty(), viewProperty.PlayerPlayedImageVisible);
        Bindings.bindBidirectional(this.CPUPlayedView.imageProperty(), viewProperty.CPUPlayedImage);
        Bindings.bindBidirectional(this.CPUPlayedView.visibleProperty(), viewProperty.CPUPlayedImageVisible);
    }

    public void PlayCard(MouseEvent mouseEvent) {
        
    }

    public void NewGame(ActionEvent actionEvent) {
        MenuModel.NewGame();
    }

    public void SaveGame(ActionEvent actionEvent) {
        MenuModel.SaveGame();
    }

    public void LoadGame(ActionEvent actionEvent) {
        MenuModel.LoadGame();
    }

    public void SaveAndQuit(ActionEvent actionEvent) {
        MenuModel.SaveGame();
        System.exit(0);
    }

    public void SurrenderAndQuit(ActionEvent actionEvent) {
        MenuModel.Surrender();
        System.exit(0);
    }

    public void deckClose(MouseEvent mouseEvent) {
        GameModel.CloseDeck();
    }

    public void trumpTake(MouseEvent mouseEvent) {
        GameModel.TakeTrump();
    }

    public void PlayerCallPair(ActionEvent actionEvent) {
        GameModel.CallPair();
    }

    public void Play20(MouseEvent mouseEvent) {
        GameModel.PlayPair();
    }
}
