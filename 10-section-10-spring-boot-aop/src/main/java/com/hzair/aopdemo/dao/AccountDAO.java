package com.hzair.aopdemo.dao;

import com.hzair.aopdemo.Account;

public interface AccountDAO {
    void addAccount(Account account, boolean vip);
    boolean doWork();
}
