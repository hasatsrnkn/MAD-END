package characters;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.*;
import helpers.GameInfo;

import helpers.GameObject;
import throwables.Bullet;


/**
 * A class for characters
 * @author Mehmet Hasat Serinkan, Mehmet Eren Balasar
 * @date 07.12.2021
 */

public abstract class Character extends GameObject {
    
	private float rotationDeg;
	private boolean isMoving;
	private boolean footStepSoundPlaying;
	private ArrayList<Bullet> bullets;
	private ArrayList<Bullet> bulletsToRemove;
	private long lastTimeShot;
	private int shotTime;
	private Sound gunShotVoice;
	private Sound footStepVoice;

    public Character(World world, float initialX, float initialY, float height, float width) {

    	super(world, initialX, initialY, height, width);

		this.isMoving = false;
    	createBody(this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1));
    	updateCharacter();
    	this.bullets = new ArrayList<Bullet>();
		this.bulletsToRemove = new ArrayList<Bullet>();
		this.lastTimeShot = System.currentTimeMillis();
		shotTime = 0;
		gunShotVoice = Gdx.audio.newSound( Gdx.files.internal( "Sounds/GunShotEffect.wav"));
		footStepSoundPlaying = false;
    }
    
    
    public void createBody(String fixtureName) {
		
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set( this.getXPosition() / GameInfo.PPM, this.getYPosition() / GameInfo.PPM );
        body = world.createBody( bodyDef );

        PolygonShape shape = new PolygonShape();
        shape.setAsBox( (this.getWidth() / 2f) / GameInfo.PPM,(this.getHeight() / 2f) / GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 5000f; //Mass of the body
        fixtureDef.friction = 2f; //To not slide on surfaces
        fixtureDef.shape = shape;

        Fixture fixture = body.createFixture( fixtureDef );
		fixture.setUserData( fixtureName );
		fixtureDef.filter.categoryBits = GameInfo.CHARACTER;
		fixtureDef.filter.maskBits = GameInfo.OBSTACLE | GameInfo.BULLET;
		body.setFixedRotation(true);
        shape.dispose();
    
	}


	public void moveCharacter( float x, float y ) {

		body.setLinearVelocity( x , y );
		isMoving = true;
		updateCharacter();


	}
	
	public void shoot(float toShootX, float toShootY) {

		gunShotVoice.play();
		setLastTimeShot( System.currentTimeMillis() );
		this.shotTime = shotTime + 1;
		float bulletInitialX = (float) ( Math.sqrt(Math.pow(60, 2) + Math.pow(20,2)) * 
				Math.cos(Math.toRadians(this.getRotationDeg() -15)));
		
		float bulletInitialY = (float) ( Math.sqrt(Math.pow(60, 2) + Math.pow(20,2)) * 
				Math.sin(Math.toRadians(this.getRotationDeg() -15)));
		
		
		float hip = ( float ) Math.sqrt( Math.pow( (toShootX - this.getXPosition() ) , 2 ) + Math.pow(( toShootY - this.getYPosition()), 2 ) );
		
		bullets.add( new Bullet( this.getWorld(), this.getXPosition() + bulletInitialX, this.getYPosition() + bulletInitialY,
				10f, 10f, this.getRotationDeg() ) );
		
        for ( Bullet bullet: bullets) {
        	
        	if( !bullet.isShot() ) {
        		
                bullet.moveBullet((toShootX - this.getXPosition()) * GameInfo.BULLET_SPEED / hip, 
                		(toShootY - this.getYPosition()) * GameInfo.BULLET_SPEED / hip);
                }
            }


	}
	
	public void updateCharacter() {
		
		body.setTransform(body.getPosition().x, body.getPosition().y, (float)Math.toRadians(rotationDeg));
        this.setPosition( (body.getPosition().x) * GameInfo.PPM, (body.getPosition().y) * GameInfo.PPM);

    }
    
	public void removeBullets() {

		for (Bullet bullet : bullets) {
			if (bullet.isRemove()) {
				bulletsToRemove.add( bullet );
				bullet.destroyBullet();
			}
		}
		bullets.removeAll( bulletsToRemove );
		bulletsToRemove.clear();
	}

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
	

	public boolean isMoving() {
		
		return isMoving;
	}

	public void setMoving( boolean isMoving ) {
		
		this.isMoving = isMoving;

	}
	
    public float getRotationDeg() {
    	
    	return this.rotationDeg;
    }
    
    public void setRotationDeg(float degrees) {
    	
    	this.rotationDeg = degrees;
    }


	public long getLastTimeShot() {
		return lastTimeShot;
	}

	public void setLastTimeShot(long lastTimeShot) {
		this.lastTimeShot = lastTimeShot;
	}

	public int getShotTime() {
		return shotTime;
	}

	public void setShotTime(int shotTime) {
		this.shotTime = shotTime;
	}

	public void setFootStepVoice(Sound footStepVoice) {
		this.footStepVoice = footStepVoice;
	}

	public Sound getGunShotVoice() {
		return gunShotVoice;
	}

	public Sound getFootStepVoice() {
		return footStepVoice;
	}

	public void playFootStepSound( ) {

		if(this.isMoving()) {
			if( footStepSoundPlaying ) {
				footStepVoice.play();
				footStepVoice.loop();
				footStepSoundPlaying = false;
			}

		}
		else {
			footStepVoice.stop();
			setFootStepSoundPlaying( true );
		}
	}

	public boolean isFootStepSoundPlaying() {
		return footStepSoundPlaying;
	}

	public void setFootStepSoundPlaying(boolean footStepSoundPlaying) {
		this.footStepSoundPlaying = footStepSoundPlaying;
	}


} //End

