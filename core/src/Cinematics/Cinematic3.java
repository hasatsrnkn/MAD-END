package Cinematics;

import com.badlogic.gdx.graphics.Texture;
import com.cscats.madend.GameMain;

public class Cinematic3 extends Cinematic{
    //Properties
    protected CinematicNextButton3 button;

    public Cinematic3(GameMain game ) {
        super( game );
        bg = new Texture( "Cinematics/Cinematic 3.png" );
        button = new CinematicNextButton3( game );
        button.getStage().act();

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        super.render( delta );

        game.getBatch().setProjectionMatrix( button.getStage().getCamera().combined);
        button.getStage().draw();
        button.getStage().act( delta );
    }

    @Override
    public void resize(int width, int height) {
        super.resize( width, height );
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
        button.getStage().dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
        button.getStage().dispose();
    }
}
