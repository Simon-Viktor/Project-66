import Model.*;
import org.junit.Assert;
import org.junit.Test;

public class ModelTesting {


    @Test
    public void TestScoring() {
        //Setting up elements for the first set of tests
        PlayerEnum CPU=PlayerEnum.CPU;
        PlayerEnum Player=PlayerEnum.Player;
        Card playerCard1=new Card(CardColour.Autumn, CardFace.King);
        Card CPUCard1=new Card(CardColour.Spring, CardFace.Ace);
        Card playerCard2=new Card(CardColour.Autumn, CardFace.King);
        Card CPUCard2=new Card(CardColour.Autumn, CardFace.Ace);
        Card playerCard3=new Card(CardColour.Spring, CardFace.Ace);
        Card CPUCard3=new Card(CardColour.Spring, CardFace.Ten);

        PlayedCards test=new PlayedCards();
        test.PlayCard(playerCard1, Player);
        var result=test.Resolve(Player);
        Assert.assertNull(result);
        test.PlayCard(CPUCard1, CPU);
        result=test.Resolve(CPU);
        var eResult=new TrickResult(playerCard1, CPUCard1, Player);
        Assert.assertEquals(result.card1, eResult.card1);
        Assert.assertEquals(result.card2, eResult.card2);
        Assert.assertEquals(result.scorer, eResult.scorer);

        test.trumpColour.setValue(CardColour.Spring);
        test.PlayCard(playerCard1, Player);
        result=test.Resolve(Player);
        Assert.assertNull(result);
        test.PlayCard(CPUCard1, CPU);
        result=test.Resolve(CPU);
        eResult=new TrickResult(playerCard1, CPUCard1, CPU);
        Assert.assertEquals(result.card1, eResult.card1);
        Assert.assertEquals(result.card2, eResult.card2);
        Assert.assertEquals(result.scorer, eResult.scorer);

        test.trumpColour.setValue(CardColour.Autumn);
        test.PlayCard(playerCard1, Player);
        result=test.Resolve(Player);
        Assert.assertNull(result);
        test.PlayCard(CPUCard1, CPU);
        result=test.Resolve(CPU);
        eResult=new TrickResult(playerCard1, CPUCard1, Player);
        Assert.assertEquals(result.card1, eResult.card1);
        Assert.assertEquals(result.card2, eResult.card2);
        Assert.assertEquals(result.scorer, eResult.scorer);

        test.trumpColour.setValue(CardColour.Winter);
        test.PlayCard(playerCard2, Player);
        result=test.Resolve(Player);
        Assert.assertNull(result);
        test.PlayCard(CPUCard2, CPU);
        result=test.Resolve(CPU);
        eResult=new TrickResult(playerCard2, CPUCard2, CPU);
        Assert.assertEquals(result.card1, eResult.card1);
        Assert.assertEquals(result.card2, eResult.card2);
        Assert.assertEquals(result.scorer, eResult.scorer);

        test.PlayCard(playerCard3, Player);
        result=test.Resolve(Player);
        Assert.assertNull(result);
        test.PlayCard(CPUCard3, CPU);
        result=test.Resolve(CPU);
        eResult=new TrickResult(playerCard3, CPUCard3, Player);
        Assert.assertEquals(result.card1, eResult.card1);
        Assert.assertEquals(result.card2, eResult.card2);
        Assert.assertEquals(result.scorer, eResult.scorer);
    }

}
