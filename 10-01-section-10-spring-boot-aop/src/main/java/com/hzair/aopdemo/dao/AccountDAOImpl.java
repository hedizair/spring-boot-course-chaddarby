package com.hzair.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hzair.aopdemo.Account;

@Repository
public class AccountDAOImpl implements AccountDAO {

    private String name;

    private String serviceCode;

    public AccountDAOImpl() {
    }

    @Override
    public void addAccount(Account account, boolean vip) {
        System.out.println(getClass() + " : DOING MY DB WORK (AccountDAO.addAccount) ...");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + " : DOING MY DB WORK (AccountDAO.doWork) ...");
        return false;
    }

    @Override
    public List<Account> findAccounts() {
        return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {

        if (tripWire) {
            throw new RuntimeException("No soup for you!!!!");
        }

        List<Account> accounts = new ArrayList<>();
        Account acc1 = new Account("Peter Parker", "GOLD");
        Account acc2 = new Account("Tony Stark", "PLATINUM");
        Account acc3 = new Account("Stephen Strange ", "DIAMOND");

        accounts.add(acc1);
        accounts.add(acc2);
        accounts.add(acc3);

        return accounts;

    }

    @Override
    public String getName() {
        System.out.println("> Call getName()");
        return this.name;
    }

    @Override
    public void setName(String name) {
        System.out.println("> Call setName()");
        this.name = name;
    }

    @Override
    public String getServiceCode() {
        System.out.println("> Call getServiceCode())");
        return this.serviceCode;
    }

    @Override
    public void setServiceCode(String serviceCode) {
        System.out.println("> Call setServiceCode()");
        this.serviceCode = serviceCode;
    }

}
