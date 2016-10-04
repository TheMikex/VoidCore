package es.mikex.elemental.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import es.mikex.elemental.engine.resourcemanagment.Drawable;


public abstract class TexturedEntity extends WorldBody {

	protected Drawable drawable;
	protected float speed;
	
	public TexturedEntity(Drawable drawable, float x, float y, float speed) {
		super(x, y, drawable.getWidth(), drawable.getHeight());
		this.drawable = drawable;
		this.speed = speed;
	}

	public void render(SpriteBatch sb) {
		drawable.render(sb, x, y);
	}

	public float getSpeed() {
		return speed;
	}

}
