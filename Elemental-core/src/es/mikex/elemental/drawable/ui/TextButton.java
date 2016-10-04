package es.mikex.elemental.drawable.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import es.mikex.elemental.engine.resourcemanagment.Drawable;

public class TextButton extends PictureButton {

	private final BitmapFont font;
	private String text;
	GlyphLayout glyphLayout = new GlyphLayout();
	public TextButton(String text, Drawable drawable, BitmapFont font, float x, float y) {
		super(drawable, x, y);
		
		this.text = text;
		this.font = font;
		glyphLayout.setText(font,text);
			}
	
	@Override
	public void render(SpriteBatch sb) {
		super.render(sb);
		float width = glyphLayout.width;
		float height = glyphLayout.height;
		font.draw(sb, text, x + (drawable.getWidth() / 2) - (width / 2), y + (drawable.getHeight() / 2) + (height / 2));
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

}
