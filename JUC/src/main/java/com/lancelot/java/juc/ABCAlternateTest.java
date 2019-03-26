package com.lancelot.java.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 编写一个程序，开启 3 个线程，这三个线程的 ID 分别为 A、B、C，每个线程将自己的 ID 在屏幕上打印 10 遍，要求输出的结果必须按顺序显示。
 *	如：ABCABCABC…… 依次递归
 * @author Lancelot Chen 
 * @date 2019年3月10日 下午10:29:49
 * @Copyright：Lancelot Chen个人所有
 * @version 1.0 
 */
public class ABCAlternateTest {

	public static void main(String[] args) {
		AlternateDemo ad = new AlternateDemo();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				for (int i = 1; i <= 20; i++) {
					ad.loopA(i);
				}
				
			}
		}, "A").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				for (int i = 1; i <= 20; i++) {
					ad.loopB(i);
				}
				
			}
		}, "B").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				for (int i = 1; i <= 20; i++) {
					ad.loopC(i);
					
					System.out.println("-----------------------------------");
				}
				
			}
		}, "C").start();
	}
	
	
	private static class AlternateDemo {
		
		private int number = 1; // ��ǰ����ִ���̵߳ı��
		
		private Lock lock = new ReentrantLock();
		private Condition condition1 = lock.newCondition();
		private Condition condition2 = lock.newCondition();
		private Condition condition3 = lock.newCondition();
		
		public void loopA(int totalLoop) {
			lock.lock();
			
			try {
				//1. �ж�
				if(number != 1){
					condition1.await();
				}
				
				//2. ��ӡ
				for (int i = 1; i <= 1; i++) {
					System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
				}
				
				//3. ����
				number = 2;
				condition2.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
			
		}
		
		public void loopB(int totalLoop){
			lock.lock();
			
			try {
				//1. �ж�
				if(number != 2){
					condition2.await();
				}
				
				//2. ��ӡ
				for (int i = 1; i <= 1; i++) {
					System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
				}
				
				//3. ����
				number = 3;
				condition3.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
		
		public void loopC(int totalLoop){
			lock.lock();
			
			try {
				//1. �ж�
				if(number != 3){
					condition3.await();
				}
				
				//2. ��ӡ
				for (int i = 1; i <= 1; i++) {
					System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
				}
				
				//3. ����
				number = 1;
				condition1.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
		
	}
	
}






