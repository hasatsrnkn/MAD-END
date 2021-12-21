package levels;

import characters.Guardian;
import characters.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.cscats.madend.GameMain;
import helpers.GameInfo;
import helpers.GameManager;
import obstacle.Rock;
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

    public Level2(GameMain game, String bgName) {

        super(game, bgName);

        rocks = new Rock[ 20 ];
        healthByDifficulty();

        createRocks();

        mapBoundaryWallView1 = new WallView( "Obstacles/Level 2/Wall1.png", mapBoundaries.getBoundaryWalls().get(0) );
        mapBoundaryWallView2 = new WallView( "Obstacles/Level 2/Wall1.png", mapBoundaries.getBoundaryWalls().get(1) );
        mapBoundaryWallView3 = new WallView( "Obstacles/Level 2/Wall2.png", mapBoundaries.getBoundaryWalls().get(2) );
        mapBoundaryWallView4 = new WallView( "Obstacles/Level 2/Wall2.png", mapBoundaries.getBoundaryWalls().get(3) );


        player = new Player(world, 300, 200, GameInfo.PLAYER_HEIGHT, GameInfo.PLAYER_WIDTH);
        playerView = new PlayerView( "Player/Player.png", (Player) player);

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
