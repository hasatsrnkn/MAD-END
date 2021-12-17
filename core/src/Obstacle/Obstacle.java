package Obstacle;

import com.badlogic.gdx.physics.box2d.*;
import helpers.GameInfo;
import viewers.ObstacleView;

/**
 * This class represents the obstacles in the game
 * @author Mehmet Berşan Özgür
 * @date 14.12.2021
 */
public class Obstacle{
        private World world;
        private Body body;


        public Obstacle(World world){
            this.world = world;
        }

        public void createBody(){
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((ObstacleView.ObstaclePositionX + ObstacleView.ObstacleWidth / 2) / GameInfo.PPM,
                    ( ObstacleView.ObstaclePositionY + ObstacleView.ObstacleHeight / 2) / GameInfo.PPM);

            body = world.createBody(bodyDef);

            PolygonShape shape = new PolygonShape();
            shape.setAsBox( (ObstacleView.ObstacleWidth / 2) / GameInfo.PPM,
                    (ObstacleView.ObstacleHeight / 2) / GameInfo.PPM );

            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.density = 1;
            fixtureDef.shape = shape;

            Fixture fixture = body.createFixture(fixtureDef);

            shape.dispose();
        }



}

