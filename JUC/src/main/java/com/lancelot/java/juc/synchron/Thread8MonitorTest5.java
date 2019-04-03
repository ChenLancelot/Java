package com.lancelot.java.juc.synchron;
/**
 * 1. 两个普通同步方法，两个线程，标准打印， 打印? //one  two
 * 2. 新增 Thread.sleep() 给 getOne() ,打印? //one  two
 * 3. 新增普通方法 getThree() , 打印? //three  one   two
 * 4. 两个普通同步方法，两个 Number 对象，打印?  //two  one
 * 5. 修改 getOne() 为静态同步方法，打印?  //two   one
 * @author Lancelot Chen 
 * @date 2019年3月10日 下午11:02:31
 * @Copyright：Lancelot Chen个人所有
 * @version 1.0 
 */
public class Thread8MonitorTest5 {

	public static void main(String[] args) {
		Number number = new Number();
//		Number number2 = new Number();
		new Thread(new Runnable() {
			@Override
			public void run() {
				number.getOne();
			} 
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				number.getTwo();
			}
		}).start();
		
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				number.getThree();
//			}
//		}).start();
	}
	
	
	private static class Number{
		
		public static synchronized void getOne(){//Number.class
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("one");
		}
		
		public synchronized void getTwo(){//this
			System.out.println("two");
		}
		
		public void getThree(){
			System.out.println("three");
		}
	}
}


