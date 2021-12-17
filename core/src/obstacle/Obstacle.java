package obstacle;

import com.badlogic.gdx.physics.box2d.*;
import helpers.GameInfo;
import helpers.GameObject;

/**
 * Obstacle class
 * @author Mehmet Hasat Serinkan
 * @date 16.12.2021
 */
public class Obstacle extends GameObject {

    public Obstacle( World world, float initialX, float initialY, float height, float width ) {
        super( world, initialX, initialY, height, width);
        createBody();
        updateObstacle();
    }

    public void createBody() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set( (this.getXPosition() ) / GameInfo.PPM, this.getYPosition() / GameInfo.PPM );
        body = world.createBody( bodyDef );

        PolygonShape shape = new PolygonShape();
        shape.setAsBox( (this.getWidth() / 2f) / GameInfo.PPM,(this.getHeight() / 2f) / GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 4f; //Mass of the body
        fixtureDef.friction = 2f; //To not slide on surfaces
        fixtureDef.shape = shape;
        fixtureDef.restitution = 0f;

        Fixture fixture = body.createFixture( fixtureDef );


        shape.dispose();

    }

    public void updateObstacle() {
        this.setPosition( body.getPosition().x * GameInfo.PPM, body.getPosition().y * GameInfo.PPM);
    }

}
