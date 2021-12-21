/**
 * 
 */
package characters;

import java.util.Random;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;

import helpers.GameInfo;

/**
 * @author Mehmet Eren Balasar
 *
 */
public class Crazy extends Enemy {

	private float xSpeed;
	private float ySpeed;
	private Random random;
	private int randomDirection;
	
	public Crazy(World world, float initialX, float initialY, float height, float width) {
		
		super(world, initialX, initialY, height, width);
		
		random = new Random();
		
		xSpeed = GameInfo.CRAZY_MOVESPEED * (float)((random.nextInt(10) + 5.0)  / 5.0);
		ySpeed = GameInfo.CRAZY_MOVESPEED * (float)((random.nextInt(10) + 5.0) / 5.0);
		
		randomDirection = random.nextInt(2);
		
		if(randomDirection == 0) {
			ySpeed = ySpeed * -1;
		}

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
