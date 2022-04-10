package rs.ac.bg.etf.kdp;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class Consumer extends Thread {
	static int ID = 0;
	private int id;
	private BlockingQueue<Integer> blockingQueue;
	private Semaphore mutex;
	
	public Consumer(BlockingQueue<Integer> blockingQueue, Semaphore mutex) {
		super("Consumer");
		this.id = ++ID;
		this.blockingQueue = blockingQueue;
		this.mutex = mutex;
	}

	@Override
	public void run() {
		while (true) {
			consume();
			try {
				mutex.acquire();
				System.out.println("ID " + id + "want item");
				mutex.release();
				int num = blockingQueue.take();
				mutex.acquire();
				System.out.println("ID " + id + "consumed "+ num);
				mutex.release();
			} catch (InterruptedException e) {
			}
		}
	}

	private void consume() {
		try {
			sleep(1000 + (int) (Math.random() * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
