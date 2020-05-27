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

    public void NewGame() {
        gameModel.NewGame(xml.LoadFirstPlayer());
    }

    public void SaveGame() {
        //TODO
    }

    public void LoadGame() {
        //TODO
    }

    public void Surrender() {
        //TODO
    }
    public void SetScore()
    {
        Score score=xml.GetScore();
        viewProperty.PlayerTotalScoreText.setValue(score.playerScore.toString());
        viewProperty.CPUTotalScoreText.setValue(score.CPUScore.toString());
    }
}
