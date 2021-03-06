package characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import helpers.BodyEditorLoader;
import helpers.GameInfo;
import throwables.Bullet;
import java.util.ArrayList;

/**
 * A class for player
 * @author Mehmet Hasat Serinkan, Mehmet Eren Balasar
 * @date 07.12.2021
 */
public class Player extends Character {

	private boolean inShooter;
	
    public Player(World w, float x, float y, float height, float width, boolean inShooter) {
    	
        super(w, x, y, height, width);

        this.setHealthPoint( GameInfo.PLAYER_HEALTH );
        this.getFixtureDef().filter.categoryBits = GameInfo.PLAYER;
        this.getFixtureDef().filter.maskBits = GameInfo.BULLET | GameInfo.ENEMY | GameInfo.OBSTACLE;
        this.getFixture().setUserData( "Player" );
        this.inShooter = inShooter;
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
        
        this.playFootStepSound();
    }

    public void handleMoveInputBossLevel(float dt) {

        moveCharacter( 2.3f ,0 );
        setMoving( false );

        if( Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.S)) {


            if (Gdx.input.isKeyPressed(Input.Keys.W)) {

                moveCharacter(this.getBody().getLinearVelocity().x, GameInfo.PLAYER_MOVESPEED);

            }

            if (Gdx.input.isKeyPressed(Input.Keys.S)) {

                moveCharacter(this.getBody().getLinearVelocity().x, -GameInfo.PLAYER_MOVESPEED);

            }


        }

        this.playFootStepSound();
    }


	public Bullet handleMouseInput(float dt, float mouseX, float mouseY ) {

		this.setRotationDeg((float)(MathUtils.radiansToDegrees *  Math.atan2 ( mouseY - this.getYPosition()  , 
				mouseX - this.getXPosition()   )));

		if(inShooter) {
			
	        Bullet newBullet = null;
	        if ( Gdx.input.isButtonJustPressed(Input.Buttons.LEFT ) ) {

	            if( this.getShotTime() == 0) {
	                 newBullet = this.shoot( mouseX, mouseY );

	            }
	            else {
	                long time = System.currentTimeMillis();
	                if (time > getLastTimeShot() + 300) {
	                    newBullet =  this.shoot(mouseX, mouseY);

	                }
	            }


	        }
	        return newBullet;
			
		}
		
		else {
			
			return null;
		}


		//TODO other mouse actions
	}


} //End
