package dev.j0sh.myfirstgame;

import dev.j0sh.myfirstgame.display.Display;

public class Game implements Runnable {

	private Display display;
	public int width, height;
	private Thread myThread;
	private boolean runnning = false;

	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;

		display = new Display(title, width, height);
	}
	
	private void init() {
		while(true) {
			
		}
	}

	public void run() {
	}

	public synchronized void start() {
		myThread = new Thread(this);
		myThread.start();

	}

	public synchronized void stop() {
		try {
			myThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
