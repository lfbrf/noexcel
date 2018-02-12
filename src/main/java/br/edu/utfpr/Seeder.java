/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import br.edu.utfpr.model.Type;
import br.edu.utfpr.model.User;
import br.edu.utfpr.model.UserRole;
import br.edu.utfpr.model.service.TypeService;
import br.edu.utfpr.model.service.UserRoleService;
import br.edu.utfpr.model.service.UserService;
import javax.annotation.PostConstruct;
import javax.management.relation.Role;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Luiz
 */
@WebListener
public class Seeder implements ServletContextListener {

    public UserRoleService getUserRoleService() {
        return userRoleService;
    }

    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private UserRoleService userRoleService;
    private UserService userService;

    public TypeService getTypeService() {
        return typeService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }
    private TypeService typeService;

    public UserRoleService getUserroleService() {
        return userroleService;
    }

    public void setUserroleService(UserRoleService userroleService) {
        this.userroleService = userroleService;
    }
    private UserRoleService userroleService;

    @PostConstruct
    public void init() {
        userRoleService = new UserRoleService();
        userService = new UserService();
        typeService = new TypeService();
        userroleService = new UserRoleService();
    }

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("BLZ FELIPE");
        User admin = new User();
        Type type = new Type();
        Type gerente_visitante = new Type();
        Type gerente_estudante = new Type();
        Type gerente_bolsista = new Type();
        Type estudante = new Type();
        Type bolsista = new Type();
        Type visitante = new Type();
        gerente_visitante.setDescription("GERENTE-VISITANTE");
        gerente_estudante.setDescription("GERENTE-ESTUDANTE");
        gerente_bolsista.setDescription("GERENTE-BOLSISTA");
        estudante.setDescription("ESTUDANTE");
        bolsista.setDescription("BOLSISTA");
        visitante.setDescription("VISITANTE");
        UserRole userRole = new UserRole();
        type.setDescription("ADMIN");
        admin.setEmail("ru@utfpr.com");
        admin.setName("Administrador");
        admin.setLogin("administrador");
        userRole.setLogin("administrador");
        userRole.setRole("ADMIN");
        String password = org.apache.commons.codec.digest.DigestUtils.sha256Hex("administrador");
        admin.setPassword(password);
        admin.setType(type);
        User us = userService.getByProperty("login", "administrador");
        if (us == null) {
            typeService.save(estudante);
            typeService.save(gerente_visitante);
            typeService.save(gerente_estudante);
            typeService.save(gerente_bolsista);
            typeService.save(bolsista);
            typeService.save(visitante);
            userRoleService.save(userRole);
            typeService.save(type);
            userService.save(admin);
            UserRole ur = new UserRole();
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
