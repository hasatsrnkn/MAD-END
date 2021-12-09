/**
 * 
 */
package viewers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import characters.Character;


/**
 * @author Mehmet Eren Balasar
 *
 */
public class CharacterView extends Sprite{

	private Character character; 
	
	public CharacterView(String textureFileName, Character ch) {
		
		super(new Texture(textureFileName));
		
		this.character = ch;
				
		character.setHeight(getHeight());
		character.setWidth(getWidth());

	}
	

	public void drawCharacter(SpriteBatch spriteBatch) {
		
		character.updateCharacter();
		
		this.setPosition(character.getXPosition() - character.getWidth() / 2f, 
        		character.getYPosition() - character.getHeight() / 2f);
		
        this.draw( spriteBatch );
        
    }
	
	public Character getCharacter() {
		
		return character;
	}
}
