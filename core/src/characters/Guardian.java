/**
 * 
 */
package characters;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import helpers.GameInfo;
import throwables.Bullet;

/**
 * @author Mehmet Eren Balasar
 *
 */
public class Guardian extends Enemy {

	private Vector2 pointer;
	private Character targetCharacter;

	private float distanceToTargetCh;
	public boolean firstArrived;
	private float proximityRadius;
	private float alarmedRadius;
	private int point = 1;
	private float delaySeconds = 2;
	private float shootDifference;
	
	public Guardian(World world, float initialX, float initialY, float height, float width, Character targetCh) {
		
		super(world, initialX, initialY, height, width);
		
		pointer = new Vector2( );
		targetCharacter = targetCh;
		
		if(GameInfo.DIFFICULT_TICK == 1) {
			
			proximityRadius = 300f;
			alarmedRadius = 600f;
			shootDifference = 120f;
		}
		
		else if(GameInfo.DIFFICULT_TICK == 2) {
			
			proximityRadius = 700f;
			alarmedRadius = 1000f;
			shootDifference = 60f;
		}
		 
		else if(GameInfo.DIFFICULT_TICK == 1) {
			
			proximityRadius = 1000f;
			alarmedRadius = 1500f;
			shootDifference = 10f;
		}
		
		firstArrived = false;
		
		
		distanceToTargetCh = (float) Math.sqrt(Math.pow(targetCharacter.getXPosition() - this.getXPosition(), 2) + 
		Math.pow(targetCharacter.getYPosition() - this.getYPosition(), 2));
	}
	
	public boolean moveGuardianTo(float targetX, float targetY) {

		boolean arrived = false;
		if( !arrived ) {

			if ( Math.abs( this.getXPosition() - targetX ) <= 3  && Math.abs( this.getYPosition() - targetY ) <= 3)  {

				super.moveCharacter(0, 0);
				arrived = true;

				if( !firstArrived ) {
					setFirstArrived( true );
				}
				else {
					setFirstArrived( false );
				}
			}
			else {
				if (this.getXPosition() > targetX) {

					super.moveCharacter(-GameInfo.GUARDIAN_MOVESPEED, this.getBody().getLinearVelocity().y);
					arrived = false;
				}
				if (this.getXPosition() < targetX) {

					super.moveCharacter(GameInfo.GUARDIAN_MOVESPEED, this.getBody().getLinearVelocity().y);
					arrived = false;
				}
				if (this.getYPosition() > targetY) {

					super.moveCharacter(this.getBody().getLinearVelocity().x, -GameInfo.GUARDIAN_MOVESPEED);
					arrived = false;
				}

				if (this.getYPosition() < targetY) {

					super.moveCharacter(this.getBody().getLinearVelocity().x, GameInfo.GUARDIAN_MOVESPEED);
					arrived = false;
				}
			}

		}
		return arrived;
	}

	boolean shot = false;
	public Bullet moveAndShoot(float firstX, float firstY, float shootPointX, float shootPointY) {

        Bullet newBullet = null;
		if(!this.isDead()) {
			
			distanceToTargetCh = (float) Math.sqrt(Math.pow(targetCharacter.getXPosition() - this.getXPosition(), 2) + 
			Math.pow(targetCharacter.getYPosition() - this.getYPosition(), 2));
			

			if(distanceToTargetCh < proximityRadius) {
				
				proximityRadius = alarmedRadius;
				
		    	this.setPointer(targetCharacter.getXPosition(), targetCharacter.getYPosition());
		    	

					if( !firstArrived ) {
						
						moveGuardianTo( shootPointX, shootPointY);
						
						if(!shot) {
							
							newBullet = shoot(pointer.x-shootDifference, pointer.y-shootDifference);
							shot = true;
						}

					}
					
					
					else {
						shot = false;
						moveGuardianTo(firstX, firstY);
					}
		    	

			}	
			
			else {
				moveCharacter(0,0);
			}
		}
		
		return newBullet;
	}
	
	

	public void updateCharacter() {
    	
        if(this.isDead()) {
            this.kill();
        }
		
        else if(!this.isDead()) {
		
			this.setPosition( (body.getPosition().x) * GameInfo.PPM, (body.getPosition().y) * GameInfo.PPM);
			this.setRotationDeg(pointer.x, pointer.y);
			
			body.setTransform(body.getPosition().x, body.getPosition().y, 
					(float)Math.toRadians(this.getRotationDeg()));
			
		
		}
	}

	public boolean isFirstArrived() {
		return firstArrived;
	}

	public void setFirstArrived( boolean firstArrived) {
		this.firstArrived = firstArrived;
	}


	public void fixedMovement(float firstX, float firstY, float shootPointX, float shootPointY, float delta) {
		
		if(!this.isDead()) {
	
			distanceToTargetCh = (float) Math.sqrt(Math.pow(targetCharacter.getXPosition() - this.getXPosition(), 2) + 
			Math.pow(targetCharacter.getYPosition() - this.getYPosition(), 2));
//			
//			System.out.println( this.getXPosition() + " " + distanceToTargetCh);
			
			if(distanceToTargetCh < proximityRadius) {
			
		    	this.setPointer(targetCharacter.getXPosition(), targetCharacter.getYPosition());
		    	
					if(point == 1) {
						
						if(moveGuardianTo(shootPointX, shootPointY)) {
							
							Timer.schedule(new Task(){
							    @Override
							    public void run() {
									shoot(pointer.x-20, pointer.y-20);
									point = 2;
							    }
							}, delaySeconds);

						}
	
					}
					
					else if (point == 2) {

							System.out.println( this.getXPosition() + "moving to firstpos");
							moveGuardianTo(firstX, firstY);
							point = 1;
						

					}
				
				
			}	
			
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

	public Character getTargetCharacter() {
		return targetCharacter;
	}


}
