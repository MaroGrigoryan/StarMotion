import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class ViewMotion extends  Applet implements Runnable {
	public double x = 2;
	public double y = 0;
	public double m = 1;
	public double _xvel = 0;
	public double _yvel = 0;
	
	private Thread motion;
	public int lag = 1;
	public boolean exit = true;
	
	public ViewMotion() {
		super();
		motion = new Thread();
	}
	
	
	//int x = 2, y = 0, r = 1; // Position and radius of the circle

	  int dx = 11, dy = 7; // Trajectory of circle
	  
	  /** This method simply draws the circle at its current position */
	  public void paint(Graphics g) {
	    g.setColor(Color.red);
	    g.fillOval(x - m, y - m, m * 2, m * 2);
	  }

	  
	  public void animate() {
		    // Bounce if we've hit an edge.
		    Rectangle bounds = getBounds();
		    if ((x - r + dx < 0) || (x + r + dx > bounds.width))
		      dx = -dx;
		    if ((y - r + dy < 0) || (y + r + dy > bounds.height))
		      dy = -dy;

		    // Move the circle.
		    x += dx;
		    y += dy;
		    
		    
		    // Ask the browser to call our paint() method to draw the circle
		    // at its new position.
		    repaint();
		  }
	
	public void start() {
		
		try {
			motion.start();
			(new Thread(this)).start();
		}
		catch (Exception e) {
		}
	}
		
	
	public void run() {
		while (exit) {
			try {
				animate();
				motion.sleep(lag);
			}
			catch (InterruptedException e) {
			}
		}
	} 
	
	public void stop(){
		try{
		exit = false;
		}
		catch (Exception e){	
		}
	}
}
  
	



