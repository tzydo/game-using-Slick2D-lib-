import org.lwjgl.opengl.Drawable;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Enemy extends BasicGameState {
    private float pozX;
    private float pozY;
    private Image still, blood;
    private Animation walking;
    private int[] duration = {80,80,80};  
    private boolean move = false;
    private float speed = 0.8f;
    private int wall=0;
    private float xDistance =0;
    private float yDistance =0;
    private double angle = 0;
    private int enemyHeight = 70;
    private int enemyWidth = 70;
    private int healt = 100;
    private int time = 100;
    
    
    
    
    
    public Enemy(float x,float y) {
        pozX = x;
        pozY = y;
    }
    
    
    
    
    public float getX() {
        return pozX;
    }
    
 
    public float getY() {
        return pozY;
    }
    
    
    
    public int getTime() {
        return time;
    }
    
    
    
  //hit detection when player shoot to zombie
     public boolean hitDetector(int pozXBullet,int pozYBullet, int bulletHeight, int bulletWidth) {
            boolean zm = false;
            
            if(((pozX+ Main.pozXWindow > pozXBullet - bulletWidth) || (pozX+ 70 +Main.pozXWindow > pozXBullet - bulletWidth)) && ((pozX + Main.pozXWindow < pozXBullet + bulletWidth) || (pozX + 70+Main.pozXWindow < pozXBullet + bulletWidth)) && ((pozY + Main.pozYWindow > pozYBullet) || (pozY + 70 +Main.pozYWindow > pozYBullet)) && ((pozY + Main.pozYWindow < pozYBullet + bulletHeight) || (pozY + 70 +Main.pozYWindow < pozYBullet + bulletHeight)))
            {
                zm = true;
                healt-=100;
               
            }
           
           
            
            return zm;
    }
    
    @Override
    public void init(GameContainer arg0, StateBasedGame arg1)
            throws SlickException {
        still = new Image("res/en0.gif");
        blood = new Image("res/blood.gif");
        Image[] enemy = {new Image("res/en0.gif"), new Image("res/en1.gif"), new Image("res/en2.gif")};
        walking = new Animation(enemy, duration,true);
        
    }

    
    
    @Override
    public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
            throws SlickException {
        // TODO Auto-generated method stub
               
      //moving or staing
        if(healt >0) {
            if(move)
                walking.draw(Main.pozXWindow+pozX,Main.pozYWindow+pozY);
            else 
                still.draw(Main.pozXWindow+pozX,Main.pozYWindow+pozY);
            
        }else
              blood.draw(Main.pozXWindow+pozX,Main.pozYWindow+pozY);
        
    }
    
    
    
    @Override
    public void update(GameContainer arg0, StateBasedGame arg1, int del)
            throws SlickException {
        //enemy rotation
        if(healt >0) {
            move = true;
            xDistance= Player.getPozX() - (Main.pozXWindow+pozX);
            yDistance = Player.getPozY() - (Main.pozYWindow+pozY);
            angle = Math.toDegrees(Math.atan2(yDistance, xDistance));
            
            if(move) {
                walking.getCurrentFrame().setRotation((float) angle +80);
            }else
                still.setRotation((float) angle + 80);
          
            //enemy fallowing whith player
            move(del);
        }
        else {
            time -=1;
        }
   
    }
    
    
    //Enemy movements
    private void move(int del) {
        move = true;
                
        float a = Player.getPozX()-(Main.pozXWindow+pozX);
        float b = Player.getPozY() - (Main.pozYWindow+pozY);
        float pom,pom1;
        float d1 = (float) Math.sqrt(((a * a)+ (b* b))); 

        pom = a/d1;
        pom1 = b/d1;
        
        pozY += pom1 *del*0.1;
        pozX += pom*del*0.1;
            
        
    }

    
    
    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 0;
    }

}
