package com.lancelot.java.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author Lancelot Chen 
 * @date 2019��3��1�� ����10:24:05
 * @Copyright��Lancelot Chen��������
 * @version 1.0 
 * @Description: TODO
 */
public class Atomic2Test {

	public static void main(String[] args) {
		AtomicDemo ad = new AtomicDemo();
		for (int i = 0; i < 10; i++) {
			new Thread(ad).start();
		}
		
	}
	
	
	
	static class AtomicDemo implements Runnable {
		/*
		 * ʹ��int���� i++ ����ʱ�������ظ�����
		 */
		private AtomicInteger serialNumber = new AtomicInteger(0);
		
		public void run() {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
			
			System.out.println(getSerialNumber());
		}
		
		public int getSerialNumber() {
			return serialNumber.getAndIncrement();
		}
	}
}