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
import java.util.Calendar;
import br.edu.utfpr.model.service.UserService;
import br.edu.utfpr.util.MessageUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author cabrito
 */
@ManagedBean
@RequestScoped
public class UserBean {

    private User user;
    private List<User> userList;
    private UserService userService;
    private boolean content = false;

    public boolean isContent() {
        return content;
    }

    public void setContent(boolean content) {
        this.content = content;
    }

    public boolean isInserir() {
        return inserir;
    }

    public void setInserir(boolean inserir) {
        this.inserir = inserir;
    }
    private boolean inserir;

    public String getNpass() {
        return npass;
    }

    public void method() {
        System.out.println("AQUIIII");
    }

    public void changeInsert() {
        System.out.println("MUDE" + inserir);
        inserir = true;
    }

    public void changeRemove() {
        inserir = false;
    }

    public void setNpass(String npass) {
        this.npass = npass;
    }

    private String npass;

    public String getNrpass() {
        return nrpass;
    }

    public void setNrpass(String nrpass) {
        this.nrpass = nrpass;
    }
    private String nrpass;

    public TypeService getTypeService() {
        return typeService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }
    private TypeService typeService;
    private UserRoleService userRoleService;
    private Long cityId;

//    @ManagedProperty(value = "#{userRoleBean}")
//    private UserRoleBean userRoleBean;
    public UserBean() {
    }

    public void mostraExtrato() {
        System.out.println("OKKOKOKO");
        setExtrato(true);
    }

    public void mostraEditar() {
        System.out.println("OKKOKOKO");
        setEditar(true);
    }

    public String avatar(String login) {
        User us = userService.getByProperty("login", login);
        if (us.getAvatar() == null) {
            return null;
        }
        return us.getAvatar();
    }

    @PostConstruct
    public void init() {
        user = new User();
        userList = new ArrayList<>();
        userService = new UserService();
        userRoleService = new UserRoleService();
        typeService = new TypeService();

        extrato = false;
        editar = false;
    }

    public boolean isBolsista(String login) {
        User u = userService.getByProperty("login", login);
        if (u != null) {
            Type t = typeService.getById(u.getType().getId());
            if (t != null && t.getDescription().equals("BOLSISTA")) {
                return true;
            }
        }

        return false;
    }

    public boolean isPending(String login) {
        User u = userService.getByProperty("login", login);
        if (u.isCheckuser() == true) {
            return true;
        }
        return false;
    }

    public String updateUs(Long type_id) {
        System.out.println("ID DO TIPO " + type_id);
        String nome = this.user.getName();
        String email = this.user.getEmail();

        System.out.println("ID DO USUARIO " + this.user.getId());
        System.out.println("NOME DO USUARIO " + this.user.getName());
        user = userService.getById(this.user.getId());
        if (isBolsista(user.getLogin())) {
            user.setCheckuser(!this.user.isCheckuser());
        }
        user.setName(nome);
        User up = userService.getByProperty("email", email);
        if (up != null && up.getId() != this.user.getId()) {
            MessageUtil.showMessage("Email ja esta em uso", "", FacesMessage.SEVERITY_INFO);
            return "";

        }
        user.setEmail(email);
        if (type_id != null && type_id > 0) {
            user.setType(typeService.getById(type_id));
        }
        if (userService.update(user)) {
            MessageUtil.showMessage("Alterado com sucesso", "", FacesMessage.SEVERITY_INFO);
        } else {
            MessageUtil.showMessage("Falha na alteração", "", FacesMessage.SEVERITY_ERROR);
        }
        return "admin/controleusuarios?faces-redirect=true";
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

    public String getByra(String ra) {
        User us = userService.getByProperty("login", ra);
        return us.getName();
    }

    public String getEmailByra(String ra) {
        User us = userService.getByProperty("login", ra);
        return us.getEmail();
    }

    public BigDecimal getBalanceByra(String ra) {
        User us = userService.getByProperty("login", ra);
        return us.getBalance();
    }

    public String getTypeByra(String ra) {
        User us = userService.getByProperty("login", ra);
        Type t = us.getType();
        return t.getDescription();
    }

    public void getEmail(String ra) {
        User us = userService.getByProperty("login", ra);
        System.out.println("-----" + user.getEmail());
        user.setName(us.getName());
        user.setEmail(us.getEmail());
        setUser(user);
        System.out.println("||||" + user.getEmail());
        // us.getEmail();
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
        System.out.println("INVOQUEI");
        setContent(true);
        this.user = user;
    }

    public boolean editProfile(String login) {
        User u = userService.getByProperty("login", login);
        if (u != null) {
            if (!u.getLogin().equals(login)) {
                MessageUtil.showMessage("Erro ao editar, email ja informado", "", FacesMessage.SEVERITY_ERROR);
                return false;
            }
            String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(user.getPassword());
            String pass = org.apache.commons.codec.digest.DigestUtils.sha256Hex(npass);

            if (!sha256hex.equals(u.getPassword())) {
                MessageUtil.showMessage("Erro ao editar, senha incorreta", "", FacesMessage.SEVERITY_ERROR);
                return false;
            }

            if (!npass.equals(nrpass) && npass != null && nrpass != null) {
                MessageUtil.showMessage("Erro ao editar, senhas nao conferem", "", FacesMessage.SEVERITY_ERROR);

                return false;
            } else {
                u.setName(user.getName());
                u.setEmail(user.getEmail());
                if (npass.equals(nrpass)) {
                    u.setPassword(pass);
                }
                if (!user.getPassword().equals("")) {

                }
                userService.update(u);
                MessageUtil.showMessage("Perfil editado com sucesso", "", FacesMessage.SEVERITY_INFO);

            }

        }

        return true;
    }

    public boolean isExtrato() {
        return extrato;
    }

    public void setExtrato(boolean extrato) {
        this.extrato = extrato;
    }
    private boolean extrato;

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }
    private boolean editar;

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

