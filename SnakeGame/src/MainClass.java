import java.awt.*;

import java.util.*;
import java.util.Timer;
import java.awt.event.*;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.JOptionPane;
public class MainClass implements KeyListener {
	//Creating all variables
	public static JFrame frame = new JFrame("Frame");
	public static JPanel player = new JPanel();
	public static int playerWidth = 30;
	public static int playerHeight = playerWidth;
	public static Random rnd = new Random();
	public static java.util.List<Integer> childCoordsX = new ArrayList<Integer>();
	public static java.util.List<Integer> childCoordsY = new ArrayList<Integer>();
	public static java.util.List<Child> childList = new ArrayList<Child>();
	public static int childs = 0;
	public static int score = childs;
	public static byte childsPerScore = 1;
	public static long delayForTimer = 75;
	public static byte reqForPu = 1;
	public static char direction;
	public static int stepSize = playerWidth;
	public static JPanel food = new JPanel();
	public static int widthInt = 2000;
	public static boolean powerupCollected = true;
	public static boolean powerupActive = false;
	public static int heightInt = 150;
	public static int frameWidth = widthInt - (widthInt % playerWidth);
	public static int[] coords = new int[frameWidth / playerWidth];
	public static int frameHeight = frameWidth - (heightInt % playerWidth);
	public static Timer timer = new Timer();
	public static java.util.List<Powerup> PUList = new ArrayList<Powerup>();
	public boolean keyPressed;
public MainClass() {
	frame.addKeyListener(this);
}
public static void main(String[] args) {
	System.out.println("swag");
	createFrame();
	createPlayer();
	createTimer();
	createGrid();
	generateFood();
	new MainClass();
	
}
public static void createTimer() {
	timer.scheduleAtFixedRate(new TimerTask() {
		  public void run() {
				childCoordsX.add(player.getLocation().x);
				childCoordsY.add(player.getLocation().y);
		  switch(direction) {
		  case 'w': player.setLocation(player.getLocation().x, player.getLocation().y - stepSize); break;
		  case 's': player.setLocation(player.getLocation().x, player.getLocation().y + stepSize); break;
		  case 'a': player.setLocation(player.getLocation().x - stepSize, player.getLocation().y); break;
		  case 'd': player.setLocation(player.getLocation().x + stepSize, player.getLocation().y); break;
		  }
		  moveChilds();
		  checkForCollision();
		  }
		}, 0 ,delayForTimer);}
public static void createGrid() {
	for(int i = 0; i < coords.length ; i++) {
		coords[i] = i * playerWidth;
	}
}
public static void generateFood() {
	food.setBackground(Color.green);
	food.setSize(playerWidth, playerWidth);
	food.setLocation(coords[rnd.nextInt(((coords.length - 1) - 1) + 1) + 1],coords[rnd.nextInt(((coords.length  - 1 )- 1) + 1) + 1]);
	System.out.println(food.getLocation());
	frame.add(food);
}
public void keyTyped(KeyEvent e) {
	if (keyPressed = true) {
		childCoordsX.add(player.getLocation().x);
		childCoordsY.add(player.getLocation().y);
	if (e.getKeyChar() == 'w' && direction != 's') {
		move("up");
	}else if (e.getKeyChar() == 's' && direction != 'w') {
		move("down");
	}else if (e.getKeyChar() == 'a' && direction != 'd') {
		move("left");
	}else if (e.getKeyChar() == 'd' && direction != 'a') {
		move("right");
	}
	}
	keyPressed = false;
}
public static void move(String dir) {
	switch(dir) {
	case "up": direction = 'w'; break;
	case "down": direction = 's'; break;
	case "left": direction = 'a'; break;
	case "right": direction = 'd'; break;
	}
}
public static void moveChilds() {
	Integer[] childx = childCoordsX.toArray(new Integer[childs + 1]);
	Integer[] childy = childCoordsY.toArray(new Integer[childs + 1]);
	if (childs > 0) {
	for (int i = 0; i <= childs - 1; i++) {
	Child[] childArray = childList.toArray(new Child[childs]);
	childArray[i].changeChildLoc(childx[(childx.length - 1) - i], childy[(childy.length  - 1)- i]);
	}
	}
}
public static void checkForCollision() {
	checkForFoodCollision();
	checkForWallCollision();
	checkForSelfCollision();
}
public static void checkForFoodCollision() {
	if (player.getLocation().x == food.getLocation().x && player.getLocation().y == food.getLocation().y) {
		System.out.println("HIT!");
		food.setVisible(false);
		generateFood();
		food.setVisible(true);
		createChild();
		childs = childs + childsPerScore;
	}
}
public static void checkForWallCollision() {
	if (player.getLocation().x <= 0) {
		player.setLocation(frameWidth, player.getLocation().y);
	}else if (player.getLocation().x >= frameWidth) {
		player.setLocation(0, player.getLocation().y);
	}else if (player.getLocation().y <= 0) {
		player.setLocation(player.getLocation().x, frameHeight);
	}else if (player.getLocation().y >= frameHeight) {
		player.setLocation(player.getLocation().x, 0);
	}
}
public static void checkForSelfCollision() {
	for (int i = 0; i < childs; i++) {
		Child[] childArray = childList.toArray(new Child[childs]);
		if (childArray[i].getLocx() == player.getLocation().x && childArray[i].getLocy() == player.getLocation().y) {
			System.out.println("FAILED!!");
			frame.setBackground(Color.red);
			timer.cancel();
			JOptionPane.showMessageDialog(null, "You Failed!!", "InfoBox: " + "LOOSER", JOptionPane.INFORMATION_MESSAGE);
			frame.dispose();
		}
	}
}
public static void createChild() {
	Integer[] childx = childCoordsX.toArray(new Integer[childs + 1]);
	Integer[] childy = childCoordsY.toArray(new Integer[childs + 1]);
	for (int i = 0; i < childsPerScore; i++) {
	Child child = new Child(childx[childx.length - 1],childy[childy.length - 1]);
	childList.add(child);
	}
	frame.repaint();
}
public void keyReleased(KeyEvent e) {
	keyPressed = true;
}
public static void createFrame() {
	frame.setSize(frameWidth + 100, frameHeight+ 100);
	frame.setVisible(true);
	frame.setLayout(null);
}
public void keyPressed(KeyEvent e) {
}
public static void createPlayer() {
	player.setLayout(null);
	player.setBackground(Color.red);
	player.setLocation(playerWidth, playerWidth);
	player.setSize(playerWidth, playerWidth);
	frame.add(player);
}
}
