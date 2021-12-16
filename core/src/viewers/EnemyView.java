/**
 * 
 */
package viewers;

import characters.Character;
import characters.Enemy;

/**
 * @author Mehmet Eren Balasar
 *
 */
public class EnemyView extends CharacterView{

	/**
	 * 
	 */
	public EnemyView(String textureFileName, Enemy ch, String atlasFileName) {
		
		super(textureFileName,  ch,  atlasFileName);
	}

}
