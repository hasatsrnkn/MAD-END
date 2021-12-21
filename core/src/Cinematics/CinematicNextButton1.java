package Cinematics;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.cscats.madend.GameMain;
import levels.Level1;


/**
 * Cinematic Next Button1
 * @author Mehmet Hasat Serinkan
 * @date 21.12.2021
 */
public class CinematicNextButton1 extends CinematicNextButton{

    public CinematicNextButton1(GameMain game) {
        super( game );

        stage = new Stage( gameViewport, game.getBatch() );
        Gdx.input.setInputProcessor(stage);
        stage.addActor( nextButton );

        addListener();

    }

    public void addListener() {
        nextButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                RunnableAction run = new RunnableAction();
                run.setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new Level1(game , "Level Backgrounds/Level 1 Background.png"));
                    }
                });
                SequenceAction sequenceAction = new SequenceAction();
                sequenceAction.addAction( Actions.color(Color.BLACK));
                sequenceAction.addAction(Actions.fadeOut( 1f ));
                sequenceAction.addAction( run );

                stage.addAction( sequenceAction );

            }
        });
    }


    public Stage getStage() {
        return stage;
    }

} //End
