/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author Luiz
 */
@ManagedBean(name = "usingBean")
@RequestScoped
public class UsingBean {

    @ManagedProperty(value = "#{recoveryPassBean}")
    private RecoveryPassBean recoveryBean;

    public void istoShow(ComponentSystemEvent event) {
        FacesContext fc = FacesContext.getCurrentInstance();

        ConfigurableNavigationHandler nav
                = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();

        Object x = null;
        System.out.println("voasd");

        if (recoveryBean.isShowDiv()) {
            System.out.println("INVOssda");
            nav.performNavigation("novasenha");
        } else {
            nav.performNavigation("index");
        }

    }

    public RecoveryPassBean getRecoveryBean() {
        return recoveryBean;
    }

    public void setRecoveryBean(RecoveryPassBean recoveryBean) {
        this.recoveryBean = recoveryBean;
    }
}
