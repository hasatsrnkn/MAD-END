package viewers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import obstacle.MapBoundaries;
import obstacle.Wall;

import java.util.ArrayList;

public class WallView extends ObstacleView {

    private Wall wall;
    private MapBoundaries mapBoundaries;
    public WallView( String textureFileName, Wall wall1 ) {
        super( textureFileName, wall1 );
        this.wall = wall1;

    }

    public void drawWallView( SpriteBatch spriteBatch) {
        super.drawObstacle( spriteBatch );
    }

}
