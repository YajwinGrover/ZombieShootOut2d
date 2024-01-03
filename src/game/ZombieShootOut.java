package game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import engine.AbstractGame;
import engine.GameContainer;
import engine.Image;
import engine.Renderer;

public class ZombieShootOut extends AbstractGame {
	
	
	float tileX;
	
	Player player = new Player("res/playerTiles.png",10,10,100,100);
	Image floor = new Image("res/floor.png");
	
	
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	Random generator = new Random();
	
	int waveNum = 2;

	
	

	public ZombieShootOut() {
		for(int i = 0; i < waveNum; i++) {
			enemies.add(new Enemy("res/zombie.png", 10, 10, generator.nextInt(240 - 10), generator.nextInt(240 * 9/16), this));
		}
	}

	@Override
	public void update(GameContainer gc, float deltaTime) {
		
		tileX += 10 * deltaTime;
		if(tileX > 9) {
			tileX = 0;
		}
		
		player.update(gc, deltaTime);
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update(gc, deltaTime);
			
			if(enemies.get(i).isDead()) {
				enemies.remove(i);
			}
		}
		
		if(gc.getInput().isKeyDown(KeyEvent.VK_1)) {
			enemies.add(new Enemy("res/zombie.png", 10,10, generator.nextInt(gc.getWidth()) + 1,generator.nextInt(gc.getHeight())  + 1,this));
		}
		
		if(enemies.size() <= 0) {
			if(waveNum >= 32) {
				generate();
			}
			else {
				waveNum *= 2;
				generate();
			}
			
			
		}
		
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(floor, 0, 0);
		r.fillRect((int)player.health/1000, 2, 0, 0, 0xff00ff00);
		r.fillRect((int)player.mana, 2, 0, 4, 0xff0000ff);
		player.render(r, gc);
		for(Enemy e : enemies) {
			e.render(r, gc);
		}
		
		
		
	

	}
	
	private void generate() {
		for(int i = 0; i < waveNum; i++) {
			enemies.add(new Enemy("res/zombie.png", 10, 10, generator.nextInt(240 - 10), generator.nextInt(240 * 9/16), this));
		}
	}
	
	

}
