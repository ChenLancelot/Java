package com.lancelot.java.juc.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

/**
 * 线程安全的同步性能比较
 * Collections.synchronizedList & CopyOnWriteArrayList
 * @author Lancelot Chen 
 * @date 2019年4月3日 下午3:56:23
 * @Copyright：Lancelot Chen个人所有
 * @version 1.0 
 */
public class SynchronizedListTest {

	private int NUM = 10000;
	
	private int THREAD_COUNT = 16;
	
	@Test
	public void testAdd() throws Exception {
		List<Integer> list = initList();
		
		List<Integer> list1 = new CopyOnWriteArrayList<>(list);
		List<Integer> list2 = Collections.synchronizedList(list);
		
		int add_copyCostTime = 0;
		int add_synchCostTime = 0;
		
		ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
		CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
		
		for (int i = 0; i < THREAD_COUNT; i++) {
			add_copyCostTime += executorService.submit(new AddTestTask(list1, countDownLatch)).get();
		}
        System.out.println("CopyOnWriteArrayList add method cost time is " + add_copyCostTime);

		for (int i = 0; i < THREAD_COUNT; i++) {
			add_synchCostTime += executorService.submit(new AddTestTask(list2, countDownLatch)).get();
		}
        System.out.println("Collections.synchronizedList add method cost time is " + add_synchCostTime);
	}
	
	@Test
	public void testGet() throws Exception{
		List<Integer> list = initList();
		
		List<Integer> list1 = new CopyOnWriteArrayList<>(list);
		List<Integer> list2 = Collections.synchronizedList(list);
		
		int get_copyCostTime = 0;
		int get_synchCostTime = 0;
		
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
		
		for (int i = 0; i < THREAD_COUNT; i++) {
			get_copyCostTime += executorService.submit(new GetTestTask(list1, countDownLatch)).get();
		}
        System.out.println("CopyOnWriteArrayList add method cost time is " + get_copyCostTime);

        for (int i = 0; i < THREAD_COUNT; i++) {
            get_synchCostTime += executorService.submit(new GetTestTask(list2, countDownLatch)).get();
        }
        System.out.println("Collections.synchronizedList add method cost time is " + get_synchCostTime);
        
		
	}
	
	
	private List<Integer> initList() {
		List<Integer> list = new ArrayList<Integer>();
		int num = new Random().nextInt(1000);
		for (int i = 0; i < NUM; i++) {
			list.add(num);
		}
		return list;
	}
	
	class AddTestTask implements Callable<Integer> {
		List<Integer> list;
		CountDownLatch countDownLatch;
	
		AddTestTask(List<Integer> list, CountDownLatch countDownLatch) {
			this.list = list;
			this.countDownLatch = countDownLatch;
		}
		
		@Override
		public Integer call() throws Exception {
			int num = new Random().nextInt(1000);
			long start = System.currentTimeMillis();
			for (int i = 0; i < NUM; i++) {
				list.add(num);
			}
			long end = System.currentTimeMillis();
			countDownLatch.countDown();
			return (int)(end - start);
		}
		
	}
	
	class GetTestTask implements Callable<Integer> {

		List<Integer> list;
		CountDownLatch countDownLatch;
		
		GetTestTask(List<Integer> list, CountDownLatch countDownLatch) {
			this.list = list;
			this.countDownLatch = countDownLatch;
		}
		
		@Override
		public Integer call() throws Exception {
			int pos = new Random().nextInt(NUM);
			long start = System.currentTimeMillis();
			for (int i = 0; i < NUM; i++) {
				list.get(pos);
			}
			long end = System.currentTimeMillis();
			countDownLatch.countDown();
			return (int)(end - start);
		}
		
	}
	
}
