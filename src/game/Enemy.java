package game;


import engine.GameContainer;
import engine.GameObject;
import engine.ImageTile;
import engine.Renderer;

public class Enemy extends GameObject {
	
	ImageTile image;
	ZombieShootOut zso;
	
	float imageCounterX;
	float imageCounterY = 0;
	int health = 100;
	boolean burning = false;
	float burnReset = 0;
	float burningCounter = 0;
	float resetCounter = 0;
	boolean damageAble = true;
	float burnTime = 0.5f;
	float burnDamage = 5;
	boolean canMove = true;
	public Enemy(String path, int tileWidth, int tileHeight,int posX, int posY, ZombieShootOut zso) {
		image = new ImageTile(path, 10,10);
		this.posX = posX;
		this.posY = posY;
		this.width = tileWidth;
		this.height = tileHeight;
		this.zso = zso;
	}

	@Override
	public void update(GameContainer gc, float deltaTime) {
		imageCounterX += 20 * deltaTime;
		burnReset += deltaTime;
		resetCounter += deltaTime;
		if(imageCounterX > 10) {
			imageCounterX = 0;
		}
		if(burnReset > 5) {
			burning = false;
			burnReset = 0;
		}
		if(burning) {
			burn(deltaTime);
		}
		if(canMove) {
			moveTo(zso.player,deltaTime);
		}
		
		collision(zso.player);
		
		if(health <= 0) {
			this.dead = true;
			zso.player.mana += 1;
			zso.player.health += 1000;
		}
		if(resetCounter > 0.5) {
			normalize();
			damageAble = true;
		}
		int playahX = zso.player.getPosX();
		int playahY = zso.player.getPosY();
		int dx = playahX - posX;
		int dy = playahY - posY;
		double distance = Math.sqrt(dx * dx + dy * dy);
		if(distance < 30) {
			attack();
		}
		
		
	}

	@Override
	public void render(Renderer r, GameContainer gc) {
		r.drawImageTile(image, posX, posY, (int)imageCounterX, (int)imageCounterY);

	}

	public void moveTo(Player player ,float deltaTime) {
		int x = player.getPosX() - 10;
		int y = player.getPosY() - 10;
		int dx = Math.abs(x - posX);
		int dy = Math.abs(y - posY);
		
		int SX = posX < x ? 1 : -1;
		int SY = posY < y ? 1 : -1;
		
		int err = dx - dy;
		int e2;
		
		if(posX == x && posY == y) {
			return;
		}
		e2 = 2 * err;
		
		if(e2 > -1 * dy) {
			err -= dy;
			posX += SX;
			
		}
		if(e2 < dx) {
			err += dx;
			posY += SY;
		}
		
	}

	
	private void collision(Player player) {
		
			Fireball fireball = player.fireball;
			int radius = fireball.getWidth()/2;
			int centX = fireball.getPosX() + radius;
			int centY = fireball.getPosY() + radius;
			
			int x = posX + width/2;
			int y = posY + width/2;
			
			int dx = centX - x;
			int dy = centY - y;
			
			double distance = Math.sqrt(dx * dx + dy * dy);
			
			if(distance < radius + width/2 && damageAble) {
				health -= 10;
				burning = player.burn;
				redAlize();
				damageAble = false;
				resetCounter = 0;
				
				if(fireball.ultimate) {
					burnDamage *= 1.5;
					burnTime /= 2.5;
					burning = true;
					canMove = false;
				}
				else {
					burnDamage = 5;
					burnTime = 0.5f;
				}
			}
		
	}

	private void attack() {
		if(zso.player.shield) {
			zso.player.health -= 5;
		}
		else {
			zso.player.health -= 10;
		}
		
	}
	
	private void burn(float deltaTime) {
		burningCounter += deltaTime;
		if(burningCounter > burnTime) {
			health -= burnDamage;
			redAlize();
			resetCounter = 0;
			burningCounter = 0;
		}
	}
	
	private void redAlize() {
		for(int i = 0; i < image.getPixels().length;i++) {
			if(image.getPixels()[i] == 0xff045200) {
				image.setPixel(i, 0xffa01300);
			}
		}
	}
	

	
	private void normalize() {
		for(int i = 0; i < image.getPixels().length;i++) {
			if(image.getPixels()[i] == 0xffa01300) {
				image.setPixel(i, 0xff045200);
			}
		}
	}
	
	
	
}
