package com.lancelot.java.juc;
 
/** 
 һ��i++ ��ԭ�������⣺i++ �Ĳ���ʵ���Ϸ�Ϊ�������衰��-��-д��
 * 		  int i = 10;
 * 		  i = i++; //10
 * 
 * 		  int temp = i;
 * 		  i = i + 1;
 * 		  i = temp;
 * 
 * ����ԭ�ӱ������� java.util.concurrent.atomic �����ṩ��һЩԭ�ӱ�����
 * 		1. volatile ��֤�ڴ�ɼ���
 * 		2. CAS��Compare-And-Swap�� �㷨��֤���ݱ�����ԭ����
 * 			CAS �㷨��Ӳ�����ڲ���������֧��
 * 			CAS ������������������
 * 			���ڴ�ֵ  V
 * 			��Ԥ��ֵ  A
 * 			�۸���ֵ  B
 * 			���ҽ��� V == A ʱ�� V = B; ���򣬲���ִ���κβ�����
 * @author Lancelot Chen 
 * @date 2019��3��1�� ����10:20:56
 * @Copyright��Lancelot Chen��������
 * @version 1.0 
 * @Description: TODO 
 */

public class AtomicTest {

	public static void main(String[] args) {
		AtomicDemo ad = new AtomicDemo();
		for (int i = 0; i < 10; i++) {
			new Thread(ad).start();
		}
		
	}
	
	static class AtomicDemo implements Runnable {
		/*
		 * ʹ��int���� i++ ����ʱ�������ظ�����
		 */
		private int serialNumber = 0;
		
		public void run() {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
			
			System.out.println(getSerialNumber());
		}
		
		public int getSerialNumber() {
			return serialNumber++;
		}
	}
	
}







	