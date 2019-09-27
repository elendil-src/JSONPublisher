package com.elendil.app.demo;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ProductModel
{

    final private String sourceFileName;
    private File productJSONFile ;
    public ProductModel(final String sourceFileName)
    {
        this.sourceFileName = sourceFileName;
        objectMapper  = new ObjectMapper();

    }

    final private ObjectMapper objectMapper;
    private JsonNode productTree ;

//    private JsonParser parser ;


void init() throws java.io.IOException, JsonParseException
{
    productJSONFile = new File(sourceFileName);
    productTree = objectMapper.readTree(productJSONFile);

//    JsonFactory factory = new JsonFactory();
//    parser = factory.createParser(sourceFileName);
}


// FIXME: Close file & DOM Tree in destructor


    List<Product> retrieveProducts()
    {
        List<Product> productsList = new ArrayList<>();

//        JsonNode productTreeNode = findProductTreeNode(productTree);

        JsonNode productTreeNode = productTree.findValue( "worksById");

            Iterator<Map.Entry<String, JsonNode>> products = productTreeNode.fields();
            while (products.hasNext() )
            {
                Map.Entry<String, JsonNode> productNode = products.next();
                Product p = new Product();
                p.setTitle( productNode.getValue().findValue("TitleText").asText());
                p.setId( productNode.getKey());

                productsList.add(p);
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
