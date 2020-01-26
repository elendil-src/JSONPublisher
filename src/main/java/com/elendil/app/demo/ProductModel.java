package com.elendil.app.demo;

import java.util.List;

public interface ProductModel {

    /**
     * Searchesfor matching products.
     * Result set is ordered alphabetically ignoring case.
     *
     * @param searchTerm term to filter products by
     * @return ordered list of all Products
     * @throws ProductModelException if data structure is invalid
     */
    List<Product> filterProducts(final String searchTerm) throws ProductModelException ;

    /**
     * Retrieves all product items from model
     * @return unordered list of all Products
     * @throws ProductModelException see filterProducts
     */
    List<Product> retrieveProducts() throws ProductModelException;

}
