package es.mikex.elemental.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import es.mikex.elemental.engine.resourcemanagment.AnimationDrawable;
import es.mikex.elemental.engine.resourcemanagment.Drawable;
import es.mikex.elemental.engine.resourcemanagment.ResourceManager;
import es.mikex.elemental.engine.resourcemanagment.SpriteDrawable;
import es.mikex.elemental.engine.resourcemanagment.SpriteSheet;
import es.mikex.elemental.entity.BodyType;
import es.mikex.elemental.entity.LivingEntity;
import es.mikex.elemental.entity.PlayerEntity;

public class Player extends LivingEntity{
	
	private final float walkingVelocity = 12f;
	private float increment;
	private TiledMapTileLayer collisionLayer;

	private String blockedKey = "blocked";

	public Player( float x, float y) {
		super(createAnimationDrawable(), x, y, 12f, 100, 100);
		
	}
	
	private static SpriteDrawable createSprite(){
		return new SpriteDrawable(new Sprite(ResourceManager.getInstance().getTexture("player")));
		
	}
	  private static AnimationDrawable createAnimationDrawable() {
	        return new AnimationDrawable(new SpriteSheet(ResourceManager.getInstance().getTexture("player"), 32, 36, 4, 4));
	    }

	@Override
	public void update() {
		
//
//		playert.draw(sb);
//		
//		increment = collisionLayer.getTileWidth();
//		increment = getWidth() < increment ? getWidth() / 2 : increment / 2;
//		
//		increment = collisionLayer.getTileHeight();
//		increment = getHeight() < increment ? getHeight() / 2 : increment / 2;
		
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			addVelocity(walkingVelocity, 0);
		}
		if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			addVelocity(-walkingVelocity, 0);
		}
		if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)) {
			addVelocity(0, walkingVelocity);
		}
		if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) {
			addVelocity(0, -walkingVelocity);
		}
	}
	
	private boolean isCellBlocked(float x, float y) {
		Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
		return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(blockedKey);
	}

	public boolean collidesRight() {
		for(float step = 0; step <= getHeight(); step += increment)
			if(isCellBlocked(getX() + getWidth(), getY() + step))
				return true;
		return false;
	}

	public boolean collidesLeft() {
		for(float step = 0; step <= getHeight(); step += increment)
			if(isCellBlocked(getX(), getY() + step))
				return true;
		return false;
	}

	public boolean collidesTop() {
		for(float step = 0; step <= getWidth(); step += increment)
			if(isCellBlocked(getX() + step, getY() + getHeight()))
				return true;
		return false;

	}

	public boolean collidesBottom() {
		for(float step = 0; step <= getWidth(); step += increment)
			if(isCellBlocked(getX() + step, getY()))
				return true;
		return false;
	}
	

	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}

	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
	}

}

