
import java.util.Queue;
import java.util.LinkedList;

public class Pool {
	private Queue<int[]> pool;
	private int maxSize;
	
	public Pool(int max) {
		pool = new LinkedList<>();
		maxSize =max;
	}
	
	public synchronized void push(int[] arr) {
		pool.add(arr);
		notifyAll();
	}
	
	public synchronized int[] pop() {
		while(pool.peek()==null) {
			try {wait();} catch(InterruptedException e) {e.printStackTrace(); System.exit(1);}
		}
		return pool.poll();
	}
	
	public synchronized boolean canContinue() {
		if(pool.peek()==null)
			try {
				wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
				System.exit(1);
			}
		return pool.peek() != null && pool.peek().length < maxSize;
	}
}
