import java.awt.*;
import javax.swing.*;
public class Child {
	JPanel child = new JPanel();
	public Child(Integer locx, Integer locy) {
		child.setSize(MainClass.playerWidth, MainClass.playerWidth);
		child.setBackground(Color.red);
		child.setLocation(locx, locy);
		child.setVisible(true);
		MainClass.frame.add(child);
	}
	public void changeChildLoc(Integer locx,Integer locy) {
		child.setLocation(locx, locy);
	}
	public int getLocx() {
		return child.getLocation().x;
	}
	public int getLocy() {
		return child.getLocation().y;
	}
}
