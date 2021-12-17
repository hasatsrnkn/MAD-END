package obstacle;

import com.badlogic.gdx.physics.box2d.World;
import java.util.ArrayList;

/**
 * Wall class
 * @author Mehmet Hasat Serinkan
 * @date 17.12.2021
 */
public class Wall extends Obstacle{

    public Wall(World world, float initialX, float initialY, float height, float width) {
        super( world, initialX, initialY, height, width);

    }

} //End of Wall
