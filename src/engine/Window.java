package engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Window {
	
	private JFrame frame;
	private BufferedImage image;
	private Canvas canvas;
	private BufferStrategy bs;
	private Graphics g;
	

	public Window(GameContainer gc) {
		image = new BufferedImage(gc.getWidth(),gc.getHeight(),BufferedImage.TYPE_INT_RGB);
		
		canvas = new Canvas();
		Dimension s = new Dimension((int)(gc.getWidth() * gc.getScale()) ,(int)(gc.getHeight() * gc.getScale()));
		//New canvas settings
		canvas.setPreferredSize(s);
		canvas.setMaximumSize(s);
		canvas.setMinimumSize(s);
		//Frame settings
		frame = new JFrame(gc.getTitle());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(canvas,BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		
		//Add drawable image to canvas
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		//create graphics to draw
		g = bs.getDrawGraphics();
	}
	
	
	public void update() {
		g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(),null);
		bs.show();
	}


	public JFrame getFrame() {
		return frame;
	}


	public void setFrame(JFrame frame) {
		this.frame = frame;
	}


	public BufferedImage getImage() {
		return image;
	}


	public void setImage(BufferedImage image) {
		this.image = image;
	}


	public Canvas getCanvas() {
		return canvas;
	}


	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}


	public BufferStrategy getBs() {
		return bs;
	}


	public void setBs(BufferStrategy bs) {
		this.bs = bs;
	}


	public Graphics getG() {
		return g;
	}


	public void setG(Graphics g) {
		this.g = g;
	}

}
