package characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import helpers.GameInfo;
import levels.Level1;


import java.util.ArrayList;

/**
 * A class for player
 * @author Mehmet Hasat Serinkan, Mehmet Eren Balasar
 * @date 07.12.2021
 */
public class Player extends Character {

	public double rotation;

    public Player(World w, float x, float y) {
    	
        super(w, x, y);
    }
    
	public void handleMoveInput(float dt) {

    	moveCharacter( 0 ,0 );
        setWalking(false );
    	
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

    
	public void handleMouseInput(float dt, float mouseX, float mouseY ) {

		rotation =  MathUtils.radiansToDegrees *  Math.atan2 ( mouseY - this.getYPosition() + (getHeight() / 2)  , mouseX - this.getXPosition() + (getWidth() / 2)   );
		
	}

} //End
