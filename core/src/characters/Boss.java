package characters;
import com.badlogic.gdx.physics.box2d.World;

public class Boss extends Enemy {

    public Boss(World world, float initialX, float initialY, float height, float width) {
        super(world, initialX, initialY, height, width);
        this.getFixture().setUserData( "Boss" );

    }

    public void moveBoss() {
        moveCharacter( 2.3f, 0);
    }

}
