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
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Job1 implements Job {

    private UserRoleService userRoleService = new UserRoleService();
    private UserService userService = new UserService();

    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    long newtime = cal.getTimeInMillis();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        List<User> users = null;
        List<UserRole> roles = null;
        roles = userRoleService.findAll();
        users = userService.findAll();
        UserRole defaultUserRole = new UserRole();
        defaultUserRole.setRole(UserRole.USER_PENDING);
        List<UserRole> usuarios = null;
        usuarios = userRoleService.listPendents();
        for (UserRole userRole : usuarios) {
            System.out.println("AQUIIIIIIIIIIIIII");
            System.out.println(userRole.getRole());
            User usuario = null;
            usuario = userService.getByProperty("login", userRole.getLogin());
            if ((usuario == null)) {
                return;
            }

            if ((getNewtime() - usuario.getTime() > 1000 * 60 * 60)) {
                System.out.println("APAGAR");
                userService.delete(usuario);
            }
            System.out.println(usuario.getBalance().toString());
        }
        /*
        for (UserRole role : roles) {
            System.out.println("AQUIII");
            System.out.println(role.getLogin());
            String aux = "";
            if (role.getRole().equals(UserRole.USER_PENDING)) {
                System.out.println("USUARIO PENDENTE");
                aux = role.getLogin();
            }
            User x = null;
            x = userService.getByProperty("login", aux);
            if ((x != null) && (getNewtime() - x.getTime() > 1000 * 60 * 60) && (x.getBalance() == null)) {
                System.out.println("APAGAR");
                userService.delete(x);
                userRoleService.delete(role);
            } else if ((x != null) && (getNewtime() - x.getTime() > 1000 * 60 * 60)) {
                System.out.println("CAIUU");
                role.setRole("USER");
                userRoleService.update(role);
            }
        }
         */
    }

    public long getNewtime() {
        return newtime;
    }

    public void setNewtime(long newtime) {
        this.newtime = newtime;
    }
}
