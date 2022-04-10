package rs.ac.bg.etf.kdp;

import java.util.concurrent.Semaphore;

public class Producer extends Thread {
	static int ID = 0;
	private int id;
	private Buffer buf;
	private Semaphore full, empty, mutexP;

	public Producer(Buffer buf, Semaphore full, Semaphore empty, Semaphore mutexP) {
		super("Producer ");
		this.id = ++ID;
		this.buf = buf;
		this.full = full;
		this.empty = empty;
		this.mutexP = mutexP;
	}

	@Override
	public void run() {
		while (true) {
			int num = produce();
			empty.acquireUninterruptibly();
			mutexP.acquireUninterruptibly();
			
			buf.put(num);
			System.out.println("P" + id + "produced" + num);
			
			mutexP.release();
			full.release();
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
