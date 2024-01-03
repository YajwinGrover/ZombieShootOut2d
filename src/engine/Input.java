package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Input implements KeyListener, MouseListener, MouseMotionListener{
	
	
	private GameContainer gc;
	
	private int NUM_KEYS = 256;
	
	private boolean[] keys = new boolean[NUM_KEYS];
	
	private boolean[] lastKeys = new boolean[NUM_KEYS];
	
	private int NUM_BUTTONS = 5;
	
	private boolean[] buttons = new boolean[NUM_BUTTONS];
	
	private boolean[] lastButtons = new boolean[NUM_BUTTONS];
	
	private int mouseX;
	private int mouseY;

	public Input(GameContainer gc) {
		this.gc = gc;
		
		gc.getWindow().getCanvas().addKeyListener(this);
		gc.getWindow().getCanvas().addMouseListener(this);
		gc.getWindow().getCanvas().addMouseMotionListener(this);
		
	}
	
	public void update() {
		for(int i = 0; i < NUM_KEYS; i++) {
			lastKeys[i] = keys[i];
			
		}
		for(int i = 0; i < NUM_BUTTONS; i++) {
			lastButtons[i] = buttons[i];
			
		}
	}
	
	public boolean isKey(int keyCode) {
		return keys[keyCode];
	}
	
	public boolean isKeyUp(int keyCode) {
		return !keys[keyCode] && lastKeys[keyCode];
	}
	public boolean isKeyDown(int keyCode) {
		return keys[keyCode] && !lastKeys[keyCode];
	}
	public boolean isButton(int keyCode) {
		return buttons[keyCode];
	}
	
	public boolean isButtonUp(int keyCode) {
		return !buttons[keyCode] && lastButtons[keyCode];
	}
	public boolean ussButtonDown(int keyCode) {
		return buttons[keyCode] && !lastButtons[keyCode];
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = (int) (e.getX() / gc.getScale());
		mouseY = (int) (e.getY() / gc.getScale());
		
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		mouseX = (int) (e.getX() / gc.getScale());
		mouseY = (int) (e.getY() / gc.getScale());
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		buttons[e.getButton()] = true;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		buttons[e.getButton()] = false;
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public int getMouseX() {
		return mouseX;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}

}
