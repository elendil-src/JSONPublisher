package com.elendil.app.demo;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
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


void init() throws java.io.IOException
{
    productJSONFile = new File(sourceFileName);
    productTree = objectMapper.readTree(productJSONFile);

//    JsonFactory factory = new JsonFactory();
//    parser = factory.createParser(sourceFileName);
}


// FIXME: Close file & DOM Tree in destructor
// FIXME:javadoc?
//FIXME chcek handling of nulls
    static private boolean titlePartialMatch(final String partialTerm, final String target)
    {
        if (partialTerm != null && target !=null)
        {
            if (partialTerm.isEmpty())
            {
                return true;
            }
            return target.toUpperCase().contains(partialTerm.toUpperCase());
        }
        return false;
    }


    List<Product> filterProducts(final String searchTerm) throws ServletException
    {
        String searchWith;
        if (searchTerm == null)
            searchWith = "";               // equates matching all
        else
            searchWith = searchTerm;

        JsonNode productTreeNode = productTree.findValue( "worksById");
        if (productTreeNode == null) {
            throw new ServletException("worksById node expected but not found");
        }
//Fixme handle errors & nulls?

        List<Product> productsList = new ArrayList<>();

            Iterator<Map.Entry<String, JsonNode>> products = productTreeNode.fields();
            while (products.hasNext())
            {
                Map.Entry<String, JsonNode> productNode = products.next();
                String productTitle = productNode.getValue().findValue("TitleText").asText();

                if (titlePartialMatch(searchWith, productTitle))
                {
                    Product p = new Product();
                    p.setTitle(productTitle);
                    p.setId(productNode.getKey());
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
