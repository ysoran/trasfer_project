package moneytransfer.database;

import moneytransfer.dao.impl.AccountDaoImpl;
import moneytransfer.dao.impl.CustomerDaoImpl;
import moneytransfer.model.Account;
import moneytransfer.model.Customer;

public class FakeDataGenerator {
	public static void generate(){
		DatabaseGenerator.create();
		Customer yasar = new Customer(1,"Yasar","Soran","Maltepe Kucukyali apt ISTANBUL");
		Customer hande = new Customer(2,"Hande","Hakan","Kurtkoy Ankara apt ISTANBUL");
		Customer john = new Customer(3,"John","Denim","Sefakoy Gul apt ISTANBUL");
		Customer begum = new Customer(4,"Begum","Taran","Tersane Naci apt ISTANBUL");
		Account account1 = new Account(1,yasar,"salary account",Float.valueOf(100000),Float.valueOf(100000),1);
		Account account5 = new Account(5,yasar,"account1",Float.valueOf(100000),Float.valueOf(100000),4);
		Account account2 = new Account(2,hande,"salary account",Float.valueOf(100000),Float.valueOf(100000),1);
		Account account3 = new Account(3,john,"work account",Float.valueOf(100000),Float.valueOf(100000),1);
		Account account4 = new Account(4,begum,"my account",Float.valueOf(100000),Float.valueOf(100000),2);
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		customerDao.addCustomer(yasar);
		customerDao.addCustomer(hande);
		customerDao.addCustomer(john);
		customerDao.addCustomer(begum);
		AccountDaoImpl accountDao = new AccountDaoImpl();
		accountDao.addAccount(account1);
		accountDao.addAccount(account2);
		accountDao.addAccount(account3);
		accountDao.addAccount(account4);
		accountDao.addAccount(account5);
	}
}
