package levels;

import Cinematics.Cinematic4;
import characters.*;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.*;
import com.cscats.madend.GameMain;
import helpers.GameInfo;

import helpers.GameManager;
import obstacle.Rock;
import throwables.Bullet;
import viewers.*;

/**
 * Level1 class
 * @author Mehmet Hasat Serinkan, Mehmet Eren Balasar
 * @date 07.12.2021
 */
public class Level1 extends Level implements Screen, ContactListener {


    private Guardian guardian1;
    private GuardianView guardian1View;
    private Guardian guardian2;
    private GuardianView guardian2View;
    private Guardian guardian3;
    private GuardianView guardian3View;
    private Guardian guardian4;
    private GuardianView guardian4View;
    private Guardian guardian5;
    private GuardianView guardian5View;
    private Guardian guardian6;
    private GuardianView guardian6View;
    private Rock[] rocks;

    private ArrayList<Enemy> allEnemies;
    private ArrayList<Enemy> enemiesToRemove;

    public Level1( GameMain game, String bgName ) {

    	super(game, bgName);
    	this.setShooterLevel(true);
    	
        rocks = new Rock[30];

        allEnemies = new ArrayList<Enemy>();
        enemiesToRemove = new ArrayList<Enemy>(); 
        
        createRocks();
        
        player = new Player(world, GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, GameInfo.PLAYER_HEIGHT, GameInfo.PLAYER_WIDTH, this.isShooterLevel());
        playerView = new PlayerView( "Player/Player.png", (Player) player);
        
        guardian1 = new Guardian(world, 350, 900, GameInfo.GUARDIAN_HEIGHT, GameInfo.GUARDIAN_WIDTH, player);
        guardian1View = new GuardianView("Enemies/Guardian.png", (Guardian)guardian1, "EnemyAnimation/terroristani.atlas");
        allEnemies.add(guardian1);
        
        guardian2 = new Guardian(world, 967, 162, GameInfo.GUARDIAN_HEIGHT, GameInfo.GUARDIAN_WIDTH, player);
        guardian2View = new GuardianView("Enemies/Guardian.png", (Guardian)guardian2, "EnemyAnimation/terroristani.atlas");
        allEnemies.add(guardian2);
        
        guardian3 = new Guardian(world, 988, 1598, GameInfo.GUARDIAN_HEIGHT, GameInfo.GUARDIAN_WIDTH, player);
        guardian3View = new GuardianView("Enemies/Guardian.png", (Guardian)guardian3, "EnemyAnimation/terroristani.atlas");
        allEnemies.add(guardian3);
        
        guardian4 = new Guardian(world, 1896, 908, GameInfo.GUARDIAN_HEIGHT, GameInfo.GUARDIAN_WIDTH, player);
        guardian4View = new GuardianView("Enemies/Guardian.png", (Guardian)guardian4, "EnemyAnimation/terroristani.atlas");
        allEnemies.add(guardian4);
        
        guardian5 = new Guardian(world, 2755, 1564, GameInfo.GUARDIAN_HEIGHT, GameInfo.GUARDIAN_WIDTH, player);
        guardian5View = new GuardianView("Enemies/Guardian.png", (Guardian)guardian5, "EnemyAnimation/terroristani.atlas");
        allEnemies.add(guardian5);
        
        guardian6 = new Guardian(world, 2223, 594, GameInfo.GUARDIAN_HEIGHT, GameInfo.GUARDIAN_WIDTH, player);
        guardian6View = new GuardianView("Enemies/Guardian.png", (Guardian)guardian6, "EnemyAnimation/terroristani.atlas");
        allEnemies.add(guardian6);
        
        Sound footstep = Gdx.audio.newSound( Gdx.files.internal( "Sounds/Level1FootStep.wav"));
        player.setFootStepVoice( footstep );
        
    }


    public void update( float dt ) {

        if (!GameManager.getInstance().isPaused) {
            super.update(dt);

            getAllBullets().add(guardian1.moveAndShoot(350, 900, 600, 900)); 
            guardian1.updateCharacter();

            getAllBullets().add(guardian2.moveAndShoot(967, 162, 967, 330));
            guardian2.updateCharacter();

            getAllBullets().add(guardian3.moveAndShoot(988, 1598, 1022, 1310));
            guardian3.updateCharacter();

            getAllBullets().add(guardian4.moveAndShoot(1896, 908, 1896, 1077));
            guardian4.updateCharacter();

            getAllBullets().add(guardian5.moveAndShoot(2755, 1564, 2529, 1564));
            guardian5.updateCharacter();
            
            getAllBullets().add(guardian6.moveAndShoot(2223, 594, 2402, 594));
            guardian6.updateCharacter();
            
            removeEnemies();
        }
    }

