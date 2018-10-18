import javax.swing.JFrame;

public class Ventana extends JFrame implements Runnable {
	GamePanel juego = new GamePanel();
	public Ventana() {
		super();
		this.setSize(juego.getWidth(),juego.getHeight());
		
	}
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
