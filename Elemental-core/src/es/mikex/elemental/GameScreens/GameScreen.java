package es.mikex.elemental.GameScreens;

import static es.mikex.elemental.settings.Variables.*;

import org.json.JSONException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

import es.mikex.elemental.Input.InputHandler;
import es.mikex.elemental.drawable.ui.GameHud;
import es.mikex.elemental.engine.camera.OrthoCamera;
import es.mikex.elemental.engine.world.World;
import es.mikex.elemental.entity.player.Player;
import es.mikex.elemental.entity.player.PlayerGeneric;
import es.mikex.elemental.screens.Screen;
import es.mikex.elemental.settings.Settings;
import es.mikex.elemental.settings.Timer;

public class GameScreen implements Screen {
	

	public static OrthoCamera camera;
	private ShaderProgram program;
	private World world;
	private GameHud gameHud;
	public static OrthogonalTiledMapRenderer renderer,colisionrender;

    private TiledMapTileLayer collisionLayer;
	public static TiledMap map ,collisionMap;
	private InputHandler inputHandler;
	private PlayerGeneric player;
	float delta = Gdx.graphics.getDeltaTime();
	boolean active = false;
	
	public GameScreen() {
	}
	
	

	@Override
	public void create() {

		gameHud = new GameHud(this, world);
		ShaderProgram.pedantic = false;
		program = new ShaderProgram(Gdx.files.internal("shaders/sepia.vsh"), Gdx.files.internal("shaders/sepia.fsh"));
		
		if (!program.isCompiled()) {
			System.out.println(program.getLog());
		}
		 
		map = new TmxMapLoader().load("maps/mapa1.tmx");
		
		 collisionMap = new TmxMapLoader().load("maps/map_collision.tmx");
	     renderer = new OrthogonalTiledMapRenderer(collisionMap);
	     
		colisionrender = new OrthogonalTiledMapRenderer(map);
		collisionLayer = (TiledMapTileLayer) collisionMap.getLayers().get(
                "block");
		
		collisionLayer.setVisible(active);
		
		camera = new OrthoCamera();
		Timer.startGameTime();
		resize(Settings.getWidth(), Settings.getHeight());
		player = new PlayerGeneric(collisionLayer);
		player.setPosition(Settings.getWidth() / 2 , Settings.getHeight() / 2);
		 inputHandler = new InputHandler();
	}

	@Override
	public void update() {
		camera.update();
		gameHud.update(camera);

		
		player.update(delta);
		inputHandler.update();
		cameraUpdate();
	
	}

	@Override
	public void render(SpriteBatch sb) {
		
		Gdx.gl.glClearColor(1, 0, 1, 0);
		
		colisionrender.setView(camera);
		colisionrender.render();
		renderer.setView(camera);
		renderer.render();
		sb.setProjectionMatrix(camera.combined);
		gameHud.render(camera);

	    sb.begin();
        player.render(sb);
        sb.end();
        
	}



	@Override
	public void resize(int width, int height) {
		
		camera.resize();
		gameHud.resize(width, height);
	}	
	@Override
	public void dispose() {
		
		Timer.stopGameTime();
	}

	@Override
	public void onBackPressed() {
		
		Timer.stopGameTime();
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {
		
		
	}

	
	public void cameraUpdate() {
	    float playerSpeed = player.getSpeed() * delta;
	    float scrollSpeed = 30 * delta;
	 
	    // vertical camera movement
	    if (UP_TOUCHED && !DOWN_TOUCHED) {
	       
	            camera.translate(0, playerSpeed, 0);
	            SCROLLTRACKER_Y += playerSpeed;
	 
	        
	    }
	    if (DOWN_TOUCHED && !UP_TOUCHED) {
	            camera.translate(0, -playerSpeed, 0);
	            SCROLLTRACKER_Y -= playerSpeed;
	        
	    }
	    if (RIGHT_TOUCHED == true && LEFT_TOUCHED == false) {
	   	 
	    	 camera.translate(playerSpeed, 0, 0);
	        SCROLLTRACKER_X += scrollSpeed;
	        
	         }
	   
	    if (LEFT_TOUCHED && !RIGHT_TOUCHED) {
	    
	    	 camera.translate(-playerSpeed,0 , 0);
	        SCROLLTRACKER_X -= scrollSpeed;
	  
	    }
	    
	    camera.update();
	    camera.setPosition(player.getX(), player.getY());
	   
	    if(Gdx.input.isKeyPressed(Keys.E)){
	    	camera.zoom += -0.5 * delta;
	    }
	    if(Gdx.input.isKeyPressed(Keys.Q)){
	    	camera.zoom += 0.5 * delta;
	    }
	    
	    if(Gdx.input.isKeyPressed(Keys.C)){
	    	camera.setPosition(player.getX(), player.getY());
	    }
	    
	    if(Gdx.input.isKeyPressed(Keys.P)){
	    	if(active = false){
	    		active = true;
	    	}else{
	    		active = false;
	    	}
	    	
	    }
	 
	  
	    }
	 
	

}
