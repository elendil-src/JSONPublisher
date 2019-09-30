package com.elendil.app.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Responsible for integrity and interpretation of the business data that corresponds to Products.
 */
class ProductModel {

    final private JsonNode productTreeNode;

    /**
     * Initialises the model
     * @param sourceFileName location of the product data source
     * @throws IOException thrown if data cannot be retried from file
     * @throws ProductModelException if data is fails various validation rules
     */
    ProductModel(final String sourceFileName) throws IOException, ProductModelException {
        ObjectMapper objectMapper = new ObjectMapper();
        File productJSONFile = new File(sourceFileName);
        JsonNode productTree = objectMapper.readTree(productJSONFile);

        productTreeNode = productTree.findValue("worksById");
        if (productTreeNode == null) {
            throw new ProductModelException("worksById node expected but not found");
        }
        if (!productTreeNode.isObject()) {
            throw new ProductModelException("worksById node expected value type of array but found:"
                    + productTreeNode.getNodeType().name());
        }

    }

    /**
     * Performs partial matching rule. Ignores case, accented characters. Empty strings are treated as matches.
     * Any leading/trailing white spaces should be stripped before calling; they will be treated as significant.
     * @param partialTerm term to be tested
     * @param target  term against which a partial match is tested
     * @return true if partialTerm matches part or all of target, otherwise false
     */
    static private boolean titlePartialMatch(final String partialTerm, final String target) {

        if (partialTerm.isEmpty()) {
            return true; //matches any target
        }
        if (target.isEmpty()) {
            return true; //matches any partial term
        }

        String normalisedPartial = StringUtils.stripAccents(partialTerm);
        String normalisedTarget = StringUtils.stripAccents(target);

        return normalisedTarget.toUpperCase().contains(normalisedPartial.toUpperCase());

    }

    /**
     * Retrieves all items by forcing a wild card match
     * @return unordered list of all Products
     * @throws ProductModelException see filterProducts
     */
    List<Product> retrieveProducts() throws ProductModelException {
        return filterProducts("");
    }

    /**
     * Searches JSON tree for matching products, and then builds the result set. Result set is unordered.
     *
     * @param searchTerm term to filter products by
     * @return ordered list of all Products
     * @throws ProductModelException if data structure is invalid
     */
    List<Product> filterProducts(final String searchTerm) throws ProductModelException {
        List<Product> productsList = new ArrayList<>();

        Iterator<Map.Entry<String, JsonNode>> products = productTreeNode.fields();
        while (products.hasNext()) {

            Map.Entry<String, JsonNode> productNode = products.next();
            JsonNode productTitleNode = productNode.getValue().findValue("TitleText");
            if (productTitleNode == null) {
                throw new ProductModelException("TitleText node expected but not found in Product:" + productNode.getKey());
            }
            if (!productTitleNode.isTextual()) {
                throw new ProductModelException("Expected TitleText node of type Text in Product "
                        + productNode.getKey() + " but found type;" + productTitleNode.getNodeType().name());
            }
            String productTitle = productTitleNode.asText();

            JsonNode productFormNode = productNode.getValue().findValue("ProductForm");
            String productForm;
            if (productFormNode != null && productFormNode.isTextual())  // treat as optional
            {
                productForm = productFormNode.asText();
            } else {
                productForm = "";
            }

            if (titlePartialMatch(searchTerm, productTitle)) {
                Product p = new Product();
                p.setTitle(productTitle);
                p.setId(productNode.getKey()); //Child node field name is the primary Product Id
                p.setProductForm(productForm);
                productsList.add(p);
            }
            Collections.sort(productsList, new ProductComparator() );

        }
        return productsList;

    }

}
