package tddbook.chap1;

public class Account {
	
	private int balance;

	public Account(int initMoney) {
		this.balance = initMoney;
	}

	public int getBalance() {
		return this.balance;
	}

	public int deposit(int money) {
		return this.balance += money;
	}

	public int withdraw(int money) {
		return this.balance -= money;
	}

}
