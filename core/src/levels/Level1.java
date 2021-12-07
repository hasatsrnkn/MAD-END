package levels;

import characters.Character;
import characters.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cscats.madend.GameMain;
import helpers.GameInfo;

public class Level1 implements Screen {

    //Properties
    private GameMain game;
    private Texture bg;
    private Character player;
    private World world;
    private OrthographicCamera mainCamera;
    private Viewport gameViewport;

    public Level1( GameMain game ) {
        this.game = game;
        bg = new Texture( "Level Backgrounds/Level 1 Background.png" );
        world = new World( new Vector2(0 , 0), true );
        player = new Player( world, GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, "Player/Player.png");
        mainCamera = new OrthographicCamera( player.getX() * 1.5f , player.getY() * 1.5f );
        gameViewport = new StretchViewport( GameInfo.WIDTH, GameInfo.HEIGHT, mainCamera);

    }


    public void update( float dt ) {
        handleInput( dt );
        moveCamera();
        player.updateCharacter();
        mainCamera.update();
    }

    public void moveCamera() {
        mainCamera.position.x = player.getX();
        mainCamera.position.y = player.getY();
    }

    public void handleInput( float dt ) {


       if( Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.S) ||
               Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D) ) {

           if (Gdx.input.isKeyPressed(Input.Keys.W)) {
               player.moveCharacter(player.getBody().getLinearVelocity().x, 3);
           }
           if (Gdx.input.isKeyPressed(Input.Keys.S)) {
               player.moveCharacter(player.getBody().getLinearVelocity().x, -3);
           }
           if (Gdx.input.isKeyPressed(Input.Keys.A)) {
               player.moveCharacter(-3, player.getBody().getLinearVelocity().y);
           }

           if (Gdx.input.isKeyPressed(Input.Keys.D)) {
               player.moveCharacter(3, player.getBody().getLinearVelocity().y);
           }
       }
        else {
            player.moveCharacter( 0 ,0 );
        }

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        update( delta );

        Gdx.gl.glClearColor( 1, 0, 0, 1 );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin(); //Begin for drawing

        game.getBatch().draw( bg, 0, 0);
        player.drawCharacter( game.getBatch() );

        game.getBatch().end(); //End for drawing

        game.getBatch().setProjectionMatrix( mainCamera.combined );

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

    }

    @Override
    public void dispose() {

    }
}
