/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import br.edu.utfpr.model.TransactionProduct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Luiz
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.edu.utfpr.model.UserRole;
import br.edu.utfpr.model.service.TransactionProductService;
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
public class TransactionProductBean {

    public TransactionProduct getTransactionProduct() {
        return transactionProduct;
    }

    public void setTransactionProduct(TransactionProduct transactionProduct) {
        this.transactionProduct = transactionProduct;
    }

    public List<TransactionProduct> getTransactionProductList() {
        return transactionProductList;
    }

    public void setTransactionProductList(List<TransactionProduct> transactionProductList) {
        this.transactionProductList = transactionProductList;
    }

    public TransactionProductService getTransactionProductService() {
        return transactionProductService;
    }

    public void setTransactionProductService(TransactionProductService transactionProductService) {
        this.transactionProductService = transactionProductService;
    }

    private TransactionProduct transactionProduct;
    private List<TransactionProduct> transactionProductList;
    private TransactionProductService transactionProductService;

    public TransactionProductBean() {
    }

    public TransactionProductBean(TransactionProduct transactionProduct, List<TransactionProduct> transactionProductList, TransactionProductService transactionProductService) {
        this.transactionProduct = transactionProduct;
        this.transactionProductList = transactionProductList;
        this.transactionProductService = transactionProductService;
    }

    @PostConstruct
    public void init() {
        transactionProduct = new TransactionProduct();
        transactionProductList = new ArrayList<>();
        transactionProductService = new TransactionProductService();
    }

    public void edit(TransactionProduct transactionProduct) {
        this.transactionProduct = transactionProduct;
    }

    public void delete(TransactionProduct transactionProduct) {
        boolean isSuccess = transactionProductService.delete(transactionProduct);
        if (isSuccess) {
            this.transactionProductList.remove(this.transactionProduct);
            MessageUtil.showMessage("Removido com sucesso", "", FacesMessage.SEVERITY_INFO);
        } else {
            MessageUtil.showMessage("Falha na remoção", "", FacesMessage.SEVERITY_ERROR);
        }
        this.transactionProduct = new TransactionProduct();
    }

    public void persist() {
        if (transactionProduct.getId() == null) {
            if (transactionProductService.save(transactionProduct)) {
                this.transactionProductList.add(transactionProduct);
                MessageUtil.showMessage("Cadastrado com sucesso", "", FacesMessage.SEVERITY_INFO);
            } else {
                MessageUtil.showMessage("Falha ao cadastrar", "", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            if (transactionProductService.update(transactionProduct)) {
                MessageUtil.showMessage("Alterado com sucesso", "", FacesMessage.SEVERITY_INFO);
            } else {
                MessageUtil.showMessage("Falha na alteração", "", FacesMessage.SEVERITY_ERROR);
            }
        }

        this.transactionProduct = new TransactionProduct();
    }

    public List<TransactionProduct> findAll() {
        return transactionProductList = transactionProductService.findAll();
    }

}
