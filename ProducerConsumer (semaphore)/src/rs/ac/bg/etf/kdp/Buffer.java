package rs.ac.bg.etf.kdp;

import java.util.LinkedList;
import java.util.List;

public class Buffer {
	private List<Integer> list;

	public Buffer() {
		list = new LinkedList<>();
	}

	public void put(Integer value) {
		list.add(value);
	}

	public int get() {
		return list.remove(0);
	}
}
