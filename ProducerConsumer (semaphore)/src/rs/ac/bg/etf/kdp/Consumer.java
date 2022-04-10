package rs.ac.bg.etf.kdp;

import java.util.concurrent.Semaphore;

public class Consumer extends Thread {
	static int ID = 0;
	private int id;
	private Buffer buf;
	private Semaphore full, empty, mutexC;

	public Consumer(Buffer buf, Semaphore full, Semaphore empty, Semaphore mutexC) {
		super("Consumer");
		this.id = ++ID;
		this.buf = buf;
		this.full = full;
		this.empty = empty;
		this.mutexC = mutexC;
	}

	@Override
	public void run() {
		while (true) {
			full.acquireUninterruptibly();
			mutexC.acquireUninterruptibly();
			int num = buf.get();
			System.out.println("C" + id + "consumed" + num);
			mutexC.release();
			empty.release();
			consume();
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
