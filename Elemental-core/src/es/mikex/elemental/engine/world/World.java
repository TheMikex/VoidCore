package es.mikex.elemental.engine.world;

import org.json.JSONException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

import es.mikex.elemental.GameScreens.GameScreen;
import es.mikex.elemental.engine.camera.OrthoCamera;
import es.mikex.elemental.entity.player.Player;
import es.mikex.elemental.entity.player.PlayerTest;
import es.mikex.elemental.settings.Settings;

public class World {

	private String worldName;
	private Player player;
	private PlayerTest playert;
	private long seed;
	private TextureAtlas playerAtlas;
	  
	boolean grounded = false;
	final Vector2 position = new Vector2();
	final Vector2 velocity = new Vector2();
	
	  
	private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		@Override
		protected Rectangle newObject () {
			return new Rectangle();
		}
	};
	private Array<Rectangle> tiles = new Array<Rectangle>();
	
	public World(){
	     player = new Player(200,200);
	     
	     playerAtlas = new TextureAtlas("img/player/player.pack");
			Animation still, left, right;
			still = new Animation(1 / 2f, playerAtlas.findRegions("still"));
			left = new Animation(1 / 6f, playerAtlas.findRegions("left"));
			right = new Animation(1 / 6f, playerAtlas.findRegions("right"));
			still.setPlayMode(Animation.PlayMode.LOOP);
			left.setPlayMode(Animation.PlayMode.LOOP);
			right.setPlayMode(Animation.PlayMode.LOOP);
	     
	     
	     playert = new PlayerTest(still, left, right, (TiledMapTileLayer) GameScreen.map.getLayers().get(0));
		}
	
	
	public World(String worldName) throws JSONException {
		this.worldName = worldName;
		
		 player = new Player(Settings.getWidth() / 2, Settings.getHeight() / 2);
			
	
	}
	
	public void update(OrthoCamera camera) {
		player.update();
		DayManager.getInstance().update();
	}
	
	public void render(SpriteBatch sb, OrthoCamera camera) {
		player.render(sb);
	}
	
	
	public Player getPlayer() {
		return player;
	}
	
	public long getSeed() {
		return seed;
	}
	
	public String getWorldName() {
		return worldName;
	}
	
	
	}