package es.mikex.elemental.screens;

import org.json.JSONException;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Screen {
	
	void create();
	
	void update() throws JSONException;
	
	void render(SpriteBatch sb);
	
	void dispose();
	
	void resize(int width, int height);

	void onBackPressed();
	
	void pause();
	
	void resume();
	
}
