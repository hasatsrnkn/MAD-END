/**
 * 
 */
package characters;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Mehmet Eren Balasar
 *
 */
public class Crazy extends Enemy {

	private float xSpeed;
	private float ySpeed;
	
	public Crazy(World world, float initialX, float initialY, float height, float width) {
		
		super(world, initialX, initialY, height, width);
		xSpeed = 3f;
		ySpeed = 3f;
	}
	
	public void moveCrazy1() {
		
		this.moveCharacter(0, ySpeed);
		
		this.setRotationDeg((float)(MathUtils.radiansToDegrees *  Math.atan2(ySpeed, 0 )));
	}
	
	
	public void changeDirection(boolean x, boolean y) {
		
		if (x) {
			
			xSpeed = -1 * xSpeed;
		}
		
		else if(y) {
			
			ySpeed = -1 * ySpeed;
		}
	}

	
	
}