    public void moveCamera() {
        super.moveCamera();
    }
    
    
    public void removeEnemies() {

        for (Enemy enemy : allEnemies) {
        	
            if( enemy != null ) {
                if (enemy.isDead()) {
                    enemiesToRemove.add(enemy);
                    enemy.kill();
                }
            }
        }
        
        allEnemies.removeAll( enemiesToRemove );
        enemiesToRemove.clear();
        
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

            player.getFootStepVoice().stop();
            game.setScreen( new Cinematic4( game ));
    	}
    }

    @Override
    public void render(float delta) {
    	
    	super.render(delta);

        playerView.drawPlayer( game.getBatch() ); //drawPlayer may be changed to drawCharacter  ******!!!!!!

        getUiHud().stopGame();
        if( !GameManager.getInstance().isPaused) {
            guardian1View.drawCharacter(game.getBatch());
            guardian2View.drawCharacter(game.getBatch());
            guardian3View.drawCharacter(game.getBatch());
            guardian4View.drawCharacter(game.getBatch());
            guardian5View.drawCharacter(game.getBatch());
            guardian6View.drawCharacter(game.getBatch());
        }

        game.getBatch().end(); //End for drawing
        game.getBatch().setProjectionMatrix( getUiHud().getStage().getCamera().combined);
        getUiHud().getStage().draw();
        getUiHud().getStage().act();

        
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
        
    	Fixture body1 = contact.getFixtureA();
        Fixture body2 = contact.getFixtureB();


        if (body1.getUserData().equals( "Bullet") || body2.getUserData().equals("Bullet") ) {
        	
             for ( Bullet bullet : getAllBullets() ) {
            	 
                if( bullet != null ) {
                	
                    if ( bullet.getBody().equals( body1.getBody() )) {
                        bullet.setRemove( true );
                    }
                    
                   else if ( bullet.getBody().equals( body2.getBody() )) {
                        bullet.setRemove( true );
                        
                    }
                }
             }
        }
               
        if ( (body1.getUserData().equals("Bullet") && body2.getUserData().equals( "Enemy" )) ||
                (body2.getUserData().equals("Bullet") && body1.getUserData().equals( "Enemy" ) )) {

            for ( Enemy enemy : allEnemies ) {

                if( enemy != null ) {
                	
                    if ( enemy.getBody().equals( body1.getBody() )) {
                    	
                        enemy.reduceHealthPoint();

                        if(enemy.isDead()) {
                        	
                            getUiHud().incrementScore( GameManager.getInstance().score + 100 );
                        }

                    }
                    else if ( enemy.getBody().equals( body2.getBody() )) {

                       enemy.reduceHealthPoint();
                       
                       if(enemy.isDead()) {
                       	
                           getUiHud().incrementScore( GameManager.getInstance().score + 100 );
                       }
                       
                    }

                }
                
             }

        }

        if ( (body1.getUserData().equals("Player") && body2.getUserData().equals( "Enemy" )) ||
                (body1.getUserData().equals("Enemy") && body2.getUserData().equals( "Player" ) )) {
            if( body1.getBody().equals( player.getBody() ) || body2.getBody().equals( player.getBody() )) {
                player.reduceHealthPoint();
                getUiHud().setHealth( GameManager.getInstance().healthScore - 1);
                if( player.isDead() ) {
                    player.getFootStepVoice().stop();
                    getUiHud().playerIsDead();
                }
            }

        }
        
        if ( (body1.getUserData().equals("Player") && body2.getUserData().equals( "Bullet" )) ||
                (body1.getUserData().equals("Bullet") && body2.getUserData().equals( "Player" ) )) {
        	
            if( body1.getBody().equals( player.getBody() ) || body2.getBody().equals( player.getBody() )) {
            	
                player.reduceHealthPoint();
                getUiHud().setHealth( GameManager.getInstance().healthScore - 1);
                
                if( player.isDead() ) {
                    player.getFootStepVoice().stop();
                    getUiHud().playerIsDead();
                }
            }

        }

    }

}
