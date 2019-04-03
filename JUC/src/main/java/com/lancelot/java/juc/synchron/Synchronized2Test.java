package com.lancelot.java.juc.synchron;

/**
 * 一个线程访问一个对象的synchronized代码块时，别的线程可以访问该对象的非synchronized代码块而不受阻塞。
 * @author Lancelot Chen 
 * @date 2019年3月25日 上午11:24:12
 * @Copyright：Lancelot Chen个人所有
 * @version 1.0 
 */
public class Synchronized2Test {

	
	public static void main(String[] args) {
			
		Counter counter = new Counter();
		Thread thread1 = new Thread(counter, "A");
		Thread thread2 = new Thread(counter, "B");
		thread1.start();
		thread2.start();
	}
	
	private static class Counter implements Runnable {
		private int count;

		public Counter() {
			count = 0;
		}

		public void countAdd() {
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

		// 非synchronized代码块，未对count进行读写操作，所以可以不用synchronized
		public void printCount() {
			for (int i = 0; i < 5; i++) {
				try {
					System.out.println(Thread.currentThread().getName() + " count:" + count);
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public void run() {
			String threadName = Thread.currentThread().getName();
			if (threadName.equals("A")) {
				countAdd();
			} else if (threadName.equals("B")) {
				printCount();
			}
		}
	}
}
