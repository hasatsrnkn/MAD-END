package obstacle;

import com.badlogic.gdx.physics.box2d.World;

/**
 * Rock class
 * @author Mehmet Hasat Serinkan
 * @date 19.12.2021
 */
public class Rock extends Obstacle{

    public Rock( World world, float initialX, float initialY, float height, float width ) {
        super( world, initialX, initialY, height, width);
    }



}
