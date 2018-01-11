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
import br.edu.utfpr.model.UserRole;
import br.edu.utfpr.model.service.ProductService;
import br.edu.utfpr.model.service.TransactionProductService;
import br.edu.utfpr.model.service.TransactionService;
import br.edu.utfpr.model.service.UserRoleService;
import br.edu.utfpr.model.service.UserService;
import br.edu.utfpr.util.MessageUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

@ManagedBean
@ViewScoped
public class TransactionBean implements Serializable {

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
        userRoleService = new UserRoleService();
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

    public List<Transaction> findAll(String x) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        if (x != null && x != "") {
            sessionMap.put("somekey", x);
        } else {
            x = (String) sessionMap.get("somekey");
        }
        transactionList = transactionsService.listbyRa(x);
        return transactionList;
    }

    public List<TransactionProduct> findProducts(Long x) {
        // System.out.println("VALOR AQUI");

        //producList = transactionProductService.listbyId("1");
        System.out.println("ATENCAO >" + x);
        List<TransactionProduct> tp = null;
        long z = 11;
        tp = transactionProductService.listbyId(x);
        for (TransactionProduct trs : tp) {
            System.out.println(trs.getData());
        }
        // System.out.println("INVOA" + transaction.getId());
        //List<TransactionProduct> productList = transactionProductService.listbyId(x);
        //List<TransactionProduct> pl = transactionProductService.findAll();
        System.out.println("VALOR AQUI antes do return");
        return tp;
    }

    public void method() {
        String ra = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("trs");
        System.out.println("INVOCADooo" + ra);

    }

    public void onRowToggle(ToggleEvent event) {
        System.out.println("----------");
        if (event.getVisibility() == Visibility.VISIBLE) {
            // your code here
        }
    }

    public void onRowToggle(Transaction trans) {
        transaction = trans;
        System.out.println(trans.getId() + "ALOU");
        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent("form:MasterDataTable:ChildDataTable");
        if (table != null) {
            table.setValueExpression("sortBy", null);
        }
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
    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

    public long getNewtime() {
        return newtime;
    }

    public void setNewtime(long newtime) {
        this.newtime = newtime;
    }
    long newtime = cal.getTimeInMillis();

    public UserRoleService getUserRoleService() {
        return userRoleService;
    }

    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }
    private UserRoleService userRoleService;

    public boolean insertCredit() { // ate aqui esta certo !!!!!!!!!!!!!!!!!!!!!!!! !! !!! !!!! !!! !! ! !!!!!!!!!!!!!!!!!!!!!!!!!!!

        String balance = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("balance");
        String ra = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("ra");
        System.out.println("VEIO AQUI" + balance);
        if (ra == "" || balance == "") {
            MessageUtil.showMessage("Falha na inserao de credito , informe um RA valido ", "", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        if (credit == null || credit.compareTo(BigDecimal.ZERO) == 0) {
            MessageUtil.showMessage("Falha na inserao de credito , informe uma quantia valida ", "", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        balance = balance.substring(3);
        // balance = balance.substring(0, balance.length() - 3);
        BigDecimal bal = new BigDecimal(balance);
        user = userService.getByProperty("login", ra);
        transaction.setLogin(ra);
        transaction.setUser(user);
        transaction.setValue(credit);

        bal = bal.add(credit);
        user.setBalance(bal);
        if (getNewtime() - user.getTime() < 604800000) {
            System.out.println("BELEZINHA" + ra);
            UserRole ur = userRoleService.getByProperty("login", ra);
            System.out.println("BH" + ur.getRole());
            if (ur.getRole().equals(ur.USER_PENDING)) {
                System.out.println("BELEZINHA2");
                ur.setRole(ur.USER);
                userRoleService.update(ur);
            }
        }
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
