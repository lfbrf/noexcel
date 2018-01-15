/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import br.edu.utfpr.model.Product;
import br.edu.utfpr.model.service.ProductService;
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

    public void persist() {
        if (product.getId() == null) {
            if (productService.save(product)) {
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
