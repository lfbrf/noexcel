/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import br.edu.utfpr.model.Product;
import br.edu.utfpr.model.Transaction;
import br.edu.utfpr.model.TransactionProduct;
import br.edu.utfpr.model.User;
import br.edu.utfpr.model.service.ProductService;
import br.edu.utfpr.model.service.TransactionProductService;
import br.edu.utfpr.model.service.TransactionService;
import br.edu.utfpr.model.service.UserService;
import br.edu.utfpr.util.MessageUtil;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;

@ManagedBean
@RequestScoped
public class TransactionBean {

    private BigDecimal credit;

    public String[] getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(String[] selectedProducts) {
        this.selectedProducts = selectedProducts;
    }
    private String[] selectedProducts;

    public TransactionProductService getTransactionProductService() {
        return transactionProductService;
    }

    public void setTransactionProductService(TransactionProductService transactionProductService) {
        this.transactionProductService = transactionProductService;
    }
    private TransactionProductService transactionProductService;

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

    public List<String> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<String> produtos) {
        this.produtos = produtos;
    }

    private List<String> produtos;

    @PostConstruct
    public void init() {
        user = new User();
        transaction = new Transaction();
        transactionsService = new TransactionService();
        productService = new ProductService();
        userService = new UserService();
        transactionProductService = new TransactionProductService();
        produtos = new ArrayList<String>();
        transactionList = new ArrayList<>();
        producList = new ArrayList<>();
        List<Product> products = productService.findAll();
        for (Product temp : products) {
            produtos.add(temp.getName());
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    private List<Transaction> transactionList;
    private List<TransactionProduct> producList;

    @ManagedProperty(value = "#{searchView}")
    private SearchView searchView;

    public SearchView getSearchView() {
        return searchView;
    }

    public void setSearchView(SearchView searchView) {
        this.searchView = searchView;
    }

    public List<Transaction> findAll() {
        transactionList = transactionsService.listbyRa(searchView.userDTO.getRa());

        return transactionList;
    }

    public List<TransactionProduct> findProducts() {
        // System.out.println("VALOR AQUI");
        //producList = transactionProductService.listByProperty("transaction_id", transaction.getId());

        //List<TransactionProduct> productList = transactionProductService.listbyId(x);
        String xx = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("valor");
        System.out.println("ÖLHA AQUI" + xx);
        List<TransactionProduct> pl = transactionProductService.findAll();
        System.out.println("VALOR AQUI antes do return");
        return pl;
    }

    public String getbyName(Transaction transaction) {
        System.out.println(">>>>" + transaction.getLogin());
        user = userService.getByProperty("login", transaction.getLogin());
        return user.getName();
    }

    /* public void removeCredit() { //
        System.out.println("ABAIO DAQUI");
        Long x = null;
        for (String temp : selectedProducts) {
            System.out.println(temp);
            Product pd = productService.getByProperty("name", temp);
            System.out.println(pd.getValue());
            x = x + pd.getValue();
        }
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
        TransactionProduct transactionProduct = new TransactionProduct(product.getId(), user.getId());
        if ((userService.update(user)) && (transactionsService.save(transaction)) && (transactionProductService.save(transactionProduct))) {
            MessageUtil.showMessage("Refeicao cadastrada com sucesso", "", FacesMessage.SEVERITY_INFO);
        } else {
            MessageUtil.showMessage("Falha no cadastro de refeicao ", "", FacesMessage.SEVERITY_ERROR);
        }
        userService = new UserService();
        transactionProductService = new TransactionProductService();
        transactionsService = new TransactionService();
        productService = new ProductService();
        user = new User();
        transaction = new Transaction();
        product = new Product();
    } */
    public boolean removeCredit() { // editar voltar um poco mais
        BigDecimal refeicao = BigDecimal.ZERO, total;
        Product pd;
        String ra = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("ra");
        if (ra == "") {
            MessageUtil.showMessage("Falha no cadastro de refeicao, informe um RA valido ", "", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        user = userService.getByProperty("login", ra);
        for (String temp : selectedProducts) {
            pd = productService.getByProperty("name", temp);
            refeicao = refeicao.add(pd.getValue());
        }
        total = user.getBalance().subtract(refeicao);
        if (total.compareTo(BigDecimal.ZERO) < 0) {
            MessageUtil.showMessage("Cliente sem saldo suficiente ", "", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        transactionsService.save(transaction);
        for (String temp : selectedProducts) {
            pd = productService.getByProperty("name", temp);

            //refeicao = refeicao + pd.getValue();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String x = dateFormat.format(date);

            TransactionProduct transactionProduct = new TransactionProduct(user.getId(), pd.getId(), x, transaction.getId());
            if ((!transactionProductService.save(transactionProduct))) {
                return false;
            }
        }
        if (refeicao.compareTo(BigDecimal.ZERO) == 0) {
            MessageUtil.showMessage("Falha no cadastro de refeicao, selecione os produtos consumidos ", "", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        BigDecimal balance;
        // balance = user.getBalance().longValue();

        //total = user.getBalance() - refeicao;
        transaction.setLogin(ra);
        transaction.setUser(user);
        refeicao = refeicao.negate();
        transaction.setValue(refeicao);
        //BigDecimal track = new BigDecimal(total);
        user.setBalance(total);
        if ((userService.update(user)) && (transactionsService.update(transaction))) {
            MessageUtil.showMessage("Refeicao cadastrada com sucesso", "", FacesMessage.SEVERITY_INFO);
        } else {
            MessageUtil.showMessage("Falha no cadastro de refeicao ", "", FacesMessage.SEVERITY_ERROR);
        }
        userService = new UserService();
        transactionProductService = new TransactionProductService();
        transactionsService = new TransactionService();
        productService = new ProductService();
        user = new User();
        transaction = new Transaction();
        product = new Product();
        return true;
    }

    public boolean insertCredit() { // ate aqui esta certo !!!!!!!!!!!!!!!!!!!!!!!! !! !!! !!!! !!! !! ! !!!!!!!!!!!!!!!!!!!!!!!!!!!
        String balance = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("balance");
        String ra = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("ra");

        if (ra == "" || balance == "") {
            MessageUtil.showMessage("Falha na inserao de credito , informe um RA valido ", "", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        if (credit == null || credit.compareTo(BigDecimal.ZERO) == 0) {
            MessageUtil.showMessage("Falha na inserao de credito , informe uma quantia valida ", "", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        balance = balance.substring(3);
        balance = balance.substring(0, balance.length() - 3);
        BigDecimal bal = new BigDecimal(balance);
        user = userService.getByProperty("login", ra);
        transaction.setLogin(ra);
        transaction.setUser(user);
        transaction.setValue(credit);

        bal = bal.add(credit);
        user.setBalance(bal);

        if ((userService.update(user)) && (transactionsService.save(transaction))) {
            MessageUtil.showMessage("Inserido com sucesso", "", FacesMessage.SEVERITY_INFO);
        } else {
            MessageUtil.showMessage("Falha na adicao de credito", "", FacesMessage.SEVERITY_ERROR);
        }
        user = new User();
        transaction = new Transaction();
        return true;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

}
