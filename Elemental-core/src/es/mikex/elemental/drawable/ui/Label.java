package es.mikex.elemental.drawable.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Label extends UIElement {
	
	private final BitmapFont font;
	private final boolean center;
	private String text;
	GlyphLayout glyphLayout = new GlyphLayout();
	
	public Label(String text, BitmapFont font, float x, float y, boolean center) {
		super(x, y);
		this.text = text;
		this.font = font;
		this.center = center;
		
		glyphLayout.setText(font,text);
	}
	
	public void render(SpriteBatch sb) {
		if (center) {
			float width = glyphLayout.width;
			float height = glyphLayout.height;
			font.draw(sb, text, x - (width / 2), y - (height / 2));
		} else {
			font.draw(sb, text, x, y);
		}
	}	
	
	public void setText(String text) {
		this.text = text;
	}
	
	public BitmapFont getFont() {
		return font;
	}
	
	
	public String getText() {
		return text;
	}

	@Override
	public void update() {
	}
	
}
