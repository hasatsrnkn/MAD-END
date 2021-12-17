package viewers;

import Obstacle.Obstacle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Obstacle View Class
 * @author Mehmet Berşan Özgür
 * @date 17.12.2021
 */
public class ObstacleView extends Sprite {

    private String textureFileName;
    private Obstacle obstacle;
    public static float ObstacleWidth;
    public static float ObstacleHeight;
    public static float ObstaclePositionX;
    public static float ObstaclePositionY;

    public ObstacleView(String textureFileName, Obstacle obstacle, float x, float y){
        super(new Texture(textureFileName));
        this.setPosition(x , y);
        this.obstacle = obstacle;
        ObstacleWidth = this.getWidth();
        ObstacleHeight = this.getHeight();
        ObstaclePositionX = this.getX();
        ObstaclePositionY = this.getY();
    }

    public void drawObstacle(SpriteBatch batch, float x, float y){
            setPosition(x + getWidth() / 2, y );
            obstacle.createBody();
            this.draw(batch);
    }
}
