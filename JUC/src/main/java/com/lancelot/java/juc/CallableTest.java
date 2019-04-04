package com.lancelot.java.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 一、创建执行线程的方式三：实现 Callable 接口。 相较于实现 Runnable 接口的方式，方法可以有返回值，并且可以抛出异常。
 * 二、执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。  FutureTask 是  Future 接口的实现类
 * @author Lancelot Chen 
 * @date 2019年3月10日 下午9:33:37
 * @Copyright：Lancelot Chen个人所有
 * @version 1.0 
 */
public class CallableTest {

	public static void main(String[] args) {
		ThreadDemo td = new ThreadDemo();

		//1.执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
		FutureTask<Integer> result = new FutureTask<>(td);
		
		new Thread(result).start();
		
		//2.接收线程运算后的结果
		try {
			Integer sum = result.get();  //FutureTask 可用于 闭锁
			System.out.println(sum);
			System.out.println("------------------------------------");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		System.out.println("sdfsdfs--------------------");

	}
	
	private static class ThreadDemo implements Callable<Integer>{

		@Override
		public Integer call() throws Exception {
			int sum = 0;
			
			for (int i = 0; i <= 100000000; i++) {
				sum += i;
			}
			
			return sum;
		}
		
	}

	/*class ThreadDemo implements Runnable{

		@Override
		public void run() {
		}
		
	}*/
	
}
