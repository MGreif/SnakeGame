import java.awt.*;
import javax.swing.*;
//Not used because of Lack of experimentation
public class Powerup {
	JPanel pu = new JPanel();
	public Powerup(Point location, Color c) {
		pu.setSize(new Dimension(MainClass.playerHeight,MainClass.playerWidth));
		pu.setLocation(location);
		pu.setVisible(true);
		pu.setBackground(c);
		MainClass.frame.add(pu);
	}
	public void changePosition(Point newLoc) {
		pu.setLocation(newLoc);
	}
	public Point getLocation() {
		return pu.getLocation();
	}
	public void dispose() {
		MainClass.frame.remove(pu);
	}
}