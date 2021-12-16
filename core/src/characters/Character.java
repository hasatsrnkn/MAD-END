package characters;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import helpers.GameInfo;
import viewers.CharacterView;

import helpers.GameObject;


/**
 * A class for characters
 * @author Mehmet Hasat Serinkan, Mehmet Eren Balasar
 * @date 07.12.2021
 */

public abstract class Character extends GameObject {
    
	private float rotationDeg;
	private boolean isMoving;

   public Character(World world, float initialX, float initialY, float height, float width) {

    	super(world, initialX, initialY, height, width);

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

		body.setFixedRotation(true);
        shape.dispose();
    
	}


	public void moveCharacter( float x, float y ) {
		
		body.setLinearVelocity( x , y );

		isMoving = true;
		updateCharacter();

	}
	
	public void updateCharacter() {
		
		body.setTransform(body.getPosition().x, body.getPosition().y, (float)Math.toRadians(rotationDeg));
        this.setPosition( body.getPosition().x * GameInfo.PPM, body.getPosition().y * GameInfo.PPM);
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


}
