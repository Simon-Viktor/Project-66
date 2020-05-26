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
    }

    public static void NewGame() {

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