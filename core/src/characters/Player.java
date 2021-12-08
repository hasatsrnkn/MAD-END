package characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.*;

import helpers.GameInfo;


/**
 * A class for player
 * @author Mehmet Hasat Serinkan
 * @date 07.12.2021
 */
public class Player extends Character {


    public Player( World world, float x, float y, String textureFileName) {
        super( world, x, y, textureFileName);
    }
    
    public void handleInput(float dt) {
    	
    	this.moveCharacter( 0 ,0 );
    	
    	
        if( Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.S) ||
                Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D) ) {

            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                this.moveCharacter(this.getBody().getLinearVelocity().x, GameInfo.MOVESPEED);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                this.moveCharacter(this.getBody().getLinearVelocity().x, -GameInfo.MOVESPEED);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                this.moveCharacter(-GameInfo.MOVESPEED, this.getBody().getLinearVelocity().y);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                this.moveCharacter(GameInfo.MOVESPEED, this.getBody().getLinearVelocity().y);
            }
        }

    }

}
