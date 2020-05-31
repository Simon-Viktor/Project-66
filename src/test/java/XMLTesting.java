import Model.GameModel;
import Model.PlayerEnum;
import XMLManagement.XMLManager;
import org.junit.Assert;
import org.junit.Test;

public class XMLTesting {

    @Test
    public void TestSavingLoading()
    {
        GameModel testGame=new GameModel(null);
        testGame.NewGame(PlayerEnum.CPU);
        XMLManager.SaveGame(testGame);
        GameModel testLoaded=new GameModel(null);
        testLoaded=XMLManager.LoadGame();
        testLoaded.GameLoad();
        testLoaded.SetParent(null);

        Assert.assertEquals(testGame, testLoaded);
    }
    @Test
    public void TestFirstPlayerManagement()
    {
        var testEnum=PlayerEnum.Player;
        XMLManager.SetFirstPlayer(testEnum);
        var loadedEnum=PlayerEnum.CPU;
        loadedEnum= XMLManager.LoadFirstPlayer();
        Assert.assertEquals(testEnum, loadedEnum);
    }
    @Test
    public void TestScoreManagement()
    {

    }
}
