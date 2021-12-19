package viewers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import obstacle.MapBoundaries;
import obstacle.Wall;

/**
 * WallView class
 * @author Mehmet Hasat Serinkan
 * @date 19.12.2021
 */
public class WallView extends ObstacleView {

    private Wall wall;
    public WallView( String textureFileName, Wall wall1 ) {
        super( textureFileName, wall1 );
        this.wall = wall1;

    }

    public void drawWall( SpriteBatch spriteBatch) {
    	
        super.drawObstacle( spriteBatch );
    }

}
