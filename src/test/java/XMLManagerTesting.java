import Model.PlayerEnum;
import Model.Score;
import XMLManagement.XMLManager;
import org.junit.Assert;
import org.junit.Test;

public class XMLManagerTesting {
    @Test
    public void ScoreTesting()
    {
        Score testScore=new Score(0, 0);
        XMLManager.SetScore(testScore);
        var result=XMLManager.GetScore();
        Assert.assertEquals(testScore.CPUScore, result.CPUScore);
        Assert.assertEquals(testScore.playerScore, result.playerScore);
        testScore.playerScore+=11;
        XMLManager.SetScore(testScore);
        result=XMLManager.GetScore();
        Assert.assertEquals(testScore.CPUScore, result.CPUScore);
        Assert.assertEquals(testScore.playerScore, result.playerScore);
    }
    @Test
    public void FPTesting()
    {
        PlayerEnum test1=PlayerEnum.CPU;
        XMLManager.SetFirstPlayer(test1);
        var result=XMLManager.LoadFirstPlayer();
        Assert.assertEquals(test1, result);
        XMLManager.SetFirstPlayer(PlayerEnum.Player);
        result=XMLManager.LoadFirstPlayer();
        Assert.assertNotSame(test1, result);
        test1=PlayerEnum.Player;
        Assert.assertEquals(test1, result);
    }
}
