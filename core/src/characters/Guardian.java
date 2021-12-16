/**
 * 
 */
package characters;

import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Mehmet Eren Balasar
 *
 */
public class Guardian extends Enemy {

	/**
	 * 
	 */
	public Guardian(World world, float initialX, float initialY, float height, float width) {
		
		super(world, initialX, initialY, height, width);
	}

}
