package rs.ac.bg.etf.kdp;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class Producer extends Thread {
	private static int ID = 0;
	private int id;
	private BlockingQueue<Integer> blockingQueue;
	private Semaphore mutex;
	
	public Producer(BlockingQueue<Integer> blockingQueue, Semaphore mutex) {
		super("Producer ");
		this.id = ++ID;
		this.blockingQueue = blockingQueue;
		this.mutex = mutex;
	}

	@Override
	public void run() {
		while (true) {
			int num = produce();
			try {
				blockingQueue.put(num);
				mutex.acquire();
				System.out.println("ID " + id + "produced "+ num);
				mutex.release();
			} catch (InterruptedException e) {
			}
		}
	}

	private int produce() {
		try {
			sleep(1000 + (int) (Math.random() * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 1 + (int) (Math.random() * 100);
	}
}
