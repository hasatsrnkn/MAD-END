package viewers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import characters.Character;
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

	
	public CharacterView(String textureFileName, Character ch, String atlasFileName) {
		
		super(new Texture(textureFileName));
		
		this.character = ch;

		characterAtlas = new TextureAtlas( atlasFileName );

		animation = new Animation(1f / 10f, characterAtlas.getRegions());
		currentFrame = (TextureRegion)  animation.getKeyFrame( elapsedTime, true);
		currentSprite = new Sprite( currentFrame );
		currentSprite.setPosition( character.getXPosition() - getWidth() / 2f,
				character.getYPosition() - getHeight() / 2f );

	}
	

	public void drawCharacter(SpriteBatch spriteBatch, float xAdjustment, float yAdjustment) {
		
		character.updateCharacter();
		if ( !character.isMoving() ) {

			this.setPosition(character.getXPosition() - (character.getWidth() / 2f + xAdjustment) ,
					character.getYPosition() - (character.getHeight() / 2f + yAdjustment));

			this.draw( spriteBatch );
		}
	
		this.setRotation((float) this.getCharacter().getRotationDeg());

    }

	/*
	NEEDS TO BE UPDATED, THIS IS JUST FOR PLAYER ANIMATION ROTATION
	 */
	public void drawCharacterAnimation( SpriteBatch spriteBatch, float frames, float scale ) {

		if (character.isMoving()) {

			elapsedTime = elapsedTime + Gdx.graphics.getDeltaTime();
			animation = new Animation(1f / frames, characterAtlas.getRegions());
			currentFrame = (TextureRegion) animation.getKeyFrame( elapsedTime, true);
			currentSprite = new Sprite( currentFrame );
			currentSprite.setScale(scale);

			currentSprite.setPosition( character.getXPosition() - getWidth() / 2f,
					character.getYPosition() - getHeight() / 2f );

			currentSprite.setRotation( (this.getCharacter() ).getRotationDeg() );

			currentSprite.draw( spriteBatch );
		}
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
