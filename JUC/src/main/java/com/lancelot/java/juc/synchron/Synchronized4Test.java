package com.lancelot.java.juc.synchron;
/**
 * 修饰一个静态的方法
 * syncThread1和syncThread2是SyncThread的两个对象，但在thread1和thread2并发执行时却保持了线程同步。
 * 这是因为run中调用了静态方法method，而静态方法是属于类的，所以syncThread1和syncThread2相当于用了同一把锁。
 * @author Lancelot Chen 
 * @date 2019年3月25日 下午7:27:01
 * @Copyright：Lancelot Chen个人所有
 * @version 1.0 
 */
public class Synchronized4Test {

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
	 
	   public synchronized static void method() {
	      for (int i = 0; i < 5; i ++) {
	         try {
	            System.out.println(Thread.currentThread().getName() + ":" + (count++));
	            Thread.sleep(100);
	         } catch (InterruptedException e) {
	            e.printStackTrace();
	         }
	      }
	   }
	 
	   public synchronized void run() {
	      method();
	   }
	}
	
}
