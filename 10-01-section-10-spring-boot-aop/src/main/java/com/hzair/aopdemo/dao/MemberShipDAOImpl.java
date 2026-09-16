package com.hzair.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository 
public class MemberShipDAOImpl implements MemberShipDAO {

    @Override
    public boolean  addSillyMember() {
        System.out.println(getClass() + " : DOING MY DB WORK (MemberShipDAO.addAccount) ...");
        return true;
    }
    
    @Override
    public void  goToSleep() {
        System.out.println(getClass() + " : DOING MY DB WORK (MemberShipDAO.goToSleep) ...");

    }
}
