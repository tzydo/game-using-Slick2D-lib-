import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Bullet extends BasicGameState
{
   private float startX = 0;
   private float startY = 0;
   private float destX = 0;
   private float destY = 0;
   public Point location = new Point(0,0);
   private float speed = 20; //how fast bullet speed
   private float deg;
   private float dx;
   private float dy;
   private Image ammo;
   private int ammoHeight = 10;
   private int ammoWidth = 10;
   public int healt = 0;

   
   
   public Bullet(float startX, float startY, float destX, float destY) throws SlickException
   {
      this.startX = startX;
      this.startY = startY;
      location.setLocation(startX, startY);
      this.destX = destX;
      this.destY = destY;
      recalculateVector(destX, destY);
      this.healt = 100;

   }
   
   
    //Calculates a new vector based on the input destination X and Y.
   public void recalculateVector(float destX, float destY)
   {
      float rad = (float)(Math.atan2(destX - startX, startY - destY));
      this.dx = (float) Math.sin(rad) * speed;
      this.dy = -(float) Math.cos(rad) * speed;
   }
   
   
   
    //Recalculates the vector for this bullet based on the current destination.
   public void recalculateVector()
   {
      recalculateVector(destX, destY);
   }
   
   
   public int getPozX() {
       return (int) location.getX();
   }
   
   public int getPozY() {
       return (int) location.getY();
   }
   
   public int getHeight() {
       return ammoHeight;
   }
 
   public int getWidth() {
       return ammoWidth;
   }
   
   
   
   
@Override
public void init(GameContainer arg0, StateBasedGame arg1)
        throws SlickException {
        ammo = new Image("res/ammo.gif");
    
}

@Override
public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
        throws SlickException {
    
        ammo.draw(location.getX(),location.getY());
      
    }



@Override
public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
        throws SlickException { 
    healt -=2;
    if(healt>0)move();
        
}



public void move(){
   float x = location.getX();
   float y = location.getY();
   
   x += dx;
   y += dy;
   
   location.setLocation(x, y);
}


@Override
public int getID() {
    // TODO Auto-generated method stub
    return 0;
}
   
}

