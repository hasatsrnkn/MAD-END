/**
 * 
 */
package characters;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import helpers.GameInfo;

/**
 * @author Mehmet Eren Balasar
 *
 */
public class Guardian extends Enemy {

	private Vector2 pointer;
	private float proximityRadius;
	private float distanceToTargetCh;
	private Character targetCharacter;
	
	
	public Guardian(World world, float initialX, float initialY, float height, float width, Character targetCh) {
		
		super(world, initialX, initialY, height, width);
		
		pointer = new Vector2();
		proximityRadius = 100f;
		targetCharacter = targetCh;
	}
	
	public void moveGuardianToTarget() {
		
//		distanceToTargetCh = (float) Math.sqrt(Math.pow(targetCharacter.getXPosition() - this.getXPosition(), 2) + 
//				Math.pow(targetCharacter.getYPosition() - this.getYPosition(), 2));
//		
//		while(distanceToTargetCh > proximityRadius) {
//			
//			if(this.getXPosition() > pointer.x) {
//				super.moveCharacter(-GameInfo.GUARDIAN_MOVESPEED, this.getBody().getLinearVelocity().y);
//			}
//			
//			if(this.getXPosition() < pointer.x) {
//				System.out.println("2");
//				super.moveCharacter(GameInfo.GUARDIAN_MOVESPEED, this.getBody().getLinearVelocity().y);
//			}
//			
//			if(this.getYPosition() > pointer.y) {
//				System.out.println("3");
//				super.moveCharacter(this.getBody().getLinearVelocity().x, -GameInfo.GUARDIAN_MOVESPEED);
//			}
//			
//			if(this.getYPosition() < pointer.y) {
//				System.out.println("4");
//				super.moveCharacter(this.getBody().getLinearVelocity().x, GameInfo.GUARDIAN_MOVESPEED);
//			}
//			
//			else {
//				
//				this.moveCharacter(0, 0);
//			}
//			
//			
//		}
//		
//		System.out.println("---");
//		
	}
	int i = 0;
	public void updateCharacter() {

		this.setRotationDeg(pointer.x, pointer.y);
		body.setTransform(body.getPosition().x, body.getPosition().y, 
				(float)Math.toRadians(this.getRotationDeg()));
		
        
//		if(i%900 == 0) {
//			System.out.println("" + body.getPosition().x);
//			this.shoot(pointer.x-20, pointer.y-20);
//		}
//		i++;
	}
	
	public Vector2 getPointer() {
		
		return pointer;
	}
	
	
	public void setPointer(float x, float y) {
		
		pointer.set(x, y);
	}
	

}
