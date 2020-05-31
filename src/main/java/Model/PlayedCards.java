package Model;

import XMLManagement.XMLManager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for resolving tricks and playing cards in a trick.
 */
public class PlayedCards {
    private static final Logger LOGGER = Logger.getLogger( XMLManager.class.getName() );
    public Card CPUPlayed;
    public Card playerPlayed;
    public ObjectProperty<CardColour> trumpColour;

    public PlayedCards()
    {
        CPUPlayed=null;
        playerPlayed=null;
        trumpColour=new SimpleObjectProperty<CardColour>();
    }

    /**
     * Plays a given card onto the play field.
     * @param card The Card played.
     * @param target The player playing the card.
     */
    public void PlayCard(Card card, PlayerEnum target)
    {
        switch (target)
        {
            case Player:
                playerPlayed=card;
                break;
            case CPU:
                CPUPlayed=card;
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    LOGGER.log(Level.SEVERE, e.toString());
                }
                break;
        }
    }

    /**
     * Resolves the current trick.
     * @param target The player who played the last card given the trick.
     * @return the result of the played cards. can be {@code null}.
     */
    public TrickResult Resolve(PlayerEnum target)
    {
        if(CPUPlayed!=null&&playerPlayed!=null)
        {
            switch (target)
            {
                case CPU:
                    return ResolveCPU();
                case Player:
                    return ResolvePlayer();
            }
        }
        return null;
    }
    private TrickResult ResolvePlayer() {
        PlayerEnum scorer=PlayerEnum.Player;
        if(playerPlayed.cardColour!=CPUPlayed.cardColour)
        {
            scorer= PlayerEnum.CPU;
            if(playerPlayed.cardColour==trumpColour.getValue()) scorer= PlayerEnum.Player;
        }
        else if(CPUPlayed.cardFace.value>playerPlayed.cardFace.value)
        {
            scorer= PlayerEnum.CPU;
        }
        TrickResult result=new TrickResult(playerPlayed, CPUPlayed, scorer);
        playerPlayed=null;
        CPUPlayed=null;
        return result;
    }

    private TrickResult ResolveCPU() {
        PlayerEnum scorer=PlayerEnum.CPU;
        if(playerPlayed.cardColour!=CPUPlayed.cardColour)
        {
            scorer= PlayerEnum.Player;
            if(CPUPlayed.cardColour==trumpColour.getValue()) scorer= PlayerEnum.CPU;
        }
        else if(playerPlayed.cardFace.value>CPUPlayed.cardFace.value)
        {
            scorer= PlayerEnum.Player;
        }
        TrickResult result=new TrickResult(playerPlayed, CPUPlayed, scorer);
        playerPlayed=null;
        CPUPlayed=null;
        return result;
    }

}
