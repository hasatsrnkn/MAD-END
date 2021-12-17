package viewers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import throwables.Bullet;

/**
 * BulletView Class
 * @author Mehmet Hasat Serinkan
 * @date 15.12.2021
 */
public class BulletView extends Sprite {

    private Bullet bullet;

    public BulletView(String fileTextName, Bullet bullet1 ) {
        super( new Texture( fileTextName ) );
        this.bullet = bullet1;
        bullet.setHeight( getHeight() );
        bullet.setWidth( getWidth() );
        this.setRotation( bullet.getRotationDeg() );


    }

    public void drawBullet(SpriteBatch spriteBatch) {

            bullet.updateBullet();
            this.setPosition(bullet.getXPosition() - bullet.getWidth() / 2f,
                    bullet.getYPosition() - bullet.getHeight() / 2f);
            this.draw(spriteBatch);


    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }
}
