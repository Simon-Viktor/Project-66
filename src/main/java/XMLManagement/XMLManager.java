package XMLManagement;

import Model.FirstPlayer;
import Model.Score;
import com.thoughtworks.xstream.XStream;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class XMLManager {
    private static String appPath=System.getProperty("user.dir")+ File.separator+"GameData";
    private static String scorePath=appPath+File.separator+"Score.xml";
    private static String savePath=appPath+File.separator+"Save.xml";
    private static String firstPlayerPath=appPath+File.separator+"First.xml";

    private XStream xStream;

    public XMLManager()
    {
        xStream=new XStream();
        if(!Exists(appPath))
        {
            new File(appPath).mkdirs();
        }
        //xStream.omitField(PlayField.class, "menuCommandHandler"); <-Useful for later when omitting things becomes necessary
    }


    public Score GetScore()
    {
        if(!Exists(scorePath))
        {
            SetScore(new Score(0, 0));
        }
        return (Score)xStream.fromXML(getXMLString(scorePath));
    }
    public void SetScore(Score score)
    {
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

    //TODO - Implement these once PlayField, or it's current equivalent is done.
    /*
    public void SaveGame(PlayField playField)
    {
        FileWriter fileWriter= null;
        try {
            fileWriter = new FileWriter(savePath, false);
            xStream.toXML(playField, fileWriter);
            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Nullable
    public PlayField LoadGame()
    {
        if(!Exists(savePath)) return null;
        PlayField ret=(PlayField)xStream.fromXML(getXMLString(savePath));
        ret.LoadAdjustment();
        return ret;
    }
*/
    public void SetFirstPlayer(FirstPlayer firstPlayer)
    {
        FileWriter fileWriter= null;
        try {
            fileWriter = new FileWriter(firstPlayerPath, false);
            xStream.toXML(firstPlayer, fileWriter);
            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public FirstPlayer LoadFirstPlayer()
    {
        if(!Exists(firstPlayerPath))
        {
            FileWriter fileWriter= null;
            try {
                fileWriter = new FileWriter(firstPlayerPath, false);
                xStream.toXML(FirstPlayer.Player, fileWriter);
                fileWriter.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return (FirstPlayer)xStream.fromXML(getXMLString(firstPlayerPath));
    }


    private Boolean Exists(String path)
    {
        File f=new File(path);
        if(f.isFile())
        {
            return true;
        }
        return false;
    }
    private String getXMLString(String path)
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
