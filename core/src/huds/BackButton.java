package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cscats.madend.GameMain;
import helpers.GameInfo;
import scenes.MainMenu;

/**
 * Back Button
 * @author Mehmet Hasat Serinkan
 * @date 10.12.2021
 */
public class BackButton {

    //Properties
    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;
    private ImageButton backButton;

    public BackButton( GameMain game ) {
        this.game = game;

        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT,
                new OrthographicCamera());

        stage = new Stage( gameViewport, game.getBatch() );

        Gdx.input.setInputProcessor( stage );

        create();

        stage.addActor( backButton );

        addListener();


    }

    public void create() {

        backButton = new ImageButton( new SpriteDrawable( new Sprite(
                new Texture("Buttons/Main Menu/Back Button.png" ))));


        backButton.setPosition( GameInfo.WIDTH / 2f - 975, GameInfo.HEIGHT / 2f + 350);
    }

    public void addListener() {
        backButton.addListener( new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen( new MainMenu( game ) );
            }
        });

    }

    public Stage getStage() {
        return stage;
    }
} //End
