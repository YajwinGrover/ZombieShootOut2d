package game;

import java.awt.event.MouseEvent;

import engine.AbstractGame;
import engine.GameContainer;
import engine.Image;
import engine.Renderer;

public class EndScreen extends AbstractGame {

	Image button = new Image("res/restartButton.png");

	public EndScreen() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(GameContainer gc, float deltaTime) {
		onClick(gc);

	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(button, gc.getWidth()/2 - button.getWidth()/2, gc.getHeight()/2 - button.getHeight()/2);

	}
	
	private void onClick(GameContainer gc) {
		if(gc.getInput().isButton(MouseEvent.BUTTON1)) {
			int mouseX = gc.getInput().getMouseX();
			int mouseY = gc.getInput().getMouseY();
			
			if(mouseX > gc.getWidth()/2 - button.getWidth()/2 && mouseX < gc.getWidth()/2 + button.getWidth()/2) {
				if(mouseY > gc.getHeight()/2 - button.getHeight()/2 && mouseY < gc.getHeight() + button.getWidth()/2) {
					gc.setGame(new ZombieShootOut());
				}
			}
		}
	}

}
