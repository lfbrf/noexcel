/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import br.edu.utfpr.model.Discount;
import br.edu.utfpr.model.Product;
import br.edu.utfpr.model.Transaction;
import br.edu.utfpr.model.TransactionProduct;
import br.edu.utfpr.model.User;
import br.edu.utfpr.model.UserRole;
import br.edu.utfpr.model.service.DiscountService;
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
import java.util.Iterator;
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

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
    private List<String> values;

    public List<String> getSelectedProducts() {
        return selectedProducts;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    private BigDecimal total;

    public int valorporProduto(String p, int x) {
        System.out.println("EIII" + p);
        Product prod = productService.getByProperty("name", p);
        BigDecimal z = prod.getValue();
        int t = Integer.valueOf(z.intValue());
        t = t * x;

        return t;
    }

    public BigDecimal getSoma() {
        return soma;
    }

    public void setSoma(BigDecimal soma) {
        this.soma = soma;
    }
    private BigDecimal soma = BigDecimal.ZERO;

    public void teste() {
        System.out.println("-------------------------------------------------");

    }
    ArrayList al = new ArrayList();

    public List<BigDecimal> getProdsQtd() {
        return prodsQtd;
    }

    public void setProdsQtd(List<BigDecimal> prodsQtd) {
        this.prodsQtd = prodsQtd;
    }
    private List<BigDecimal> prodsQtd;
    List<String> prodsNome = new ArrayList<String>();
    List lista = new ArrayList();

    public void att() {
        soma = BigDecimal.ZERO;
        for (int i = 0; i < selectedProducts.size(); i = i + 1) {
            Product j = productService.getByProperty("name", selectedProducts.get(i));
            System.out.println("BARREIRa");
            //System.out.println(al.get(i));
            BigDecimal k = prodsQtd.get(i);
            BigDecimal w = j.getValue();
            System.out.println("KKKK" + k + "WWWWWWW" + w);
            k = k.multiply(w);
            soma = soma.add(k);

        }
    }

    public void atualizaQuantidade(String p, int val) {

        boolean aux = false;
        //al.set(val + 1, quantity);
        //al.set(val, p);

        for (int i = 0; i < produtos.size(); i = i + 1) {
            System.out.println("AAAAAA" + produtos.get(i));
        }
        int y = 0;
        //al.set(val, quantity);
        prodsQtd.set(val, quantity);
        for (int i = 0; i < selectedProducts.size(); i = i + 1) {
            Product j = productService.getByProperty("name", selectedProducts.get(i));

            if (p.equals(j.getName())) {

            }
        }
        for (int i = 0; i < selectedProducts.size(); i = i + 1) {
            System.out.println("BARREIRa");
            //System.out.println(al.get(i));

        }

        //quantitys.add(val, quantity);
        //System.out.println("PPPPPPPPPPPPPPPP" + p);
        Product prod = productService.getByProperty("name", p);
        Integer xc = (int) (long) prod.getId();
        //lista.add(xc, prod.getName());

        BigDecimal z = prod.getValue();
        int t = Integer.valueOf(z.intValue());
        //t = t * quantity;
        System.out.println("SOU O T" + t);
        // setSoma(t);
        for (int i = 0; i < prodsNome.size(); i++) {
            System.out.print("--------------------------" + prodsNome.get(i));
        }
        //soma = 0;
        //System.out.println("VALOR DE MEU SOMA:" + soma);
        //setTotal(BigDecimal.valueOf(soma));
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
    private BigDecimal quantity = BigDecimal.ONE;

    public boolean atualizaTotal() {
        String ra = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("ra");

        total = BigDecimal.ZERO;
        if (quantitys != null) {
            for (int y : quantitys) {
                System.out.println("VALOR DE MEU R:" + y);
            }
        }
        User u = userService.getByProperty("login", ra);
        int w = 0;
        for (int i = 0; i < selectedProducts.size(); i++) {
            //al.add(1);
            prodsQtd.add(BigDecimal.ONE);
        }
        for (String x : selectedProducts) {

            Product p = productService.getByProperty("name", x);

            if (p != null) {
                if (u != null) {
                    if (discountService.isrepeatFilds(u.getType().getId(), p.getId())) {
                        List<Discount> d = discountService.listrepeatFilds(u.getType().getId(), p.getId());
                    } else {
                        //al.set(w, x);
                        total = total.add(p.getValue());
                        setTotal(total);
                        System.out.println("VALOR DE TOTAL LOGO " + total);
                    }
                }
            }

            //tratar desconto
        }
        return false;
    }

    public void extend() {
        selectedProducts.add("");
    }

    public void setSelectedProducts(List<String> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }
    private List<String> selectedProducts;

    private List<Integer> quantitys;

    public List<Integer> getQuantitys() {
        return quantitys;
    }

    public void setQuantitys(List<Integer> quantitys) {
        this.quantitys = quantitys;
    }

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

    public DiscountService getDiscountService() {
        return discountService;
    }

    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }
    private DiscountService discountService;
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
        values = new ArrayList();
        values.add("");
        user = new User();
        transaction = new Transaction();
        transactionsService = new TransactionService();
        productService = new ProductService();
        userService = new UserService();
        discountService = new DiscountService();
        transactionProductService = new TransactionProductService();
        produtos = new ArrayList<String>();
        prodsQtd = new ArrayList<>();
        transactionList = new ArrayList<>();
        quantitys = new ArrayList<>();
        soma = BigDecimal.ZERO;
        userRoleService = new UserRoleService();
        producList = new ArrayList<>();
        total = BigDecimal.ZERO;
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
        user = userService.getByProperty("login", transaction.getLogin());
        return user.getName();
    }

    public String getbyManager(Transaction transaction) {
        String manager = transaction.getManager_id();
        user = userService.getByProperty("login", manager);
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
    public boolean removeCredit(String loggedin) { // editar voltar um poco mais
        //BigDecimal refeicao = BigDecimal.ZERO, total;
        Product pd;

        String ra = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("ra");
        if (ra == "") {
            MessageUtil.showMessage("Falha no cadastro de refeicao, informe um RA valido ", "", FacesMessage.SEVERITY_ERROR);
            return false;
        }

        user = userService.getByProperty("login", ra);
        if (user.isCheckuser()) {
            MessageUtil.showMessage("Falha na inserao de credito, condicao de bolsista nao confirmada ", "", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        for (int i = 0; i < al.size(); i = i + 2) {
            if (i > 0) {
                System.out.println(al.get(i));
                System.out.println("|||||||||||||||");
                pd = productService.getByProperty("name", al.get(i).toString());
                if (discountService.isrepeatFilds(user.getType().getId(), pd.getId())) {
                    List<Discount> di = discountService.listrepeatFilds(user.getType().getId(), pd.getId());
                    for (Discount d : di) {
                        pd.setValue(d.getAtualValue());
                    }

                }
                BigDecimal z = null;
                z = z.multiply(pd.getValue());
                //refeicao = refeicao.add(z);
            }

        }

        // total = user.getBalance().subtract(refeicao);
        System.out.println("ME ACHE" + user.getType().getId());
        if (total.compareTo(BigDecimal.ZERO) < 0) {
            MessageUtil.showMessage("Cliente sem saldo suficiente ", "", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        transactionsService.save(transaction);
        int currentPosition = 0;
        for (int i = 0; i < selectedProducts.size(); i++) {
            pd = productService.getByProperty("name", selectedProducts.get(i));

            //refeicao = refeicao + pd.getValue();
            TransactionProduct transactionProduct = new TransactionProduct(user, pd, transaction, prodsQtd.get(i).intValue());
            if ((!transactionProductService.save(transactionProduct))) {
                return false;
            }
        }
        if (soma.compareTo(BigDecimal.ZERO) == 0) {
            MessageUtil.showMessage("Falha no cadastro de refeicao, selecione os produtos consumidos ", "", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        BigDecimal balance;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String x = dateFormat.format(date);
        System.out.println("XXXX AQUI" + x);
        transaction.setManager_id(loggedin);
        transaction.setLogin(ra);
        transaction.setUser(user);
        transaction.setData(x);
        soma = soma.negate();
        transaction.setValue(soma);

        //BigDecimal track = new BigDecimal(total);
        total = user.getBalance();
        total = total.subtract(soma.negate());
        user.setBalance(total);
        soma = soma.ZERO;
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
        currentPosition++;
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

    public boolean insertCredit(String loggedin) { // ate aqui esta certo !!!!!!!!!!!!!!!!!!!!!!!! !! !!! !!!! !!! !! ! !!!!!!!!!!!!!!!!!!!!!!!!!!!

        String balance = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("balance");
        String ra = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("ra");
        System.out.println("VEIO AQUI" + balance);
        UserRole rol = userRoleService.getByProperty("login", loggedin);
        if (ra == "" || balance == "") {
            MessageUtil.showMessage("Falha na inserao de credito , informe um RA|CPF valido ", "", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        if (credit == null || credit.compareTo(BigDecimal.ZERO) == 0) {
            MessageUtil.showMessage("Falha na inserao de credito , informe uma quantia valida ", "", FacesMessage.SEVERITY_ERROR);
            return false;
        }

        if (rol.getRole().equals("MANAGER")) {
            MessageUtil.showMessage("Falha na inserao de credito, solicite ao administrador essa transacao ", "", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        balance = balance.substring(3);
        // balance = balance.substring(0, balance.length() - 3);
        BigDecimal bal = new BigDecimal(balance);
        user = userService.getByProperty("login", ra);
        if (user.isCheckuser()) {
            MessageUtil.showMessage("Falha na inserao de credito, condicao de bolsista nao confirmada ", "", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        UserRole ur = userRoleService.getByProperty("login", ra);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String x = dateFormat.format(date);
        transaction.setManager_id(loggedin);
        transaction.setData(x);
        transaction.setLogin(ra);
        transaction.setUser(user);
        transaction.setValue(credit);

        bal = bal.add(credit);
        user.setBalance(bal);
        if (getNewtime() - user.getTime() < 604800000) {
            System.out.println("BELEZINHA" + ra);

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
