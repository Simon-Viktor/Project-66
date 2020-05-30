package Model;

import Controller.ViewProperty;
import XMLManagement.XMLManager;

public class MenuModel {
    GameModel gameModel;
    public MenuModel(GameModel game)
    {
        gameModel=game;
    }

    public void NewGame() {
        gameModel.NewGame(XMLManager.LoadFirstPlayer());
    }

    public void SaveGame() {
        XMLManager.SaveGame(gameModel);
    }

    public GameModel LoadGame() {
        gameModel=XMLManager.LoadGame();
        gameModel.GameLoad();
        return gameModel;
    }

    public void Surrender() {
        var score=gameModel.ProcessScoring(PlayerEnum.CPU);
        var total=XMLManager.GetScore();
        total.CPUScore+=score;
        XMLManager.SetScore(total);
        gameModel.Surrender();
    }
    public Score GetScore()
    {
        return XMLManager.GetScore();
    }
}
