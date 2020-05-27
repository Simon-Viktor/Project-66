package Model;

import Controller.ViewProperty;
import XMLManagement.XMLManager;

public class MenuModel {
    //TODO - Implement interactions with the menus here
    GameModel gameModel;
    ViewProperty viewProperty;
    XMLManager xml;
    public MenuModel(GameModel game, ViewProperty view)
    {
        gameModel=game;
        viewProperty=view;
        xml=new XMLManager();
        gameModel.firstPlayer=xml.LoadFirstPlayer();
    }

    public void NewGame() {
        viewProperty.NewGameButtonText.setValue("Surrender and New Game");
        viewProperty.CanSave.set(true);
        viewProperty.QuitButtonText.setValue("Surrender and Quit");
        gameModel.NewGame(xml.LoadFirstPlayer());
    }

    public static void SaveGame() {

    }

    public static void LoadGame() {

    }

    public static void Surrender() {

    }
    public void SetScore()
    {
        Score score=xml.GetScore();
        viewProperty.PlayerTotalScoreText.setValue(score.playerScore.toString());
        viewProperty.CPUTotalScoreText.setValue(score.CPUScore.toString());
    }
}
