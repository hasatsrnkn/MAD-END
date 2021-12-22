/**
 * 
 */
package viewers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import characters.Character;
import helpers.GameInfo;

/**
 * @author Mehmet Eren Balasar
 *
 */
public class CrazyView extends CharacterView {

	/**
	 * 
	 */
	public CrazyView(String textureFileName, Character ch, String atlasFileName) {
		
		super(textureFileName, ch, atlasFileName);
		this.setScale(2f);

	}
	
	public void drawCharacter(SpriteBatch spriteBatch) {
		
		if(!getCharacter().isDead()) {
			
			super.drawCharacter(spriteBatch, 0f, 0f);
			super.drawCharacterAnimation(spriteBatch, 10f, 2f);
		}

	}
	
	

}
