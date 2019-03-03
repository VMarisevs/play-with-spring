package com.vm.playwspring.dao;

import org.springframework.stereotype.Repository;
import play.Logger;

@Repository
public class SupportDao {

    public void checkTable(final String table) {

        for (int i=0; i < 20; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Logger.debug("Checking if table exist {}", table);

        }
    }

}
