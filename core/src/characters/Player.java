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


    public Player(World w, float x, float y) {
    	
        super(w, x, y);
    }
    
	public void handleInput(float dt) {

    	moveCharacter( 0 ,0 );
    	
        if( Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.S) ||
                Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D) ) {

            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            	
            	moveCharacter(this.getBody().getLinearVelocity().x, GameInfo.MOVESPEED);
            }
            
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            	
            	moveCharacter(this.getBody().getLinearVelocity().x, -GameInfo.MOVESPEED);
            }
            
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            	
            	moveCharacter(-GameInfo.MOVESPEED, this.getBody().getLinearVelocity().y);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            	
            	moveCharacter(GameInfo.MOVESPEED, this.getBody().getLinearVelocity().y);
            }
        }

    }

    
   
}
