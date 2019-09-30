package com.elendil.app.demo;

import java.util.Comparator;

/**
 * Comparator for Product comparing respective Product Ids
 */
class ProductComparator implements Comparator<Product> {
    public int compare(Product p1, Product p2) {
        return p1.getId().compareTo(p2.getId()); // favours Lower string value
    }

}
