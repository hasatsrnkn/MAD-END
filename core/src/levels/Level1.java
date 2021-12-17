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
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
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
public class Level1 implements Screen {

    //Properties
    private GameMain game;

    private Texture bg;
    private Character player;
    private CharacterView characterView;
    private PlayerView playerView;
    private MapBoundaries mapBoundaries;
    private ObstacleView obstacleView;

    private World world;

    
    private OrthographicCamera mainCamera;
    private Viewport gameViewport;
    private Vector3 vector3;

   
    private Character guardian1;
    private GuardianView guardian1View;
    
    //test
    Box2DDebugRenderer bodyRenderer; 
    OrthographicCamera box2DCam;
    
    public Level1( GameMain game ) {


        this.game = game;
        
        bg = new Texture( "Level Backgrounds/Level 1 Background.png" );

        world = new World( new Vector2(0 , 0), true );

        mapBoundaries = new MapBoundaries(world, bg.getHeight(), bg.getWidth(), (bg.getHeight()) ,
                (100), (100),(bg.getWidth() ));
        obstacleView = new ObstacleView( "Obstacles/Level 1/Wall.png",new Obstacle( this.world, 200,200,100, 41) );

        player = new Player(world, GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, GameInfo.PLAYER_HEIGHT, GameInfo.PLAYER_WIDTH);
        playerView = new PlayerView( "Player/Player.png", (Player) player);
        
        guardian1 = new Guardian(world, GameInfo.WIDTH / 2f + 120, GameInfo.HEIGHT / 2f, GameInfo.GUARDIAN_HEIGHT, GameInfo.GUARDIAN_WIDTH);
        guardian1View = new GuardianView("Enemies/Guardian.png", (Guardian)guardian1, "PlayerAnimation/PlayerAnimation.atlas");


        mainCamera = new OrthographicCamera( GameInfo.WIDTH / 1.3f , GameInfo.HEIGHT / 1.3f );
        gameViewport = new StretchViewport( GameInfo.WIDTH, GameInfo.HEIGHT, mainCamera);
        vector3 = new Vector3( 0, 0, 0);
        
        box2DCam = new OrthographicCamera();
        box2DCam.setToOrtho(false, GameInfo.WIDTH / GameInfo.PPM, GameInfo.HEIGHT / GameInfo.PPM);
        box2DCam.position.set(GameInfo.WIDTH / 2, GameInfo.WIDTH / 2, 0);
        bodyRenderer = new Box2DDebugRenderer();
        
    }

    
    public void update( float dt ) {
    	
        ((Player)player).handleMoveInput( dt );
        ((Player)player).handleMouseInput( dt, vector3.x, vector3.y);
        obstacleView.getObstacle().updateObstacle();
        player.updateCharacter();
        moveCamera();

    }

    public void moveCamera() {
    	
        mainCamera.position.x = player.getXPosition();
        mainCamera.position.y = player.getYPosition();
        
        box2DCam.position.x = player.getBody().getPosition().x;
        box2DCam.position.y = player.getBody().getPosition().y;
        
        box2DCam.update();
        mainCamera.update();
        
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        update( delta );

        Gdx.gl.glClearColor( 1, 0, 0, 1 );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        vector3.set(Gdx.input.getX(), Gdx.input.getY(), 0f);
        mainCamera.unproject(vector3);

        game.getBatch().begin(); //Begin for drawing

        game.getBatch().draw( bg, 0, 0);

        playerView.drawPlayer( game.getBatch() ); //drawPlayer may be changed to drawCharacter  ******!!!!!!
        playerView.drawCharacterAnimation(game.getBatch());
        obstacleView.drawObstacle(game.getBatch());
        for( int i = 0; i < 2; i = i + 1) {
            WallView wallView = new WallView( "Obstacles/Level 1/Wall1.png", mapBoundaries.getBoundaryWalls().get(i) );
            wallView.drawWallView(game.getBatch());
        }
        for( int i = 2; i < 4; i = i + 1) {
            WallView wallView = new WallView( "Obstacles/Level 1/Wall2.png", mapBoundaries.getBoundaryWalls().get(i) );
            wallView.drawWallView(game.getBatch());
        }
        game.getBatch().end(); //End for drawing

        game.getBatch().setProjectionMatrix( mainCamera.combined );

        //playerView.drawBody(mainCamera); //debugrenderer does not work???
        
		
		bodyRenderer.render(world, box2DCam.combined);
        
        world.step( delta, 6 ,2 );
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
        bg.dispose();
    }

    @Override
    public void dispose() {
       
    	bg.dispose();
    }


}
