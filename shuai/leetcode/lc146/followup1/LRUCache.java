import java.util.*;
import java.util.concurrent.locks.ReentrantLock;


class ListNode {
    public int key;
	public int val;
	public long timestamp;
    public ListNode next;
    public ListNode pre;

    public ListNode(int key, int val) {
        this.key = key;
        this.val = val;
        this.next = null;
		this.pre = null;
		this.timestamp = System.currentTimeMillis();
    }

}

class DList {
    ListNode head = null;
    ListNode tail = null;

    public ListNode rmNode(ListNode node) {
        if (node == null) {
            return null;
        }
        ListNode pre = node.pre;
        ListNode next = node.next;
        if (pre != null) {
            pre.next = next;
        } else {
            head = node.next;
        }
        if (next != null) {
            next.pre = pre;
        } else {
            tail = node.pre;
        }
        node.pre = null;
        node.next = null;
        return node;
    }

    public void addToTail(ListNode node) {
        if (this.tail == null) {
            this.tail = node;
            this.head = node;
        } else {
            this.tail.next = node;
            node.pre = this.tail;
            this.tail = node;
        }
    }

    public ListNode removeHead() {
        return rmNode(this.head);
    }
}

class ClearCacheThread extends Thread{
	LRUCache cache;
	long expireTime;
	int n;
	public ClearCacheThread(LRUCache cache, long expireTime, int n){
		this.cache = cache;
		this.expireTime = expireTime;
		this.n = n;
	}
	@Override
	public void run(){
		try {
            while(true){
				sleep(n * expireTime);
				cache.clearCache();
		    }
		} catch(InterruptedException ex){
                ex.printStackTrace();
		}
		
	}
}
public class LRUCache {
    Map<Integer, ListNode> dataMap;
	DList list;
	ReentrantLock cacheLock;
	ClearCacheThread cacheThread;
	int capacity;
	// expire time in millsencond
	long expireTime = 1000; 
	int n = 2;

    public LRUCache(int capacity) {
        dataMap = new HashMap<>();
        list = new DList();
		this.capacity = capacity;
		this.cacheLock = new ReentrantLock();
		this.cacheThread = new ClearCacheThread(this, expireTime, n);
		cacheThread.start();

    }

    public int get(int key) {
		int res = -1;
		this.cacheLock.lock();
        if (dataMap.containsKey(key)) {
			ListNode node = dataMap.get(key);
			// 如果超出 expire time 则返回-1
			if(System.currentTimeMillis() - node.timestamp > this.expireTime){
				return res;
			}
			node.timestamp = System.currentTimeMillis();
            node = list.rmNode(node);
            list.addToTail(node);
            dataMap.put(key, node);
            res = node.val;
        }
        this.cacheLock.unlock();
        return res;

    }

    public void set(int key, int value) {
		ListNode node = null;
		this.cacheLock.lock();
        if (dataMap.containsKey(key)) {
            node = dataMap.get(key);
			node.val = value;
			node.timestamp = System.currentTimeMillis();
            node = list.rmNode(node);

        } else {
            node = new ListNode(key, value);

        }

        list.addToTail(node);

        dataMap.put(key, node);
        if (dataMap.size() > this.capacity) {
            dataMap.remove(list.removeHead().key);
		}
		this.cacheLock.unlock();

	}
	
	public void clearCache(){
		// if return true, we need to clear cache
		// otherwise we do not need clear cache
		long currentTime = System.currentTimeMillis();
		cacheLock.lock();
		while(list.head != null){
				ListNode node = list.head;
				if(currentTime - node.timestamp > expireTime){
					dataMap.remove(node.key);
                    list.rmNode(node);
				} else {
					break;
				}
		}
		cacheLock.unlock();
		
	}

    public static void main(String[] args) {
		LRUCache obj = new LRUCache(10);
		obj.set(10, 13);
		obj.set(3, 17);
		obj.set(6, 11);
		obj.set(10, 5);
		obj.set(9, 10);
		System.out.println(obj.get(13));
		obj.set(2, 19);
		System.out.println(obj.get(2));
		System.out.println(obj.get(3));
		obj.set(5, 25);
		System.out.println(obj.get(8));
		obj.set(9, 22);
		obj.set(5, 5);
		obj.set(1, 30);
		System.out.println(obj.get(11));
		obj.set(9, 12);
		System.out.println(obj.get(7));
		System.out.println(obj.get(5));
		System.out.println(obj.get(8));
		System.out.println(obj.get(9));
		obj.set(4, 30);
		obj.set(9, 3);
		System.out.println(obj.get(9));
		System.out.println(obj.get(10));
		System.out.println(obj.get(10));
		obj.set(6, 14);
		obj.set(3, 1);
		System.out.println(obj.get(3));
		obj.set(10, 11);
		System.out.println(obj.get(8));
		obj.set(2, 14);
		System.out.println(obj.get(1));
		System.out.println(obj.get(5));
		System.out.println(obj.get(4));
		obj.set(11, 4);
		obj.set(12, 24);
		obj.set(5, 18);
		System.out.println(obj.get(13));
		obj.set(7, 23);
		System.out.println(obj.get(8));
		System.out.println(obj.get(12));
		obj.set(3, 27);
		obj.set(2, 12);
		System.out.println(obj.get(5));
		obj.set(2, 9);
		obj.set(13, 4);
		obj.set(8, 18);
		obj.set(1, 7);
		System.out.println(obj.get(6));
		obj.set(9, 29);
		obj.set(8, 21);
		System.out.println(obj.get(5));
		obj.set(6, 30);
		obj.set(1, 12);
		System.out.println(obj.get(10));
		obj.set(4, 15);
		obj.set(7, 22);
		obj.set(11, 26);
		obj.set(8, 17);
		obj.set(9, 29);
		System.out.println(obj.get(5));
		obj.set(3, 4);
		obj.set(11, 30);
		System.out.println(obj.get(12));
		obj.set(4, 29);
		System.out.println(obj.get(3));
		System.out.println(obj.get(9));
		System.out.println(obj.get(6));
		obj.set(3, 4);
		System.out.println(obj.get(1));
		System.out.println(obj.get(10));
		obj.set(3, 29);
		obj.set(10, 28);
		obj.set(1, 20);
		obj.set(11, 13);
		System.out.println(obj.get(3));
		obj.set(3, 12);
		obj.set(3, 8);
		obj.set(10, 9);
		obj.set(3, 26);
		System.out.println(obj.get(8));
		System.out.println(obj.get(7));
		System.out.println(obj.get(5));
		obj.set(13, 17);
		obj.set(2, 27);
		obj.set(11, 15);
		System.out.println(obj.get(12));
		obj.set(9, 19);
		obj.set(2, 15);
		obj.set(3, 16);
		System.out.println(obj.get(1));
		obj.set(12, 17);
		obj.set(9, 1);
		obj.set(6, 19);
		System.out.println(obj.get(4));
		System.out.println(obj.get(5));
		System.out.println(obj.get(5));
		obj.set(8, 1);
		obj.set(11, 7);
		obj.set(5, 2);
		obj.set(9, 28);
		System.out.println(obj.get(1));
		obj.set(2, 2);
		obj.set(7, 4);
		obj.set(4, 22);
		obj.set(7, 24);
		obj.set(9, 26);
		obj.set(13, 28);
		obj.set(11, 26);

	}


}