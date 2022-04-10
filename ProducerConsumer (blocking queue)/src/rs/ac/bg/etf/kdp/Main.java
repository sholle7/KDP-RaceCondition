package rs.ac.bg.etf.kdp;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		final int N = 2;
		final int consumerNumber = 10;
		final int producerNumber = 1;
		BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(N, true);
		Semaphore mutex = new Semaphore(1, true);
		

		for (int i = 0; i < producerNumber; i++) {
			Producer p = new Producer(blockingQueue, mutex);
			p.start();
		}

		for (int j = 0; j < consumerNumber; j++) {
			Consumer c = new Consumer(blockingQueue, mutex);
			c.start();
		}
	}
}
