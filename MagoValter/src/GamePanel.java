import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener
{
  private Graphics dbg;
  private Image dbImage = null;
  private static final int PWIDTH = 500;   // size of panel
  private static final int PHEIGHT = 400;
  private Thread animator;            // for the animation
  private volatile boolean running = false;    // stops the animation
  private volatile boolean gameOver = false; // for game termination
  private volatile boolean isPaused = false;
  private GameLogic logica;
  // more variables, explained later
  //       :
  public GamePanel( )
  {
	  setBackground(Color.white);    // white background
	  setPreferredSize( new Dimension(PWIDTH, PHEIGHT));
	  logica = new GameLogic();
	  this.startGame();
	  addMouseListener(this);
	  this.addKeyListener(this);
	  setFocusable(true);
	  
   }  // end of GamePanel( )
  
  public void addNotify( )
  /* Wait for the JPanel to be added to the
     JFrame/JApplet before starting. */
  {
    super.addNotify( );   // creates the peer
    startGame( );         // start the thread
  }
  private void startGame( )
  // initialise and start the thread
  {
    if (animator == null || !running) {
      animator = new Thread(this);
      animator.start( );
    }
  } // end of startGame( )
  public void stopGame( )
  // called by the user to stop execution
  {  
	 running = false;   
  }
  public void pauseGame() {
	  isPaused = true;
  }
  public synchronized void resumeGame( )
  {  isPaused = false;   // I do not do this 
  }
  public void run( )
  /* Repeatedly: update, render, sleep so loop takes close
  to period ms */
  {
	  long beforeTime, timeDiff, sleepTime;
	  beforeTime = System.currentTimeMillis( );
	  int period = 1000/100;
	  running = true;
	  while(running) {
		  try {
		      if (isPaused) {
		        synchronized(this) {
		          while (isPaused && running)
		            wait( );
		         }
		       }
		      } // of try block
		  catch (InterruptedException e){}
		  gameUpdate( );
		  gameRender( );
		  paintScreen();
		  timeDiff = System.currentTimeMillis( ) - beforeTime;
	
		  sleepTime = period - timeDiff;   // time left in this loop
		  System.out.println(sleepTime);
		  if (sleepTime <= 0)  // update/render took longer than period
			  sleepTime = 5;    // sleep a bit anyway
		  try {
			  Thread.sleep(sleepTime);  // in ms
		  }
		  catch(InterruptedException ex){}
		  beforeTime = System.currentTimeMillis( );
		  
	  }
	  
	  System.exit(0);
  }
  
  private void gameUpdate(){ 
	  if(!gameOver) {
		  logica.update();
		  
		  
		 
	  }
		 
      // update game state ...
  }
  
  private void gameRender( )
//draw the current frame to an image buffer
  {
	  if (dbImage == null){  // create the buffer
		  dbImage = createImage(PWIDTH, PHEIGHT);
		  if (dbImage == null) {
			 System.out.println("dbImage is null");
			 return;
		  }
		  else
	         dbg = dbImage.getGraphics( );
	     }
	     // clear the background
	     dbg.setColor(Color.white);
	     dbg.fillRect (0, 0, PWIDTH, PHEIGHT);
	     logica.paint(dbg);
	     dbg.setColor(Color.blue);
	     // draw game elements
	     // ...
	     if (gameOver)
	       gameOverMessage(dbg);
	     
	     dbg.dispose();
	   }
  private void
  paintScreen()
  // actively render the buffer image to the screen
  {
    Graphics g;
    try {
      g = this.getGraphics( );  // get the panel's graphic context
      if ((g != null) && (dbImage != null))
        g.drawImage(dbImage, 0, 0, null);
      Toolkit.getDefaultToolkit( ).sync( );  // sync the display on some systems
      g.dispose( );
    }
    catch (Exception e)
    { System.out.println("Graphics context error: " + e);  }
  } 
  private void gameOverMessage(Graphics g)
//center the game-over message
  { // code to calculate x and y...
	  g.drawString("Se acabo el juego", PWIDTH/2, PHEIGHT/2);
  }  // end of gameOverMessage( )
  
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    if (dbImage != null)
      g.drawImage(dbImage, 0, 0, null);
  }
  
// INPUTS (KEY LISTENERS)
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
  
	public void keyPressed(KeyEvent k) {
		
		if(k.getKeyCode() == 39) {
			System.out.println("hey");
		}
		
	}

	
	public void keyReleased(KeyEvent k) {
		
		
	}

	
	public void keyTyped(KeyEvent k) {
		
		
	}	
	
	
// INPUTS (MOUSE LISTENERS)
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
	@Override
	public void mouseClicked(MouseEvent m) {
		// TODO Auto-generated method stub
		System.out.println(m.getX());
		System.out.println(m.getY());
	}

	@Override
	public void mouseEntered(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}
// SETTERS Y GETTERS
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
  public void setDbg(Graphics dbg) {
		this.dbg = dbg;
	}

	public Image getDbImage() {
		return dbImage;
	}

	public void setDbImage(Image dbImage) {
		this.dbImage = dbImage;
	}

	public Thread getAnimator() {
		return animator;
	}

	public void setAnimator(Thread animator) {
		this.animator = animator;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public boolean isPaused() {
		return isPaused;
	}
	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public static int getPwidth() {
		return PWIDTH;
	}

	public static int getPheight() {
		return PHEIGHT;
	}
	  public Graphics getDbg() {
			return dbg;
	}

	

	
}