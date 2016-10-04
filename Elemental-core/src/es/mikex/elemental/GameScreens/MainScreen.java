package es.mikex.elemental.GameScreens;


import org.json.JSONException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import es.mikex.elemental.drawable.ui.TextButton;
import es.mikex.elemental.engine.camera.OrthoCamera;
import es.mikex.elemental.engine.resourcemanagment.ResourceManager;
import es.mikex.elemental.engine.resourcemanagment.SpriteDrawable;
import es.mikex.elemental.screens.Screen;
import es.mikex.elemental.screens.ScreenManager;
import es.mikex.elemental.settings.Settings;

public class MainScreen implements Screen{


	private Sprite bg = ResourceManager.getInstance().getSprite("menuBg");
	private OrthoCamera camera = new OrthoCamera();
	private TextButton newWorldBtn = new TextButton("Nuevo Mundo", new SpriteDrawable(ResourceManager.getInstance().getSprite("btnBg")), ResourceManager.getInstance().getFont("font"), Settings.getWidth() / 2 - 125, 250);
	private TextButton loadWorldBtn = new TextButton("Cargar Mundo", new SpriteDrawable(ResourceManager.getInstance().getSprite("btnBg")), ResourceManager.getInstance().getFont("font"), Settings.getWidth() / 2 - 125, 175);


	
	@Override
	public void create() {
		bg.setPosition(0, 0);
		bg.setScale(1, 1);
		resize(Settings.getWidth(), Settings.getHeight());
	}

	@Override
	public void update() throws JSONException {
		camera.update();
		if (Gdx.input.isTouched()) {
			float touchX = camera.unprojectXCoordinate(Gdx.input.getX(), Gdx.input.getY());
			float touchY = camera.unprojectYCoordinate(Gdx.input.getX(), Gdx.input.getY());
			
		if (newWorldBtn.isPressed(touchX, touchY)) {
			ScreenManager.setScreen(new GameScreen());
		} else if (loadWorldBtn.isPressed(touchX, touchY)) {
			ScreenManager.setScreen(new GameScreen());
		}
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		bg.draw(sb);
		newWorldBtn.render(sb);
		loadWorldBtn.render(sb);
		sb.end();
	}

	@Override
	public void dispose() {
		
		
	}

	@Override
	public void resize(int width, int height) {
		
		camera.resize();
	}

	@Override
	public void onBackPressed() {
		
		
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {
		
		
	}

	

}
