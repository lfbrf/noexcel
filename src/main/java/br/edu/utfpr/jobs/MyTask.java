/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.jobs;

import br.edu.utfpr.UserRoleBean;
import br.edu.utfpr.model.User;
import br.edu.utfpr.model.UserRole;
import br.edu.utfpr.model.service.UserRoleService;
import br.edu.utfpr.model.service.UserService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.TimerTask;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Luiz
 */
class MyTask extends TimerTask {

    private UserService userService = new UserService();
    private UserRoleService userRoleService = new UserRoleService();
    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    long newtime = cal.getTimeInMillis();

    public long getNewtime() {
        return newtime;
    }

    public void setNewtime(long newtime) {
        this.newtime = newtime;
    }

    public MyTask() {
        //Some stuffs

    }

    @Override
    public void run() {
        System.out.println("MUNDAO");
        List<User> usuarios = null;
        List<User> users = null;
        List<UserRole> userroles = null;
        users = userService.findAll();
        userroles = userRoleService.findAll();
        UserRoleService userRoleService = new UserRoleService();
        for (UserRole urs : userroles) {
            if (urs.getRole().equals(UserRole.USER_PENDING)) {
                User us = userService.getByProperty("login", urs.getLogin());
                System.out.println(us.getTime() + "VALORES" + getNewtime());
                if (us.getBalance().compareTo(BigDecimal.ZERO) == 1) {
                    urs.setRole(UserRole.USER);
                    userRoleService.update(urs);
                } else if (getNewtime() - us.getTime() > 1000 * 60) {
                    System.out.println("CON");

                    try {
                        synchronized (this) {
                            userRoleService.delete(urs);
                        }
                        synchronized (this) {
                            userService.delete(us);
                        }
                        // ...
                    } catch (Exception e) {
                        System.out.println("Thread  interrupted.");
                    }
                }
            }
        }

    }

}
