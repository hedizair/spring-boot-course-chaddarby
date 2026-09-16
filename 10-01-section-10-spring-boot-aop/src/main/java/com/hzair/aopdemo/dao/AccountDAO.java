package com.hzair.aopdemo.dao;

import java.util.List;

import com.hzair.aopdemo.Account;

public interface AccountDAO {
    void addAccount(Account account, boolean vip);

    boolean doWork();

    String getName();

    void setName(String name);

    public String getServiceCode();

    public void setServiceCode(String serviceCode);

    public List<Account> findAccounts();

    public List<Account> findAccounts(boolean tripWire);
}
