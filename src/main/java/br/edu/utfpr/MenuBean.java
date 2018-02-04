/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import br.edu.utfpr.model.Menu;
import br.edu.utfpr.model.Type;
import br.edu.utfpr.model.service.MenuService;
import br.edu.utfpr.model.service.ProductService;
import br.edu.utfpr.model.service.TypeService;
import br.edu.utfpr.util.MessageUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Luiz
 */
@ManagedBean
@RequestScoped
public class MenuBean {

    /**
     * Creates a new instance of DiscountBean
     */
    public MenuBean() {
    }

    @PostConstruct
    public void init() {
        discount = new Menu();
        discountList = new ArrayList<>();
        menuList = new ArrayList<>();
        menuService = new MenuService();
        productService = new ProductService();
        typeService = new TypeService();
    }

    public Menu getDiscount() {
        return discount;
    }

    public void setDiscount(Menu discount) {
        this.discount = discount;
    }

    public List<Menu> getDiscountList() {
        return discountList;
    }

    public void setDiscountList(List<Menu> discountList) {
        this.discountList = discountList;
    }

    public void persistDiscount(long produto, long tipo) {
        Type t = typeService.getById(tipo);
        String aux = "GERENTE-" + t.getDescription();
        Type ty = typeService.getByProperty("description", aux);
        System.out.println("............" + ty.getDescription() + ty.getId());
        Menu g = menuService.listrepeatFilds(ty.getId(), produto);
        Menu m = menuService.listrepeatFilds(tipo, produto);

        m.setValue(newValue);
        g.setValue(newValue);
        menuService.update(g);
        menuService.update(m);
    }

    public Menu listrepeatFilds(long tipo, long produto) {
        return menuService.listrepeatFilds(tipo, produto);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    private BigDecimal total;

    public boolean isTot() {
        return tot;
    }

    public void setTot(boolean tot) {
        this.tot = tot;
    }

    private List<Menu> menuList;

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public List<Menu> findAll() {
        return menuList = menuService.findAll();
    }

    public void delete(Menu menu) {
        boolean isSuccess = menuService.delete(menu);
        if (isSuccess) {
            this.menuList.remove(menu);
            MessageUtil.showMessage("Removido com sucesso", "", FacesMessage.SEVERITY_INFO);
        } else {
            MessageUtil.showMessage("Falha na remoção", "", FacesMessage.SEVERITY_ERROR);
        }

    }

    private boolean tot;

    public void changeMenu(long produto, long tipo) {
        System.out.println("OKK" + produto + tipo);
        Menu m = menuService.listrepeatFilds(tipo, produto);
        if (m != null) {
            total = m.getValue();
        }

    }

    public BigDecimal getNewValue() {
        return newValue;
    }

    public void setNewValue(BigDecimal newValue) {
        this.newValue = newValue;
    }
    private BigDecimal newValue;
    private Menu discount;
    private List<Menu> discountList;

    public TypeService getTypeService() {
        return typeService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
    private MenuService menuService;

    public MenuService getMenuService() {
        return menuService;
    }

    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
    private TypeService typeService;
    private ProductService productService;

}
