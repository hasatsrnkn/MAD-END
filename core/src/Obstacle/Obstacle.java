package Obstacle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import helpers.GameInfo;

/**
 * This class represents the obstacles in the game
 * @author Mehmet Berşan Özgür
 * @date 14.12.2021
 */
public class Obstacle {

    private final World world;
    private Body body;
    private float xPosition;
    private float yPosition;
    private float height;
    private float width;

    public Obstacle( World world, float initialX, float initialY )
    {
        this.world = world;
        this.setHeight(200/2 );
        this.setWidth(400/2);

        this.setPosition(initialX, initialY);


        createBody();
        updateObstacle();
    }


    public void createBody() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set( this.getXPosition() / GameInfo.PPM, this.getYPosition() / GameInfo.PPM );
        body = world.createBody( bodyDef );

        PolygonShape shape = new PolygonShape();
        shape.setAsBox( (this.getWidth() / 2f) / GameInfo.PPM,(this.getHeight() / 2f) / GameInfo.PPM);


        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1f; //Mass of the body
        fixtureDef.shape = shape;

        Fixture fixture = body.createFixture( fixtureDef );


        shape.dispose();

    }


    public void updateObstacle() {

        this.setPosition( body.getPosition().x , body.getPosition().y );
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

    public World getWorld() {

        return this.world;
    }
}
