package levels;

import characters.Crazy;
import characters.Enemy;
import characters.Guardian;
import characters.Player;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.cscats.madend.GameMain;
import helpers.GameInfo;
import helpers.GameManager;
import obstacle.Rock;
import throwables.Bullet;
import viewers.CrazyView;
import viewers.GuardianView;
import viewers.PlayerView;
import viewers.WallView;

/**
 * Level 2
 * @author Mehmet Hasat Serinkan
 * 21.12.2021
 */
public class Level2 extends Level implements Screen, ContactListener {

    private Rock[] rocks;
    
    private Crazy crazy1;
	private CrazyView crazy1View;
	private Crazy crazy2;
	private CrazyView crazy2View;
	private Crazy crazy3;
	private CrazyView crazy3View;
	private Crazy crazy4;
	private CrazyView crazy4View;
	private Crazy crazy5;
	private CrazyView crazy5View;
	private Crazy crazy6;
	private CrazyView crazy6View;
	
    private ArrayList<Enemy> allEnemies;
    private ArrayList<Enemy> enemiesToRemove;

    public Level2(GameMain game, String bgName) {

        super(game, bgName);

        rocks = new Rock[ 30 ];

        healthByDifficulty();
        
        this.setShooterLevel(true);
        createRocks();
        
        allEnemies = new ArrayList<Enemy>();
        enemiesToRemove = new ArrayList<Enemy>(); 

        mapBoundaryWallView1 = new WallView( "Obstacles/Level 2/Wall1.png", mapBoundaries.getBoundaryWalls().get(0) );
        mapBoundaryWallView2 = new WallView( "Obstacles/Level 2/Wall1.png", mapBoundaries.getBoundaryWalls().get(1) );
        mapBoundaryWallView3 = new WallView( "Obstacles/Level 2/Wall2.png", mapBoundaries.getBoundaryWalls().get(2) );
        mapBoundaryWallView4 = new WallView( "Obstacles/Level 2/Wall2.png", mapBoundaries.getBoundaryWalls().get(3) );


        player = new Player(world, 300, 200, GameInfo.PLAYER_HEIGHT, GameInfo.PLAYER_WIDTH, this.isShooterLevel());
        playerView = new PlayerView( "Player/Player.png", (Player) player);
        
        
		crazy1 = new Crazy(world, 809, 797, 
				GameInfo.CRAZY_HEIGHT, GameInfo.CRAZY_WIDTH);
		crazy1View = new CrazyView("Enemies/Crazy.png", crazy1, "EnemyAnimation/crazyani.atlas");
		allEnemies.add(crazy1);
		
		crazy2 = new Crazy(world, 450, 1093, 
				GameInfo.CRAZY_HEIGHT, GameInfo.CRAZY_WIDTH);
		crazy2View = new CrazyView("Enemies/Crazy.png", crazy2, "EnemyAnimation/crazyani.atlas");
		allEnemies.add(crazy2);
		
		crazy3 = new Crazy(world, 734, 1667, 
				GameInfo.CRAZY_HEIGHT, GameInfo.CRAZY_WIDTH);
		crazy3View = new CrazyView("Enemies/Crazy.png", crazy3, "EnemyAnimation/crazyani.atlas");
		allEnemies.add(crazy3);
		
		crazy4 = new Crazy(world, 1599, 1426, 
				GameInfo.CRAZY_HEIGHT, GameInfo.CRAZY_WIDTH);
		crazy4View = new CrazyView("Enemies/Crazy.png", crazy4, "EnemyAnimation/crazyani.atlas");
		allEnemies.add(crazy4);
		
		crazy5 = new Crazy(world, 2694, 1347, 
				GameInfo.CRAZY_HEIGHT, GameInfo.CRAZY_WIDTH);
		crazy5View = new CrazyView("Enemies/Crazy.png", crazy5, "EnemyAnimation/crazyani.atlas");
		allEnemies.add(crazy5);
		
		crazy6 = new Crazy(world, 2499, 707, 
				GameInfo.CRAZY_HEIGHT, GameInfo.CRAZY_WIDTH);
		crazy6View = new CrazyView("Enemies/Crazy.png", crazy6, "EnemyAnimation/crazyani.atlas");
		allEnemies.add(crazy6);
        

        Sound footstep = Gdx.audio.newSound( Gdx.files.internal( "Sounds/Level2FootStep.wav"));
        player.setFootStepVoice( footstep );

    }

