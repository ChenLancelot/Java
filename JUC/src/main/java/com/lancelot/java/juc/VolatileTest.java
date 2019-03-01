package com.lancelot.java.juc;
/**
 * һ��volatile �ؼ��֣�������߳̽��в�����������ʱ�����Ա�֤�ڴ��е����ݿɼ���
 * 					  ����� synchronized ��һ�ֽ�Ϊ��������ͬ�����ԡ�
 * 
 * ע�⣺
 * 1. volatile ���߱��������ԡ�
 * 2. volatile ���ܱ�֤�����ġ�ԭ���ԡ�
 * @author Lancelot Chen 
 * @date 2019��3��1�� ����10:30:35
 * @Copyright��Lancelot Chen��������
 * @version 1.0 
 * @Description: TODO
 */
public class VolatileTest {
	public static void main(String[] args) {
		ThreadDemo td = new ThreadDemo();
		new Thread(td).start();
		
		while(true){
			if(td.isFlag()){
				System.out.println("------------------");
				break;
			}
		}
		
	}
}
class ThreadDemo implements Runnable {

	/*
	 * ����volatile ��û������������ 
	 */
//	private boolean flag = false;
	private volatile boolean flag = false;
	
	public void run() {
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}

		flag = true;
		
		System.out.println("flag=" + isFlag());

	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}