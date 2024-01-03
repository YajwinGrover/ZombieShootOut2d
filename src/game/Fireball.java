package game;

import engine.GameContainer;
import engine.GameObject;
import engine.ImageTile;
import engine.Renderer;

public class Fireball extends GameObject{
	
	ImageTile image;
	float imageCounter;
	int targetX, targetY;
	boolean ultimate = false;

	public Fireball(String path, int tileWidth, int tileHeight, int posX, int posY) {
		image = new ImageTile(path, tileWidth, tileHeight);
		this.posX = posX;
		this.posY = posY;
		this.width = tileWidth;
		this.height = tileHeight;
		targetX = posX;
		targetY = posY;
	}

	@Override
	public void update(GameContainer gc, float deltaTime) {
		imageCounter += 10 * deltaTime;
		if(imageCounter > 9) {
			imageCounter = 0;
		}
		
	}

	@Override
	public void render(Renderer r, GameContainer gc) {
		r.drawImageTile(image, posX, posY, (int)imageCounter, 0);
		
	}
	
	public void moveTo(Player player) {
		int x = targetX;
		int y = targetY;
		
		int dx = Math.abs(x - posX);
		int dy = Math.abs(y - posY);
		
		int SX = posX < x ? 1 : -1;
		int SY = posY < y ? 1 : -1;
		
		int err = dx - dy;
		int e2;
		
		if(posX == x && posY == y) {
			targetX = player.getPosX();
			targetY = player.getPosY();
		}
		e2 = 2 * err;
		
		if(e2 > -1 * dy) {
			err -= dy;
			posX += SX * 1.5;
			
		}
		if(e2 < dx) {
			err += dx;
			posY += SY * 1.5;
		}
		
	}
	
	public void burnFireball() {
		for(int i = 0; i < image.getPixels().length;i++) {
			if(image.getPixels()[i] == 0xffffa200 || image.getPixels()[i] == 0xffff4800) {
				image.setPixel(i, 0xffff00ff);
			}
		}
	}
	
	public void normalize() {
		for(int i = 0; i < image.getPixels().length;i++) {
			if(image.getPixels()[i] == 0xffff00ff ||  image.getPixels()[i] == 0xffff4800) {
				image.setPixel(i, 0xffffa200);
			}
		}
	}
	
	public void ultimateFireball() {
		for(int i = 0; i < image.getPixels().length;i++) {
			if(image.getPixels()[i] == 0xffffa200 || image.getPixels()[i] == 0xffff00ff) {
				image.setPixel(i, 0xffff4800);
			}
		}
	}
	

}
