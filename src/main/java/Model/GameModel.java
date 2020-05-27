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
    }

    public void CloseDeck() {
        //TODO - fully implement deck closing
        deck.isClosed=true;
        UpdateView();
    }

    public static void CallPair() {

    }

    public static void TakeTrump() {

    }

    public static void PlayPair() {

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

    public void UpdateView() {
        handleHandsViews();
        handleDeckView();
    }

    private void handleHandsViews()
    {
        view.CPUHand1Visible.set(CPU.Hand.size()>0);
        view.CPUHand2Visible.set(CPU.Hand.size()>1);
        view.CPUHand3Visible.set(CPU.Hand.size()>2);
        view.CPUHand4Visible.set(CPU.Hand.size()>3);
        view.CPUHand5Visible.set(CPU.Hand.size()>4);

        view.PlayerHand5Visible.set(false);
        view.PlayerHand4Visible.set(false);
        view.PlayerHand3Visible.set(false);
        view.PlayerHand2Visible.set(false);
        view.PlayerHand1Visible.set(false);

        switch (player.Hand.size())
        {
            case 5:
                view.PlayerHand5Visible.set(true);
                view.PlayerHand5.setValue(cardsFaceMap.get(player.cardInHand(5)));
            case 4:
                view.PlayerHand4Visible.set(true);
                view.PlayerHand4.setValue(cardsFaceMap.get(player.cardInHand(4)));
            case 3:
                view.PlayerHand3Visible.set(true);
                view.PlayerHand3.setValue(cardsFaceMap.get(player.cardInHand(3)));
            case 2:
                view.PlayerHand2Visible.set(true);
                view.PlayerHand2.setValue(cardsFaceMap.get(player.cardInHand(2)));
            case 1:
                view.PlayerHand1Visible.set(true);
                view.PlayerHand1.setValue(cardsFaceMap.get(player.cardInHand(1)));
            default:
        }
    }
    private void handleDeckView()
    {
        if(deck.trump!=null)
        {
            if(!deck.isClosed)
            {
                view.DeckVisible.set(true);
                view.TrumpCardVisibility.set(true);
                view.TrumpCardImage.setValue(cardsFaceMap.get(deck.trump));
            }
            else
            {
                view.TrumpDeckClosedVisibility.set(true);
                view.TrumpCardVisibility.set(false);
            }
        }
        else
        {
            view.DeckVisible.set(false);
            view.TrumpCardVisibility.set(false);
        }
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
    private HashSet<Card> cloneDeck()
    {
        HashSet<Card> ret=new HashSet<Card>();
        for (Card card:(HashSet<Card>)(fullDeck.clone())) {
            ret.add(card);
        }
        return ret;
    }
}
