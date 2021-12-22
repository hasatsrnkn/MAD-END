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
	private int randomDirection1;
	private int randomDirection2;
	private float speedMultiplier;
	
	public Crazy(World world, float initialX, float initialY, float height, float width) {
		
		super(world, initialX, initialY, height, width);
		
		random = new Random();
		
		xSpeed = GameInfo.CRAZY_MOVESPEED * (float)((random.nextInt(10) + 5.0)  / 5.0);
		ySpeed = GameInfo.CRAZY_MOVESPEED * (float)((random.nextInt(10) + 5.0) / 5.0);
		
		randomDirection1 = random.nextInt(2);
		
		if(randomDirection1 == 0) {
			ySpeed = ySpeed * -1;
		}
		
		if(randomDirection2 == 0) {
			xSpeed = xSpeed * -1;
		}
		
		if(GameInfo.DIFFICULT_TICK == 1) {
			
			speedMultiplier = 0.5f;
		}
		
		else if(GameInfo.DIFFICULT_TICK == 2) {
			
			speedMultiplier = 1f;
		}
		 
		else if(GameInfo.DIFFICULT_TICK == 3) {
			
			speedMultiplier = 1.4f;
		}
		

	}
	
	public void moveCrazy1() {
		
		this.moveCharacter(0, ySpeed);
		this.setRotationDeg((float)(MathUtils.radiansToDegrees *  Math.atan2(ySpeed, 0 )));
	}
	
	
	public void moveCrazy2() {
	
		this.moveCharacter(xSpeed * speedMultiplier , ySpeed * speedMultiplier);
		this.setRotationDeg((float)(MathUtils.radiansToDegrees *  Math.atan2(ySpeed, 0 )));
	}
	
	public void changeDirection(boolean x, boolean y) {
		
		if (x) {
			
			xSpeed = -1 * xSpeed;
		}
		
		if(y) {
			
			ySpeed = -1 * ySpeed;
		}
	}
	
	public void changeDirectionRandom() {
		
		int randomD = random.nextInt(4);
		
		if (randomD == 0) {
			
			changeDirection(true, false);
		}
		
		else if (randomD == 1) {
			
			changeDirection(false, true);
		}
		
		else if (randomD == 2) {
			
			changeDirection(true, true);
		}
		
//		else if (randomD == 3) {
//			
//			changeDirection(false, false);
//		}
		
	}

	
	
}
