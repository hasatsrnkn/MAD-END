package viewers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import characters.*;

/**
 * PlayerView Class
 * @author Mehmet Hasat Serinkan, Mehmet Eren Balasar
 * @date 07.12.2021
 */

public class PlayerView extends CharacterView {
	


	public PlayerView(String textureFileName, Player ch) {
			
		super(textureFileName, ch, "PlayerAnimation/PlayerAnimation.atlas");

	}
	public void drawPlayer(SpriteBatch spriteBatch) {

		super.drawCharacter(spriteBatch);
		//TODO additional player drawings?
	}


	public void drawPlayerAnimation(SpriteBatch spriteBatch) {
		
		this.getCharacter().updateCharacter();

		if (this.getCharacter().isMoving()) {
			

			setElapsedTime( getElapsedTime() + Gdx.graphics.getDeltaTime() );
			setAnimation( new Animation(1f / 15f, getCharacterAtlas().getRegions() ) );
			setCurrentFrame( (TextureRegion) getAnimation().getKeyFrame( getElapsedTime(), true) );
			setCurrentSprite( new Sprite( getCurrentFrame() ));


			getCurrentSprite().setPosition( this.getCharacter().getXPosition() - getWidth() / 2f,
					this.getCharacter().getYPosition() - getHeight() / 2f );


			getCurrentSprite().setRotation( (float) ((Player)this.getCharacter() ).getRotationDeg() );

			getCurrentSprite().draw( spriteBatch );

		}

	}
		

		

} //End
