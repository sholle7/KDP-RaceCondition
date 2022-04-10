package rs.ac.bg.etf.kdp;

public class Main {

	public static void main(String[] args) {
		final int N = 2;
		final int consumerNumber = 2;
		final int producerNumber = 5;

		Buffer buffer = new Buffer(N);

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
