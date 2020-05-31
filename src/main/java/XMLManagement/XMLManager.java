package XMLManagement;

import Model.GameModel;
import Model.PlayerEnum;
import Model.Score;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.*;


/**
 * Wrapper class for handling XML storage of information relevant to the game.
 */
public final class XMLManager {
    private static final Logger LOGGER = Logger.getLogger( XMLManager.class.getName() );
    private static String appPath=System.getProperty("user.dir")+ File.separator+"GameData";
    private static String scorePath=appPath+File.separator+"Score.xml";
    private static String savePath=appPath+File.separator+"Save.xml";
    private static String firstPlayerPath=appPath+File.separator+"First.xml";
    private static Boolean IsInitialized=false;


    private static XStream xStream;

    private XMLManager()
    {
    }

    private static void Initialize()
    {
        LOGGER.log(Level.INFO, "Initializing Manager");
        xStream=new XStream();
        if(!Exists(appPath))
        {
            new File(appPath).mkdirs();
        }
        xStream.omitField(GameModel.class, "parent");
        xStream.omitField(GameModel.class, "cardsFaceMap");
        xStream.omitField(GameModel.class, "fullDeck");
        IsInitialized=true;
    }

    /**
     * Returns the currently saved score
     * @return An object containing the currently saved game score
     */
    public static Score GetScore()
    {
        if(!IsInitialized)
        {
            Initialize();
        }
        LOGGER.log(Level.INFO, "Loading Score");
        if(!Exists(scorePath))
        {
            SetScore(new Score(0, 0));
        }
        return (Score)xStream.fromXML(getXMLString(scorePath));
    }

    /**
     * Saves the given score.
     * @param score The new current score to save.
     */
    public static void SetScore(Score score)
    {
        if(!IsInitialized)
        {
            Initialize();
        }
        LOGGER.log(Level.INFO, "Saving Score");
        FileWriter fileWriter= null;
        try {
            fileWriter = new FileWriter(scorePath, false);
            xStream.toXML(score, fileWriter);
            fileWriter.close();
        }
        catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }
    }

    /**
     * Saves the given game.
     * @param gameModel The current GameModel object representing the state of the game.
     */
    public static void SaveGame(GameModel gameModel)
    {
        if(!IsInitialized)
        {
            Initialize();
        }
        LOGGER.log(Level.INFO, "Saving Game");
        FileWriter fileWriter= null;
        try {
            fileWriter = new FileWriter(savePath, false);
            xStream.toXML(gameModel, fileWriter);
            fileWriter.close();
        }
        catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }
    }

    /**
     * Loads in and returns the currently saved game. Use {@code GameModel.GameLoad()} and {@code GameModel.SetParent()} to fix certain binding errors.
     * @return the loaded game.
     */
    public static GameModel LoadGame()
    {
        if(!IsInitialized)
        {
            Initialize();
        }
        LOGGER.log(Level.INFO, "Loading Game");
        GameModel ret=(GameModel)xStream.fromXML(getXMLString(savePath));
        return ret;
    }

    /**
     * Returns whether a saved file exists or not.
     * @return true if loading is possible
     */
    public static Boolean CanLoad()
    {
        if(Exists(savePath))
        {
            return true;
        }
        return false;
    }

    /**
     * Saves the current First Player.
     * @param playerEnum The Player enum representing First Player that will be saved.
     */
    public static void SetFirstPlayer(PlayerEnum playerEnum)
    {
        if(!IsInitialized)
        {
            Initialize();
        }

        LOGGER.log(Level.INFO, "Saving First Player");
        FileWriter fileWriter= null;
        try {
            fileWriter = new FileWriter(firstPlayerPath, false);
            xStream.toXML(playerEnum, fileWriter);
            fileWriter.close();
        }
        catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }
    }

    /**
     * Returns the saved First Player.
     * @return The first player saved.
     */
    public static PlayerEnum LoadFirstPlayer()
    {
        if(!IsInitialized)
        {
            Initialize();
        }
        LOGGER.log(Level.INFO, "Loading First Player");
        if(!Exists(firstPlayerPath))
        {
            FileWriter fileWriter= null;
            try {
                fileWriter = new FileWriter(firstPlayerPath, false);
                xStream.toXML(PlayerEnum.Player, fileWriter);
                fileWriter.close();
            }
            catch (IOException e) {
                LOGGER.log(Level.SEVERE, e.toString());
            }
        }
        return (PlayerEnum)xStream.fromXML(getXMLString(firstPlayerPath));
    }


    private static Boolean Exists(String path)
    {
        File f=new File(path);
        if(f.isFile())
        {
            return true;
        }
        return false;
    }
    private static String getXMLString(String path)
    {
        String xml="";
        try{
            xml= Files.readString(Path.of(path));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }
        return xml;
    }
}
