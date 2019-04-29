package com.lancelot.java.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * @author Lancelot Chen 
 * @date 2019年4月21日 上午3:10:50
 * @Copyright：Lancelot Chen个人所有
 * @version 1.0 
 */
public class AopTest {

	public static void main(String[] args) {
		SuperMan man = new SuperMan();// 创建一个被代理类的对象
		
		Object obj = MyProxy.getProxyInstance(man);
		
		Human hu = (Human)obj;
		hu.info();//通过代理类的对象调用重写的抽象方法
		
		System.out.println();
		
		hu.fly();
		
		//*********
		NikeClothFactory nike = new NikeClothFactory();
		Object obj1 = MyProxy.getProxyInstance(nike);
		ClothFactory cloth = (ClothFactory)obj1;
		cloth.productCloth();
		
	}
	
}

interface Human {
	void info();
	
	void fly();
}

// 被代理类
class SuperMan implements Human {

	@Override
	public void info() {
		System.out.println("我是超人！我怕谁！");
		
	}

	@Override
	public void fly() {
		System.out.println("I believe I can fly!");
	}
	
}

class HumanUtil {
	public void method1() {
		System.out.println("=======方法一=======");
	}

	public void method2() {
		System.out.println("=======方法二=======");
	}
}

class MyInvocationHandler implements InvocationHandler {

	Object obj;// 被代理类对象的声明
	
	public void setObject(Object obj) {
		this.obj = obj;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		HumanUtil h = new HumanUtil();
		h.method1();
		
		Object returnVal = method.invoke(obj, args);
		
		h.method2();
		return returnVal;
	}
}

class MyProxy {
	// 动态的创建一个代理类的对象
	public static Object getProxyInstance (Object obj) {
		MyInvocationHandler handler = new MyInvocationHandler();
		handler.setObject(obj);
		
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj
				.getClass().getInterfaces(), handler);
	}
	
}




