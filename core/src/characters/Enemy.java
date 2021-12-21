/**
 * 
 */
package characters;

import com.badlogic.gdx.physics.box2d.World;
import helpers.GameInfo;

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

		this.getFixtureDef().filter.categoryBits = GameInfo.ENEMY;
		this.getFixtureDef().filter.maskBits = GameInfo.BULLET | GameInfo.PLAYER | GameInfo.OBSTACLE;
		this.getFixture().setUserData( "Enemy" );
	}

}
