package throwables;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import helpers.GameInfo;
import helpers.GameObject;

/**
 * Bullet Class
 * @author Mehmet Hasat Serinkan
 * @date 15.12.2021
 */

public class Bullet extends GameObject {

	private boolean remove;
	private boolean shot;
	private float rotationDeg;
	private Fixture fixture;

	public Bullet( World world, float initialX, float initialY, float height, float width, float rotationDeg) {
		super(world, initialX, initialY, height, width);
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

		fixture = body.createFixture( fixtureDef );


		shape.dispose();
	}

	public void moveBullet( float x, float y ) {

		body.setLinearVelocity( new Vector2( x , y) );
		updateBullet();
		this.setShot( true );

	}

	public void updateBullet( ) {
		
		body.setTransform(body.getPosition().x, body.getPosition().y, (float)Math.toRadians(rotationDeg));
		this.setPosition( body.getPosition().x * GameInfo.PPM, body.getPosition().y * GameInfo.PPM);

		if( getYPosition() > 2000 || getYPosition() < 0 || getXPosition() > 3000 || getXPosition() < 0  ) {
			this.setRemove( true );
			body.destroyFixture( fixture );
		}

	}

	public float getRotationDeg() {
		return rotationDeg;
	}

	public void setRotationDeg(float rotationDeg) {
		this.rotationDeg = rotationDeg;
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
