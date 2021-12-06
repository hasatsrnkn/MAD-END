package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cscats.madend.GameMain;
import helpers.GameInfo;
import huds.WelcomeScreenButtons;

/**
 * Welcome Screen
 * @author Mehmet Hasat Serinkan
 * @date 07.12.2021
 */
public class WelcomeScreen implements Screen {

    //Properties
    private GameMain game;
    private OrthographicCamera mainCamera;
    private Viewport gameViewport;
    private Texture bg;
    private WelcomeScreenButtons button;

    public WelcomeScreen( GameMain game ) {
        this.game = game;

        mainCamera = new OrthographicCamera();
        mainCamera.setToOrtho( false, GameInfo.WIDTH, GameInfo.HEIGHT );
        mainCamera.position.set( GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0 );

        gameViewport = new StretchViewport( GameInfo.WIDTH, GameInfo.HEIGHT, mainCamera);

        bg = new Texture( "Backgrounds/Welcome Screen.png" );
        button = new WelcomeScreenButtons( game );

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor( 1, 0, 0, 1 );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin(); //Begin for drawing

        game.getBatch().draw( bg, 0, 0);

        game.getBatch().end(); //End for drawing

        game.getBatch().setProjectionMatrix( button.getStage().getCamera().combined);
        button.getStage().draw();

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
        bg.dispose();
        button.getStage().dispose();
    }


} //End Of Main Menu
