package huds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cscats.madend.GameMain;
import helpers.GameInfo;
import helpers.GameManager;
import scenes.MainMenu;

/**
 * Buttons for Welcome Screen
 * @author Mehmet Hasat Serinkan
 * @date 07.12.2021
 */
public class WelcomeScreenButtons {

    //Properties
    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;
    private ImageButton playButton;

    public WelcomeScreenButtons( GameMain game ) {
        this.game = game;

        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT,
                new OrthographicCamera());

        stage = new Stage( gameViewport, game.getBatch() );

        createAndPosition();

        Gdx.input.setInputProcessor( this.stage );

        stage.addActor( playButton );

        addListener();
    }

    public void createAndPosition() {

        playButton = new ImageButton( new SpriteDrawable( new Sprite(
                new Texture("Buttons/Welcome Screen/Welcome Screen Button.png" ))));

        playButton.setPosition( GameInfo.WIDTH / 2f + 200, GameInfo.HEIGHT / 2f - 400);

    }

    public void addListener() {
        playButton.addListener( new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen( new MainMenu( game ) );
            }
        });
    }

    public Stage getStage() {
        return this.stage;
    }











} //Welcome Screen buttons end
