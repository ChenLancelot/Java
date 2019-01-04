package com.lancelot.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试 堆内存溢出
 * 设置堆内存参数: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @author Lancelot Chen 
 * @date 2019年1月4日 下午10:37:37
 * @Copyright：Lancelot Chen个人所有
 * @version 1.0 
 * @Description: 
 */
public class HeapOOM {

	static class OOMObject {
		
	}
	
	public static void main(String[] args) {
		
		List<OOMObject> list = new ArrayList<OOMObject>();
		
		while(true) {
			list.add(new OOMObject());
		}
		
	}
	
}
