/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import br.edu.utfpr.model.Type;
import br.edu.utfpr.model.service.TypeService;
import br.edu.utfpr.util.MessageUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Luiz
 */
@ManagedBean
@RequestScoped
public class TypeBean {

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void teste() {
        System.out.println("FUNCIONOU");
    }

    public List<Type> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Type> typeList) {
        this.typeList = typeList;
    }

    public TypeService getTypeService() {
        return typeService;
    }

    public long getTypeID() {
        return typeID;
    }

    public void setTypeID(long typeID) {
        this.typeID = typeID;
    }
    private long typeID;

    /**
     * Creates a new instance of TypeBean
     */
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    private Type type;
    private List<Type> typeList;
    private TypeService typeService;

    public TypeBean() {
    }

    @PostConstruct
    public void init() {
        type = new Type();
        typeList = new ArrayList<>();
        typeService = new TypeService();
        typeList = typeService.findAll();
    }

    public void edit(Type type) {
        this.type = type;
    }

    public void delete(Type type) {
        boolean isSuccess = typeService.delete(type);
        if (isSuccess) {
            this.typeList.remove(type);
            MessageUtil.showMessage("Removido com sucesso", "", FacesMessage.SEVERITY_INFO);
        } else {
            MessageUtil.showMessage("Falha na remoção", "", FacesMessage.SEVERITY_ERROR);
        }
        this.type = new Type();
    }

    public void persist() {
        if (type.getId() == null) {
            if (typeService.save(type)) {
                this.typeList.add(type);
                MessageUtil.showMessage("Persistido com sucesso", "", FacesMessage.SEVERITY_INFO);
            } else {
                MessageUtil.showMessage("Falha ao persistir", "", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            if (typeService.update(type)) {
                MessageUtil.showMessage("Alterado com sucesso", "", FacesMessage.SEVERITY_INFO);
            } else {
                MessageUtil.showMessage("Falha na alteração", "", FacesMessage.SEVERITY_ERROR);
            }
        }

        this.type = new Type();
    }

    public List<Type> findAll() {
        return typeList = typeService.findAll();
    }

    public Type findByid(Long id) {
        Type p = new Type();
        return typeService.getById(id);
    }

}
