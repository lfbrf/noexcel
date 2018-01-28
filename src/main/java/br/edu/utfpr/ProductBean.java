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
import br.edu.utfpr.util.MessageUtil;
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
public class ProductBean {

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public ProductService getProductService() {
        return productService;
    }

    /**
     * Creates a new instance of ProductBean
     */
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    private Product product;
    private List<Product> productList;
    private ProductService productService;

    public MenuService getMenuService() {
        return menuService;
    }

    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
    private MenuService menuService;

    public TypeService getTypeService() {
        return typeService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }
    private TypeService typeService;

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }
    private long productID;

    public ProductBean() {
    }

    @PostConstruct
    public void init() {
        product = new Product();
        productList = new ArrayList<>();
        productService = new ProductService();
        typeService = new TypeService();
        menuService = new MenuService();
        productList = productService.findAll();
    }

    public void edit(Product product) {
        this.product = product;
    }

    public void delete(Product product) {
        boolean isSuccess = productService.delete(product);
        if (isSuccess) {
            this.productList.remove(product);
            MessageUtil.showMessage("Removido com sucesso", "", FacesMessage.SEVERITY_INFO);
        } else {
            MessageUtil.showMessage("Falha na remoção", "", FacesMessage.SEVERITY_ERROR);
        }
        this.product = new Product();
    }

    public void persistMenus() {
        List<Type> types = null;
        types = typeService.findAll();
        for (Type t : types) {
            System.out.println("PASSOU POR AQUI");
            Menu m = new Menu();
            m.setProduct(product);
            m.setType(t);
            m.setValue(product.getValue());
            menuService.save(m);
        }
    }

    public void persist() {

        if (product.getId() == null) {
            if (productService.save(product)) {
                persistMenus();
                this.productList.add(product);
                MessageUtil.showMessage("Persistido com sucesso", "", FacesMessage.SEVERITY_INFO);
            } else {
                MessageUtil.showMessage("Falha ao persistir", "", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            if (productService.update(product)) {
                MessageUtil.showMessage("Alterado com sucesso", "", FacesMessage.SEVERITY_INFO);
            } else {
                MessageUtil.showMessage("Falha na alteração", "", FacesMessage.SEVERITY_ERROR);
            }
        }

        this.product = new Product();
    }

    public List<Product> findAll() {
        System.out.println("AQUIII");
        productList = productService.findAll();
        List<Product> products = productService.findAll();

        return productList;
    }

    public Product findByid(Long id) {
        Product p = new Product();
        return productService.getById(id);
    }
}
