/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import br.edu.utfpr.model.Discount;
import br.edu.utfpr.model.Product;
import br.edu.utfpr.model.Type;
import br.edu.utfpr.model.service.DiscountService;
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
public class DiscountBean {

    /**
     * Creates a new instance of DiscountBean
     */
    public DiscountBean() {
    }

    @PostConstruct
    public void init() {
        discount = new Discount();
        discountList = new ArrayList<>();
        discountService = new DiscountService();
        productService = new ProductService();
        typeService = new TypeService();
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public List<Discount> getDiscountList() {
        return discountList;
    }

    public void setDiscountList(List<Discount> discountList) {
        this.discountList = discountList;
    }

    public DiscountService getDiscountService() {
        return discountService;
    }

    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }

    public void persistDiscount(long produto, long tipo) {

        System.out.println("OKKKK");
        System.out.println(getNewValue());
        Type type = typeService.getById(tipo);
        Product product = productService.getById(produto);
        System.out.println(product.getValue() + "<<<");
        if (discountService.isrepeatFilds(tipo, produto)) {
            System.out.println("TRUEEEEE");
        } else {
            discount.setType(type);
            discount.setProduct(product);
            discount.setAtualValue(newValue);
            discountService.save(discount);
        }

    }

    public BigDecimal getNewValue() {
        return newValue;
    }

    public void setNewValue(BigDecimal newValue) {
        this.newValue = newValue;
    }
    private BigDecimal newValue;
    private Discount discount;
    private List<Discount> discountList;

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
    private DiscountService discountService;
    private TypeService typeService;
    private ProductService productService;

}
