package levels;

import Cinematics.Cinematic10;
import Cinematics.Cinematic8;
import characters.Boss;
import characters.Guardian;
import characters.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cscats.madend.GameMain;
import helpers.GameInfo;
import helpers.GameManager;
import throwables.Bullet;
import viewers.BossView;
import viewers.GuardianView;
import viewers.PlayerView;
import viewers.WallView;

/**
 * Level 4
 * @author Mehmet Hasat Serinkan
 * @date 21.12.2021
 */
public class Level4 extends Level implements Screen, ContactListener {

    private Boss boss;
    private BossView bossView;
    public Level4(GameMain game, String bgName) {
        super(game, bgName);

        healthByDifficulty();

        mapBoundaryWallView1 = new WallView( "Obstacles/Level 1/Wall1.png", mapBoundaries.getBoundaryWalls().get(0) );
        mapBoundaryWallView2 = new WallView( "Obstacles/Level 1/Wall1.png", mapBoundaries.getBoundaryWalls().get(1) );
        mapBoundaryWallView3 = new WallView( "Obstacles/Level 1/Wall2.png", mapBoundaries.getBoundaryWalls().get(2) );
        mapBoundaryWallView4 = new WallView( "Obstacles/Level 1/Wall2.png", mapBoundaries.getBoundaryWalls().get(3) );


        player = new Player(world, 300, 900, GameInfo.PLAYER_HEIGHT, GameInfo.PLAYER_WIDTH, false);
        playerView = new PlayerView( "Player/Player.png", (Player) player);

        Sound footstep = Gdx.audio.newSound( Gdx.files.internal( "Sounds/Level2FootStep.wav"));
        player.setFootStepVoice( footstep );

        boss = new Boss(world, 950, 900, GameInfo.PLAYER_HEIGHT, GameInfo.PLAYER_WIDTH);
        bossView = new BossView("Player/Player.png", boss, "PlayerAnimation/PlayerAnimation.atlas");

    }

    public void update( float dt ) {
        if (!GameManager.getInstance().isPaused) {

            ((Player)player).handleMoveInputBossLevel( dt );
            player.updateCharacter();
            moveCamera();
            boss.updateCharacter();
            boss.moveBoss();
        }
    }

    public void moveCamera() {
        super.moveCamera();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        super.render( delta );
        playerView.drawPlayer(game.getBatch()); //drawPlayer may be changed to drawCharacter  ******!!!!!!

        getUiHud().stopGame();
        bossView.drawCharacter(game.getBatch());
        game.getBatch().end(); //End for drawing
        game.getBatch().setProjectionMatrix(getUiHud().getStage().getCamera().combined);
        getUiHud().getStage().draw();
        getUiHud().getStage().act();
        world.step(Gdx.app.getGraphics().getDeltaTime(), 3, 3);

        //tester
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

    public void advanceToNextLevel() {

        //you can change this statement to how you want to advance to the next level
        //also it is currently advancing to level3
        if(boss.getXPosition() >= 2900) {
            GameManager.getInstance().checkForNewHighScore();
            player.getFootStepVoice().stop();
            game.setScreen( new Cinematic10( game ));

        }
    }






















} //End
