package Model;

import Controller.ViewProperty;
import XMLManagement.XMLManager;

public class MenuModel {
    //TODO - Implement interactions with the menus here
    GameModel gameModel;
    XMLManager xml;
    public MenuModel(GameModel game)
    {
        gameModel=game;
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
        var score=gameModel.ProcessScoring(PlayerEnum.CPU);
        var total=xml.GetScore();
        total.CPUScore+=score;
        xml.SetScore(total);
        gameModel.Surrender();
    }
    public Score GetScore()
    {
        return xml.GetScore();
    }
}
