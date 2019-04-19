package com.lancelot.java.juc;

/**
 * 生产者和消费者案例
 * @author Lancelot Chen 
 * @date 2019年3月10日 下午9:54:29
 * @Copyright：Lancelot Chen个人所有
 * @version 1.0 
 * @Description: TODO
 */
public class ProductorAndConsumer {

	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		
		Productor pro = new Productor(clerk);
		Consumer cus = new Consumer(clerk);
		
		new Thread(pro, "生产者 A").start();
		new Thread(cus, "消费者 B").start();
		
		new Thread(pro, "生产者 C").start();
		new Thread(cus, "消费者 D").start();
	}
	
	// 店员
	private static class Clerk{
		private int product = 0;
		
		// 进货
		public synchronized void get(){// 循环次数：0
			while(product >= 1){// 为了避免虚假唤醒问题，应该总是使用在循环中
				System.out.println("产品已满!");
				
				try {
					this.wait();
				} catch (InterruptedException e) {
				}
				
			}
			
			System.out.println(Thread.currentThread().getName() + " : " + ++product);
			this.notifyAll();
		}
		
		// 卖货
		public synchronized void sale(){//product = 0; 循环次数：0
			while(product <= 0){
				System.out.println("缺货!");
				
				try {
					this.wait();
				} catch (InterruptedException e) {
				}
			}
			
			System.out.println(Thread.currentThread().getName() + " : " + --product);
			this.notifyAll();
		}
	}
	
	// 生产者
	private static class Productor implements Runnable{
		private Clerk clerk;

		public Productor(Clerk clerk) {
			this.clerk = clerk;
		}

		@Override
		public void run() {
			for (int i = 0; i < 20; i++) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}
				
				clerk.get();
			}
		}
	}
	
	// 消费者
	private static class Consumer implements Runnable{
		private Clerk clerk;

		public Consumer(Clerk clerk) {
			this.clerk = clerk;
		}

		@Override
		public void run() {
			for (int i = 0; i < 20; i++) {
				clerk.sale();
			}
		}
	}
	
	
}
