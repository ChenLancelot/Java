package com.lancelot.java.juc.atomic;
/**
 * 模拟CAS算法
 * @author Lancelot Chen 
 * @date 2019年3月1日 下午11:01:47
 * @Copyright：Lancelot Chen个人所有
 * @version 1.0 
 * @Description: TODO
 */
public class CompareAndSwapTest {

	public static void main(String[] args) {
		final CompareAndSwap cas = new CompareAndSwap();
		
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				
				public void run() {
					int expectedValue = cas.get();
					boolean b = cas.compareAndSet(expectedValue, (int)(Math.random() * 101));
					System.out.println(b);
				}
			}).start();
		}
	}
	
	
	static class CompareAndSwap {
		private int value;
		//获取内存值
		public synchronized int get() {
			return value;
		}
		//比较
		public synchronized int compareAndSwap(int expectValue, int newValue) {
			int oldValue = value;
			
			if (oldValue == expectValue) {
				this.value = newValue;
			}
			return oldValue;
		}
		
		//设置
		public synchronized boolean compareAndSet(int expectedValue, int newValue){
			return expectedValue == compareAndSwap(expectedValue, newValue);
		}
	}
	
	
}
