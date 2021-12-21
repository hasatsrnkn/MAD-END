package huds;

import Cinematics.Cinematic1;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cscats.madend.GameMain;
import helpers.GameInfo;
import helpers.GameManager;
import levels.Level1;
import levels.Level2;
import scenes.CreditsScreen;
import scenes.SettingsScreen;

/**
 * Main Menu Buttons
 * @author Mehmet Hasat Serinkan
 * @date 07.12.2021
 */
public class MainMenuButtons {
    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;
    private ImageButton startButton;
    private ImageButton loadButton;
    private ImageButton leaderboardButton;
    private ImageButton settingsButton;
    private ImageButton creditsButton;
    private ImageButton exitButton;



    public MainMenuButtons(GameMain game ) {
        this.game = game;

        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT,
                new OrthographicCamera());

        stage = new Stage( gameViewport, game.getBatch() );


        createAndPositionButtons();

        Gdx.input.setInputProcessor( stage );

        stage.addActor( startButton );
        stage.addActor( loadButton );
        stage.addActor( leaderboardButton );
        stage.addActor( settingsButton );
        stage.addActor( creditsButton );
        stage.addActor( exitButton );

        addListener();
    }

    public void createAndPositionButtons() {
    	
        startButton = new ImageButton( new SpriteDrawable( new Sprite(
                new Texture("Buttons/Main Menu/Start Game Button.png" ))));

        loadButton = new ImageButton( new SpriteDrawable( new Sprite(
                new Texture("Buttons/Main Menu/Load Game Button.png" ))));

        leaderboardButton = new ImageButton( new SpriteDrawable( new Sprite(
                new Texture("Buttons/Main Menu/Leaderboard Button.png" ))));

        settingsButton = new ImageButton( new SpriteDrawable( new Sprite(
                new Texture("Buttons/Main Menu/Settings Button.png" ))));

        creditsButton = new ImageButton( new SpriteDrawable( new Sprite(
                new Texture("Buttons/Main Menu/Credits Button.png" ))));

        exitButton = new ImageButton( new SpriteDrawable( new Sprite(
                new Texture("Buttons/Main Menu/Exit Button.png" ))));

        startButton.setPosition( 0 , 900);
        loadButton.setPosition( 0 , 720);
        leaderboardButton.setPosition( 0, 540);
        settingsButton.setPosition( 0, 360);
        creditsButton.setPosition( 0, 175);
        exitButton.setPosition( 0, 0);

    }

    public void addListener(){

        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameManager.getInstance().gameStartedFromMainMenu = true;

                RunnableAction run = new RunnableAction();
                run.setRunnable(new Runnable() {
                                    @Override
                                    public void run() {
                                        game.setScreen(new Level2(game, "Level Backgrounds/Level 2 Background.png"));
                                    }
                                });
                SequenceAction sequenceAction = new SequenceAction();
                sequenceAction.addAction( Actions.color(Color.BLACK));
                sequenceAction.addAction(Actions.fadeOut( 1f ));
                sequenceAction.addAction( run );

                stage.addAction( sequenceAction );

            }
        });

        creditsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                game.setScreen( new CreditsScreen( game ) );
            }
        });

        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen( new SettingsScreen( game ) );
            }
        });

        exitButton.addListener( new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }

    public Stage getStage() {
        return this.stage;
    }

} //End of Main Menu Buttons
