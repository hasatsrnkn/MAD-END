/**
 * 
 */
package levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.cscats.madend.GameMain;

import characters.Crazy;
import characters.Enemy;
import characters.Player;
import helpers.GameInfo;
import viewers.CrazyView;
import viewers.PlayerView;

/**
 * @author Mehmet Eren Balasar
 *
 */
public class Level3 extends Level{

	
	private Enemy crazy1;
	private CrazyView crazy1View;
	
	
	public Level3(GameMain game, String bgName) {
		
		super(game, bgName);
		this.setShooterLevel(false);
		
        player = new Player(world, GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, GameInfo.PLAYER_HEIGHT, GameInfo.PLAYER_WIDTH);
        playerView = new PlayerView( "Player/Player.png", (Player) player);
		
		crazy1 = new Crazy(world, GameInfo.WIDTH / 2f + 200, GameInfo.HEIGHT / 2f, GameInfo.PLAYER_HEIGHT, GameInfo.PLAYER_WIDTH);
		crazy1View = new CrazyView("Enemies/Crazy.png", crazy1, "EnemyAnimation/crazyani.atlas");
		Sound footstep = Gdx.audio.newSound( Gdx.files.internal( "Sounds/Level1FootStep.wav"));
		player.setFootStepVoice( footstep );
	}
	
	
	public void update(float delta) {
		
		super.update(delta);
		((Crazy)crazy1).moveCrazy1();
	}
	
	
	public void render(float delta) {
		
		super.render(delta);
		
		playerView.drawPlayer(game.getBatch());
		playerView.drawPlayerAnimation(game.getBatch());
		
		crazy1View.drawCharacter(game.getBatch());
		crazy1View.drawCharacterAnimation(game.getBatch());
		
		game.getBatch().end();
		
		super.renderBodies(delta);
	}
	
	
	@Override
	public void beginContact(Contact contact) {
		
		Fixture body1;
        Fixture body2;

        
        if (contact.getFixtureA().getUserData().equals("Crazy")) {
        	
            body1 = contact.getFixtureA();
            body2 = contact.getFixtureB();
        }
        
        else {
        	
            body1 = contact.getFixtureB();
            body2 = contact.getFixtureA();
        }

        if (body1.getUserData().equals("Crazy") && body2.getUserData().equals("Player") ) {
            System.out.println("Puzzle interface should be implemented here.");

        }
        
        else if (body2.getUserData().equals("Crazy") && body1.getUserData().equals("Player") ) {
            System.out.println("Puzzle interface should be implemented here.");

        }
        
        if (body1.getUserData().equals("Crazy") && body2.getUserData().equals("Obstacle") ) {
        	
        	((Crazy) crazy1).changeDirection(false, true);
        }
        
        else if (body2.getUserData().equals("Crazy") && body1.getUserData().equals("Obstacle") ) {
        	
        	((Crazy) crazy1).changeDirection(false, true);
        }
		
	}
	

}
