/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.jobs;

import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Web application lifecycle listener.
 *
 * @author Luiz
 */
@WebListener
public class QuartzListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        Timer t = new Timer();
//        MyTask mTask = new MyTask();
//        // This task is scheduled to run every 10 seconds
//
//        //t.scheduleAtFixedRate(mTask, 0, 86400000);
//        t.scheduleAtFixedRate(mTask, 0, 6000);

        JobDetail job = getJob(Job1.class, "a", "groupA");

        Trigger trigger = getTrigger("a", "0/30 * * * * ?", "groupA");

        //schedule it
        Scheduler scheduler = null;
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (SchedulerException ex) {
            Logger.getLogger(QuartzListener.class.getName()).log(Level.SEVERE, null, ex);

        }

        System.out.println("Quartz Funcionando!!!!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Trigger getTrigger(String name, String cron, String group) {
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(name, group)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .build();
        return trigger;
    }

    private JobDetail getJob(Class clazz, String name, String group) {
        JobDetail job = JobBuilder
                .newJob(clazz)
                .withIdentity(name, group)
                .build();
        return job;
    }
}
