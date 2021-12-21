/**
 * 
 */
package viewers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import characters.Character;
import characters.Guardian;
import throwables.Bullet;

/**
 * @author Mehmet Eren Balasar
 *
 */
public class GuardianView extends EnemyView{


	private Guardian guardian = (Guardian)this.getCharacter();
	
	public GuardianView(String textureFileName, Guardian ch, String atlasFileName) {
		
		super(textureFileName, ch, atlasFileName);
		this.setScale(2f);
		
		//JUST FOR INITIALIZATION !é
		//JUST FOR INITIALIZATION İ

		
	}
	
	public void drawCharacter(SpriteBatch spriteBatch) {

		if(!guardian.isDead()) {
			
			super.drawCharacter(spriteBatch, 15f, -20f);
			super.drawCharacterAnimation(spriteBatch, 8f, 2f);
		
		}
	}

}
