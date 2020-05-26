package Model;

import java.util.HashSet;

public class PlayedCards {
    Card CPUPlayed;
    Card playerPlayed;
    CardColour trumpColour;

    public PlayedCards()
    {
        CPUPlayed=null;
        playerPlayed=null;
    }

    public TrickResult PlayCard(Card card, PlayerEnum target)
    {
        switch (target)
        {
            case Player:
                playerPlayed=card;
                if(CPUPlayed!=null)
                break;
            case CPU:
                CPUPlayed=card;
                break;
        }
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
            if(playerPlayed.cardColour==trumpColour) scorer= PlayerEnum.Player;
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
            if(CPUPlayed.cardColour==trumpColour) scorer= PlayerEnum.CPU;
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
