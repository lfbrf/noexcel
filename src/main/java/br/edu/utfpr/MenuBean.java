/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import br.edu.utfpr.model.Menu;
import br.edu.utfpr.model.Product;
import br.edu.utfpr.model.Type;
import br.edu.utfpr.model.service.MenuService;
import br.edu.utfpr.model.service.ProductService;
import br.edu.utfpr.model.service.TypeService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
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
        Menu m = menuService.listrepeatFilds(tipo, produto);
        System.out.println("............" + m.getValue());
        m.setValue(newValue);
        menuService.update(m);
    }

    public void changeMenu(long produto, long tipo) {

        System.out.println("OKK" + produto + tipo);

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
