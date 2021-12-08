package viewers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.World;
import characters.*;
import helpers.GameInfo;

public class PlayerView extends CharacterView {
	
		public PlayerView(String textureFileName, Player ch, World world ) {
			super(textureFileName, ch, world);
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
