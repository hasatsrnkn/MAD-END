package characters;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
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
	private long lastTimeShot;
	private int shotTime;
	private Sound gunShotVoice;
	private Sound footStepVoice;
	private int heathPoint;
	private boolean isDead;
	private boolean killedExecuted;
	private Fixture fixture;
	private FixtureDef fixtureDef;

    public Character(World world, float initialX, float initialY, float height, float width) {

    	super(world, initialX, initialY, height, width);

		this.isMoving = false;
		this.isDead = false;
		this.heathPoint = GameInfo.CHARACTER_HEALTH;
		this.killedExecuted = false;

    	createBody();

    	//createBody(this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1));
    	this.bullets = new ArrayList<Bullet>();
		this.lastTimeShot = System.currentTimeMillis();
		shotTime = 0;
		gunShotVoice = Gdx.audio.newSound( Gdx.files.internal( "Sounds/GunShotEffect.wav"));
		footStepSoundPlaying = false;

    }
    
    
    public void createBody() {
		
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set( this.getXPosition() / GameInfo.PPM, this.getYPosition() / GameInfo.PPM );
        body = world.createBody( bodyDef );

        PolygonShape shape = new PolygonShape();
        shape.setAsBox( (this.getWidth() / 2f) / GameInfo.PPM,(this.getHeight() / 2f) / GameInfo.PPM);

		fixtureDef = new FixtureDef();
        fixtureDef.density = 5000f; //Mass of the body
        fixtureDef.friction = 2f; //To not slide on surfaces
        fixtureDef.shape = shape;

        fixture = body.createFixture( fixtureDef );
		fixture.setUserData( "Character" );
		fixtureDef.filter.categoryBits = GameInfo.CHARACTER;
		fixtureDef.filter.maskBits = GameInfo.OBSTACLE | GameInfo.BULLET | GameInfo.CHARACTER;
		body.setFixedRotation(true);
        shape.dispose();
	}


	public void moveCharacter( float x, float y ) {

		body.setLinearVelocity( x , y );
		isMoving = true;
		updateCharacter();


	}
	

	public Bullet shoot(float toShootX, float toShootY) {


		gunShotVoice.play( GameInfo.GUNSHOT_VOLUME );
		setLastTimeShot( System.currentTimeMillis() );
		this.shotTime = shotTime + 1;
		float bulletInitialX = (float) ( Math.sqrt(Math.pow(60, 2) + Math.pow(20,2)) * 
				Math.cos(Math.toRadians(this.getRotationDeg() -15)));
		
		float bulletInitialY = (float) ( Math.sqrt(Math.pow(60, 2) + Math.pow(20,2)) * 
				Math.sin(Math.toRadians(this.getRotationDeg() -15)));
		
		
		float hip = ( float ) Math.sqrt( Math.pow( (toShootX - this.getXPosition() ) , 2 ) + Math.pow(( toShootY - this.getYPosition()), 2 ) );

		Bullet newBullet = new Bullet( this.getWorld(), this.getXPosition() + bulletInitialX, this.getYPosition() + bulletInitialY,
				10f, 10f, this.getRotationDeg() );
		bullets.add( newBullet );
		
        for ( Bullet bullet: bullets) {
        	
        	if( !bullet.isShot() ) {
        		
                bullet.moveBullet((toShootX - this.getXPosition()) * GameInfo.BULLET_SPEED / hip, 
                		(toShootY - this.getYPosition()) * GameInfo.BULLET_SPEED / hip);
                }
            }


		return newBullet;

	}
	
	public void updateCharacter() {
		
		body.setTransform(body.getPosition().x, body.getPosition().y, (float)Math.toRadians(rotationDeg));
        this.setPosition( (body.getPosition().x) * GameInfo.PPM, (body.getPosition().y) * GameInfo.PPM);

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
    
    public void setRotationDeg(float x, float y) {
    	
		this.setRotationDeg((float)(MathUtils.radiansToDegrees *  Math.atan2 ( y - this.getYPosition()  , 
				x - this.getXPosition()   )));
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
				footStepVoice.play( GameInfo.FOOTSTEP_VOLUME );
				footStepVoice.loop( GameInfo.FOOTSTEP_VOLUME );
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


	public int getHeathPoint() {
		return heathPoint;
	}

	public void setHeathPoint( int heathPoint) {
		this.heathPoint = heathPoint;
	}

	public void reduceHeathPoint() {
		heathPoint = heathPoint - 1;
		if( heathPoint == 0) {
			this.setDead( true );
			this.getFootStepVoice().stop();
		}
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead( boolean dead ) {
		isDead = dead;
	}

	public void killed() {

		if( !killedExecuted ) {
			this.getBody().destroyFixture( this.getFixture() );
			this.getWorld().destroyBody( this.getBody() );
			killedExecuted = true;
		}


	}

	public void setFixture(Fixture fixture) {
		this.fixture = fixture;
	}

	public Fixture getFixture() {
		return fixture;
	}

	public FixtureDef getFixtureDef() {
		return fixtureDef;
	}

	public void setFixtureDef(FixtureDef fixtureDef) {
		this.fixtureDef = fixtureDef;
	}


} //End

