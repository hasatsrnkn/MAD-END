package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cscats.madend.GameMain;
import helpers.GameInfo;
import scenes.MainMenu;

/**
 * Buttons for Settings screen
 * @author Mehmet Hasat Serinkan
 * @date 20.12.2021
 */
public class SettingsScreenButtons {

    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;
    private ImageButton easyButton;
    private ImageButton mediumButton;
    private ImageButton hardButton;
    private ImageButton lowButton;
    private ImageButton normalButton;
    private ImageButton highButton;
    private ImageButton yesButton;
    private ImageButton noButton;
    private ImageButton backButton;
    private Image difficultyTick;
    private Image soundTick;
    private Image fullscreenTick;

    public SettingsScreenButtons( GameMain game) {
        this.game = game;

        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT,
                new OrthographicCamera());

        stage = new Stage( gameViewport, game.getBatch() );

        createAndPositionButtons();

        Gdx.input.setInputProcessor( stage );

        stage.addActor( easyButton );
        stage.addActor( mediumButton );
        stage.addActor( hardButton );
        stage.addActor( lowButton );
        stage.addActor( normalButton );
        stage.addActor( highButton );
        stage.addActor( yesButton );
        stage.addActor( noButton );
        stage.addActor( difficultyTick);
        stage.addActor( soundTick );
        stage.addActor( fullscreenTick );
        stage.addActor( backButton );

        addListener();
    }

    public void createAndPositionButtons() {
        easyButton = new ImageButton( new SpriteDrawable( new Sprite(
                new Texture("Buttons/Settings Screen/Easy Button.png" ))));

        mediumButton = new ImageButton( new SpriteDrawable( new Sprite(
                new Texture("Buttons/Settings Screen/Medium Button.png" ))));

        hardButton = new ImageButton( new SpriteDrawable( new Sprite(
                new Texture("Buttons/Settings Screen/Hard Button.png" ))));

        lowButton = new ImageButton( new SpriteDrawable( new Sprite(
                new Texture("Buttons/Settings Screen/Low Button.png" ))));

        normalButton = new ImageButton( new SpriteDrawable( new Sprite(
                new Texture("Buttons/Settings Screen/Normal Button.png" ))));

        highButton = new ImageButton( new SpriteDrawable( new Sprite(
                new Texture("Buttons/Settings Screen/High Button.png" ))));

        yesButton = new ImageButton( new SpriteDrawable( new Sprite(
                new Texture("Buttons/Settings Screen/Yes Button.png" ))));

        noButton = new ImageButton( new SpriteDrawable( new Sprite(
                new Texture("Buttons/Settings Screen/No Button.png" ))));

        backButton = new ImageButton( new SpriteDrawable( new Sprite(
                new Texture("Buttons/Main Menu/Back Button.png" ))));

        difficultyTick = new Image( new SpriteDrawable( new Sprite(
                new Texture("Buttons/Settings Screen/Tick.png" ))));

        soundTick = new Image( new SpriteDrawable( new Sprite(
                new Texture("Buttons/Settings Screen/Tick.png" ))));

        fullscreenTick = new Image( new SpriteDrawable( new Sprite(
                new Texture("Buttons/Settings Screen/Tick.png" ))));


        easyButton.setPosition( 600, 567);
        mediumButton.setPosition(1050, 567);
        hardButton.setPosition(1500, 567);

        lowButton.setPosition(600,305);
        normalButton.setPosition(1050,305);
        highButton.setPosition(1500,305);

        yesButton.setPosition(750,30);
        noButton.setPosition(1125,30);
        backButton.setPosition( GameInfo.WIDTH / 2f - 975, GameInfo.HEIGHT / 2f + 350 );

        difficultyTick.setPosition(1290,635);
        soundTick.setPosition(1290,370);
        fullscreenTick.setPosition(1370,100);

    }

    public void addListener() {

        easyButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        mediumButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        hardButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        lowButton.addListener( new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
            }
        });

        normalButton.addListener( new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
            }
        });

        highButton.addListener( new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
            }
        });

        yesButton.addListener( new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.graphics.setFullscreenMode( Gdx.graphics.getDisplayMode());
                fullscreenTick.setX( noButton.getX() - 120 );
            }
        });

        noButton.addListener( new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                fullscreenTick.setPosition(1370,100);
                Gdx.graphics.setResizable( true );
                Gdx.graphics.setWindowedMode( GameInfo.WIDTH, GameInfo.HEIGHT );

            }
        });

        backButton.addListener( new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen( new MainMenu( game ) );
            }
        });
    }

    public Stage getStage() {
        return this.stage;
    }
}
