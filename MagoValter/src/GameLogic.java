import java.awt.Color;
import java.awt.Graphics;

public class GameLogic {
	private int x,y;
	public GameLogic() {
		super();
		x = 0;
		y=0;
	}
	
	
	
	public void update() {
		
	}
	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 100, 100);
	}



	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}
}
