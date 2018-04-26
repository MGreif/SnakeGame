import java.awt.*;
import java.util.*;
import javax.swing.*;
public class Powerup {
	public Powerup(Integer locx, Integer locy, Color c) {
		JPanel powerup = new JPanel();
		powerup.setBackground(c);
		powerup.setLocation(locx, locy);
		powerup.setSize(MainClass.playerWidth, MainClass.playerHeight);
		MainClass.frame.add(powerup);
	}
}
