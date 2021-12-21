package helpers;

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

    private GameManager(){
    }

    public static GameManager getInstance() {
        return ourInstance;
    }





} //End
