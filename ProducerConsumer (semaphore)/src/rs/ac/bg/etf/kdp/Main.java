package rs.ac.bg.etf.kdp;

import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		final int N = 2;
		final int consumerNumber = 2;
		final int producerNumber = 5;

		Buffer buffer = new Buffer();
		Semaphore full = new Semaphore(0), empty = new Semaphore(N);
		Semaphore mutexP = new Semaphore(1), mutexC = new Semaphore(1);
		for (int i = 0; i < producerNumber; i++) {
			Producer p = new Producer(buffer, full, empty, mutexP);
			p.start();
		}
		
		for (int j = 0; j < consumerNumber; j++) {
			Consumer c = new Consumer(buffer, full, empty, mutexC);
			c.start();
		}
	}
}
