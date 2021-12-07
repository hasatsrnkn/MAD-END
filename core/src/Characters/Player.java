package Characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import helpers.GameInfo;

/**
 * A class for player
 * @author Mehmet Hasat Serinkan
 * @date 07.12.2021
 */
public class Player extends Sprite {
    private World world;
    private Body body;

    public Player( World world, float x, float y ) {
        super( new Texture( "" ) );
        this.world = world;
        setPosition( x , y );
        createBody();
    }

    public void createBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set( getX() / GameInfo.PPM, getY() / GameInfo.PPM );
        body = world.createBody( bodyDef );

        PolygonShape shape = new PolygonShape();
        shape.setAsBox( (getWidth() / 2f) / GameInfo.PPM,(getHeight() / 2f) / GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 4f; //Mass of the body
        fixtureDef.friction = 2f; //To not slide on surfaces
        fixtureDef.shape = shape;

        Fixture fixture = body.createFixture( fixtureDef );

        shape.dispose();
    }

    public void drawPlayer(SpriteBatch spriteBatch) {
        spriteBatch.draw( this, getX() - getWidth() / 2f, getY() - getHeight() / 2f);
    }

    public void updatePlayer() {
        setPosition( body.getPosition().x * GameInfo.PPM, body.getPosition().y * GameInfo.PPM);
    }

    public Body getBody() {
        return body;
    }

    public World getWorld() {
        return world;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public void setWorld( World world ) {
        this.world = world;
    }

}
