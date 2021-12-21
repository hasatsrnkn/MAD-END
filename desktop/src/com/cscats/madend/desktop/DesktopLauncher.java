package com.cscats.madend.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cscats.madend.GameMain;
import helpers.GameInfo;

public class DesktopLauncher {
	static LwjglApplicationConfiguration config;

	public static void main (String[] arg) {
		config = new LwjglApplicationConfiguration();
		new LwjglApplication(new GameMain(), config);
		config.height = GameInfo.HEIGHT;
		config.width = GameInfo.WIDTH;

	}
	public LwjglApplicationConfiguration getConfig() {
		return config;
	}


}
