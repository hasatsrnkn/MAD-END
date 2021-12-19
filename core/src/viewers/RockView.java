package viewers;

import obstacle.Obstacle;
import obstacle.Rock;

/**
 * RockView Class
 * @author Mehmet Hasat Serinkan
 * @date 19.12.2021
 */
public class RockView extends ObstacleView{

    private Rock rock;
    public RockView( String textureFileName, Obstacle obs )  {
        super( textureFileName, obs );
    }


}
