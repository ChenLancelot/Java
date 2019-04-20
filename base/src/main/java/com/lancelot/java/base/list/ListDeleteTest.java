package com.lancelot.java.base.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

/**
 * 测试集合的删除
 * 
 * @author Lancelot Chen
 * @date 2019年4月21日 上午12:39:39
 * @Copyright：Lancelot Chen个人所有
 * @version 1.0
 */
public class ListDeleteTest {

	/**
	 * 第一种方式，如果只是删除一个元素，不会有问题，但是删除多个会出现遗漏的情况
	 * @author Lancelot Chen
	 * @date 2019年4月21日 上午1:10:16 
	 * @version V1.0
	 */
	@Test
	public void test() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        
        for (int i = 0; i < list.size(); i++) {
        	if (3 == list.get(i) || 4 == list.get(i)){
                list.remove(i);
            }
        }
        
        println(list);
	}

	/**
	 * 增强for删除，
	 * 如果在删除后继续循环会报错 ConcurrentModificationException
	 * 如果删除后使用了break则不会报错
	 * @author Lancelot Chen
	 * @date 2019年4月21日 上午1:14:46 
	 * @version V1.0
	 */
	@Test
	public void test2() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        
        for (Integer integer : list) {
        	if (3 == integer){
                list.remove(integer);
                break;
            }
        }
        
        println(list);
	}
	
	/**
	 * 使用 iterator 来删除元素
	 * 可以删除多个元素, 但必须使用 iterator.remove()
	 * 如果使用 list.remove() 则仍然会报 ConcurrentModificationException
	 * @author Lancelot Chen
	 * @date 2019年4月21日 上午1:19:01 
	 * @version V1.0
	 */
	@Test
	public void test3() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
        	Integer next = iterator.next();
        	if (3 == next || 4 == next){
                iterator.remove();
//        		list.remove(next);
            }
        }
        
        
        println(list);
	}
	
	
	public <T> void println(List<T> list) {
		for(T t: list) {
			System.out.println("element : " + t);
		}
	}

}
