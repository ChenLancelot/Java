package com.lancelot.java.juc.synchron;

/**
 * 一个线程访问一个对象中的synchronized(this)同步代码块时，其他试图访问该对象的线程将被阻塞
 * @author Lancelot Chen 
 * @date 2019年3月25日 下午7:18:08
 * @Copyright：Lancelot Chen个人所有
 * @version 1.0 
 * @Description: TODO
 */
public class Synchronized1Test {

	public static void main(String[] args) {
		
//		SyncThread s1 = new SyncThread();
//		SyncThread s2 = new SyncThread();
//		Thread t1 = new Thread(s1);
//		Thread t2 = new Thread(s2);
		
		SyncThread s = new SyncThread();
		Thread t1 = new Thread(s);
		Thread t2 = new Thread(s);

		t1.start();
		t2.start();
	}

	private static class SyncThread implements Runnable {
		private static int count;

		public SyncThread() {
			count = 0;
		}

		public void run() {
			synchronized (this) {
				for (int i = 0; i < 5; i++) {
					try {
						System.out.println(Thread.currentThread().getName() + ":" + (count++));
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

		public int getCount() {
			return count;
		}
	}

}
