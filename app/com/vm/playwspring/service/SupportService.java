package com.vm.playwspring.service;

import com.vm.playwspring.dao.SupportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import play.Logger;

@Service
public class SupportService {

    @Autowired
    private SupportDao supportDao;

    public void verifyDatabaseConnection() {

        Logger.debug("Verifying database connection");

        supportDao.checkTable("main_table");

    }
}
