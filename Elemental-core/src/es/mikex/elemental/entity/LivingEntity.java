package es.mikex.elemental.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import es.mikex.elemental.engine.resourcemanagment.AnimationDrawable;
import es.mikex.elemental.engine.resourcemanagment.Drawable;
import es.mikex.elemental.settings.Timer;

public abstract class LivingEntity extends TexturedEntity{
	
	protected static final ShapeRenderer shapeRenderer = new ShapeRenderer();
	private static final float healthBarWidth = 30, healthBarHeight = 5;
	public enum Direction{
		LEFT, RIGHT , UP, DOWN
	}
 
	private Direction facingDirection = Direction.DOWN;
	private float health,maxHealth;
	private float lastToolUsedTime, lastDamageReceived = Timer.getGameTimeElapsed();

	 public static int playerX;
	  public static int playerY;
      
	  private TiledMapTileLayer collisionLayer;

		private String blockedKey = "blocked";
		
	public LivingEntity(Drawable drawable, float x, float y, float speed ,float maxHealth,float health) {
		super(drawable, x, y, speed);
		this.health = health;
		this.maxHealth = maxHealth;
		
	}
	
	public void damage(float damage) {
		health -= damage;
		lastDamageReceived = Timer.getGameTimeElapsed();
	}

	public void heal(float amount) {
		health += amount;
		if (health > maxHealth)
			health = maxHealth;
	}

	public boolean isDead() {
		return health <= 0;
	}
	
	public float getX(){
		return drawable.getX();
	}
	
	public float getY(){
		return drawable.getY();
	}
	
	@Override
	public void render(SpriteBatch sb) {
	 sb.draw(drawable.getTextureRegion(), x, y, drawable.getTextureRegion().getRegionWidth(), drawable.getTextureRegion().getRegionHeight());
	

		if (Timer.getGameTimeElapsed() - lastDamageReceived <= 5f) {
			renderHealthBar(sb);
		}
	}
	
	public void renderHealthBar(SpriteBatch sb) {
		float renderX = x + (drawable.getWidth() / 2) - (healthBarWidth / 2);
		float renderY = y + (drawable.getHeight() + 5);
		float currentHealthWidth = health * (healthBarWidth / maxHealth);
		if (currentHealthWidth < 0)
			currentHealthWidth = 0;
		sb.end();

		shapeRenderer.setProjectionMatrix(sb.getProjectionMatrix());
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.rect(renderX, renderY, healthBarWidth, healthBarHeight);
		shapeRenderer.setColor(Color.GREEN);
		shapeRenderer.rect(renderX, renderY, currentHealthWidth, healthBarHeight);
		shapeRenderer.end();

		sb.begin();
	}
	
	public void usedTool() {
		lastToolUsedTime = Timer.getGameTimeElapsed();
	}

	public float getHealth() {
		return health;
	}

	public float getMaxHealth() {
		return maxHealth;
	}
	
	
	 public void faceDirection(Direction direction) {
	        if(!(drawable instanceof AnimationDrawable) && direction != facingDirection) {
	            drawable.getTextureRegion().flip(true, false);
	        }
	        else if(drawable instanceof AnimationDrawable) {
	            AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
	            if(facingDirection != direction && direction == Direction.LEFT) {
	                animationDrawable.setAnimationByType(AnimationDrawable.Type.WALK_LEFT);
	                animationDrawable.update();
	            }
	            else if(facingDirection != direction && direction == Direction.RIGHT) {
	                animationDrawable.setAnimationByType(AnimationDrawable.Type.WALK_RIGHT);
	                animationDrawable.update();
	            }
	            if(facingDirection != direction && direction == Direction.DOWN) {
	                animationDrawable.setAnimationByType(AnimationDrawable.Type.WALK_UP);
	                animationDrawable.update();
	            }
	            else if(facingDirection != direction && direction == Direction.UP) {
	                animationDrawable.setAnimationByType(AnimationDrawable.Type.WALK_DOWN);
	                animationDrawable.update();
	            }
	        }
	        facingDirection = direction;
	    }

	    public Direction getFacingDirection() {
	        return facingDirection;
	    }

		@Override
		public void addVelocity(float x, float y) {
	        if(x < 0 && velX != 0) {
	            faceDirection(Direction.RIGHT);
	        } else if(x > 0 && velX != 0) {
	            faceDirection(Direction.LEFT);
	        }
	        if(y < 0 && velY != 0) {
	            faceDirection(Direction.UP);
	        } else if(y > 0 && velY != 0) {
	            faceDirection(Direction.DOWN);
	        }
	        super.addVelocity(x, y);
	    }
	

}
