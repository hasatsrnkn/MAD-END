package characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import helpers.GameInfo;

public class Character {
    
	private float xPosition;
	private float yPosition;
	private float height;
	private float width;
    
    
    public Character( float x, float y) {

        setPosition( x , y );
       
    }

    public void setPosition(float x, float y) {
		
		this.xPosition = x;
		this.yPosition = y;
	}

    public float getXPosition() {
		// TODO Auto-generated method stub
		return this.xPosition;
	}

    public float getYPosition() {
		// TODO Auto-generated method stub
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






}
