/**
 * 
 */
package viewers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import characters.Character;
import helpers.GameInfo;

/**
 * @author yusufbalasar
 *
 */
public class CharacterView extends Sprite{

	private Character character; 
	private World world;
	private Body body;
	
	public CharacterView(String textureFileName, Character ch, World world ) {
		
		super(new Texture(textureFileName));
		
		this.character = ch;
		
		character.setHeight(getHeight());
		character.setWidth(getWidth());
		this.world = world;
		createBody();
	}
	
	public void updateCharacter() {
        character.setPosition( body.getPosition().x * GameInfo.PPM, body.getPosition().y * GameInfo.PPM);
        
    }

    public void moveCharacter( float x, float y ) {
        body.setLinearVelocity( x , y );
    }
    public void createBody() {
		
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set( character.getXPosition() / GameInfo.PPM, character.getYPosition() / GameInfo.PPM );
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

	public Body getBody() {
		return body;
	}

	public void drawCharacter(SpriteBatch spriteBatch) {
		
        spriteBatch.draw( this, character.getXPosition() - character.getWidth() / 2f, 
        		character.getYPosition() - character.getHeight() / 2f);
    }
	
	
}
