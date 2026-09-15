package com.hzair.aopdemo.dao;

import org.springframework.stereotype.Repository;

import com.hzair.aopdemo.Account;

@Repository
public class AccountDAOImpl implements AccountDAO {

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

}
