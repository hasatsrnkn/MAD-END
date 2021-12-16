/**
 * 
 */
package throwables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import helpers.GameInfo;

/**
 * @author Mehmet Eren Balasar
 *
 */

public class Bullet  {

	private final World world;
	private Body body;
	private float xPosition;
	private float yPosition;
	private float height;
	private float width;
	private boolean remove;
	private boolean shot;
	private float rotationDeg;

	public Bullet( World world,float initialX, float initialY, float rotationDeg) {
		this.world = world;
		this.setHeight(10 /* just an initialization */ );
		this.setWidth(10 /* just an initialization */ );
		this.setPosition(initialX, initialY);
		remove = false;
		shot = false;
		this.rotationDeg = rotationDeg;
		createBody();
		updateBullet();

	}


	public void createBody() {

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set( this.getXPosition() / GameInfo.PPM, this.getYPosition() / GameInfo.PPM );
		body = world.createBody( bodyDef );

		PolygonShape shape = new PolygonShape();
		shape.setAsBox( (this.getWidth() / 2f) / GameInfo.PPM,(this.getHeight() / 2f) / GameInfo.PPM);


		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.density = 2f; //Mass of the body
		fixtureDef.friction = 1f; //To not slide on surfaces
		fixtureDef.shape = shape;

		Fixture fixture = body.createFixture( fixtureDef );


		shape.dispose();

	}

	public void moveBullet( float x, float y ) {

		body.setLinearVelocity( new Vector2( x , y) );
		updateBullet();
		this.setShot( true );
	}

	public void updateBullet( ) {

		this.setPosition( body.getPosition().x * GameInfo.PPM, body.getPosition().y * GameInfo.PPM);

		if( getYPosition() > 2000 || getYPosition() < 0 || getXPosition() > 3000 || getXPosition() < 0  ) {
			this.setRemove( true );
		}

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

	public float getRotationDeg() {
		return rotationDeg;
	}

	public void setRotationDeg(float rotationDeg) {
		this.rotationDeg = rotationDeg;
	}

	public World getWorld() {

		return this.world;
	}

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}

	public void setShot(boolean shot) {
		this.shot = shot;
	}

	public boolean isShot() {
		return shot;
	}


} //end
