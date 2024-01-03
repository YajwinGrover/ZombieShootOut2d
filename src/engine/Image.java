package engine;


import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;


import javax.imageio.ImageIO;

public class Image {
	
	private int width,height;
	
	private int[] pixels;

	public Image(String path) {
		
		BufferedImage image = null;

		
		try {
			image = ImageIO.read(new FileInputStream(path));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		width = image.getWidth();
		height = image.getHeight();
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
		
		image.flush();
		
	}

	public void flipImage() {
		int[] newPixels = new int[pixels.length];
		for (int i = 0; i < pixels.length; i++) {
		    newPixels[i] = pixels[i - 2 * (i % width) + width - 1];
		}
		
		pixels = newPixels;
		
		
	}
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int[] getPixels() {
		return pixels;
	}

	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}
	public void setPixel(int index, int value) {
		this.pixels[index] = value;
	}

}
