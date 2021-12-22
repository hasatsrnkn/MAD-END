/**
 * 
 */
package levels;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import Cinematics.Cinematic4;
import Cinematics.Cinematic8;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.cscats.madend.GameMain;

import characters.Crazy;
import characters.Enemy;
import characters.Player;
import helpers.GameInfo;
import helpers.GameManager;
import helpers.GifDecoder;
import huds.CupGame;
import huds.Puzzle;
import viewers.CrazyView;
import viewers.PlayerView;

/**
 * @author Mehmet Eren Balasar
 *
 */
public class Level3 extends Level {

	
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
	private Crazy crazy7;
	private CrazyView crazy7View;
	private Crazy crazy8;
	private CrazyView crazy8View;
	private Crazy crazy9;
	private CrazyView crazy9View;
	private Crazy crazy10;
	private CrazyView crazy10View;
	
    private ArrayList<Enemy> allEnemies;
    private ArrayList<Enemy> enemiesToRemove;
    
	private int collisionCount = 0;
	
	public Level3(GameMain game, String bgName) {
		
		super(game, bgName);
		this.setShooterLevel(false);

		
		allEnemies = new ArrayList<Enemy>();
		enemiesToRemove = new ArrayList<Enemy>();
		
        player = new Player(world, GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, GameInfo.PLAYER_HEIGHT, GameInfo.PLAYER_WIDTH, this.isShooterLevel());
        playerView = new PlayerView( "Player/Player.png", (Player) player);
		
		crazy1 = new Crazy(world, 1 * GameInfo.WIDTH / 10f, 6 * (GameInfo.HEIGHT / 10f), 
				GameInfo.CRAZY_HEIGHT, GameInfo.CRAZY_WIDTH);
		crazy1View = new CrazyView("Enemies/Crazy.png", crazy1, "EnemyAnimation/crazyani.atlas");
		allEnemies.add(crazy1);
		
		crazy2 = new Crazy(world, 2 * GameInfo.WIDTH / 10f, 3 * GameInfo.HEIGHT / 10f, 
				GameInfo.CRAZY_HEIGHT, GameInfo.CRAZY_WIDTH);
		crazy2View = new CrazyView("Enemies/Crazy.png", crazy2, "EnemyAnimation/crazyani.atlas");
		allEnemies.add(crazy2);
		
		crazy3 = new Crazy(world, 3 * GameInfo.WIDTH / 10f, 9 * GameInfo.HEIGHT / 10f, 
				GameInfo.CRAZY_HEIGHT, GameInfo.CRAZY_WIDTH);
		crazy3View = new CrazyView("Enemies/Crazy.png", crazy3, "EnemyAnimation/crazyani.atlas");
		allEnemies.add(crazy3);
		
		crazy4 = new Crazy(world, 4 * GameInfo.WIDTH / 10f, 5 * GameInfo.HEIGHT / 10f, 
				GameInfo.CRAZY_HEIGHT, GameInfo.CRAZY_WIDTH);
		crazy4View = new CrazyView("Enemies/Crazy.png", crazy4, "EnemyAnimation/crazyani.atlas");
		allEnemies.add(crazy4);
		
		crazy5 = new Crazy(world, 5 * GameInfo.WIDTH / 10f, 2 * GameInfo.HEIGHT / 10f, 
				GameInfo.CRAZY_HEIGHT, GameInfo.CRAZY_WIDTH);
		crazy5View = new CrazyView("Enemies/Crazy.png", crazy5, "EnemyAnimation/crazyani.atlas");
		allEnemies.add(crazy5);
		
		crazy6 = new Crazy(world, 6 * GameInfo.WIDTH / 10f, 8 * GameInfo.HEIGHT / 10f, 
				GameInfo.CRAZY_HEIGHT, GameInfo.CRAZY_WIDTH);
		crazy6View = new CrazyView("Enemies/Crazy.png", crazy6, "EnemyAnimation/crazyani.atlas");
		allEnemies.add(crazy6);
		
		crazy7 = new Crazy(world, 7 * GameInfo.WIDTH / 10f, 3 * GameInfo.HEIGHT / 10f, 
				GameInfo.CRAZY_HEIGHT, GameInfo.CRAZY_WIDTH);
		crazy7View = new CrazyView("Enemies/Crazy.png", crazy7, "EnemyAnimation/crazyani.atlas");
		allEnemies.add(crazy7);
		
		crazy8 = new Crazy(world, 8 * GameInfo.WIDTH / 10f, 2 * GameInfo.HEIGHT / 10f, 
				GameInfo.CRAZY_HEIGHT, GameInfo.CRAZY_WIDTH);
		crazy8View = new CrazyView("Enemies/Crazy.png", crazy8, "EnemyAnimation/crazyani.atlas");
		allEnemies.add(crazy8);
		
		crazy9 = new Crazy(world, 9 * GameInfo.WIDTH / 10f, 6 * GameInfo.HEIGHT / 10f, 
				GameInfo.CRAZY_HEIGHT, GameInfo.CRAZY_WIDTH);
		crazy9View = new CrazyView("Enemies/Crazy.png", crazy9, "EnemyAnimation/crazyani.atlas");
		allEnemies.add(crazy9);
		
		crazy10 = new Crazy(world, 10 * GameInfo.WIDTH / 10f, 1 * GameInfo.HEIGHT / 10f, 
				GameInfo.CRAZY_HEIGHT, GameInfo.CRAZY_WIDTH);
		crazy10View = new CrazyView("Enemies/Crazy.png", crazy10, "EnemyAnimation/crazyani.atlas");
		allEnemies.add(crazy10);
		
		Sound footstep = Gdx.audio.newSound( Gdx.files.internal( "Sounds/Level2FootStep.wav"));
		player.setFootStepVoice( footstep );
	}
	
	
	public void update(float delta) {
		
		if (!GameManager.getInstance().isPaused) {
			
			super.update(delta);
			crazy1.moveCrazy1();
			crazy2.moveCrazy1();
			crazy3.moveCrazy1();
			crazy4.moveCrazy1();
			crazy5.moveCrazy1();
			crazy6.moveCrazy1();
			crazy7.moveCrazy1();
			crazy8.moveCrazy1();
			crazy9.moveCrazy1();
			crazy10.moveCrazy1();
		
		}
	}
	
	
	public void render(float delta) {
		
		super.render(delta);
		
		playerView.drawPlayer(game.getBatch());
		
		crazy1View.drawCharacter(game.getBatch());
		crazy2View.drawCharacter(game.getBatch());
		crazy3View.drawCharacter(game.getBatch());
		crazy4View.drawCharacter(game.getBatch());
		crazy5View.drawCharacter(game.getBatch());
		crazy6View.drawCharacter(game.getBatch());
		crazy7View.drawCharacter(game.getBatch());
		crazy8View.drawCharacter(game.getBatch());
		crazy9View.drawCharacter(game.getBatch());
		crazy10View.drawCharacter(game.getBatch());
		
        getUiHud().stopGame();

        game.getBatch().end(); //End for drawing
        game.getBatch().setProjectionMatrix(getUiHud().getStage().getCamera().combined);
        getUiHud().getStage().draw();
        getUiHud().getStage().act();

		advanceToNextLevel();
		super.renderBodies(delta);
	}

