/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import br.edu.utfpr.model.Employe;
import br.edu.utfpr.model.service.EmployeService;
//import br.edu.utfpr.model.service.EmployeService;
import br.edu.utfpr.util.MessageUtil;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author FELIPE
 */
@ManagedBean
@RequestScoped
public class EmployeBean {

    /**
     * @return the employe
     */
    private Employe employe;
    private List<Employe> employList;
    private EmployeService employeService;

    @PostConstruct
    public void init() {
        employe = new Employe();
        employList = new ArrayList<>();
        employeService = new EmployeService();
    }

    public Employe getEmploye() {
        return employe;
    }

    /**
     * @param employe the employe to set
     */
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    /**
     * @return the employList
     */
    public List<Employe> getEmployeList() {
        return employList;
    }

    /**
     * @param employList the employList to set
     */
    public void setEmployeList(List<Employe> employList) {
        this.employList = employList;
    }

    public void delete(Employe employe) {
        boolean isSuccess = employeService.delete(employe);
        //employeService.
        if (isSuccess) {
            this.employList.remove(employe);
            MessageUtil.showMessage("Removido com sucesso", "", FacesMessage.SEVERITY_INFO);
        }
        this.employe = new Employe();
    }

    public String verify() throws IOException {
        // boolean isSuccess = employeService.getByProperty(propertyName, propertyValue);
        //System.out.println(employeService.getByProperty(employe.getUsername(), "joao"));
        //employeService.

        String user = employe.getUsername();
        String pass = employe.getPassword();
        for (Employe c : employeService.findAll()) {
            System.out.println(c.getUsername());

        }
        System.out.println("---------------------------------------------------------");
        for (Employe e : employeService.findAll()) {
            System.out.println("ENTROUAQUI");
            System.out.println(e.getUsername());
            if ((e.getUsername().equals(user)) && (e.getPassword().equals(pass))) {
                System.out.println("IGUAIS");
                //HttpSession session = SessionUtils.getSession();
                //session.setAttribute("username", user);
                if (e.getUsername().equals("admin")) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("admin.jsf");
                    //return "report?faces-redirect=true";
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("usuario.jsf");
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Incorrect Username and Passowrd",
                                "Please enter correct username and Password"));

            }

        }
        //employeService.
        /* if (isSuccess) {
            this.employList.remove(employe);
            MessageUtil.showMessage("Removido com sucesso", "", FacesMessage.SEVERITY_INFO);
        }
        this.employe = new Employe();
        return "login";
         */
        return "login";

    }

    public String persist() throws ClassNotFoundException, SQLException {

        /*
         *
         * Verifica se o objeto tem id, se tiver, se trata de edição.
         * Caso contrário, o objeto ainda não existe no banco de dados, se tratando de uma inserção
         *
         */
        if (employe.getId() == null) {
            if (employeService.save(employe)) {
                this.employList.add(employe);
                MessageUtil.showMessage("Persistido com sucesso", "", FacesMessage.SEVERITY_INFO);

            }
        } else {
            employeService.update(employe);
        }

        this.employe = new Employe();
        return "login";
    }

    public void edit(Employe employee) {
        this.employe = employee;
    }

    public List<Employe> findAll() {

        return employeService.findAll();
    }

    public EmployeBean() {
    }

}
