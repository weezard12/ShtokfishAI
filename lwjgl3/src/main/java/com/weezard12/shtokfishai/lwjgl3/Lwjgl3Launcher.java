package com.weezard12.shtokfishai.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.glutils.HdpiMode;
import com.weezard12.shtokfishai.main.MyGdxGame;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.
        createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new MyGdxGame(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("ShtokfishAI");
        configuration.useVsync(true);
        //// Limits FPS to the refresh rate of the currently active monitor.
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate);
        //// If you remove the above line and set Vsync to false, you can get unlimited FPS, which can be
        //// useful for testing performance, but can also be very stressful to some hardware.
        //// You may also need to configure GPU drivers to fully disable Vsync; this can cause screen tearing.
        configuration.setWindowedMode(MyGdxGame.boardSize+(int)(MyGdxGame.boardSize*0.6), MyGdxGame.boardSize);
        configuration.setWindowIcon("windowIcon\\knight128.png", "windowIcon\\knight64.png", "windowIcon\\knight32.png", "windowIcon\\knight16.png");
        //sets the assets path to be for desktop
        MyGdxGame.assetsPath = "assets\\";

        return configuration;
    }
}
