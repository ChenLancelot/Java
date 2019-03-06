package com.lancelot.java.reflect.reflect;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

import org.junit.Test;

/**
 * 
 * @author Lancelot Chen 
 * @date 2019��3��6�� ����4:32:05
 * @Copyright��Lancelot Chen��������
 * @version 1.0 
 * @Description: 
 */
public class ReflectionTest {
	
	/**
	 * ���з�����ǰ����δ���һ����Ķ��󣬲��������еķ���������
	 * @author Lancelot Chen
	 * @date 2019��3��6�� ����4:35:07 
	 * @version V1.0
	 */
	@Test
	public void test1() {
		Person p = new Person();
		Person p1 = new Person();
		p.setAge(10);
		p.setName("TangWei");
		System.out.println(p);
		p.show();
//		p.display("HK");
	}
	
	/**
	 * ���˷��䣬����ͨ�����䴴��һ����Ķ��󣬲��������еĽṹ
	 * @author Lancelot Chen
	 * @date 2019��3��6�� ����4:39:03 
	 * @version V1.0
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws Exception 
	 * @throws  
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void test2() throws Exception {
		Class clazz = Person.class;
		
		// 1.����clazz��Ӧ������ʱ��Person��Ķ���
		Person p = (Person) clazz.newInstance();
		System.out.println(p);
		
		// 2.ͨ�������������ʱ���ָ��������
		// 2.1
		Field f1 = clazz.getField("name");
		f1.set(p, "LiuDeHua");
		System.out.println(p);
		//2.2
		Field f2 = clazz.getDeclaredField("age");
		f2.setAccessible(true);
		f2.set(p, 20);
		System.out.println(p);
		
		// 3.ͨ�������������ʱ���ָ���ķ���
		Method m1 = clazz.getMethod("show");
		m1.invoke(p);
		
		Method m2 = clazz.getDeclaredMethod("display", String.class, Integer.class);
		m2.setAccessible(true);
		m2.invoke(p, "CHN", 3);
		
	}
	
	/**
	 * java.lang.Class:�Ƿ����Դͷ��
	 * ���Ǵ�����һ���࣬ͨ�����루javac.exe��,���ɶ�Ӧ��.class�ļ���֮������ʹ��java.exe���أ�JVM�����������ɵģ�
	 * ��.class�ļ�����.class�ļ����ص��ڴ��Ժ󣬾���һ������ʱ�࣬�����ڻ���������ô�������ʱ�౾�����һ��Class��ʵ����
	 * 1.ÿһ������ʱ��ֻ����һ�Σ�
	 * 2.����Class��ʵ���Ժ����ǲſ��Խ������µĲ�����
	 *     1��*������Ӧ������ʱ��Ķ���
	 *     2����ȡ��Ӧ������ʱ��������ṹ�����ԡ����������������ڲ��ࡢ���ࡢ���ڵİ����쳣��ע�⡢...��
	 *     3��*���ö�Ӧ������ʱ���ָ���Ľṹ(���ԡ�������������)
	 *     4�������Ӧ�ã���̬����
	 * @author Lancelot Chen
	 * @date 2019��3��6�� ����7:08:10 
	 * @version V1.0
	 */
	@Test
	public void test3() {
		Person p = new Person();
		Class clazz = p.getClass();//ͨ������ʱ��Ķ��󣬵�����getClass()������������ʱ�ࡣ
		System.out.println(clazz);
	}
	
	/**
	 * ��λ�ȡClass��ʵ����3�֣�
	 * @author Lancelot Chen
	 * @date 2019��3��6�� ����7:26:01 
	 * @version V1.0
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void test4() throws Exception {
		//1.��������ʱ�౾���.class����
		Class clazz1 = Person.class;
		System.out.println(clazz1.getName());
		
		Class clazz2 = String.class;
		System.out.println(clazz2.getName());
		
		//2.ͨ������ʱ��Ķ����ȡ
		Person p = new Person();
		Class clazz3 = p.getClass();
		System.out.println(clazz3.getName());
		
		//3.ͨ��Class�ľ�̬������ȡ.ͨ���˷�ʽ�����һ�£�����Ķ�̬��
		String className = "com.lancelot.java.reflect.reflect.Person";
		
		Class clazz4 = Class.forName(className);
		System.out.println(clazz4.getName());
		
		//4.ͨ����ļ�����
		ClassLoader cloassLoader = p.getClass().getClassLoader();
		Class clazz5 = cloassLoader.loadClass(className);
		
		System.out.println(clazz1 == clazz3);
		System.out.println(clazz1 == clazz4);
		System.out.println(clazz1 == clazz5);
	}
	
	/**
	 * ������ļ�������ClassLoader
	 * @author Lancelot Chen
	 * @date 2019��3��6�� ����7:41:06 
	 * @version V1.0
	 * @throws Exception 
	 */
	@Test
	public void test5() throws Exception {
		ClassLoader loader1 = ClassLoader.getSystemClassLoader();
		System.out.println(loader1);
		
		ClassLoader loader2 = loader1.getParent();
		System.out.println(loader2);
		
		ClassLoader loader3 = loader2.getParent();
		System.out.println(loader3);
		
		Class clazz1 = Person.class;
		ClassLoader loader4 = clazz1.getClassLoader();
		System.out.println(loader4);
		
		String className = "java.lang.String";
		Class<?> clazz2 = Class.forName(className);
		ClassLoader loader5 = clazz2.getClassLoader();
		System.out.println(loader5);
		
		ClassLoader loader = this.getClass().getClassLoader();
		InputStream is = loader.getResourceAsStream("com\\lancelot\\java\\reflect\\reflect\\jdbc.properties");

//		FileInputStream is = new FileInputStream(new File("jdbc1.properties"));
		Properties pros = new Properties();
		pros.load(is);
		String name = pros.getProperty("user");
		System.out.println(name);
		
		String password = pros.getProperty("password");
		System.out.println(password);
	}
	
}
