    public void update( float dt ) {
    	
        if (!GameManager.getInstance().isPaused) {
            super.update(dt);
            
			crazy1.moveCrazy2();
			crazy2.moveCrazy2();
			crazy3.moveCrazy2();
			crazy4.moveCrazy2();
			crazy5.moveCrazy2();
			crazy6.moveCrazy2();
			
            removeEnemies();
        }
    }

    public void moveCamera() {
        super.moveCamera();
    }

    public void createRocks() {
        rocks[0] = new Rock( world, 225, 840, 200, 300 );
        rocks[1] = new Rock( world, 500, 800, 150, 250 );
        rocks[2] = new Rock( world, 200, 1450, 300, 300 );
        rocks[3] = new Rock( world, 300, 325, 100, 200 );
        rocks[4] = new Rock( world, 450, 345, 160, 200 );
        rocks[5] = new Rock( world, 1200, 315, 120, 1200 );
        rocks[6] = new Rock( world, 1000, 455, 120, 900 );
        rocks[7] = new Rock( world, 1000, 555, 100, 700 );
        rocks[8] = new Rock( world, 700, 1300, 100, 200);
        rocks[9] = new Rock( world, 1000, 1320, 200, 400);
        rocks[10] = new Rock( world, 1000, 1460, 80, 300);
        rocks[11] = new Rock( world, 1280, 1330, 120, 100);
        rocks[12] = new Rock( world, 1360, 1710, 100, 600);
        rocks[13] = new Rock( world, 1800, 1000, 80, 1800);
        rocks[14] = new Rock( world, 2000, 1100, 80, 1300);
        rocks[15] = new Rock( world, 2100, 1200, 100, 800);
        rocks[16] = new Rock( world, 2100, 1300, 100, 600);
        rocks[17] = new Rock( world, 2100, 1420, 150, 300);
        rocks[18] = new Rock( world, 2500, 1700, 100, 500);
        rocks[19] = new Rock( world, 1800, 750, 80, 500);
        rocks[20] = new Rock( world, 2400, 420, 200, 500);
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
    

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        super.render(delta);

        playerView.drawPlayer(game.getBatch()); //drawPlayer may be changed to drawCharacter  ******!!!!!!

		crazy1View.drawCharacter(game.getBatch());
		crazy2View.drawCharacter(game.getBatch());
		crazy3View.drawCharacter(game.getBatch());
		crazy4View.drawCharacter(game.getBatch());
		crazy5View.drawCharacter(game.getBatch());
		crazy6View.drawCharacter(game.getBatch());
        
        getUiHud().stopGame();

        game.getBatch().end(); //End for drawing
        game.getBatch().setProjectionMatrix(getUiHud().getStage().getCamera().combined);
        getUiHud().getStage().draw();
        getUiHud().getStage().act();


        //tester
        super.renderBodies(delta);

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
    
    public void beginContact(Contact contact) {
    	
		Fixture body1 = contact.getFixtureA();
        Fixture body2 = contact.getFixtureB();

        if ( (body1.getUserData().equals("Enemy") || body2.getUserData().equals( "Enemy" )) ) {

            for ( Enemy enemy : allEnemies ) {

                if( enemy != null ) {
                	
                    if ( enemy.getBody().equals( body1.getBody() )) {
                    	
                        ((Crazy)enemy).changeDirectionRandom();
                    }
                    
                    else if ( enemy.getBody().equals( body2.getBody() )) {

                    	((Crazy)enemy).changeDirectionRandom();
                    }

                }
                
             }

        }
        
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
    }




} //End
