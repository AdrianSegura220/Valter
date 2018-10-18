import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
			GamePanel panel = new GamePanel();
			JFrame frame = new JFrame();
			frame.add(panel);
			frame.setVisible(true);
			frame.setSize(panel.getPwidth(),panel.getPheight());
			frame.setLocation(650, 300);
			

	}

}
