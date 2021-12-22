package helpers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;

/**
 * GameManager
 * @author Mehmet Hasat Serinkan
 * @date 21.12.2021
 */
public class GameManager {
    private static GameManager ourInstance = new GameManager();

    public boolean gameStartedFromMainMenu;
    public boolean isPaused = false;

    public int healthScore;
    public int score;

    public GameData gameData;
    private Json json = new Json();
    private FileHandle fileHandle = Gdx.files.local( "bin/GameData.json");

    public Music mainMenuMusic = Gdx.audio.newMusic( Gdx.files.internal( "Sounds/Main Menu Music.mp3" )) ;

    private GameManager(){
    }

    public static GameManager getInstance() {
        return ourInstance;
    }

    public void playMusic() {
        mainMenuMusic.play( );
        mainMenuMusic.setLooping( true );
    }

    public void stopMusic() {
        mainMenuMusic.stop();
    }

    public void saveData() {
        if(gameData != null ) {
            fileHandle.writeString(Base64Coder.encodeString( json.prettyPrint(gameData) ), false );
        }
    }

    public void loadData() {
        gameData = json.fromJson(GameData.class , Base64Coder.decodeString( fileHandle.readString() ) );
    }

    public void initializeGameData() {
        if(!fileHandle.exists() ) {
            gameData = new GameData();

            gameData.setHighScore( 0 );

            saveData();
        }
        else {
            loadData();
        }
    }

    public void checkForNewHighScore() {
        int oldHighScore = gameData.getHighScore();

        if( oldHighScore < score ) {
            gameData.setHighScore( score );
        }

        saveData();

    }

} //End
