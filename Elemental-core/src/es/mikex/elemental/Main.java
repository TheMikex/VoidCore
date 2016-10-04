package es.mikex.elemental;

import org.json.JSONException;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import es.mikex.elemental.GameScreens.MainScreen;
import es.mikex.elemental.engine.resourcemanagment.ResourceManager;
import es.mikex.elemental.screens.ScreenManager;
import es.mikex.elemental.settings.Settings;

public class Main implements ApplicationListener {
	
	   protected static SpriteBatch sb;
	    protected boolean paused;
	    protected long lastBack;
	    
	@Override
	public void create() {
		 sb = new SpriteBatch(150);
	        sb.enableBlending();
		instantiateSettings();	
		

		 ResourceManager.getInstance().loadSprite("menuBg", "menu/fondo.png");
	        ResourceManager.getInstance().loadSprite("btnBg", "menu/boton.png", .75f, .75f);
	        ResourceManager.getInstance().loadFont("font", "fonts/source-sans-pro-regular.ttf", Color.BLACK, 22);
	        ResourceManager.getInstance().loadFont("smallFont", "fonts/source-sans-pro-regular.ttf", Color.WHITE, 12);
	        ResourceManager.getInstance().loadTexture("playerAnimated", "player_animated.png");
	        ResourceManager.getInstance().loadTexture("heart", "ui/heart.png");
	        ResourceManager.getInstance().loadTexture("player", "player_animated.png");
		ScreenManager.setScreen(new MainScreen());
			}

	

	@Override
	public void render() {
		if (!paused) {
           

            if (ScreenManager.getCurrent() != null)
				try {
					ScreenManager.getCurrent().update();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }
        if (Gdx.input.isKeyPressed(Input.Keys.BACK) && System.currentTimeMillis() - lastBack > 300) {
            if (ScreenManager.getCurrent() != null) {
                ScreenManager.getCurrent().onBackPressed();
            }
            lastBack = System.currentTimeMillis();
        }

        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(0f, 0f, 0f, 1);

        if (ScreenManager.getCurrent() != null) {
            ScreenManager.getCurrent().render(sb);
        }
		
	}
	
	@Override
	public void resize(int width, int height) {
		 if (ScreenManager.getCurrent() != null)
	            ScreenManager.getCurrent().resize(width, height);
	    }
		
	

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {
		
		
	}

	@Override
	public void dispose() {
		sb.dispose();
		
	}
	  public static void instantiateSettings() {
	        Settings.addSetting("width", 1280);
	        Settings.addSetting("height", 720);
	        Settings.addSetting("Title", "Dev VoidCore 0.0.1");
	        Settings.addSetting("Vsync", false);
	    }

}
