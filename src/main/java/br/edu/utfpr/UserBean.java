/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import br.edu.utfpr.model.User;
import br.edu.utfpr.model.UserRole;
import br.edu.utfpr.model.dao.UserDAO;
import br.edu.utfpr.model.service.UserRoleService;
import java.util.Calendar;
import br.edu.utfpr.model.service.UserService;
import static br.edu.utfpr.util.JPAUtil.getEntityManager;
import br.edu.utfpr.util.MessageUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

/**
 *
 * @author cabrito
 */
@ManagedBean
@RequestScoped
@PersistenceUnit
public class UserBean {

    private User user;
    private List<User> userList;
    private UserService userService;
    private UserRoleService userRoleService;
    private Long cityId;

//    @ManagedProperty(value = "#{userRoleBean}")
//    private UserRoleBean userRoleBean;
    public UserBean() {
    }

    @PostConstruct
    public void init() {
        user = new User();
        userList = new ArrayList<>();
        userService = new UserService();
        userRoleService = new UserRoleService();
    }

    public List<User> createUsers(int size) {
        List<User> list = new ArrayList<User>();
        List<User> us = null;
        us = userService.findAll();

        for (User xx : us) {
            list.add(new User(xx.name, xx.getEmail(), xx.getLogin(), xx.getPassword(), BigDecimal.ONE, true, 0));
        }

        return list;
    }

    public List<String> autocompleteUsuarios(String consulta) {
        List<String> paisesSugeridos = new ArrayList<>();
        List<User> users = userService.findAll();
        UserRole defaultUserRole = new UserRole();

        List<UserRole> usuarios = null;
        System.out.println("AUTOCOMPLETE1");
        System.out.println("AUTOCOMPLETE1");
        for (User usuario : users) {
            if (usuario.getName() != null) {
                if (usuario.getName().toLowerCase().startsWith(consulta.toLowerCase())) {
                    paisesSugeridos.add(usuario.getName());
                }
            }
        }

        return paisesSugeridos;
    }

    public void atualizar() {

        System.out.println("País: " + user);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Perfil atualizado!"));
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public void edit(User user) {
        this.user = user;
    }

    public void delete(User user) {
        boolean isSuccess = userService.delete(user);
        if (isSuccess) {
            this.userList.remove(user);
            MessageUtil.showMessage("Removido com sucesso", "", FacesMessage.SEVERITY_INFO);
        } else {
            MessageUtil.showMessage("Falha na remoção", "", FacesMessage.SEVERITY_ERROR);
        }
        this.user = new User();
    }

    public List<String> listapornomes() {
        List<String> us = null;
        us = userService.listByNames();
        return us;
    }

    public String persist() {

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        System.out.println("TIMEEEE");
        user.setBalance(BigDecimal.ONE);
        if (user.getBalance().compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("O VALOR E ZERO MESMO");
        }
        System.out.println("BALANCEEEEE" + user.getBalance());
        System.out.println("PASSOUAQUI");
        long time = cal.getTimeInMillis();

        List<String> us = null;
        us = userService.listByNames();

        for (String xx : us) {
            System.out.println("FLAGG");
            System.out.println(xx.toString());
        }

        System.out.println("PASSOU");

        if (userService.getByProperty("email", user.getEmail()) != null) {
            MessageUtil.showMessage("Erro ao cadastrar, email ja informado", "", FacesMessage.SEVERITY_ERROR);
        } else if (userService.getByProperty("login", user.getLogin()) != null) {
            MessageUtil.showMessage("Erro ao cadastrar, cpf ja informado", "", FacesMessage.SEVERITY_ERROR);
        } else if (user.getId() == null) {
            String passwordMd5 = gerarHashMD5(user.getPassword());
            user.setPassword(passwordMd5);
            UserRole userRole = new UserRole(user.getLogin(), UserRole.USER_PENDING);
            //user.getRoles().add(userRole);
            user.setTime(time);

            //TODO Cadastrar o user e role em apenas uma transaçao - criar um novo método save em algum service
            if (userService.save(user) && userRoleService.save(userRole)) {
                System.out.println("Ate aqui chegou");
                MessageUtil.showMessage("Cadastrado com sucesso", "", FacesMessage.SEVERITY_INFO);
                return "pending-user/page?faces-redirect=true";
            } else {
                MessageUtil.showMessage("Falha ao cadastrar", "", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            if (userService.update(user)) {
                MessageUtil.showMessage("Alterado com sucesso", "", FacesMessage.SEVERITY_INFO);
            } else {
                MessageUtil.showMessage("Falha na alteração", "", FacesMessage.SEVERITY_ERROR);
            }

        }
        //tirei o this abaixo
        user = new User();
        return "";
    }

    public List<User> findAll() {

        return userList = userService.findAll();

    }

    public String gerarHashMD5(String passwordRaw) {
        byte[] b;
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.reset();
            b = md.digest(passwordRaw.getBytes());

            return new BigInteger(1, b).toString(16);
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

}
