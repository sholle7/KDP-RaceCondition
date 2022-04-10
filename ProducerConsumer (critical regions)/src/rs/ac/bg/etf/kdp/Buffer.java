package rs.ac.bg.etf.kdp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Buffer {
	private List<Integer> list;
	private int capacity;

	public Buffer(int capacity) {
		list = new ArrayList<>();
		this.capacity = capacity;
	}

	public void put(Integer value) {
		synchronized (this) {
			while (list.size() >= capacity) {
				try {
					wait();
				} catch (InterruptedException e) {
				}
			}

			list.add(value);
			System.out.println("P " + "produced" + value);
			notifyAll();
		}
	}

	public int get() {
		synchronized (this) {
			while (list.isEmpty()) {
				try {
					wait();
				} catch (InterruptedException e) {
				}
			}
			notifyAll();
			System.out.println("C " + "consumed" + list.get(0));
			return list.remove(0);
		}
	}
}