	public void advanceToNextLevel() {

		//you can change this statement to how you want to advance to the next level
		//also it is currently advancing to level3
		if(player.getXPosition() >= 1800 && player.getYPosition() <= 100) {

			player.getFootStepVoice().stop();
			game.setScreen( new Cinematic8( game ));
		}
	}


	@Override
	public void beginContact(Contact contact) {
		
		Fixture body1 = contact.getFixtureA();
        Fixture body2 = contact.getFixtureB();;

        if ( (body1.getUserData().equals("Enemy") && body2.getUserData().equals( "Obstacle" )) ||
                (body2.getUserData().equals("Enemy") && body1.getUserData().equals( "Obstacle" ) )) {

            for ( Enemy enemy : allEnemies ) {

                if( enemy != null ) {
                	
                    if ( enemy.getBody().equals( body1.getBody() )) {
                    	
                        ((Crazy)enemy).changeDirection(false, true);
                    }
                    
                    else if ( enemy.getBody().equals( body2.getBody() )) {

                    	((Crazy)enemy).changeDirection(false, true);
                    }

                }
                
             }

        }
      
        else if((body1.getUserData().equals("Enemy") && body2.getUserData().equals( "Player" )) ||
                (body2.getUserData().equals("Enemy") && body1.getUserData().equals( "Player" ) )) {
        	
        	if(collisionCount == 0) {
        		GameManager.getInstance().isPaused = true;
        		Puzzle puzzle = new Puzzle();
        		if(puzzle.gameOver() == true) {
        			
        			GameManager.getInstance().isPaused = false;
        		}
        		
        		collisionCount++;
        		
        	}
        	
        	else if (collisionCount == 1) {
        		
           		GameManager.getInstance().isPaused = true;
           		
        		CupGame cupGame = new CupGame();
        		
                JFrame frame = new JFrame();
                frame.getContentPane().add(cupGame);
                frame.setVisible(true);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        		
                System.out.println(cupGame.isGameOver());
        		if(cupGame.isGameOver()) {
        			
        			GameManager.getInstance().isPaused = false;

        		}
        		
        		else {
        			
        			
        		}

        	}
        	
        	else {
        		System.out.println("devam");
        	}
        	collisionCount++;
        }
        
        
        
        
//        
//        
//
//        if (body1.getUserData().equals("Enemy") && body2.getUserData().equals("Player") ) {
//            System.out.println("Puzzle interface should be implemented here.");
//
//        }
//        
//        else if (body2.getUserData().equals("Enemy") && body1.getUserData().equals("Player") ) {
//            System.out.println("Puzzle interface should be implemented here.");
//
//        }
//        
//        if (body1.getUserData().equals("Enemy") && body2.getUserData().equals("Obstacle") ) {
//        	
//        	((Crazy) crazy1).changeDirection(false, true);
//        }
//        
//        else if (body2.getUserData().equals("Enemy") && body1.getUserData().equals("Obstacle") ) {
//
//        	((Crazy) crazy1).changeDirection(false, true);
//        }
//        
//        if (body1.getUserData().equals("Enemy") && body2.getUserData().equals("Enemy") ) {
//        	
//        	((Crazy) crazy1).changeDirection(false, true);
//        }
//        
//        else if (body2.getUserData().equals("Enemy") && body1.getUserData().equals("Enemy") ) {
//
//        	((Crazy) crazy1).changeDirection(false, true);
//        }
//		
	}
	

}
