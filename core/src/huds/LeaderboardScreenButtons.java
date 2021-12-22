package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cscats.madend.GameMain;
import helpers.GameInfo;
import helpers.GameManager;
import scenes.MainMenu;

public class LeaderboardScreenButtons {

    //Properties
    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;
    private ImageButton backButton;
    private Label scoreLabel;

    public LeaderboardScreenButtons( GameMain game ) {
        this.game = game;

        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT,
                new OrthographicCamera());

        stage = new Stage( gameViewport, game.getBatch() );

        createAndPosition();

        Gdx.input.setInputProcessor( this.stage );

        stage.addActor( backButton );

        stage.addActor( scoreLabel );

    }

    public void createAndPosition() {

        backButton = new ImageButton( new SpriteDrawable( new Sprite(
                new Texture("Buttons/Main Menu/Back Button.png" ))));


        backButton.setPosition( GameInfo.WIDTH / 2f - 975, GameInfo.HEIGHT / 2f + 350);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal( "Fonts/cour.ttf" ));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 80;

        BitmapFont font = generator.generateFont( parameter );

        scoreLabel = new Label( "HIGH-SCORE: " + GameManager.getInstance().gameData.getHighScore(), new Label.LabelStyle( font, Color.WHITE ));

        scoreLabel.setPosition( GameInfo.WIDTH / 2f - 900, GameInfo.HEIGHT / 2f);

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


}
