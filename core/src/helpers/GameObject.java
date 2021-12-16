/**
 * 
 */
package helpers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Mehmet Eren Balasar
 *
 */
public abstract class GameObject {

	protected float xPosition;
	protected float yPosition;
	protected float height;
	protected float width;
	protected World world;
	protected Body body;
	
	
	public GameObject(World world, float initialX, float initialY, float height, float width) {
		
		this.world = world;
    	this.setHeight(height);
    	this.setWidth(width);
    	this.setPosition(initialX, initialY);
	}
	
	
	public Body getBody() {
		
		return this.body;
	}

    public void setPosition(float x, float y) {
		
		this.xPosition = x;
		this.yPosition = y;
	}

    public float getXPosition() {
		
		return this.xPosition;
	}

    public float getYPosition() {
		
		return this.yPosition;
	}
    
    public void setHeight(float h) {
    	
    	this.height = h;
    }
    
    public void setWidth(float w) {
    	
    	this.width = w;
    }
    
    public float getHeight() {
    	
    	return this.height;
    }
    
    public float getWidth() {
    	
    	return this.width;
    }
    
    public void setWorld(World world) {
    	
    	this.world = world;
    }
    
	public World getWorld() {

		return this.world;
	}

}
