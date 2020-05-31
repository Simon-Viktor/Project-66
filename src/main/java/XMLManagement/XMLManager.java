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

public final class XMLManager {
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

    public static Score GetScore()
    {
        if(!IsInitialized)
        {
            Initialize();
        }
        if(!Exists(scorePath))
        {
            SetScore(new Score(0, 0));
        }
        return (Score)xStream.fromXML(getXMLString(scorePath));
    }
    public static void SetScore(Score score)
    {
        if(!IsInitialized)
        {
            Initialize();
        }
        FileWriter fileWriter= null;
        try {
            fileWriter = new FileWriter(scorePath, false);
            xStream.toXML(score, fileWriter);
            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void SaveGame(GameModel gameModel)
    {
        if(!IsInitialized)
        {
            Initialize();
        }
        FileWriter fileWriter= null;
        try {
            fileWriter = new FileWriter(savePath, false);
            xStream.toXML(gameModel, fileWriter);
            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static GameModel LoadGame()
    {
        if(!IsInitialized)
        {
            Initialize();
        }
        GameModel ret=(GameModel)xStream.fromXML(getXMLString(savePath));
        return ret;
    }
    public static Boolean CanLoad()
    {
        if(Exists(savePath))
        {
            return true;
        }
        return false;
    }
    public static void SetFirstPlayer(PlayerEnum playerEnum)
    {
        if(!IsInitialized)
        {
            Initialize();
        }
        FileWriter fileWriter= null;
        try {
            fileWriter = new FileWriter(firstPlayerPath, false);
            xStream.toXML(playerEnum, fileWriter);
            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static PlayerEnum LoadFirstPlayer()
    {
        if(!IsInitialized)
        {
            Initialize();
        }
        if(!Exists(firstPlayerPath))
        {
            FileWriter fileWriter= null;
            try {
                fileWriter = new FileWriter(firstPlayerPath, false);
                xStream.toXML(PlayerEnum.Player, fileWriter);
                fileWriter.close();
            }
            catch (IOException e) {
                e.printStackTrace();
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
            e.printStackTrace();
        }
        return xml;
    }
}
