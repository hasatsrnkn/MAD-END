package viewers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import characters.*;
import helpers.GameInfo;

/**
 * playerview
 * @author Mehmet Hasat Serinkan, Mehmet Eren Balasar
 * @date 07.12.2021
 */

public class PlayerView extends CharacterView {
	
	
	public PlayerView(String textureFileName, Player ch) {
			
		super(textureFileName, ch);

	}
	
	public void drawCharacter(SpriteBatch spriteBatch) {
		
		super.drawCharacter(spriteBatch);
		
		this.setRotation((float) ((Player)this.getCharacter()).rotation);
		this.draw(spriteBatch);
		
	}
		

		

}
