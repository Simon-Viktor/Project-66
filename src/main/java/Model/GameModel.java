package Model;

import Controller.ViewProperty;
import javafx.beans.binding.Bindings;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.HashSet;

public class GameModel {
    HashSet<Card> fullDeck;
    public HashMap<Card, Image> cardsFaceMap;

    ViewProperty view;
    public Deck deck;
    public Player player;
    public CPUPlayer CPU;
    public PlayedCards playedCards;
    public PlayerEnum firstPlayer;

    public GameModel(ViewProperty view)
    {
        this.view=view;
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
        }
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
        //TODO - Calculate winning before these parts
        player=new Player();
        CPU=new CPUPlayer();
        deck=new Deck();
        playedCards=new PlayedCards();
    }
}
