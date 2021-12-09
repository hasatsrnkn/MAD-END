package characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import helpers.GameInfo;
/**
 * A class for player
 * @author Mehmet Hasat Serinkan, Mehmet Eren Balasar
 * @date 07.12.2021
 */
public class Player extends Character {

	public double rotation;
	private float mouseX;
	private float mouseY;

    public Player(World w, float x, float y) {
    	
        super(w, x, y);
    }
    
	public void handleMoveInput(float dt) {

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

    
	public void handleMouseInput(float dt) {
		
		Vector2 mouseVector =  new Vector2();
		mouseVector.set(Gdx.input.getX(), Gdx.input.getY());
		
		mouseX = Gdx.input.getX();
		mouseY = GameInfo.HEIGHT - Gdx.input.getY();
		
		rotation = Math.toDegrees(Math.atan((mouseX -  this.getXPosition()) / 
				(mouseY - this.getYPosition())));
		
		System.out.println(Gdx.input.getX() + "   " + this.getXPosition());
		
		mouseVector.rotateRad(dt);
		
		this.setDirectionVector(mouseVector);
		
	}
	
}
