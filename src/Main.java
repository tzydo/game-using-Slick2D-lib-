import java.awt.Polygon;
import java.awt.RenderingHints.Key;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;





public class Main extends BasicGame {
    
    private static AppGameContainer app;            //game container
    private static int screenWidth = 800;
    private static int screenHeight = 600;
    public static int screenSizeWidth = 2000;
    public static int screenSizeMinWidth = -2000;
    public static int screenSizeHeight = 1500;
    public static int screenSizeMinHeight = -1500;
    public static int pozXWindow = -2000;
    public static int pozYWindow = -1500;
    private int enemies = 100;
    public int kill = 0;
    
    
    private Random r = new Random();
    private Image background;
    private Image cursor;
    
    private Player p;
    private Enemy e;
  
    
    static ArrayList<Player> list = new ArrayList<Player>();
    static ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    static ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
    static ArrayList<Enemy> deadEnemy = new ArrayList<Enemy>();
  
    
    
    public static void main(String[] args) throws SlickException {
        app = new AppGameContainer(new Main("Zombi shooter"));
        app.setDisplayMode(screenWidth, screenHeight, false);
     //   app.setFullscreen(true);
        app.setTargetFrameRate(80);     //set fps
        
        
        app.start();
    }
    
    
    
    public Main(String title) {
        super(title);
        // TODO Auto-generated constructor stub
    }

    
    
    
    
    @Override
    public void init(GameContainer arg0) throws SlickException {
        // TODO Auto-generated method stub
        background = new Image("res/mapa.png");
        cursor = new Image("res/cursor2.gif");
        
        
        p = new Player();
        list.add(p);
        
            
        
        try {
            arg0.setMouseCursor(cursor, 16, 16);    
        }catch(SlickException ex){
            System.out.println("cursor error!");
        }
       
        
 
        for(int i =0;i<enemies;i++) {
            e = new Enemy(r.nextInt(4000)-screenSizeMinWidth,r.nextInt(4000)- screenSizeMinHeight);
            enemyList.add(e);
            //list.add(e);
        }
        
        
        for(BasicGameState b : bulletList) {
            b.init(arg0, null);
        }
        

        for(BasicGameState a : list){
            a.init(arg0, null);
         }
        
        for(Enemy e : enemyList){
            e.init(arg0, null);
         }
        
    }
    
    
    

    @Override
    public void render(GameContainer arg0, Graphics arg1)
            throws SlickException {
        // TODO Auto-generated method stub
        
        background.draw(pozXWindow,pozYWindow);
        arg1.setColor(Color.orange);
        
        arg1.drawString("Kill :" + kill, 700, 500);
        
        for(Enemy e : deadEnemy){
            e.render(arg0,null, arg1);
         }
        
        
        
        for(BasicGameState a : list){
           a.render(arg0,null, arg1);
        }
        
        
        
        for(Enemy e : enemyList){
            e.render(arg0,null, arg1);
         }
        
        
        
        for(BasicGameState b : bulletList) {
            b.init(arg0, null);
            b.render(arg0,null, arg1);
        }
        
      

    }
    

   

    @Override
    public void update(GameContainer gc, int arg1) throws SlickException {
      
      //update all objects
        
        for(Player a : list){
            a.update(gc,null, arg1);
         }
        
        
        for(Enemy e : enemyList) {
            if(e!=null)e.update(gc,null, arg1);
        }
       
       //Bullet updating and detection colision
        for(int i=0;i<bulletList.size();i++) {
            Bullet b = bulletList.get(i);
            
            
            
            for(int j=0;j<enemyList.size();j++) {
                Enemy en = enemyList.get(j);
                    if(en.hitDetector(b.getPozX(), b.getPozY(),10, 10)) {
                        System.out.println("Trafiony!");
                        kill+=1;
                        bulletList.remove(b);
                        enemyList.remove(en);
                        deadEnemy.add(en);
                        
                    }
                
                
          }
            
            
                if(b!=null && b.healt >0)b.update(gc,null, arg1);
                
            }
        
        
        
        for(Enemy b : deadEnemy) {
            if(b.getTime() == 0) {
                deadEnemy.remove(b);
            }
        }
        
        
        
        
       
       
        
        
        
        
        
        //input events
        Input input = gc.getInput();
        
        
        //mouse Listener
        if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            Bullet b = new Bullet(Player.getPozX()+35, Player.getPozY()+35, gc.getInput().getMouseX(),gc.getInput().getMouseY());
            bulletList.add(b);
            System.out.println("Shoot!");
         }
    }
    
    

}
