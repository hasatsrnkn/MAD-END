/**
 * 
 */
package levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.bullet.linearmath.btVector3;
import com.badlogic.gdx.physics.bullet.linearmath.btVector4;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cscats.madend.GameMain;

import characters.Character;
import characters.Guardian;
import characters.Player;
import helpers.GameInfo;
import helpers.GameManager;
import huds.UIHud;
import obstacle.MapBoundaries;
import obstacle.Obstacle;
import throwables.Bullet;
import viewers.*;

import java.util.ArrayList;

/**
 * @author Mehmet Eren Balasar
 *
 */
public class Level implements Screen, ContactListener {


    //Properties
    protected GameMain game;
    
    protected Texture bg;
    protected Texture sideBg;
    protected Character player;
//    private CharacterView characterView;
    protected PlayerView playerView;
    protected MapBoundaries mapBoundaries;
    protected ObstacleView obstacleView;

    protected World world;

    protected OrthographicCamera mainCamera;
    protected Viewport gameViewport;
    protected Vector3 vector3;

    private UIHud uiHud;

    private boolean isShooterLevel;

    protected ArrayList<Bullet> allBullets;
    protected ArrayList<Bullet> bulletsToRemove;

    protected WallView mapBoundaryWallView1;
    protected WallView mapBoundaryWallView2;
    protected WallView mapBoundaryWallView3;
    protected WallView mapBoundaryWallView4;

    private BulletView bulletViewer;
    
    //tester
    private Box2DDebugRenderer bodyRenderer; 
    private OrthographicCamera box2DCam;
    private ArrayList<Vector2> pointsArray = new ArrayList<Vector2>();
    

    public Level( GameMain game, String bgName ) {


        this.game = game;
        this.bg = new Texture(bgName);
        sideBg = bg;
        
        world = new World( new Vector2(0 , 0), true );
        world.setContactListener( this );

        mapBoundaries = new MapBoundaries(world, bg.getHeight(), bg.getWidth(), (bg.getHeight()) ,
                (40), (40),(bg.getWidth() ));


        mapBoundaryWallView1 = new WallView( "Obstacles/Level 1/Wall1.png", mapBoundaries.getBoundaryWalls().get(0) );
        mapBoundaryWallView2 = new WallView( "Obstacles/Level 1/Wall1.png", mapBoundaries.getBoundaryWalls().get(1) );
        mapBoundaryWallView3 = new WallView( "Obstacles/Level 1/Wall2.png", mapBoundaries.getBoundaryWalls().get(2) );
        mapBoundaryWallView4 = new WallView( "Obstacles/Level 1/Wall2.png", mapBoundaries.getBoundaryWalls().get(3) );


        mainCamera = new OrthographicCamera( GameInfo.WIDTH / 1.3f , GameInfo.HEIGHT / 1.3f );
        gameViewport = new StretchViewport( GameInfo.WIDTH, GameInfo.HEIGHT, mainCamera);
        vector3 = new Vector3( 0, 0, 0);
        
        allBullets = new ArrayList<Bullet>();
        bulletsToRemove = new ArrayList<Bullet>();

        uiHud = new UIHud( this.game );
        uiHud.getStage().act();
        //JUST FOR INITIALIZATION !é
        bulletViewer = new BulletView( "Throwables/Bullet1.png", new Bullet( world, 10f,10f,
                10f,10, 10f )  );
        //JUST FOR INITIALIZATION İ
        
    }

    
    public void update( float dt ) {
        if( !GameManager.getInstance().isPaused ){
        	
            world.step( dt, 6 ,2 );
        	
            allBullets.add(((Player)player).handleMouseInput( dt, vector3.x, vector3.y));
            ((Player)player).handleMoveInput( dt );
            player.updateCharacter();
            moveCamera();
            removeBullets();

        }


    }


    public void drawAllBullets(SpriteBatch spriteBatch) {
        for( Bullet bullet: allBullets ) {
            if ( bullet != null ) {
                bulletViewer.setBullet( bullet );
                bulletViewer.setRotationOfBullet();
                bulletViewer.drawBullet( spriteBatch );
            }
        }
    }
    public void removeBullets() {

        for (Bullet bullet : allBullets) {
            if( bullet != null ) {
                if (bullet.isRemove()) {
                    bulletsToRemove.add(bullet);
                    bullet.destroyBullet();
                }
            }
        }
        allBullets.removeAll( bulletsToRemove );
        bulletsToRemove.clear();
    }

    public void moveCamera() {
    	
        mainCamera.position.x = player.getXPosition();
        mainCamera.position.y = player.getYPosition();
        
        mainCamera.update();
        
    }

    @Override
    public void show() {

    }

    @Override
    /**
     * close the batch in child classes!!!!!
     */
    public void render(float delta) {

        update( delta );
        
        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        vector3.set(Gdx.input.getX(), Gdx.input.getY(), 0f);
        mainCamera.unproject(vector3);

        game.getBatch().setProjectionMatrix( mainCamera.combined );
        	
        game.getBatch().begin();
        
        game.getBatch().draw( bg, 0, 0);
        game.getBatch().draw( bg, -bg.getWidth(), 0);
        game.getBatch().draw( bg, -bg.getWidth(), bg.getHeight());
        game.getBatch().draw( bg, 0,  bg.getHeight());
        game.getBatch().draw( bg, bg.getWidth(), bg.getHeight());
        game.getBatch().draw( bg, bg.getWidth(), 0);
        game.getBatch().draw( bg, 0, -bg.getHeight());
        game.getBatch().draw( bg, -bg.getWidth(), -bg.getHeight());
        game.getBatch().draw( bg, bg.getWidth(), -bg.getHeight());
        drawAllBullets( game.getBatch() );

        mapBoundaryWallView1.drawWall(game.getBatch());
        mapBoundaryWallView2.drawWall(game.getBatch());
        mapBoundaryWallView3.drawWall(game.getBatch());
        mapBoundaryWallView4.drawWall(game.getBatch());

    }

    public ArrayList<Bullet> getAllBullets() {
        return allBullets;
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
    	bulletViewer.getTexture().dispose();

        playerView.getTexture().dispose();
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

    public UIHud getUiHud() {
        return uiHud;
    }

    public void healthByDifficulty() {
        if( GameInfo.DIFFICULT_TICK == 1) {
            uiHud.setHealth( GameManager.getInstance().healthScore + 2 );
        }

        if( GameInfo.DIFFICULT_TICK == 2) {
            uiHud.setHealth( GameManager.getInstance().healthScore + 1 );
        }

    }

}

	
	
	
	
	
	
	
	
