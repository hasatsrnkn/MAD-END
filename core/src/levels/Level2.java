package levels;

import characters.Crazy;
import characters.Enemy;
import characters.Guardian;
import characters.Player;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.cscats.madend.GameMain;
import helpers.GameInfo;
import helpers.GameManager;
import obstacle.Rock;
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
        

        Sound footstep = Gdx.audio.newSound( Gdx.files.internal( "Sounds/Level2FootStep.wav"));
        player.setFootStepVoice( footstep );

    }

    public void update( float dt ) {
    	
        if (!GameManager.getInstance().isPaused) {
            super.update(dt);
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
        rocks[4] = new Rock( world, 600, 325, 200, 200 );

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        super.render(delta);

        playerView.drawPlayer(game.getBatch()); //drawPlayer may be changed to drawCharacter  ******!!!!!!

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






















} //End
