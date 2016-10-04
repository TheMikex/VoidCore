package es.mikex.elemental.drawable.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import es.mikex.elemental.GameScreens.GameScreen;
import es.mikex.elemental.engine.camera.OrthoCamera;
import es.mikex.elemental.engine.resourcemanagment.Drawable;
import es.mikex.elemental.engine.resourcemanagment.ResourceManager;
import es.mikex.elemental.engine.world.World;
import es.mikex.elemental.entity.LivingEntity;
import es.mikex.elemental.settings.Settings;


/**
 * Created by Jon on 9/29/15.
 */
public class GameHud {

    private static final Drawable blankTile = ResourceManager.getInstance().getDrawable("blankTile");
    private static final Texture heartTexture = ResourceManager.getInstance().getTexture("heart");


    private Stage stage;
    private SpriteBatch stageSb, sb;
    public static OrthoCamera camera;
    private World world;
    public static Texture bg;

       private float lastBlockPlace;
   
    private GameScreen gameScreen;

    public GameHud(GameScreen gameScreen, World world) {
        this.gameScreen = gameScreen;
        this.world = world;
        stageSb = new SpriteBatch();
        sb = new SpriteBatch();
        stage = new Stage(new StretchViewport(Settings.getWidth(), Settings.getHeight()), stageSb);
   
        
        stage.act(Gdx.graphics.getDeltaTime());

        camera = new OrthoCamera();
      

        bg = ResourceManager.getInstance().getTexture("bg");

   
    }

    public void update(OrthoCamera gameCamera) {
        camera.update();
      

    }

    public void render(OrthoCamera gameCamera) {
    	
    	
      
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        ResourceManager.getInstance().getFont("font").draw(sb, "FPS: " + Gdx.graphics.getFramesPerSecond(), Settings.getWidth() - 150, Settings.getHeight() - 60);
        

        int hearts = 6;
        for (int i = 0; i < hearts; i++) {
            sb.draw(heartTexture, Settings.getWidth() - 75 - (i * (heartTexture.getWidth() + 5)), Settings.getHeight() - heartTexture.getHeight() - 15);
        }
    
        sb.end();
        stage.draw();
    }



    public void resize(int width, int height) {
        camera.resize();
    }


}
