package com.lancelot.java.juc;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList/CopyOnWriteArraySet : “写入并复制”
 * 注意：添加操作多时，效率低，因为每次添加时都会进行复制，开销非常的大。并发迭代操作多时可以选择。
 * @author Lancelot Chen 
 * @date 2019年3月10日 下午9:05:23
 * @Copyright：Lancelot Chen个人所有
 * @version 1.0 
 * @Description: TODO
 */
public class CopyOnWriteArrayListTest {
	
	public static void main(String[] args) {
		HelloThread ht = new HelloThread();
		
		for (int i = 0; i < 10; i++) {
			new Thread(ht).start();
		}
	}
	
	private static class HelloThread  implements Runnable{
		
		// ʹ���������ֱ����޸��쳣
		//private static List<String> list = Collections.synchronizedList(new ArrayList<String>());
		
		private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

		static{
			list.add("AA");
			list.add("BB");
			list.add("CC");
		}
		
		@Override
		public void run() {
			Iterator<String> it = list.iterator();
			
			while(it.hasNext()){
				System.out.println(it.next());
				
				list.add("AA");
			}
		}
		
	}
}

