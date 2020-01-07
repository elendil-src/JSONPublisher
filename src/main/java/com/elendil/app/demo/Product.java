package com.elendil.app.demo;

/**
 * Simple data object that represents key properties of Product
 * Public access required by View JSP
 *
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class Product {
    private String id;
    private String title;
    private String productForm;

    public Product(String title, String id,  String productForm) {
        this.id = id;
        this.title = title;
        this.productForm = productForm;
    }

    public Product() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductForm() {
        return productForm;
    }

    public void setProductForm(String productForm) {
        this.productForm = productForm;
    }




}
