package Model;

import Controller.ViewProperty;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.HashSet;

public class GameModel {
    //TODO - Interactions with the game itself go here
    HashSet<Card> fullDeck;
    HashMap<Card, Image> cardsFaceMap;

    ViewProperty view;
    Deck deck;
    Player player;
    CPUPlayer CPU;
    PlayedCards playedCards;

    public GameModel(ViewProperty view)
    {
        this.view=view;
        deck=new Deck();
        player=new Player();
        CPU=new CPUPlayer();
        playedCards=new PlayedCards();

        generateFullDeck();
    }

    public static void CloseDeck() {

    }

    public static void CallPair() {

    }

    public static void TakeTrump() {

    }

    public static void PlayPair() {

    }

    public void Play(int target) {
        //TODO - Got a number from 1-5. Lower number by 1. play that element of hand. Play that element onto the played cards.
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
}
