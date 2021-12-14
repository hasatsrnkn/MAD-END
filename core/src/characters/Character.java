package characters;

import com.badlogic.gdx.physics.box2d.*;
import helpers.GameInfo;


/**
 * A class for characters
 * @author Mehmet Hasat Serinkan, Mehmet Eren Balasar
 * @date 07.12.2021
 */

public class Character {
    
	private final World world;
	private Body body;
	private float xPosition;
	private float yPosition;
	private float height;
	private float width;
	private float rotationDeg;
	private boolean isMoving;

    
    
    public Character(World world, float initialX, float initialY) {

    	this.world = world;
    	this.setHeight(10 /* just an initialization */ );
    	this.setWidth(10 /* just an initialization */ );
    	
    	this.setPosition(initialX, initialY);

		  isMoving = false;
    	createBody();
    	updateCharacter();
    }
    
    
    public void createBody() {
		
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set( this.getXPosition() / GameInfo.PPM, this.getYPosition() / GameInfo.PPM );
        body = world.createBody( bodyDef );

        PolygonShape shape = new PolygonShape();
        shape.setAsBox( (this.getWidth() / 2f) / GameInfo.PPM,(this.getHeight() / 2f) / GameInfo.PPM);
        

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 4f; //Mass of the body
        fixtureDef.friction = 2f; //To not slide on surfaces
        fixtureDef.shape = shape;

        Fixture fixture = body.createFixture( fixtureDef );


        shape.dispose();
    
	}


	public void moveCharacter( float x, float y ) {
		
		body.setLinearVelocity( x , y );
		isMoving = true;
		updateCharacter();

	}
	
	public void updateCharacter() {
		
        this.setPosition( body.getPosition().x * GameInfo.PPM, body.getPosition().y * GameInfo.PPM);
    }
    
	public Body getBody() {
		
		return this.body;
	}

    public void setPosition(float x, float y) {
		
		this.xPosition = x;
		this.yPosition = y;
	}

    public float getXPosition() {
		
		return this.xPosition;
	}

    public float getYPosition() {
		
		return this.yPosition;
	}
    
    public void setHeight(float h) {
    	
    	this.height = h;
    }
    
    public void setWidth(float w) {
    	
    	this.width = w;
    }
    
    public float getHeight() {
    	
    	return this.height;
    }
    
    public float getWidth() {
    	
    	return this.width;
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


	public World getWorld() {

		return this.world;
	}

}
