package com.lancelot.java.juc.threadlocal;
/**
 * 在进行get之前，必须先set，否则会报空指针异常
 * @author Lancelot Chen 
 * @date 2019年4月26日 下午2:42:22
 * @Copyright：Lancelot Chen个人所有
 * @version 1.0 
 */
public class ThreadLocalTest2 {

	ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringLocal = new ThreadLocal<String>();
	
    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }
    
    public long getLong() {
        return longLocal.get();
    }
     
    public String getString() {
        return stringLocal.get();
    }
    
    public static void main(String[] args) throws Exception {
    	final ThreadLocalTest2 test = new ThreadLocalTest2();
        
        
//        test.set();
        System.out.println(test.getLong());
        System.out.println(test.getString());
     
         
        Thread thread1 = new Thread(){
            public void run() {
//                test.set();
                System.out.println(test.getLong());
                System.out.println(test.getString());
            };
        };
        thread1.start();
        thread1.join();
         
        System.out.println(test.getLong());
        System.out.println(test.getString());
	}
	
}
