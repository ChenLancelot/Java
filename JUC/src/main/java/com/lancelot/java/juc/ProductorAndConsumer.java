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
		
		new Thread(pro, "������ A").start();
		new Thread(cus, "������ B").start();
		
		new Thread(pro, "������ C").start();
		new Thread(cus, "������ D").start();
	}
	
	//��Ա
	private static class Clerk{
		private int product = 0;
		
		//����
		public synchronized void get(){//ѭ��������0
			while(product >= 1){//Ϊ�˱�����ٻ������⣬Ӧ������ʹ����ѭ����
				System.out.println("��Ʒ������");
				
				try {
					this.wait();
				} catch (InterruptedException e) {
				}
				
			}
			
			System.out.println(Thread.currentThread().getName() + " : " + ++product);
			this.notifyAll();
		}
		
		//����
		public synchronized void sale(){//product = 0; ѭ��������0
			while(product <= 0){
				System.out.println("ȱ����");
				
				try {
					this.wait();
				} catch (InterruptedException e) {
				}
			}
			
			System.out.println(Thread.currentThread().getName() + " : " + --product);
			this.notifyAll();
		}
	}
	
	//������
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
	
	//������
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
