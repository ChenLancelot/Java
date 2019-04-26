package com.lancelot.java.base.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

/**
 * 集合排序
 * @author Lancelot Chen 
 * @date 2019年4月21日 上午1:26:02
 * @Copyright：Lancelot Chen个人所有
 * @version 1.0 
 */
public class ListSortTest {

	@Test
	public void test() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(5);
		list.add(1);
		list.add(4);
		list.add(3);
        list.add(2);
        
        Collections.sort(list, (o1, o2) -> {return -o1.compareTo(o2);});
        
        println(list);
	}
	
	
	public <T> void println(List<T> list) {
		for(T t: list) {
			System.out.println("element : " + t);
		}
	}
	
}
