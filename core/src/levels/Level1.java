package levels;

import characters.*;
import characters.Character;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
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

import obstacle.Rock;
import throwables.Bullet;
import viewers.*;

import java.util.ArrayList;

/**
 * Level1 class
 * @author Mehmet Hasat Serinkan, Mehmet Eren Balasar
 * @date 07.12.2021
 */
public class Level1 extends Level implements Screen, ContactListener {

    private Character guardian1;
    private Character guardian2;
    private GuardianView guardian1View;
    private GuardianView guardian2View;
    private Rock[] rocks;
    private ArrayList<Bullet> allBullets;
    private ArrayList<Enemy> allEnemies;

    public Level1( GameMain game, String bgName ) {

    	super(game, bgName);

        rocks = new Rock[30];
        allBullets = new ArrayList<Bullet>();
        allEnemies = new ArrayList<Enemy>();



        createRocks();

        player = new Player(world, GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, GameInfo.PLAYER_HEIGHT, GameInfo.PLAYER_WIDTH);
        playerView = new PlayerView( "Player/Player.png", (Player) player);
        
        guardian1 = new Guardian(world, GameInfo.WIDTH / 2f + 120, GameInfo.HEIGHT / 2f, GameInfo.GUARDIAN_HEIGHT, GameInfo.GUARDIAN_WIDTH);
        guardian2 = new Guardian(world, GameInfo.WIDTH / 2f + 320, GameInfo.HEIGHT / 2f, GameInfo.GUARDIAN_HEIGHT, GameInfo.GUARDIAN_WIDTH);
        guardian1View = new GuardianView("Enemies/Guardian.png", (Guardian)guardian1, "PlayerAnimation/PlayerAnimation.atlas");
        guardian2View = new GuardianView("Enemies/Guardian.png", (Guardian)guardian2, "PlayerAnimation/PlayerAnimation.atlas");
        Sound footstep = Gdx.audio.newSound( Gdx.files.internal( "Sounds/Level1FootStep.wav"));
        player.setFootStepVoice( footstep );

    }

    
    public void update( float dt ) {
    	super.update(dt);
        if(guardian1.isDead()) {
            guardian1.kill();
        }
    }

    public void moveCamera() {
        super.moveCamera();
    }

    public void createRocks(){

        rocks[0] = new Rock( world, 400,750, 170,170);
        rocks[1] = new Rock( world, 920, 750, 40,40);
        rocks[2] = new Rock( world, 1050, 780, 50,50);
        rocks[3] = new Rock( world, 275, 1725, 80,80);
        rocks[4] = new Rock( world, 850, 1425, 150,190);
        rocks[5] = new Rock( world, 860, 1550, 50,70);
        rocks[6] = new Rock( world, 1050, 220, 50,50);
        rocks[7] = new Rock( world, 860, 260, 40,30);
        rocks[8] = new Rock( world, 880, 160, 80,60);
        rocks[9] = new Rock( world, 1800, 910, 60,60);
        rocks[10] = new Rock( world, 1710, 915, 140,60);
        rocks[11] = new Rock( world, 1820, 1510, 30,30);
        rocks[12] = new Rock( world, 1900, 1510, 40,40);
        rocks[13] = new Rock( world, 1850, 1410, 60,60);
        rocks[14] = new Rock( world, 2460, 1670, 30,30);
        rocks[15] = new Rock( world, 2600, 1690, 50,50);
        rocks[16] = new Rock( world, 2700, 1410, 180,180);
        rocks[17] = new Rock( world, 2835, 1400, 40,40);
        rocks[18] = new Rock( world, 2805, 1300, 80,90);
        rocks[19] = new Rock( world, 2385, 1140, 50,60);
        rocks[20] = new Rock( world, 2665, 840, 160,185);
        rocks[21] = new Rock( world, 2387, 250, 400,40);
        rocks[22] = new Rock( world, 2450, 490, 40,40);
        rocks[23] = new Rock( world, 2500, 520, 40,40);
        rocks[24] = new Rock( world, 1665, 150, 200,420);
        rocks[25] = new Rock( world, 1760, 350, 200,420);
        rocks[26] = new Rock( world, 1760, 515, 120,150);
        rocks[27] = new Rock( world, 1900, 615, 120,150);
        rocks[28] = new Rock( world, 2080, 660, 170,160);
        rocks[29] = new Rock( world, 2250, 700, 85,150);

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

        if(!guardian1.isDead()) {
            guardian1View.drawCharacter(game.getBatch());
        }

        guardian2View.drawCharacter(game.getBatch());


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
        player.getFootStepVoice().dispose();
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
        else if (body1.getUserData().equals("Bullet") && body2.getUserData().equals( "Enemy" )) {
            if( body2.getBody().equals( guardian1.getBody() )) {
                guardian1.reduceHeathPoint();

            }
        }

    }

}
