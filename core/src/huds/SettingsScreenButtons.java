package huds;

import com.badlogic.gdx.Game;
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
    private int difficultTickPoint;
    private int volumeTickPoint;
    private int fullscreenTickPoint;

    public SettingsScreenButtons( GameMain game, int difficultTickPoint, int volumeTickPoint, int fullscreenTickPoint) {
        this.game = game;

        this.difficultTickPoint = difficultTickPoint;
        this.volumeTickPoint = volumeTickPoint;
        this.fullscreenTickPoint = fullscreenTickPoint;

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

        if(difficultTickPoint == 1) {
            difficultyTick.setPosition(850,635);
        }
        if( difficultTickPoint == 2) {
            difficultyTick.setPosition(1300,635);
        }
        if( difficultTickPoint == 3) {
            difficultyTick.setPosition(1750,635);
        }
        if(volumeTickPoint == 1) {
            soundTick.setPosition(850,370);
        }
        if( volumeTickPoint == 2) {
            soundTick.setPosition(1300,370);
        }
        if( volumeTickPoint == 3) {
            soundTick.setPosition(1750,370);
        }
        if( fullscreenTickPoint == 1) {
            fullscreenTick.setPosition(1000,100);
        }
        if ( fullscreenTickPoint == 2) {
            fullscreenTick.setPosition(1370,100);
        }

    }

    public void addListener() {

        easyButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameInfo.DIFFICULT_TICK = 1;
                difficultyTick.setPosition(850,635);
                GameInfo.PLAYER_HEALTH = 7;
                GameInfo.CHARACTER_HEALTH = 2;

            }
        });

        mediumButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameInfo.DIFFICULT_TICK = 2;
                difficultyTick.setPosition(1300,635);
                GameInfo.PLAYER_HEALTH = 5;
                GameInfo.CHARACTER_HEALTH = 3;
            }
        });

        hardButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameInfo.DIFFICULT_TICK = 3;
                difficultyTick.setPosition(1750,635);
                GameInfo.PLAYER_HEALTH = 4;
                GameInfo.CHARACTER_HEALTH = 4;
            }
        });

        lowButton.addListener( new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameInfo.VOLUME_TICK = 1;
                soundTick.setPosition(850,370);
                GameInfo.GUNSHOT_VOLUME = 0.5f;
                GameInfo.FOOTSTEP_VOLUME = 0.3f;
            }
        });

        normalButton.addListener( new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameInfo.VOLUME_TICK = 2;
                soundTick.setPosition(1300,370);
                GameInfo.GUNSHOT_VOLUME = 0.7f;
                GameInfo.FOOTSTEP_VOLUME = 0.5f;
            }
        });

        highButton.addListener( new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameInfo.VOLUME_TICK = 3;
                soundTick.setPosition(1750,370);
                GameInfo.GUNSHOT_VOLUME = 0.9f;
                GameInfo.FOOTSTEP_VOLUME = 0.7f;
            }
        });

        yesButton.addListener( new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameInfo.FULLSCREEN_TICK = 1;
                fullscreenTick.setPosition(1000,100);
                Gdx.graphics.setFullscreenMode( Gdx.graphics.getDisplayMode());
            }
        });

        noButton.addListener( new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameInfo.FULLSCREEN_TICK = 2;
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
