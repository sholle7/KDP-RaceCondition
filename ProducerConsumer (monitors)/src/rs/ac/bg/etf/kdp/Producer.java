package rs.ac.bg.etf.kdp;

public class Producer extends Thread {
	static int ID = 0;
	private int id;
	private Buffer buf;

	public Producer(Buffer buf) {
		super("Producer ");
		this.id = ++ID;
		this.buf = buf;
	}

	@Override
	public void run() {
		while (true) {
			int num = produce();
			buf.put(num);
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
