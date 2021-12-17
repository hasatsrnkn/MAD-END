package obstacle;

import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;




public class MapBoundaries {

    private ArrayList<Wall> boundaryWalls;
    public MapBoundaries( World world, float mapHeight, float mapWidth, float height1, float width1, float height2, float width2) {
        boundaryWalls = new ArrayList<Wall>();
        boundaryWalls.add( new Wall( world, 0, mapHeight / 2f ,height1, width1 ) );
        boundaryWalls.add( new Wall( world, mapWidth, mapHeight / 2f, height1, width1 ) );
        boundaryWalls.add( new Wall( world, mapWidth / 2f, mapHeight, height2, width2 ) );
        boundaryWalls.add( new Wall( world, mapWidth / 2f, 0, height2, width2 ) );

    }

    public ArrayList<Wall> getBoundaryWalls() {
        return boundaryWalls;
    }

    public void setBoundaryWalls(ArrayList<Wall> boundaryWalls) {
        this.boundaryWalls = boundaryWalls;
    }


}
