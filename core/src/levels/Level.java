/**
 * 
 */
package levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cscats.madend.GameMain;

import characters.Character;
import characters.Guardian;
import characters.Player;
import helpers.GameInfo;
import obstacle.MapBoundaries;
import obstacle.Obstacle;
import viewers.CharacterView;
import viewers.GuardianView;
import viewers.ObstacleView;
import viewers.PlayerView;
import viewers.WallView;

/**
 * @author Mehmet Eren Balasar
 *
 */
public class Level implements Screen, ContactListener {


    //Properties
    protected GameMain game;
    
    protected Texture bg;
    protected Character player;
//    private CharacterView characterView;
    protected PlayerView playerView;
    protected MapBoundaries mapBoundaries;
    protected ObstacleView obstacleView;

    protected World world;

    protected OrthographicCamera mainCamera;
    protected Viewport gameViewport;
    protected Vector3 vector3;

    private boolean isShooterLevel;
    
    private WallView mapBoundaryWallView1;
    private WallView mapBoundaryWallView2;
    private WallView mapBoundaryWallView3;
    private WallView mapBoundaryWallView4;
    
    //tester
    private Box2DDebugRenderer bodyRenderer; 
    private OrthographicCamera box2DCam;
    

    public Level( GameMain game, String bgName ) {


        this.game = game;
        this.bg = new Texture(bgName);
        
        world = new World( new Vector2(0 , 0), true );
        world.setContactListener( this );

        mapBoundaries = new MapBoundaries(world, bg.getHeight(), bg.getWidth(), (bg.getHeight()) ,
                (100), (100),(bg.getWidth() ));


        mapBoundaryWallView1 = new WallView( "Obstacles/Level 1/Wall1.png", mapBoundaries.getBoundaryWalls().get(0) );
        mapBoundaryWallView2 = new WallView( "Obstacles/Level 1/Wall1.png", mapBoundaries.getBoundaryWalls().get(1) );
        mapBoundaryWallView3 = new WallView( "Obstacles/Level 1/Wall2.png", mapBoundaries.getBoundaryWalls().get(2) );
        mapBoundaryWallView4 = new WallView( "Obstacles/Level 1/Wall2.png", mapBoundaries.getBoundaryWalls().get(3) );


        mainCamera = new OrthographicCamera( GameInfo.WIDTH / 1.3f , GameInfo.HEIGHT / 1.3f );
        gameViewport = new StretchViewport( GameInfo.WIDTH, GameInfo.HEIGHT, mainCamera);
        vector3 = new Vector3( 0, 0, 0);
        
        
        //tester
        box2DCam = new OrthographicCamera();
        box2DCam.setToOrtho(false, GameInfo.WIDTH / GameInfo.PPM, GameInfo.HEIGHT / GameInfo.PPM);
        box2DCam.position.set(GameInfo.WIDTH / 2, GameInfo.WIDTH / 2, 0);
        bodyRenderer = new Box2DDebugRenderer();
        
    }

    
    public void update( float dt ) {
    	
        ((Player)player).handleMoveInput( dt );
        ((Player)player).handleMouseInput( dt, vector3.x, vector3.y);
        player.updateCharacter();
        moveCamera();

    }

    public void moveCamera() {
    	
        mainCamera.position.x = player.getXPosition();
        mainCamera.position.y = player.getYPosition();
        
        //test
        box2DCam.position.x = player.getBody().getPosition().x;
        box2DCam.position.y = player.getBody().getPosition().y;
        box2DCam.update();
        
        mainCamera.update();
        
    }

    @Override
    public void show() {

    }
    
    public void renderBodies(float delta) {
        
		bodyRenderer.render(world, box2DCam.combined);
    }

    @Override
    /**
     * close the batch in child classes!!!!!
     */
    public void render(float delta) {

        update( delta );

        world.step( delta, 6 ,2 );
        
        Gdx.gl.glClearColor( 1, 0, 0, 1 );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        vector3.set(Gdx.input.getX(), Gdx.input.getY(), 0f);
        mainCamera.unproject(vector3);

        game.getBatch().setProjectionMatrix( mainCamera.combined );
        	
        game.getBatch().begin();
        
        game.getBatch().draw( bg, 0, 0);
        
        mapBoundaryWallView1.drawWall(game.getBatch());
        mapBoundaryWallView2.drawWall(game.getBatch());
        mapBoundaryWallView3.drawWall(game.getBatch());
        mapBoundaryWallView4.drawWall(game.getBatch());

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
        playerView.getBulletViewer().getTexture().dispose();
        playerView.getTexture().dispose();
//        characterView.getTexture().dispose();
        world.dispose();

    }



	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}
 

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }


	public boolean isShooterLevel() {
		return isShooterLevel;
	}


	public void setShooterLevel(boolean isShooterLevel) {
		this.isShooterLevel = isShooterLevel;
	}


}

	
	
	
	
	
	
	
	
