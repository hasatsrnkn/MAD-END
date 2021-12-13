/**
 * 
 */
package viewers;

import characters.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import characters.Character;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
/**
 * Character View Class
 * @author Mehmet Eren Balasar/Mehmet Hasat Serinkan
 * @date 10.12.2021
 */
public class CharacterView extends Sprite{

	private Character character;
	private TextureAtlas characterAtlas;
	private Animation animation;
	private TextureRegion currentFrame;
	private Sprite currentSprite;
	private float elapsedTime;
  private Box2DDebugRenderer bodyRenderer;

	
	public CharacterView(String textureFileName, Character ch, String atlasFileName) {
		
		super(new Texture(textureFileName));
		
		this.character = ch;
				
		character.setHeight(getHeight());
		character.setWidth(getWidth());


		characterAtlas = new TextureAtlas( atlasFileName );

		animation = new Animation(1f / 10f, characterAtlas.getRegions());
		currentFrame = (TextureRegion)  animation.getKeyFrame( elapsedTime, true);
		currentSprite = new Sprite( currentFrame );
		currentSprite.setPosition( character.getXPosition() - getWidth() / 2f,
				character.getYPosition() - getHeight() / 2f );

	}
	

	public void drawCharacter(SpriteBatch spriteBatch) {

		character.updateCharacter();

		if ( !character.isMoving() ) {

			this.setPosition(character.getXPosition() - character.getWidth() / 2f,
					character.getYPosition() - character.getHeight() / 2f);

			this.draw( spriteBatch );
		}
	
		this.setRotation((float) this.getCharacter().getRotationDeg());


    }

	/*
	NEEDS TO BE UPDATED, THIS IS JUST FOR PLAYER ANIMATION ROTATION
	 */
	public void drawCharacterAnimation( SpriteBatch spriteBatch ) {
		character.updateCharacter();

		if (character.isMoving()) {

			elapsedTime = elapsedTime + Gdx.graphics.getDeltaTime();
			animation = new Animation(1f / 15f, characterAtlas.getRegions());
			currentFrame = (TextureRegion) animation.getKeyFrame( elapsedTime, true);
			currentSprite = new Sprite( currentFrame );

			currentSprite.setPosition( character.getXPosition() - getWidth() / 2f,
					character.getYPosition() - getHeight() / 2f );

			currentSprite.setRotation( (float) ((Player)this.getCharacter() ).getRotationDeg() );

			currentSprite.draw( spriteBatch );
		}
	}
	
	//for developing purposes!!!!!
	public void drawBody(OrthographicCamera camera) {
		
		bodyRenderer = new Box2DDebugRenderer();
		bodyRenderer.render(character.getWorld(), camera.combined);
	}


	public Character getCharacter() {
		
		return character;
	}

	public Sprite getCurrentSprite() {
		return currentSprite;
	}

	public TextureRegion getCurrentFrame() {
		return currentFrame;
	}

	public Animation getAnimation() {
		return animation;
	}

	public float getElapsedTime() {
		return elapsedTime;
	}

	public TextureAtlas getCharacterAtlas() {
		return characterAtlas;
	}

	public void setElapsedTime(float elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public void setCurrentFrame( TextureRegion currentFrame) {
		this.currentFrame = currentFrame;
	}

	public void setCurrentSprite( Sprite currentSprite) {
		this.currentSprite = currentSprite;
	}



} //End
