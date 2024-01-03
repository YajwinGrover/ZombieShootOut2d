package engine;

import java.awt.image.DataBufferInt;

public class Renderer {
	
	int pixelWidth;
	int pixelHeight;
	
	int[] pixels;

	public Renderer(GameContainer gc) {
		pixelWidth = gc.getWidth();
		pixelHeight = gc.getHeight();
		pixels = ((DataBufferInt)gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
		
		
		
	}
	
	public void clear() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	
	public void setPixel(int offX, int offY, int value) {
		if(offX < 0 || offX > pixelWidth || offY < 0 || offY > pixelHeight) {
			return;
		}
		if(value == 0xff4a412a)
			return;
		
		if(offX + offY * pixelWidth > pixels.length) {
			return;
		}
		pixels[offX + offY * pixelWidth] = value;
	}
	
	
	public void drawRect(int width, int height, int offX, int offY, int color) {
		for(int y = 0; y <= height; y++) {
			setPixel(offX, y + offY, color);
			setPixel(offX + width, y+ offY, color);
		}
		for(int x = 0; x <= width; x++) {
			setPixel(x + offX, offY, color);
			setPixel(x + offX, offY + height, color);
		}
	}
	
	public void fillRect(int width, int height, int offX, int offY, int color) {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				setPixel(x + offX, y + offY, color);
			}
		}
	}
	
	public void drawCircle(int radius, int offX, int offY, int color) {
		int diameter = radius * 2;
		
		for(int y = 0; y < diameter;y++) {
			for(int x = 0; x < diameter; x++) {
				double distance = Math.sqrt((x - radius) * (x - radius) + (y - radius) * (y - radius));
				
				if(distance < radius) {
					setPixel(x + offX, y + offY, color);
				}
			}
		}
	}
	
	
	public void drawImage(Image image, int offX, int offY) {
		
			
		for(int y = 0; y < image.getHeight(); y++) {
			for(int x = 0; x < image.getWidth(); x++) {
				if(x + y * image.getWidth() > image.getPixels().length) {
					return;
				}
				int color = image.getPixels()[x + y * image.getWidth()];
				
				setPixel(offX + x, offY + y, color);
			}
		}
	}
	
	public void drawImageTile(ImageTile tile, int offX, int offY, int tileX, int tileY) {
		
		for(int y = 0; y < tile.getTileHeight();y++) {
			for(int x = 0; x < tile.getTileWidth(); x++) {
				if((x + tileX * tile.getTileWidth()) + (y + tileY * tile.getTileHeight()) * tile.getWidth() > tile.getPixels().length) {
					return;
				}
				int color = tile.getPixels()[(x + tileX * tile.getTileWidth()) + (y + tileY * tile.getTileHeight()) * tile.getWidth()];
				setPixel(offX + x, offY + y, color);
			}
		}
	}
	

}
