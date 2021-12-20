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

	
	private BulletView bulletViewer;
	private Guardian guardian = (Guardian)this.getCharacter();
	
	public GuardianView(String textureFileName, Guardian ch, String atlasFileName) {
		
		super(textureFileName, ch, atlasFileName);
		this.setScale(2f);
		
		//JUST FOR INITIALIZATION !é
		bulletViewer = new BulletView( "Throwables/Bullet1.png", new Bullet( guardian.getWorld(), 10f,10f,
				10f,10, guardian.getRotationDeg())  );
		//JUST FOR INITIALIZATION İ

		
	}
	
	public void drawCharacter(SpriteBatch spriteBatch) {
		
		super.getCharacter().updateCharacter();
		super.drawCharacter(spriteBatch, 15f, -20f);
		super.drawCharacterAnimation(spriteBatch, 8f, 2f);

		for( Bullet bullet: guardian.getBullets() ) {
			bulletViewer.setBullet( bullet );
			bulletViewer.setRotationOfBullet();
			bulletViewer.drawBullet( spriteBatch );
		}

		guardian.removeBullets();
		
	}

}
