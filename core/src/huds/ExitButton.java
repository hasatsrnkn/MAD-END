package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
import scenes.MainMenu;

public class ExitButton {
    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;
    private ImageButton exitButton;

    public ExitButton(GameMain game ) {
        this.game = game;

        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT,
                new OrthographicCamera());

        stage = new Stage( gameViewport, game.getBatch() );


        createAndPositionButtons();

        Gdx.input.setInputProcessor( stage );

        stage.addActor( exitButton );

        addListener();
    }

    public void createAndPositionButtons() {

        exitButton = new ImageButton( new SpriteDrawable( new Sprite(
                new Texture( "UIHud/Exit Button2.png" ))));

        exitButton.setPosition( 1520, 0);

    }

    public void addListener() {

        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameManager.getInstance().gameStartedFromMainMenu = true;

                RunnableAction run = new RunnableAction();
                run.setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new MainMenu(game));
                    }
                });
                SequenceAction sequenceAction = new SequenceAction();
                sequenceAction.addAction(Actions.color(Color.BLACK));
                sequenceAction.addAction(Actions.fadeOut(1f));
                sequenceAction.addAction(run);

                stage.addAction(sequenceAction);

            }
        });

    }

    public Stage getStage() {
        return stage;
    }
}
