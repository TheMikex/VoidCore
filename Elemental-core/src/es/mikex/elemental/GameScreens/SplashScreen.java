package es.mikex.elemental.GameScreens;

import org.json.JSONException;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import es.mikex.elemental.drawable.ui.FadingSprite;
import es.mikex.elemental.screens.Screen;
import es.mikex.elemental.screens.ScreenManager;
import es.mikex.elemental.settings.Settings;

public class SplashScreen implements Screen {
	
	FadingSprite sprite;
	Sprite sprite1;
	Texture tex;
	
	private double counter;
	int i;

	@Override
	public void create() {
		tex = new Texture("");
		sprite1 = new Sprite(tex);
		counter = 100000000;
		i = 0;
		sprite = new FadingSprite(sprite1, Settings.getWidth() /2, Settings.getHeight(),0.5f,5,1, 0.2f);
	}

	@Override
	public void update() throws JSONException {
		
		if(i < counter){
			
			
			
			
			
			i++;
		}
		
	}

	@Override
	public void render(SpriteBatch sb) {
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
