package Obstacle;

import com.badlogic.gdx.physics.box2d.World;

public class Walls extends Obstacle {
    public Walls(World world){
    super(world);
    }

    @Override
    public void createBody() {
        super.createBody();
    }
}
