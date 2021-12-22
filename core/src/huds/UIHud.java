package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cscats.madend.GameMain;
import helpers.GameInfo;
import helpers.GameManager;
import scenes.MainMenu;
import scenes.PlayerDiedScreen;

/**
 * UIHud class
 * @author Mehmet Hasat Serinkan
 * @date 21.12.2021
 */
public class UIHud {

    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;
    private Image healthImage;
    private Image scoreImage;
    private Image pausePanelImage;
    private Image playerDied;
    private Label healthLabel;
    private Label scoreLabel;
    private ImageButton resumeButton;
    private ImageButton exitButton;

    public UIHud( GameMain game ) {
       this.game = game;

       gameViewport = new StretchViewport(GameInfo.WIDTH , GameInfo.HEIGHT,
               new OrthographicCamera());

       stage = new Stage( gameViewport, game.getBatch() );

       Gdx.input.setInputProcessor( stage );

       if ( GameManager.getInstance().gameStartedFromMainMenu) {
           GameManager.getInstance().isPaused = false;
           GameManager.getInstance().gameStartedFromMainMenu = false;

           GameManager.getInstance().healthScore = GameInfo.PLAYER_HEALTH;
           GameManager.getInstance().score = 0;

       }
       createLabels();

       createImages();

       Table healthBar = new Table();
       Table scoreBar = new Table();

       healthBar.bottom().left();
       healthBar.setFillParent( true );
       scoreBar.top().right();
       scoreBar.setFillParent( true );

       healthBar.add( healthImage ).padLeft( 10 ).padBottom( 10 );
       healthBar.add( healthLabel ).padLeft( 10 ).padBottom( 10 );
       scoreBar.add( scoreImage ).padRight( 10 ).padTop( 10 );
       scoreBar.add( scoreLabel).padRight( 10 ).padTop( 10 );



        stage.addActor( healthBar );
        stage.addActor( scoreBar );

    }

    public void createLabels() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal( "Fonts/cour.ttf" ));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 40;

        BitmapFont font = generator.generateFont( parameter );

        healthLabel = new Label( "x" + GameManager.getInstance().healthScore, new Label.LabelStyle( font, Color.WHITE ));
        scoreLabel = new Label( "" + GameManager.getInstance().score , new Label.LabelStyle( font, Color.WHITE ));
    }

    public void createImages() {
        healthImage = new Image( new Texture( "UIHud/Health.png" ));
        scoreImage = new Image( new Texture( "UIHud/Score.png"));
    }

    public Stage getStage() {
        return stage;
    }

    public void stopGame() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) ) {
            if ( !GameManager.getInstance().isPaused) {
                createPausePanel();
            }

        }
    }

    public void createPausePanel() {
        pausePanelImage = new Image( new Texture( "UIHud/TP2.png" ) );
        resumeButton = new ImageButton( new SpriteDrawable( new Sprite(
                new Texture( "UIHud/Resume Button.png" ))));
        exitButton = new ImageButton( new SpriteDrawable( new Sprite(
                new Texture( "UIHud/Exit Button.png" ))));

        pausePanelImage.setPosition( GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, Align.center);
        resumeButton.setPosition( GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f + 200, Align.center );

        exitButton.setPosition( GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f - 200, Align.center );

        resumeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                removePausePanel();
            }
        });

        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen( new MainMenu( game ) );
            }
        });

        stage.addActor( pausePanelImage );
        stage.addActor( resumeButton );
        stage.addActor( exitButton );
        GameManager.getInstance().isPaused = true;
    }

    public void removePausePanel() {
        GameManager.getInstance().isPaused = false;
        pausePanelImage.remove();
        resumeButton.remove();
        exitButton.remove();
    }

    public void incrementScore( int score ) {
        GameManager.getInstance().score = score;
        scoreLabel.setText( String.valueOf( GameManager.getInstance().score ) );
    }

    public void setHealth( int health ) {
        GameManager.getInstance().healthScore = health;
        healthLabel.setText( "x" + GameManager.getInstance().healthScore );
    }

    public void playerIsDead() {
        GameManager.getInstance().checkForNewHighScore();
        GameManager.getInstance().isPaused = true;
        RunnableAction run = new RunnableAction();
        run.setRunnable(new Runnable() {
            @Override
            public void run() {
                game.setScreen( new PlayerDiedScreen( game ));
            }
        });
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction( Actions.color(Color.BLACK));
        sequenceAction.addAction(Actions.fadeOut( 1f ));
        sequenceAction.addAction( run );

        getStage().addAction( sequenceAction );
    }


} //End
