/**
 * 
 */
package characters;

import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Mehmet Eren Balasar
 *
 */
public abstract class Enemy extends Character{

	/**
	 * 
	 */
	public Enemy(World world, float initialX, float initialY, float height, float width) {
		
		super(world, initialX, initialY, height, width);
	}

}
