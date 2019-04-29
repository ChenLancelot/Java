package com.lancelot.java.juc.threadlocale;

import java.util.concurrent.CountDownLatch;

/**
 * 
 * @author Lancelot Chen 
 * @date 2019年4月26日 下午4:40:47
 * @Copyright：Lancelot Chen个人所有
 * @version 1.0 
 */
public class ThreadLocalTest4 {

	public static void main(String[] args) {
		int threads = 3;
		CountDownLatch latch = new CountDownLatch(3);
		InnerClass innerClass = new InnerClass();
		for (int i = 0; i < threads; i++) {
			new Thread(() -> {
				for (int j = 0; j < 4; j++) {
					innerClass.add(String.valueOf(j));
					innerClass.print();
				}
				innerClass.set("hello world");
				latch.countDown();
			}, "thread-" + i).start();
		}
		
	}
	
	private static class InnerClass {
		
		public void add(String newStr) {
			Counter.counter.get().append(newStr);
		}
		
		public void print() {
			System.out.printf("Thread name:%s , ThreadLocal hashcode: %s, Instance hashCode: %s, value: %s \n",
					Thread.currentThread().getName(),
					Counter.counter.get().hashCode(),
					Counter.counter.get().hashCode(),
					Counter.counter.get().toString());
		}
		
		public void set(String words) {
			Counter.counter.set(new StringBuilder(words));
			System.out.printf("Set Thread name:%s , ThreadLocal hashcode: %s, Instance hashCode: %s, value: %s \n",
					Thread.currentThread().getName(),
					Counter.counter.get().hashCode(),
					Counter.counter.get().hashCode(),
					Counter.counter.get().toString());
		}
		
		
	}
	
	private static class Counter {
		private static ThreadLocal<StringBuilder> counter = new ThreadLocal<StringBuilder>() {
			protected StringBuilder initialValue() {
				return new StringBuilder();
			};
		};
	}
	
}
