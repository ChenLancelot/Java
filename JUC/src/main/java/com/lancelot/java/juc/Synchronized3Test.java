package com.lancelot.java.juc;
/**
 * 指定要给某个对象加锁
 * @author Lancelot Chen 
 * @date 2019年3月25日 下午7:26:00
 * @Copyright：Lancelot Chen个人所有
 * @version 1.0 
 * @Description: TODO
 */
public class Synchronized3Test {

	public static void main(String[] args) {
		Account account = new Account("zhang san", 10000.0f);
		AccountOperator accountOperator = new AccountOperator(account);
 
		final int THREAD_NUM = 20;
		Thread threads[] = new Thread[THREAD_NUM];
		for (int i = 0; i < THREAD_NUM; i++) {
		   threads[i] = new Thread(accountOperator, "Thread" + i);
		   threads[i].start();
		}
	}

}

/**
 * 账户操作类
 */
class AccountOperator implements Runnable {
	private Account account;

	public AccountOperator(Account account) {
		this.account = account;
	}

	public void run() {
//		synchronized (account) {
			account.deposit(500);
			account.withdraw(500);
			System.out.println(Thread.currentThread().getName() + ":" + account.getBalance());
//		}

	}
}
/**
 * 银行账户类
 */
class Account {
	String name;
	float amount;
	
	public Account(String name, float amount) {
		this.name = name;
		this.amount = amount;
	}
	//存钱
	public  void deposit(float amt) {
		amount += amt;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//取钱
	public void withdraw(float amt) {
		amount -= amt;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public float getBalance() {
		return amount;
	}
}
