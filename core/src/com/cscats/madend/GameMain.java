package com.cscats.madend;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import helpers.GameData;
import helpers.GameInfo;
import helpers.GameManager;
import scenes.WelcomeScreen;

public class GameMain extends Game {
	SpriteBatch batch;
	
	@Override
	public void create () {
		GameManager.getInstance().initializeGameData();
		batch = new SpriteBatch();
		setScreen( new WelcomeScreen( this ) );
	}

	@Override
	public void render () {
		super.render();
	}


	public SpriteBatch getBatch() {
		return this.batch;
	}
}
