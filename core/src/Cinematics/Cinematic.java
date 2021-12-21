package Cinematics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cscats.madend.GameMain;
import helpers.GameInfo;
/**
 * Cinematic Class
 * @author Mehmet Hasat Serinkan
 * @date 21.12.2021
 */
public class Cinematic implements Screen {

    //Properties
    protected GameMain game;
    protected OrthographicCamera mainCamera;
    protected Viewport gameViewport;
    protected Texture bg;


    public Cinematic( GameMain game ) {
        this.game = game;

        mainCamera = new OrthographicCamera();
        mainCamera.setToOrtho( false, GameInfo.WIDTH, GameInfo.HEIGHT );
        mainCamera.position.set( GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0 );

        gameViewport = new StretchViewport( GameInfo.WIDTH, GameInfo.HEIGHT, mainCamera);
        bg = new Texture( "Cinematics/Cinematic 1.png" );

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin(); //Begin for drawing

        game.getBatch().draw( bg, 0, 0);

        game.getBatch().end(); //End for drawing



    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update( width, height);
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
} //End
