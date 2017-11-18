/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.jobs;

import br.edu.utfpr.UserRoleBean;
import br.edu.utfpr.model.User;
import br.edu.utfpr.model.UserRole;
import br.edu.utfpr.model.service.UserService;
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
//        System.out.println("MUNDAO");
//        List<User> usuarios = null;
//        List<User> users = null;
//        users = userService.findAll();
//
//        for (User user : users) {
//
//            if (user.isUserInRole(UserRole.USER) || user.isUserInRole(UserRole.ADMIN)) {
//                continue;
//            }
//
//            System.out.println("TIMEolDATE: ");
//            if (user.getBalance() != null) {
//                // setar role de usuario
//                userRoleBean.getUserRole().setRole("USER");
//                userRoleBean.persist();
//                //1000 * 60 * 60 * 24 * 7
//            } else if (getNewtime() - user.getTime() > 1000 * 60) {
//                System.out.println("APAGAR");
//                userService.delete(user);
//            }
//        }
//
//        System.out.println("Hi see you after 10 seconds");
    }

}
