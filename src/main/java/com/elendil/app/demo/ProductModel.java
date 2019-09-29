package com.elendil.app.demo;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
//import com.sun.tools.javac.util.StringUtils;
import org.apache.commons.lang3.StringUtils;
import sun.text.Normalizer;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ProductModel
{

    private File productJSONFile ;
    public ProductModel(final String sourceFileName) throws IOException, ProductModelException
    {
        objectMapper  = new ObjectMapper();
            productJSONFile = new File(sourceFileName);
            JsonNode productTree = objectMapper.readTree(productJSONFile);

        productTreeNode = productTree.findValue( "worksById");
        if (productTreeNode == null) {
            throw new ProductModelException("worksById node expected but not found");
        }
        if ( !productTreeNode.isObject()){
            throw new ProductModelException("worksById node expected value type of array but found:"
                    + productTreeNode.getNodeType().name());
        }

    }

    final private JsonNode productTreeNode;
    final private ObjectMapper objectMapper;




// FIXME: Close file & DOM Tree in destructor
// FIXME:javadoc? = describe handling of empty strings and nulls
//FIXME chcek handling of nulls
    static private boolean titlePartialMatch(final String partialTerm, final String target)
    {

            if (partialTerm.isEmpty())
            {
                return true; //matches any target
            }
        if (target.isEmpty())
        {
            return true; //matches any partial term
        }

        String normalisedPartial = StringUtils.stripAccents(partialTerm);
        String normalisedTarget = StringUtils.stripAccents(target);

            return normalisedTarget.toUpperCase().contains(normalisedPartial.toUpperCase());


    }

    List<Product> retrieveProducts() throws ProductModelException
    {
        return filterProducts("");
    }

    List<Product> filterProducts(final String searchTerm) throws ProductModelException
    {

//Fixme handle errors & nulls?

        List<Product> productsList = new ArrayList<>();

            Iterator<Map.Entry<String, JsonNode>> products = productTreeNode.fields();
            while (products.hasNext())
            {
                //Child node field name is the primary Product Id
                Map.Entry<String, JsonNode> productNode = products.next();
                JsonNode productTitleNode = productNode.getValue().findValue("TitleText");
                if (productTitleNode == null)
                {
                    throw new ProductModelException("TitleText node expected but not found in Product:"+productNode.getKey());
                }
                if (!productTitleNode.isTextual())
                {
                    throw new ProductModelException("Expected TitleText node of type Text in Product "
                            +productNode.getKey()+ " but found type;" + productTitleNode.getNodeType().name());
                }

                String productTitle = productTitleNode.asText();



                if (titlePartialMatch(searchTerm, productTitle))
                {
                    Product p = new Product();
                    p.setTitle(productTitle);
                    p.setId(productNode.getKey());
                    productsList.add(p);
                }
//                throw new ProductModelException("pt=" + productTitle + ":st=" + searchTerm); //FIXME
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
