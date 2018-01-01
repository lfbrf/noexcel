/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import br.edu.utfpr.model.Product;
import br.edu.utfpr.model.Transaction;
import br.edu.utfpr.model.User;
import br.edu.utfpr.model.service.ProductService;
import br.edu.utfpr.model.service.TransactionService;
import br.edu.utfpr.model.service.UserService;
import br.edu.utfpr.util.MessageUtil;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class TransactionBean {

    private long credit;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    private User user;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    private Product product;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
    private long productId;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
    private Transaction transaction;

    public TransactionService getTransactionsService() {
        return transactionsService;
    }

    public void setTransactionsService(TransactionService transactionsService) {
        this.transactionsService = transactionsService;
    }
    private TransactionService transactionsService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    private UserService userService;
    private ProductService productService;

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Creates a new instance of TransactionBean
     */
    public TransactionBean() {
    }

    private String message;

    @PostConstruct
    public void init() {
        user = new User();
        transaction = new Transaction();
        transactionsService = new TransactionService();
        productService = new ProductService();
        userService = new UserService();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void removeCredit() {
        System.out.println("OK " + productId);

        product = productService.getById(productId);
        System.out.println("OKKKK " + product.getName());
        String ra = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("ra");
        user = userService.getByProperty("login", ra);
        System.out.println("---------");
        System.out.println(user.getName());

        Long total, balance;
        balance = user.getBalance().longValue();

        total = balance - product.getValue();
        transaction.setLogin(ra);
        transaction.setUser(user);
        transaction.setValue((product.getValue() * -1));
        BigDecimal track = new BigDecimal(total);
        user.setBalance(track);

        if ((userService.update(user)) && (transactionsService.save(transaction))) {
            MessageUtil.showMessage("Inserido com sucesso", "", FacesMessage.SEVERITY_INFO);
        } else {
            MessageUtil.showMessage("Falha na adicao de credito", "", FacesMessage.SEVERITY_ERROR);
        }
        user = new User();
        transaction = new Transaction();
        product = new Product();
    }

    public void insertCredit() {
        System.out.println("INVOCADO");
        String balance = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("balance");
        balance = balance.substring(3);
        balance = balance.substring(0, balance.length() - 3);
        System.out.println(balance);
        String ra = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("ra");
        user = userService.getByProperty("login", ra);
        transaction.setLogin(ra);
        transaction.setUser(user);
        transaction.setValue(credit);

        Long l = Long.parseLong(balance);
        l = l + credit;

        BigDecimal track = new BigDecimal(l);
        user.setBalance(track);

        if ((userService.update(user)) && (transactionsService.save(transaction))) {
            MessageUtil.showMessage("Inserido com sucesso", "", FacesMessage.SEVERITY_INFO);
        } else {
            MessageUtil.showMessage("Falha na adicao de credito", "", FacesMessage.SEVERITY_ERROR);
        }
        user = new User();
        transaction = new Transaction();
    }

    public long getCredit() {
        return credit;
    }

    public void setCredit(long credit) {
        this.credit = credit;
    }

}
