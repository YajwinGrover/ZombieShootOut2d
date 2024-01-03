package engine;

public class GameContainer implements Runnable{
	
	private Thread thread;
	
	private AbstractGame game;
	
	private Renderer renderer;
	
	private Window window;
	
	private Input input;
	
	private int width = 240, height = width * 9/16;
	
	private float scale = 5f;
	
	private String title = "Zombie Shoot Out 2-d";
	
	private boolean running = false;
	
	private double UPDATE_CAP = 1.0/60.0;

	public GameContainer(AbstractGame game) {
		this.game = game;
	}

	public void start() {
		thread = new Thread(this);
		window = new Window(this);
		renderer = new Renderer(this);
		input = new Input(this);
	
		
		thread.run();
		
	}
	@Override
	public void run() {
		running = true;
		
		boolean rendering = false;
		 
		double lastTime = System.nanoTime() / 1000000000.0;
		
		double firstTime = 0;
		
		double passedTime = 0;
		
		double unproccesedTime = 0;
		
		while(running) {
			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			
			unproccesedTime += passedTime;
			
			while(unproccesedTime >= UPDATE_CAP) {
				rendering = true;
				unproccesedTime -= UPDATE_CAP;
				
				game.update(this, (float)UPDATE_CAP);
				input.update();
			}
			
			if(rendering) {
				renderer.clear();
				game.render(this, renderer);
				window.update();
			}
			else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public AbstractGame getGame() {
		return game;
	}

	public void setGame(AbstractGame game) {
		this.game = game;
	}

	public Renderer getRenderer() {
		return renderer;
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
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

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public double getUPDATE_CAP() {
		return UPDATE_CAP;
	}

	public void setUPDATE_CAP(double uPDATE_CAP) {
		UPDATE_CAP = uPDATE_CAP;
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

}
