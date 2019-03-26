package com.lancelot.java.juc;

/**
 * 给class加锁和上例的给静态方法加锁是一样的，所有对象公用一把锁
 * @author Lancelot Chen 
 * @date 2019年3月25日 下午7:30:11
 * @Copyright：Lancelot Chen个人所有
 * @version 1.0 
 */
public class Synchronized5Test {

	
	public static void main(String[] args) {
		SyncThread syncThread1 = new SyncThread();
		SyncThread syncThread2 = new SyncThread();
		Thread thread1 = new Thread(syncThread1, "SyncThread1");
		Thread thread2 = new Thread(syncThread2, "SyncThread2");
		thread1.start();
		thread2.start();
	}
	
	/**
	 * 同步线程
	 */
	private static class SyncThread implements Runnable {
	   private static int count;
	 
	   public SyncThread() {
	      count = 0;
	   }
	 
	   public static void method() {
	      synchronized(SyncThread.class) {
	         for (int i = 0; i < 5; i ++) {
	            try {
	               System.out.println(Thread.currentThread().getName() + ":" + (count++));
	               Thread.sleep(100);
	            } catch (InterruptedException e) {
	               e.printStackTrace();
	            }
	         }
	      }
	   }
	 
	   public synchronized void run() {
	      method();
	   }
	}
}
