package game;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import engine.GameContainer;
import engine.GameObject;
import engine.ImageTile;
import engine.Renderer;

public class Player extends GameObject {
	
	ImageTile image;
	Fireball fireball;
	
	boolean shootable = true;
	
	float imageCounter = 0;
	boolean moving = false;
	boolean flipped = false;
	int imageCounterY = 0;
	float counter = 0;
	
	boolean shield = false;
	boolean burn;
	
	int health = 100000;
	float mana = 100;

	public Player(String path, int tileWidth, int tileHeight, int posX, int posY) {
		image = new ImageTile(path, tileWidth, tileHeight);
		this.posX = posX;
		this.posY = posY;
		this.width = image.getTileWidth();
		this.height = image.getTileHeight();
		fireball = new Fireball("res/fireball.png",10,10,posX,posY);
		
	}

	@Override
	public void update(GameContainer gc, float deltaTime) {
		if(health <= 0) {
			gc.setGame(new EndScreen());
		}
		imageCounter += 20 * deltaTime;
		counter += deltaTime ;
		if(counter > 5) {
			
			shield = false;
			counter = 0;
			burn = false;
			fireball.normalize();
			fireball.ultimate = false;
		}
		if(imageCounter > 10)
			imageCounter = 0;
		if(posX < 0 || posY > gc.getWidth()) {
			return;
		}
		if(posY < 0 ) {
			posY = 0;
		}
		if(posY + height > 130)
			posY = 130 - height;
		
		move(gc);
		attack(gc);
		
		if(gc.getInput().isKeyDown(KeyEvent.VK_Q) && mana > 0)
			abilityQ();
		
		if(gc.getInput().isKeyDown(KeyEvent.VK_E) && mana > 0)
			abilityE();
		
		if(gc.getInput().isKeyDown(KeyEvent.VK_R) && mana > 0)
			abliltyR();
		
		
		if(moving == false) {
			imageCounter = 0;
		}
		
		fireball.update(gc, deltaTime);
		fireball.moveTo(this);
		fireball.moveTo(this);
		if(fireball.getPosX() > posX - 5 && fireball.getPosX() < posX + 5)
			if(fireball.getPosY() > posY - 5 && fireball.getPosY() < posY + 5)
			shootable = true;
		

	}

	@Override
	public void render(Renderer r, GameContainer gc) {
		if(shield) {
			r.drawCircle(7, posX - 2 , posY - 2, 0xffff0000);
		}

		fireball.render(r, gc);
		r.drawImageTile(image, posX, posY, (int)imageCounter, imageCounterY);
		
		
		
	}
	
	private void move(GameContainer gc) {
		if(gc.getInput().isKey(KeyEvent.VK_D)) {
			imageCounterY = 0;
			moving = true;
			posX += 2;
			if(flipped) {
				flipped = false;
				image.flipImage();
			}
		}
		else if(gc.getInput().isKey(KeyEvent.VK_A)) {
			imageCounterY = 0;
			moving = true;
			posX -= 2;
			if(!flipped) {
				flipped = true;
				image.flipImage();
			}
		}
		else if(gc.getInput().isKey(KeyEvent.VK_W)) {
			moving = true;
			posY -= 2;
			imageCounterY = 1;
		}
		else if(gc.getInput().isKey(KeyEvent.VK_S)) {
			moving = true;
			posY += 2;
			imageCounterY = 2;
		}
		
		else {
			moving = false;
			imageCounterY = 0;
		}
		if(posX < 0) {
			posX = 0;
		}
		if(posX > gc.getWidth() - this.width) {
			posX = gc.getWidth() - this.width;
		}
		
		
	}
	
	private void attack(GameContainer gc) {
		if(gc.getInput().isButton(MouseEvent.BUTTON1) && shootable) {
			fireball.targetX = gc.getInput().getMouseX();
			fireball.targetY = gc.getInput().getMouseY();
			shootable = false;
		}
	}
	
	private void abilityQ() {
		shield = true;
		counter = 0;
		mana -= 5;
	}
	
	private void abilityE() {
		fireball.burnFireball();
		burn = true;
		counter = 0;
		mana -= 10;
	}
	
	private void abliltyR() {
		fireball.ultimateFireball();
		burn = true;
		fireball.ultimate = true;
		counter = 0;
		mana -= 25;
	}
	
	

}
