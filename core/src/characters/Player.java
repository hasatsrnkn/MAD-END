package characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import helpers.GameInfo;
import throwables.Bullet;
import java.util.ArrayList;

/**
 * A class for player
 * @author Mehmet Hasat Serinkan, Mehmet Eren Balasar
 * @date 07.12.2021
 */
public class Player extends Character {

    private ArrayList<Bullet> bullets;

    public Player(World w, float x, float y, float height, float width) {
    	
        super(w, x, y, height, width);
        bullets = new ArrayList<Bullet>();
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
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

        if ( Gdx.input.isButtonJustPressed(Input.Buttons.LEFT ) ) {
            bullets.add( new Bullet( this.getWorld(), this.getXPosition() + 15, this.getYPosition() + 15, this.getRotationDeg() ) );
            float hip = ( float ) Math.sqrt( Math.pow( (mouseX - this.getXPosition() ) , 2 ) + Math.pow(( mouseY - this.getYPosition()), 2 ) );

            for ( Bullet bullet: bullets) {
                if( !bullet.isShot() ) {
                    bullet.moveBullet((mouseX - this.getXPosition()) * GameInfo.BULLET_SPEED /
                            hip, (mouseY - this.getYPosition()) * GameInfo.BULLET_SPEED / hip);
                }
            }



        }

		

		//TODO other mouse actions
	}


} //End
