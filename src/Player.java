import org.lwjgl.opengl.Drawable;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends BasicGameState {
    
    
    private static  float pozY = 300;//Main.pozXWindow + 272;
    private static  float pozX = 400; //Main.pozYWindow + 365;
    private Image still;
    private Animation walking;
    private int[] duration = {80,80,80};  
    private boolean move = false;
    private float speed = 0.8f;
    private int wall = 0;
    private final int playerHeight = 70;
    private final int playerWidth = 70;
  
       
    
    public static float getPozX() {
        return pozX;
    }
    
    
    
    public static float getPozY() {
        return pozY;
    }
    
    
    
    public int getPlayerHeight() {
        return playerHeight;
    }
    
    

    public int getPlayerWidth() {
        return playerWidth;
    }
    
    @Override
    public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
        try{
            still = new Image("res/soldier0.gif");
            Image[] Soldier = {new Image("res/soldier0.gif"), new Image("res/soldier1.gif"), new Image("res/soldier2.gif")};
            walking = new Animation(Soldier, duration,true);
        }catch(Exception ex) {
        }
    }

    
    
    
    
    
    @Override
    public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
       //draw when player stay or move               
        if(move) {
            walking.draw(pozX,pozY);
        }
        else {
            still.draw(pozX,pozY);
        }
    }
    
    
    
    
    
    

    @Override
    public void update(GameContainer gc, StateBasedGame arg1, int del) throws SlickException {
        move = false;       //animation when player run on/off
        
        
        //mouse position
        float mouseX = gc.getInput().getMouseX();
        float mouseY = gc.getInput().getMouseY();
        
      
        //input events
        Input input = gc.getInput();
        
        
        if(input.isKeyDown(Input.KEY_S)) {
            move = true;
            mouseX = gc.getInput().getMouseX();
            mouseY = gc.getInput().getMouseY();
           
            float a = mouseX-pozX;
            float b = mouseY - pozY;
            float pom,pom1;
            float d1 = (float) Math.sqrt(((a * a)+ (b* b))); 

            pom = a/d1;
            pom1 = b/d1;
            //if(0 > Main.pozYWindow &&  Main.pozYWindow>=-2400 ) {
                Main.pozYWindow += pom1*del*speed;
            //}
            
            //if(0 > Main.pozXWindow &&  Main.pozXWindow>=-3180 )
                Main.pozXWindow += pom*del*speed;
            
            
       }
        
        
        if(input.isKeyDown(Input.KEY_W)) {
            move = true;
            mouseX = gc.getInput().getMouseX();
            mouseY = gc.getInput().getMouseY();
           
            float a = mouseX-pozX;
            float b = mouseY - pozY;
            float pom,pom1;
            float d1 = (float) Math.sqrt(((a * a)+ (b* b))); 

            pom = a/d1;
            pom1 = b/d1;
            //System.out.println(pom + " , "+ pom1);
            Main.pozYWindow -= pom1 *del*speed;
            Main.pozXWindow -= pom*del*speed;
            
        }
        
        
        if(input.isKeyDown(Input.KEY_D)) {
            Main.pozXWindow -= del *speed;
            move = true;
        }
        
        
        if(input.isKeyDown(Input.KEY_A)) {
            Main.pozXWindow += del *speed;
            System.out.println(Main.pozXWindow);
            move = true;
        }
        
        
        
      //hero rotation with mouse
        float xDistance = mouseX - pozX;
        float yDistance = mouseY - pozY;
        double angle = Math.toDegrees(Math.atan2(yDistance, xDistance));
        
        if(move) {
            walking.getCurrentFrame().setRotation((float) angle +80);
        }else
            still.setRotation((float) angle +80);
        
        
    }
    
    

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 0;
    }

}
