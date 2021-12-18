package levels;

import characters.*;
import characters.Character;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cscats.madend.GameMain;
import helpers.GameInfo;
import obstacle.MapBoundaries;
import obstacle.Obstacle;
import viewers.CharacterView;
import viewers.ObstacleView;
import viewers.GuardianView;
import viewers.PlayerView;
import viewers.WallView;

/**
 * Level1 class
 * @author Mehmet Hasat Serinkan, Mehmet Eren Balasar
 * @date 07.12.2021
 */
public class Level1 extends Level implements Screen, ContactListener {

	
    private Character guardian1;
    private GuardianView guardian1View;
    
    
    public Level1( GameMain game, String bgName ) {

    	super(game, bgName);
        
        obstacleView = new ObstacleView( "Obstacles/Level 1/Wall.png",new Obstacle( this.world, 200,200,100, 41) );

        player = new Player(world, GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, GameInfo.PLAYER_HEIGHT, GameInfo.PLAYER_WIDTH);
        playerView = new PlayerView( "Player/Player.png", (Player) player);
        
        guardian1 = new Guardian(world, GameInfo.WIDTH / 2f + 120, GameInfo.HEIGHT / 2f, GameInfo.GUARDIAN_HEIGHT, GameInfo.GUARDIAN_WIDTH);
        guardian1View = new GuardianView("Enemies/Guardian.png", (Guardian)guardian1, "PlayerAnimation/PlayerAnimation.atlas");
        
    }

    
    public void update( float dt ) {
    	
    	super.update(dt);
        obstacleView.getObstacle().updateObstacle();
    }

    public void moveCamera() {
        
        super.moveCamera();
        
    }

    @Override
    public void show() {

    }
    
    public void advanceToNextLevel() {
    	
    	//you can change this statement to how you want to advance to the next level
    	//also it is currently advancing to level3
    	if(player.getXPosition() >= 1800 && player.getYPosition() <= 100) {
    		
    		this.dispose();
    		game.setScreen( new Level3( game,  "Level Backgrounds/Level 1 Background.png"  ) );
    		 
    	}
    }

    @Override
    public void render(float delta) {
    	
    	super.render(delta);

        playerView.drawPlayer( game.getBatch() ); //drawPlayer may be changed to drawCharacter  ******!!!!!!
        playerView.drawCharacterAnimation(game.getBatch());
        
        guardian1View.drawCharacter(game.getBatch());
        
        obstacleView.drawObstacle(game.getBatch());

        game.getBatch().end(); //End for drawing
        
        //tester
        super.renderBodies(delta);
        
        advanceToNextLevel();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
       
    	super.dispose();
    }
    
    @Override
    public void beginContact(Contact contact) {
        
    	Fixture body1;
        Fixture body2;

        if (contact.getFixtureA().getUserData() == "Bullet" ) {
            body1 = contact.getFixtureA();
            body2 = contact.getFixtureB();
            
        }
        else {
            body1 = contact.getFixtureB();
            body2 = contact.getFixtureA();
        }

        if (body1.getUserData().equals( "Bullet") && body2.getUserData().equals("Obstacle") ) {
            playerView.getBulletViewer().getBullet().setRemove( true );

        }

        else if (body1.getUserData().equals( "Bullet") && body2.getUserData().equals("Bullet") ) {
            playerView.getBulletViewer().getBullet().setRemove( true );

        }

    }

}
