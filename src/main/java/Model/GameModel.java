package Model;

import Controller.GameController;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class GameModel {
    HashSet<Card> fullDeck;
    public HashMap<Card, Image> cardsFaceMap;

    GameController parent;
    public Deck deck;
    public Player player;
    public CPUPlayer CPU;
    public PlayedCards playedCards;
    public PlayerEnum firstPlayer;

    public GameModel(GameController controller)
    {
        parent=controller;
        deck=new Deck();
        player=new Player();
        CPU=new CPUPlayer();
        playedCards=new PlayedCards();

        generateFullDeck();
        Bindings.bindBidirectional(this.deck.trumpColour, this.playedCards.trumpColour);
        Bindings.bindBidirectional(this.deck.trumpColour, this.CPU.TrumpColour);
        Bindings.bindBidirectional(this.deck.trumpColour, this.player.TrumpColour);
    }

    public void CloseDeck() {
        //TODO - fully implement deck closing
        if(deck.deck.size()>=3) {
            deck.isClosed = true; //TODO - deck.isClosed is also true when endgame hits, but deck-size is 0. That way, win calculations won't award 3 for not winning.
        }
    }

    public void CallPair() {

    }

    public void TakeTrump() {
        Card temp=new Card(deck.trumpColour.get(), CardFace.Underknave);
        if(player.Hand.contains(temp))
        {
            player.Hand.remove(temp);
            player.Hand.add(deck.trump);
            deck.trump=temp;
        }
    }

    public void PlayPair() {

    }

    public void Play(int target) {
        Card played=player.Play(target);
        if(played!=null)
        {
            playedCards.PlayCard(played, PlayerEnum.Player);
            var result=playedCards.Resolve(PlayerEnum.Player);
            if(result!=null)
            {
                if(result.scorer==PlayerEnum.Player) {
                    firstPlayer=PlayerEnum.Player;
                    if (player.ScoreCards(result)) {
                        processVictory(result);
                        return;
                    }
                    TrickOverDraw();
                }
                else
                {
                    firstPlayer=PlayerEnum.CPU;
                    if(CPU.ScoreCards(result))
                    {
                        processVictory(result);
                        return;
                    }
                    TrickOverDraw();
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
    }

    private void processVictory(TrickResult result) {
        //TODO - calculate total game score here
        //TODO - write the new values of the game's results out
        //TODO - reassign new first player and write it out
    }

    private void CPUPlay()
    {
        if(firstPlayer==PlayerEnum.Player) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        playedCards.PlayCard(CPU.pickCard(), PlayerEnum.CPU);
        UpdateView();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        var result=playedCards.Resolve(PlayerEnum.CPU);
        if(result!=null)
        {
            if(result.scorer==PlayerEnum.Player) {
                firstPlayer=PlayerEnum.Player;
                if (player.ScoreCards(result)) {
                    processVictory(result);
                    return;
                }
                TrickOverDraw();
            }
            else
            {
                firstPlayer=PlayerEnum.CPU;
                if(CPU.ScoreCards(result))
                {
                    processVictory(result);
                    return;
                }
                TrickOverDraw();
            }
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

    public void NewGame(PlayerEnum firstPlayer) {
        this.firstPlayer=firstPlayer;
        deck.Shuffle(cloneDeck());
        StartDraws();
    }
    private HashSet<Card> cloneDeck()
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
        //TODO - Calculate winning scores before these parts
        player=new Player();
        CPU=new CPUPlayer();
        deck=new Deck();
        playedCards=new PlayedCards();
    }
}
