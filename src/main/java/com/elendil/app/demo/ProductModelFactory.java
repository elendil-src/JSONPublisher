package com.elendil.app.demo;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * Factory for implementations of Product model
 */
public class ProductModelFactory {

    public ProductModel create(String dataModelFileName) throws ProductModelException, IOException {

        ProductJsonRepository repository = new ProductJsonRepository();
        JsonNode productJsonRootNode = repository.retrieveProducts(dataModelFileName);

        return new ProductModelImpl(productJsonRootNode);
    }
}