    public String persistManager(long type_id) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        user.setBalance(BigDecimal.ZERO);
        String aux = user.getLogin().replace(".", "");
        aux = aux.replace("-", "");
        user.setLogin(aux);
        long time = cal.getTimeInMillis();
        String descricao;
        List<String> us = null;
        us = userService.listByNames();
        Type type = typeService.getById(type_id);
        if (type == null) {
            MessageUtil.showMessage("Erro ao cadastrar, informe o tipo de gerente.", "", FacesMessage.SEVERITY_ERROR);
            return "";
        }
        user.setType(type);
        if (userService.getByProperty("email", user.getEmail()) != null) {
            MessageUtil.showMessage("Erro ao cadastrar, email ja informado", "", FacesMessage.SEVERITY_ERROR);
        } else if (userService.getByProperty("login", user.getLogin()) != null) {
            MessageUtil.showMessage("Erro ao cadastrar, login ja informado", "", FacesMessage.SEVERITY_ERROR);
        } else if (user.getId() == null) {

            String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(user.getPassword());
            user.setPassword(sha256hex);
            UserRole userRole = new UserRole(user.getLogin(), UserRole.MANAGER);
            //user.getRoles().add(userRole);
            user.setTime(time);

            //TODO Cadastrar o user e role em apenas uma transaçao - criar um novo método save em algum service
            if (userService.save(user) && userRoleService.save(userRole)) {
                System.out.println("Ate aqui chegou");
                MessageUtil.showMessage("Cadastrado com sucesso", "", FacesMessage.SEVERITY_INFO);
                return "admin/gerentes?faces-redirect=true";
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

    public String persist() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        user.setBalance(BigDecimal.ZERO);
        String aux = user.getLogin().replace(".", "");
        aux = aux.replace("-", "");
        user.setLogin(aux);
        user.setAvatar("user.png");
        long time = cal.getTimeInMillis();
        String descricao;
        List<String> us = null;
        us = userService.listByNames();
        int length = user.getLogin().length();
        if (length == 8 && user.isCheckuser()) {
            user.setCheckuser(true);
            descricao = "Bolsista";
        } else if (length == 8) {
            descricao = "Estudante";
        } else {
            descricao = "Visitante";
        }
        Type t = typeService.getByProperty("description", descricao);
        if (t == null) {
            Type ty = new Type();
            ty.setDescription(descricao);
            typeService.save(ty);
            user.setType(ty);

        } else {
            user.setType(t);
        }

        if (userService.getByProperty("email", user.getEmail()) != null) {
            MessageUtil.showMessage("Erro ao cadastrar, email ja informado", "", FacesMessage.SEVERITY_ERROR);
        } else if (userService.getByProperty("login", user.getLogin()) != null) {
            MessageUtil.showMessage("Erro ao cadastrar, login ja informado", "", FacesMessage.SEVERITY_ERROR);
        } else if (user.getId() == null) {

            String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(user.getPassword());
            user.setPassword(sha256hex);
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

    public long listType(String ra) {
        System.out.println("IMPRESSAO AQUI" + ra);
        User u = userService.getByProperty("login", ra);
        if (u != null) {
            return u.type.getId();
        }
        return 0;
    }

    public List<User> findAll() {
        return userList = userService.findAll();
    }

    public List<User> findOnlyUsers() {
        return userService.typesNotManager();
    }

}
