package viewers;

import characters.Boss;
import characters.Guardian;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BossView extends EnemyView {

    private Boss boss = (Boss) this.getCharacter();
    public BossView( String textureFileName, Boss ch, String atlasFileName ) {
        super( textureFileName, ch, atlasFileName);


    }

    public void drawCharacter(SpriteBatch spriteBatch) {

        if(!boss.isDead()) {

            super.drawCharacter(spriteBatch, 10f, 12f);
            super.drawCharacterAnimation(spriteBatch, 15f, 1f);

        }
    }
}
