package com.lecoding.service.impl;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-8 下午5:12
 */
@Service
public class TaskServiceImpl {

    @Autowired
    SessionFactory sessionFactory;

    @Scheduled(cron = "0 * * * * *")
    public void work() {
        Logger.getLogger(this.getClass()).log(Level.INFO, new Date().getTime());

        Session session = sessionFactory.openSession();
        String pauseSQL = "UPDATE customer SET status = \"pause\", last_pay = CURRENT_DATE() WHERE status = \"active\" and datediff(CURRENT_DATE(), last_pay) >= 365;";
        String cancelSQL = "UPDATE customer SET status = \"cancel\", last_pay = CURRENT_DATE() WHERE status = \"pause\" and datediff(CURRENT_DATE(), last_pay) >= 365;";
        SQLQuery query1 = session.createSQLQuery(pauseSQL);
        SQLQuery query2 = session.createSQLQuery(cancelSQL);
        query1.executeUpdate();
        query2.executeUpdate();
        session.close();
    }
}
