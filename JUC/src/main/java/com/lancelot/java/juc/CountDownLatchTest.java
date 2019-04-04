package com.lancelot.java.juc;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch ：闭锁，在完成某些运算是，只有其他所有线程的运算全部完成，当前运算才继续执行
 * @author Lancelot Chen 
 * @date 2019年3月10日 下午9:13:46
 * @Copyright：Lancelot Chen个人所有
 * @version 1.0 
 */
public class CountDownLatchTest {

	public static void main(String[] args) {
		final CountDownLatch latch = new CountDownLatch(5);
		LatchDemo ld  = new LatchDemo(latch);
		
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < 5; i++) {
			new Thread(ld).start();
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			
		}

		long end = System.currentTimeMillis();

		System.out.println("耗费时间为：" + (end - start));		
	}
	
	
	private static class LatchDemo implements Runnable {

		private CountDownLatch latch;

		public LatchDemo(CountDownLatch latch) {
			this.latch = latch;
		}

		@Override
		public void run() {

			try {
				for (int i = 0; i < 50000; i++) {
					if (i % 2 == 0) {
//						System.out.println(i);
					}
				}
				System.out.println(Thread.currentThread().getName());
			} finally {
				latch.countDown();
			}

		}
	}
}
