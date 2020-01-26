package com.elendil.app.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Responsible for access Product data from Json repository
 */
class ProductJsonRepository {

    /**
     *
     * @param sourceFileName where repsoitory is a JSON file
     * @return JSONNode that should be parent of tree
     * @throws IOException - opening Json file.
     */

    JsonNode retrieveProducts(final String sourceFileName) throws IOException {

        File productJSONFile = new File(sourceFileName);
        ObjectMapper objectMapper = new ObjectMapper();
        return  objectMapper.readTree(productJSONFile);
    }


}
