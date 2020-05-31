package Model;

import XMLManagement.XMLManager;

/**
 * Class focused on handling interactions with the menu bar.
 */
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
        var score=gameModel.Surrender();
        var total=XMLManager.GetScore();
        total.CPUScore+=score;
        XMLManager.SetScore(total);
    }
    public Score GetScore()
    {
        return XMLManager.GetScore();
    }
}
