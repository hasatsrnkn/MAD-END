package viewers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import characters.*;
import throwables.Bullet;
import java.util.ArrayList;

/**
 * PlayerView Class
 * @author Mehmet Hasat Serinkan, Mehmet Eren Balasar
 * @date 07.12.2021
 */

public class PlayerView extends CharacterView {
	
	private BulletView bulletViewer;
	private Player player;


	public PlayerView(String textureFileName, Player ch) {

		super(textureFileName, ch, "PlayerAnimation/PlayerAnimation.atlas");
		player = ch;

		//JUST FOR INITIALIZATION !é
		//bulletViewer = new BulletView( "Throwables/Bullet1.png", new Bullet( player.getWorld(), 10f,10f,
		//		10f,10, player.getRotationDeg())  );
		//JUST FOR INITIALIZATION İ


	}
	public void drawPlayer(SpriteBatch spriteBatch) {

		super.drawCharacter(spriteBatch, 10f, 12f);
		//TODO additional player drawings?


		super.drawCharacterAnimation(spriteBatch, 15f, 1f);

	}

	//just in case, never used
	public void drawPlayerAnimation(SpriteBatch spriteBatch) {

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

	public Player getPlayer() {
		return player;
	}

	public BulletView getBulletViewer() {
		return bulletViewer;
	}
} //End
