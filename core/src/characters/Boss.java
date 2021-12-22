package characters;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Boss class
 * @author Mehmet Hasat Serinkan
 * @date 22.12.2021
 */
public class Boss extends Enemy {

    public Boss(World world, float initialX, float initialY, float height, float width) {
        super(world, initialX, initialY, height, width);
        this.getFixture().setUserData( "Boss" );

    }

    public void moveBoss() {
        moveCharacter( 2.0f, 0);
    }

}
