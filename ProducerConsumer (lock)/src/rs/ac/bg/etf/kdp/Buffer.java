package rs.ac.bg.etf.kdp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Buffer {
	private List<Integer> list;
	private Lock lock;
	private Condition full, empty;
	private int capacity;

	public Buffer(int capacity, Lock lock, Condition full, Condition empty) {
		list = new ArrayList<>();
		this.capacity = capacity;
		this.lock = lock;
		this.empty = empty;
		this.full = full;
	}

	public void put(Integer value) {
		lock.lock();
		try {
			while (list.size() >= capacity) {
				try {
					full.await();
				} catch (InterruptedException e) {
				}
			}
	
			list.add(value);
			System.out.println("P " + "produced" + value);
			empty.signal();
		} finally {
			lock.unlock();
		}
	}

	public int get() {
		lock.lock();
		try {
			while (list.isEmpty()) {
				try {
					empty.await();
				} catch (InterruptedException e) {
				}
			}
			full.signal();
			System.out.println("C " + "consumed" + list.get(0));
			return list.remove(0);
		} finally {
			lock.unlock();
		}
		
	}
}
