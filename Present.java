import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Present<Integer extends Comparable<Integer>> {
	
	//T tag;
  int tag;

  //int key;
	
	Present<Integer> next;
	
	boolean marked;
	
	Lock lock;

	Present(int tag) {
		this.tag = tag;
    //this.key = tag.hashCode();
		this.next = null;
		this.marked = false;
		this.lock = new ReentrantLock();
	}

  /* Present(int key) {
		this.tag = null;
    this.key = key;
		this.next = null;
		this.marked = false;
		this.lock = new ReentrantLock();
	} */

	void lock() {
		lock.lock();
	}

	void unlock() {
		lock.unlock();
	}
}