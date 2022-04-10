package rs.ac.bg.etf.kdp;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

	public static void main(String[] args) {
		final int N = 2;
		final int consumerNumber = 2;
		final int producerNumber = 5;
		Lock lock = new ReentrantLock(true);
		Condition full = lock.newCondition(), empty = lock.newCondition();

		Buffer buffer = new Buffer(N, lock, full, empty);

		for (int i = 0; i < producerNumber; i++) {
			Producer p = new Producer(buffer);
			p.start();
		}

		for (int j = 0; j < consumerNumber; j++) {
			Consumer c = new Consumer(buffer);
			c.start();
		}
	}
}
