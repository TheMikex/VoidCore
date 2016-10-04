package es.mikex.elemental.entity.player;

import static es.mikex.elemental.settings.Variables.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import es.mikex.elemental.GameScreens.GameScreen;
import es.mikex.elemental.engine.camera.VirtualViewport;
import es.mikex.elemental.settings.Settings;
public class PlayerGeneric {
	
	private Vector2 velocity = new Vector2();
	private VirtualViewport virtualViewport;
	 private float x, y;
     private Texture player ,player_up, player_middle, player_down, player_right ,player_left;
     private float speed = 60f;
     private float width, height;
     private TiledMapTileLayer collisionLayer;
     private boolean colisionX,colisionY = false;
     public PlayerGeneric(TiledMapTileLayer collisionLayer) {
    	 this.collisionLayer = collisionLayer;
     
          
         loadPlayerTextures();
         width = player_middle.getWidth();
         height = player_middle.getHeight();
         player = player_middle;
     }
      
     public void update (float delta){
    	 
    	 velocity.x = speed;
    	 velocity.y = speed;
    	  float oldX = getX(), oldY = getY(), tileWidth = collisionLayer.getTileWidth(), tileHeight = collisionLayer.getTileHeight();
    	  
    	  
    	  // update player movement
    	 if (RIGHT_TOUCHED) {
    	      
    	            x += velocity.x * delta;
    	        
    	    }
    	    if (LEFT_TOUCHED) {
    	       
    	            x -= velocity.x * delta;
    	      
    	    }
    	    if (UP_TOUCHED) {
    	        
    	            y += velocity.y * delta;
    	       
    	    }
    	    if (DOWN_TOUCHED) {
    	       
    	            y -= velocity.y * delta;
    	       
    	    }
    	    
    	    
    	    
          
         // set ship texture:
         if (UP_TOUCHED == true && DOWN_TOUCHED == false ) {
             player = player_up;
         } else if (DOWN_TOUCHED == true && UP_TOUCHED ) {
             player = player_down;
             }
         
             else if (RIGHT_TOUCHED == true && LEFT_TOUCHED == false){
                player = player_right;         	 
             } else if(LEFT_TOUCHED == true && RIGHT_TOUCHED == false){
            	 player = player_left;
             }
          else {
             player = player_middle;
         }
         //checkCollisionMap();
         
         if(colisionX == true){
        	 velocity.x = 0;
        	 x = oldX; 
         }
         if(colisionY == true){
        	 velocity.y = 0;
        	 y = oldY;
         }
         boolean collisionX = false, collisionY = false;
         
         if(velocity.x < 0) // going left
 			collisionX = collidesLeft();
 		else if(velocity.x > 0) // going right
 			collisionX = collidesRight();

 		if(velocity.y < 0) // going down
 			 collisionY = collidesBottom();
 		else if(velocity.y > 0) // going up
 			collisionY = collidesTop();

 		// react to x collision
 		if(collisionX) {
 			x = oldX;
 			velocity.x = 0;
 		}
 	// react to y collision
 			if(collisionY) {
 				y = oldY;
 				velocity.y = 0;
 			}

     }
      
     public void render (SpriteBatch sb){
         sb.draw(player,x,y);
     }
      
     public void loadPlayerTextures(){
         player_middle = new Texture("player/player_middle.png");
         player_up = new Texture("player/player_up.png");
         player_down = new Texture("player/player_down.png");
         player_right = new Texture("player/player_right.png");
         player_left = new Texture("player/player_left.png");
     }
    
     public float getX(){
    	 return x;
     }
     public float getY(){
    	 return y;
     }
     public float getSpeed() {
    	    return speed;
    	}
     
     public void checkCollisionMap(){
         float xWorld = x + SCROLLTRACKER_X;
         float yWorld = y + SCROLLTRACKER_Y; 
                                                                                                      
         ////////////////// Check For Collision
         boolean collisionWithMapX = false;
         boolean collisionWithMapY = false;
         // check right side middle
         collisionWithMapX = isCellBlockedX();
         collisionWithMapY = isCellBlockedY();
  
         // //////////////// React to Collision
         if (collisionWithMapX) {             
             System.out.println("player-map collision!!!" + getX());
             colisionX = true;
    
             
             
         }
         if (collisionWithMapY) {             
             System.out.println("player-map collision in Y!!!" + getY());
             colisionY = true;
    
             
             
         }
 }
     
 

     private boolean isCellBlockedX() {
    	 boolean collisionX = false;
     
     for(int col = 0; col < collisionLayer.getWidth(); col++) {
  		
		  Cell cell = collisionLayer.getCell(
				  col,(Integer) null);
		
		if( cell != null && cell.getTile() != null
	            && cell.getTile().getProperties().containsKey("blocked")){
			collisionX = true;
		}
     }
	return collisionX;
     }
     
     private boolean isCellBlockedY() {
    	 boolean collisionY = false;
    		// go through all the cells in the layer
 		for(int row = 0; row < collisionLayer.getHeight(); row++) {
 			  Cell cell = collisionLayer.getCell((Integer) null, row);
 			
 			if( cell != null && cell.getTile() != null
 		            && cell.getTile().getProperties().containsKey("blocked")){
 				collisionY = true;
 			}
 				
 			}
		return collisionY;
 			
        
 			}

    
    
     public Vector2 getVelocity() {
 		return velocity;
 	}

     public void setVelocity(Vector2 velocity) {
 		this.velocity = velocity;
 	}

 
 	public void setSpeed(float speed) {
 		this.speed = speed;
 	}
 	
 	public void setPosition(float f, float g){
 		this.x = f;
 		this.y = g;
 	}
 	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}

	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
	}
	
	
	private boolean isCellBlocked(float x, float y) {
		Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
		return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
	}

	public boolean collidesRight() {
			if(isCellBlocked(getX() +10, getY()))
				return true;
		return false;
	}

	public boolean collidesLeft() {
			if(isCellBlocked(getX(), getY()))
				return true;
		return false;
	}

	public boolean collidesTop() {
			if(isCellBlocked(getX(), getY()))
				return true;
		return false;

	}

	public boolean collidesBottom() {
		
			if(isCellBlocked(getX(), getY()))
				return true;
		return false;
	}
     
}
	

