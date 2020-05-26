package Model;

import Controller.ViewProperty;

import java.util.HashSet;

public class GameModel {
    //TODO - Interactions with the game itself go here
    HashSet<Card> fullDeck;

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

        fullDeck=generateFullDeck();
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
        //TODO - Got a number from 1-5. Lower number by 1. play that element of hand.
    }

    private HashSet<Card> generateFullDeck()
    {
        HashSet<Card> ret=new HashSet<Card>();

        return ret;
    }
}
