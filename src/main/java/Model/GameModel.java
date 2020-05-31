package Model;

import Controller.GameController;
import XMLManagement.XMLManager;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameModel {
    private static final Logger LOGGER = Logger.getLogger( XMLManager.class.getName() );
    HashSet<Card> fullDeck;
    public HashMap<Card, Image> cardsFaceMap;

    GameController parent;
    public Deck deck;
    public Player player;
    public CPUPlayer CPU;
    public PlayedCards playedCards;
    public PlayerEnum firstPlayer;
    public Boolean canPlay;
    public Boolean isGoing;
    @Nullable
    public PlayerEnum winner;
    @Nullable
    public PlayerEnum closer;
    public Boolean playerCalledPair;

    public GameModel(GameController controller)
    {
        parent=controller;
        deck=new Deck();
        player=new Player();
        CPU=new CPUPlayer();
        playedCards=new PlayedCards();
        canPlay=false;
        isGoing=false;
        winner=null;
        closer=null;
        playerCalledPair=false;
        canPlay=true;

        generateFullDeck();
    }

    public void CloseDeck(PlayerEnum closer) {
        this.closer=closer;
        if(deck.deck.size()>=3) {
            deck.isClosed = true;
        }
    }

    public void CallPair() {
        playerCalledPair=!playerCalledPair;
        canPlay=!canPlay;
    }

    public Boolean CanCall()
    {
        return playedCards.CPUPlayed==null&&playedCards.playerPlayed==null;
    }

    public void TakeTrump() {
        if (deck.deck.size() >= 3) {
            Card temp = new Card(deck.trumpColour.get(), CardFace.Underknave);
            if (player.Hand.contains(temp)) {
                player.Hand.remove(temp);
                player.Hand.add(deck.trump);
                deck.trump = temp;
            }
        }
    }

    public void PlayPair(CardColour colour) {
        playerCalledPair=false;
        player.ColourRestriction=colour;
        player.PairRestriction=true;
        player.ExtraScore+=20;
        if(colour==deck.trumpColour.get()) player.ExtraScore+=20;
        if(player.Score()>=66)processVictory(PlayerEnum.Player);
        canPlay=true;
    }

    public void Play(int target) {
        if(deck.isClosed&&firstPlayer==PlayerEnum.CPU) player.ColourRestriction=playedCards.CPUPlayed.cardColour;
        Card played=player.Play(target);
        if(played!=null)
        {
            canPlay=false;
            playedCards.PlayCard(played, PlayerEnum.Player);
            var result=playedCards.Resolve(PlayerEnum.Player);
            if(result!=null)
            {
                if(result.scorer==PlayerEnum.Player) {
                    firstPlayer=PlayerEnum.Player;
                    if (player.ScoreCards(result)) {
                        processVictory(result.scorer);
                        return;
                    }
                    canPlay=true;
                    TrickOverDraw();
                    if(player.Hand.isEmpty()){
                        processOutOfCards(result.scorer);
                    }
                }
                else
                {
                    firstPlayer=PlayerEnum.CPU;
                    if(CPU.ScoreCards(result))
                    {
                        processVictory(result.scorer);
                        return;
                    }
                    TrickOverDraw();
                    if(player.Hand.isEmpty()){
                        processOutOfCards(result.scorer);
                    }
                    Task t=new Task() {
                        @Override
                        protected Object call() throws Exception {
                            CPUPlay();
                            return null;
                        }
                    };
                    Thread thread=new Thread(t);
                    thread.start();
                }
            }
            else
            {
                Task t=new Task() {
                    @Override
                    protected Object call() throws Exception {
                        CPUPlay();
                        return null;
                    }
                }  ;
                Thread thread=new Thread(t);
                thread.start();
            }
        }
        player.PairRestriction=false;
        player.ColourRestriction=null;
    }

    private void processOutOfCards(PlayerEnum last) {
        if(deck.deck.isEmpty())
        {
            processVictory(last);
        }
        else
        {
            if(closer==PlayerEnum.Player)
            {
                var score=XMLManager.GetScore();
                score.CPUScore+=3;
                XMLManager.SetScore(score);
                XMLManager.SetFirstPlayer(PlayerEnum.CPU);
            }
            else
            {
                var score=XMLManager.GetScore();
                score.playerScore+=3;
                XMLManager.SetScore(score);
                XMLManager.SetFirstPlayer(PlayerEnum.Player);
            }
            endGameRestrictions();
        }
        UpdateView();
    }

    private void processVictory(PlayerEnum result) {
        winner=result;
        int score=ProcessScoring(winner);
        Score totals= XMLManager.GetScore();
        if(result==PlayerEnum.Player)
        {
            totals.playerScore+=score;
        }
        else{
            totals.CPUScore+=score;
        }
        XMLManager.SetScore(totals);
        XMLManager.SetFirstPlayer(result);
        endGameRestrictions();
        UpdateView();
    }

    private void endGameRestrictions()
    {
        isGoing=false;
        canPlay=false;
        parent.gameUpdate();
    }
    public Integer ProcessScoring(PlayerEnum winner)
    {
        Integer ret=0;
        if(winner== PlayerEnum.Player)
        {
            ret+=1;
            if(CPU.Score()<33) ret+=1;
            if(CPU.Score()==0) ret+=1;
        }
        else
        {
            ret+=1;
            if(player.Score()<33) ret+=1;
            if(player.Score()==0) ret+=1;
        }
        return ret;
    }

    private void CPUPlay()
    {
        if(firstPlayer==PlayerEnum.Player) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, e.toString());
            }
        }
        if(deck.isClosed&&firstPlayer==PlayerEnum.Player) CPU.ColourRestriction=playedCards.playerPlayed.cardColour;
        playedCards.PlayCard(CPU.pickCard(), PlayerEnum.CPU);
        UpdateView();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }
        var result=playedCards.Resolve(PlayerEnum.CPU);
        if(result!=null)
        {
            if(result.scorer==PlayerEnum.Player) {
                firstPlayer=PlayerEnum.Player;
                if (player.ScoreCards(result)) {
                    processVictory(result.scorer);
                    return;
                }
                TrickOverDraw();
                if(player.Hand.isEmpty()){
                    processOutOfCards(result.scorer);
                }
                canPlay=true;
            }
            else
            {
                firstPlayer=PlayerEnum.CPU;
                if(CPU.ScoreCards(result))
                {
                    processVictory(result.scorer);
                    return;
                }
                TrickOverDraw();
                if(player.Hand.isEmpty()){
                    processOutOfCards(result.scorer);
                }
                Task t=new Task() {
                    @Override
                    protected Object call() throws Exception {
                        CPUPlay();
                        return null;
                    }
                }  ;
                Thread thread=new Thread(t);
                thread.start();
            }
        }
        else
        {
            canPlay=true;
        }
        UpdateView();

    }
    private void TrickOverDraw()
    {
        if(!deck.isClosed)
        {
            if(firstPlayer== PlayerEnum.Player)
            {
                player.Draw(deck.Draw());
                CPU.Draw(deck.Draw());
            }
            else
            {
                CPU.Draw(deck.Draw());
                player.Draw(deck.Draw());
            }
        }
    }
    private void UpdateView()
    {
        parent.gameUpdate();
    }

    private void generateFullDeck()
    {
        cardsFaceMap=new HashMap<Card, Image>();
        fullDeck=new HashSet<Card>();
        for (CardColour colour: CardColour.values()) {
            for (CardFace face : CardFace.values()) {
                Card card = new Card(colour, face);
                cardsFaceMap.put(card, new Image(getClass().getResource( "/Images/"+card.cardColour.fileSegment+card.cardFace.fileSegment+".jpg").toExternalForm()));
                fullDeck.add(card);
            }
        }
    }

    public void GameLoad()
    {
        generateFullDeck();
    }

    public void NewGame(PlayerEnum firstPlayer) {
        winner=null;
        isGoing=true;
        this.firstPlayer=firstPlayer;
        player=new Player();
        CPU=new CPUPlayer();
        deck=new Deck();
        playedCards=new PlayedCards();
        deck.Shuffle(cloneDeck());
        StartDraws();
        if(firstPlayer== PlayerEnum.Player) canPlay=true;
        playerCalledPair=false;
        this.closer=null;

        playedCards.trumpColour.unbind();
        CPU.TrumpColour.unbind();
        player.TrumpColour.unbind();
        playedCards.trumpColour.bind(deck.trumpColour);
        CPU.TrumpColour.bind(deck.trumpColour);
        player.TrumpColour.bind(deck.trumpColour);

        if(firstPlayer==PlayerEnum.CPU)
        {
            Task t=new Task() {
                @Override
                protected Object call() throws Exception {
                    CPUPlay();
                    return null;
                }
            }  ;
            Thread thread=new Thread(t);
            thread.start();
        }
    }

    public HashSet<Card> cloneDeck()
    {
        HashSet<Card> ret=new HashSet<Card>();
        for (Card card:(HashSet<Card>)(fullDeck.clone())) {
            ret.add(card);
        }
        return ret;
    }

    private void StartDraws()
    {
        if(firstPlayer==PlayerEnum.Player)
        {
            for (int i = 0; i < 5; i++) {
                player.Draw(deck.Draw());
                CPU.Draw(deck.Draw());
            }
        }
        else
        {
            for (int i = 0; i < 5; i++) {
                CPU.Draw(deck.Draw());
                player.Draw(deck.Draw());
            }
        }
        deck.HitTrump();
    }

    public void Surrender() {
        player=new Player();
        CPU=new CPUPlayer();
        deck=new Deck();
        playedCards=new PlayedCards();
    }

    public void SetParent(GameController parent)
    {
        this.parent=parent;
    }
}
