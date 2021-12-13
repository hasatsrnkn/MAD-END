package characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import helpers.GameInfo;


/**
 * A class for player
 * @author Mehmet Hasat Serinkan, Mehmet Eren Balasar
 * @date 07.12.2021
 */
public class Player extends Character {


    public Player(World w, float x, float y) {
    	
        super(w, x, y);
    }
    
	public void handleMoveInput(float dt) {

    	moveCharacter( 0 ,0 );
        setMoving( false );
    	
        if( Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.S) ||
                Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D) ) {


            if (Gdx.input.isKeyPressed(Input.Keys.W)) {

            	moveCharacter(this.getBody().getLinearVelocity().x, GameInfo.PLAYER_MOVESPEED);

            }
            
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            	
            	moveCharacter(this.getBody().getLinearVelocity().x, -GameInfo.PLAYER_MOVESPEED);

            }
            
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            	
            	moveCharacter(-GameInfo.PLAYER_MOVESPEED, this.getBody().getLinearVelocity().y);

            }

            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            	
            	moveCharacter(GameInfo.PLAYER_MOVESPEED, this.getBody().getLinearVelocity().y);

            }
        } 

    }


	public void handleMouseInput(float dt, float mouseX, float mouseY ) {

		this.setRotationDeg((float)(MathUtils.radiansToDegrees *  Math.atan2 ( mouseY - this.getYPosition()  , 
				mouseX - this.getXPosition()   )));
		

		//TODO other mouse actions
	}

} //End
