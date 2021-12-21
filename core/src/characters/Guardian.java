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
	private Character targetCharacter;
	private float distanceToTargetCh;
	float proximityRadius;
	int point = 1;
	
	
	public Guardian(World world, float initialX, float initialY, float height, float width, Character targetCh) {
		
		super(world, initialX, initialY, height, width);
		
		pointer = new Vector2( );
		targetCharacter = targetCh;
		proximityRadius = 800f;
		
		distanceToTargetCh = (float) Math.sqrt(Math.pow(targetCharacter.getXPosition() - this.getXPosition(), 2) + 
		Math.pow(targetCharacter.getYPosition() - this.getYPosition(), 2));
	}
	
	public boolean moveGuardianTo(float targetX, float targetY) {
		
//		distanceToTargetCh = (float) Math.sqrt(Math.pow(targetCharacter.getXPosition() - this.getXPosition(), 2) + 
//				Math.pow(targetCharacter.getYPosition() - this.getYPosition(), 2));
//		
//		System.out.println(distanceToTargetCh);
		boolean arrived = false;

		if((this.getXPosition() - targetX <= 3 && this.getYPosition() - targetY <= 3) && arrived == false) {
			
			System.out.println("called");
			super.moveCharacter(0,0);
			arrived = true;
		}
		
		if(!arrived) {
		
			if(this.getXPosition() > targetX) {
				
				super.moveCharacter(-GameInfo.GUARDIAN_MOVESPEED, this.getBody().getLinearVelocity().y);
				arrived = false;
			}
			
			if(this.getXPosition() < targetX) {

				super.moveCharacter(GameInfo.GUARDIAN_MOVESPEED, this.getBody().getLinearVelocity().y);
				arrived = false;
			}
			
			if(this.getYPosition() > targetY) {

				super.moveCharacter(this.getBody().getLinearVelocity().x, -GameInfo.GUARDIAN_MOVESPEED);
				arrived = false;
			}
			
			if(this.getYPosition() < targetY) {

				super.moveCharacter(this.getBody().getLinearVelocity().x, GameInfo.GUARDIAN_MOVESPEED);
				arrived = false;
			}		
			
		}	
		return arrived;
	}
	
	
	int i = 0;
	public void updateCharacter() {
		
		if(!this.isDead()) {
		
			this.setPosition( (body.getPosition().x) * GameInfo.PPM, (body.getPosition().y) * GameInfo.PPM);
			
			if(this.getRotationDeg() > this.calculateRotationDeg(pointer.x, pointer.y))
			this.setRotationDeg(pointer.x, pointer.y);
			
			
			body.setTransform(body.getPosition().x, body.getPosition().y, 
					(float)Math.toRadians(this.getRotationDeg()));
			
	        
	//		if(i%900 == 0) {
	//
	//			this.shoot(pointer.x-20, pointer.y-20);
	//		}
	//		i++;
		
		}
	}
	
	
	public void fixedMovement(float firstX, float firstY, float shootPointX, float shootPointY, float delta) {
		
		if(!this.isDead()) {
	
			distanceToTargetCh = (float) Math.sqrt(Math.pow(targetCharacter.getXPosition() - this.getXPosition(), 2) + 
			Math.pow(targetCharacter.getYPosition() - this.getYPosition(), 2));
//			
//			System.out.println( this.getXPosition() + " " + distanceToTargetCh);
			
		    	this.setPointer(targetCharacter.getXPosition(), targetCharacter.getYPosition());
				
				if(i % 180 == 0) {
					
					
					if(point == 1) {
						
						boolean boo = moveGuardianTo(shootPointX, shootPointY);
						System.out.println( boo);
						if(boo) {
							
							System.out.println( this.getXPosition() + "arrrrrrrived");
							this.shoot(pointer.x-20, pointer.y-20);
							point = 2;
						}
	
					}
					
					else if (point == 2) {
						
						if(moveGuardianTo(firstX, firstY)) {
							
							System.out.println( this.getXPosition() + "moving to firstpos");
							moveGuardianTo(firstX, firstY);
							point = 1;
						}

					}
				}
				
	
			i++;
			
		}	
	}
	
	
	public void fixedMovement2(float firstX, float firstY, float shootPointX, float shootPointY, float delta) {
		
		if (point == 1) {
			
			moveGuardianTo(shootPointX, shootPointY);
			point = 2;
			
		}
		
		else if (point == 2) {
			
			moveGuardianTo(firstX, firstY);
			point = 1;
		}
	}
	
	public Vector2 getPointer() {
		
		return pointer;
	}
	
	
	public void setPointer(float x, float y) {
		
		pointer.set(x, y);
	}
	

}
