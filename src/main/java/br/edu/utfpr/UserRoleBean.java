/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import br.edu.utfpr.model.UserRole;
import br.edu.utfpr.model.service.UserRoleService;
import br.edu.utfpr.util.MessageUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author cabrito
 */
@ManagedBean(eager = true)
@RequestScoped
public class UserRoleBean {

    private UserRole userRole;
    private List<UserRole> userRoleList;
    private UserRoleService userRoleService;

    public UserRoleBean() {
    }

    public UserRoleBean(UserRole userRole, List<UserRole> userRoleList, UserRoleService userRoleService) {
        this.userRole = userRole;
        this.userRoleList = userRoleList;
        this.userRoleService = userRoleService;
    }

    @PostConstruct
    public void init() {
        userRole = new UserRole();
        userRoleList = new ArrayList<>();
        userRoleService = new UserRoleService();
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public UserRoleService getUserRoleService() {
        return userRoleService;
    }

    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    public void edit(UserRole userRole) {
        this.userRole = userRole;
    }

    public void delete(UserRole userRole) {
        boolean isSuccess = userRoleService.delete(userRole);
        if (isSuccess) {
            this.userRoleList.remove(userRole);
            MessageUtil.showMessage("Removido com sucesso", "", FacesMessage.SEVERITY_INFO);
        } else {
            MessageUtil.showMessage("Falha na remoção", "", FacesMessage.SEVERITY_ERROR);
        }
        this.userRole = new UserRole();
    }

    public void persist() {
        if (userRole.getId() == null) {
            if (userRoleService.save(userRole)) {
                this.userRoleList.add(userRole);
                MessageUtil.showMessage("Cadastrado com sucesso", "", FacesMessage.SEVERITY_INFO);
            } else {
                MessageUtil.showMessage("Falha ao cadastrar", "", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            if (userRoleService.update(userRole)) {
                MessageUtil.showMessage("Alterado com sucesso", "", FacesMessage.SEVERITY_INFO);
            } else {
                MessageUtil.showMessage("Falha na alteração", "", FacesMessage.SEVERITY_ERROR);
            }
        }

        this.userRole = new UserRole();
    }

    public List<UserRole> findAll() {
        return userRoleList = userRoleService.findAll();
    }

}
