package com.elendil.app.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//javadoc fixme
class ProductModel {

    final private JsonNode productTreeNode;

    //javadoc fixme
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

    // FIXME: Close file & DOM Tree in destructor
// FIXME:javadoc? = describe handling of empty strings and nulls
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

    List<Product> retrieveProducts() throws ProductModelException {
        return filterProducts("");
    }

    /*
      @javadoc FIXME
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
        }
        return productsList;

    }




/*
    void parseJSONModel() throws IOException
    {
        while (parser.nextToken() != null)
        {

        }
    }
*/

}
