package rs.ac.bg.etf.kdp;

public class Consumer extends Thread {
	static int ID = 0;
	private int id;
	private Buffer buf;

	public Consumer(Buffer buf) {
		super("Consumer");
		this.id = ++ID;
		this.buf = buf;
	}

	@Override
	public void run() {
		while (true) {
			consume();
			int num = buf.get();
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
