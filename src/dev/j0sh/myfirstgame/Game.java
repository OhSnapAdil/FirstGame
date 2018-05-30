package dev.j0sh.myfirstgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import dev.j0sh.myfirstgame.display.Display;
import dev.j0sh.myfirstgame.gfx.ImageLoader;
import dev.j0sh.myfirstgame.gfx.SpriteSheet;
import dev.j0sh.myfirstgame.gfx.Assets;

public class Game implements Runnable {

	// variables for the game as well as thread to start game
	private Display display;
	public int width, height;
	public String title;
	private Thread startingThread;
	private boolean running = false;
	private BufferStrategy bs;
	private Graphics g;


	public synchronized void start() {

		running = true;
		startingThread = new Thread(this);
		startingThread.start();
	}

	public void run() {

		init();

		while (running) {
			tick();
			render();
		}

		stop();
	}

	// init is used to create display window(new object of the Display class)
	private void init() {
		display = new Display(title, width, height);
	}

	private void tick() {
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs==null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		//draw here
		g.fillRect(0, 0, width, height);
		g.drawImage(null, 100, 190, null);
		
		//end drawing
		bs.show();
		g.dispose();
	}

	public synchronized void stop() {
		if (!running) {
			return;
		}
		running = false;

		try {
			startingThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// game constructor set to take in title, width, height
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
	}
}