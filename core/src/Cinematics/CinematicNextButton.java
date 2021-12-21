package Cinematics;

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
import levels.Level1;
import scenes.MainMenu;

/**
 * Cinematic Button
 * @author Mehmet Hasat Serinkan
 * @date 21.12.2021
 */
public class CinematicNextButton {


    //Properties
    protected GameMain game;
    protected Stage stage;
    protected Viewport gameViewport;
    protected ImageButton nextButton;

    public CinematicNextButton( GameMain game ) {
        this.game = game;

        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT,
                new OrthographicCamera());

        create();

    }


    public void create() {

        nextButton = new ImageButton( new SpriteDrawable( new Sprite(
                new Texture("Cinematics/NextButton.png" ))));


        nextButton.setPosition( (GameInfo.WIDTH / 2f) + 600, (GameInfo.HEIGHT / 2f) - 500);

    }

    public Stage getStage() {
        return stage;
    }



} //End
