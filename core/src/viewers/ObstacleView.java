package viewers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import obstacle.Obstacle;

public class ObstacleView extends Sprite {


    private Obstacle obstacle;
    public ObstacleView(String textureFileName, Obstacle obs ) {
        super( new Texture( textureFileName ) );
        this.obstacle = obs;

    }

    public void drawObstacle(SpriteBatch spriteBatch) {
        this.obstacle.updateObstacle();
        this.setPosition(obstacle.getXPosition() - obstacle.getWidth() / 2f,
                obstacle.getYPosition() - obstacle.getHeight() / 2f);
        this.draw( spriteBatch );
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }
}
