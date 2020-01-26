package com.elendil.app.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestProductModelImpl {

    @Test(expected = ProductModelException.class)
    public void Given_ValidIncorrectJson_When_OnConstruction_Then_ThrowsProductModelException() throws ProductModelException, JsonProcessingException {

        String jsonString = "{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"}";

        JsonNode jsonNode = new ObjectMapper().readTree(jsonString);
        ProductModel model = new ProductModelImpl(jsonNode);
    }

    @Test(expected = ProductModelException.class)
    public void Given_ValidEmptyJsonNode_When_OnConstruction_Then_ThrowsProductModelException() throws ProductModelException, JsonProcessingException {

        String jsonString = "{}";

        JsonNode jsonNode = new ObjectMapper().readTree(jsonString);
        ProductModel model = new ProductModelImpl(jsonNode);
    }

    @Test(expected = ProductModelException.class)
    public void Given_WorksByIdButNoSubtreeJsonNode_When_OnConstruction_Then_ThrowsProductModelException() throws ProductModelException, JsonProcessingException {

        String jsonString = "{ \"worksById\" : \"just-a-value\" }";

        JsonNode jsonNode = new ObjectMapper().readTree(jsonString);
        ProductModel model = new ProductModelImpl(jsonNode);
    }

    @Test
    public void Given_WorksByIdAndSubtreeJsonNode_When_OnConstruction_Then_Sucessful() throws ProductModelException, JsonProcessingException {

        String jsonString = "{ \"worksById\" : {} }";

        JsonNode jsonNode = new ObjectMapper().readTree(jsonString);
        ProductModel model = new ProductModelImpl(jsonNode);
        Assert.assertTrue(true);
    }


    @Test
    public void Given_ModelWithJsonMissingEmptySubNode_When_RetrieveAllProducts_Then_ReturnsNoMatches() throws ProductModelException, JsonProcessingException {

        String jsonString = "{ \"worksById\" : {} }";

        JsonNode jsonNode = new ObjectMapper().readTree(jsonString);
        ProductModel model = new ProductModelImpl(jsonNode);
        List<Product> resultSet =  model.retrieveProducts();
        Assert.assertTrue(resultSet.isEmpty() );
    }

    @Test(expected = ProductModelException.class)
    public void Given_ModelWithJsonMissingTitleTextNode_When_FilterProducts_Then_ThrowsProductModelException() throws ProductModelException, JsonProcessingException {

        String jsonString = "{ \"worksById\" : { \"node\" : \"value\"} }";

        JsonNode jsonNode = new ObjectMapper().readTree(jsonString);
        ProductModel model = new ProductModelImpl(jsonNode);
        model.filterProducts("");
    }

    @Test(expected = ProductModelException.class)
    public void Given_ModelWithTitleTextNodeAsInteger_When_FilterProducts_Then_ThrowsProductModelException() throws ProductModelException, JsonProcessingException {

        String jsonString = "{ \"worksById\" : { \"titleText\" : 1} }";

        JsonNode jsonNode = new ObjectMapper().readTree(jsonString);
        ProductModel model = new ProductModelImpl(jsonNode);
        model.filterProducts("");
    }

    @Test
    public void Given_ModelWithValidSingleProduct_When_RetrieveAllProducts_Then_ReturnsOneResult() throws ProductModelException, JsonProcessingException {

        final String TITLE = "a title";
        final String ID = "9780000001306";
        final String FORM = "BC";
        String jsonString =
                "{" +
                    "\"worksById\" : { " +
                        " \"" + ID + "\": { " +
                            " \"NotificationType\": \"03\", " +
                            " \"ProductIdentifier\": [ " +
                                " {  " +
                                    " \"ProductIDType\": \"03\", " +
                                    " \"IDValue\": \"9780000001306\" " +
                                " }" +
                            " ], " +
                            " \"Barcode\": \"03\", " +
                            " \"ProductForm\": \"" + FORM + "\", " +
                            " \"Title\": { " +
                                " \"TitleType\": \"01\", " +
                                " \"TitleText\": \"" + TITLE +"\" " +
                            " } " +
                        " } " +
                    "} " +
                "} ";

        JsonNode jsonNode = new ObjectMapper().readTree(jsonString);
        ProductModel model = new ProductModelImpl(jsonNode);
        List<Product> resultSet = model.retrieveProducts();
        Assert.assertEquals(1, resultSet.size());
        Assert.assertEquals(TITLE, resultSet.get(0).getTitle());
        Assert.assertEquals(FORM, resultSet.get(0).getProductForm());
        Assert.assertEquals(ID, resultSet.get(0).getId());
    }

    @Test
    public void Given_ModelWithValidSingleProduct_When_MatchingTitle_Then_ReturnsMatchingResult() throws ProductModelException, JsonProcessingException {

        final String TITLE = "a title";
        final String ID = "9780000001306";
        final String FORM = "BC";
        String jsonString =
                "{" +
                        "\"worksById\" : { " +
                        " \"" + ID + "\": { " +
                        " \"NotificationType\": \"03\", " +
                        " \"ProductIdentifier\": [ " +
                        " {  " +
                        " \"ProductIDType\": \"03\", " +
                        " \"IDValue\": \"9780000001306\" " +
                        " }" +
                        " ], " +
                        " \"Barcode\": \"03\", " +
                        " \"ProductForm\": \"" + FORM + "\", " +
                        " \"Title\": { " +
                        " \"TitleType\": \"01\", " +
                        " \"TitleText\": \"" + TITLE +"\" " +
                        " } " +
                        " } " +
                        "} " +
                        "} ";

        JsonNode jsonNode = new ObjectMapper().readTree(jsonString);
        ProductModel model = new ProductModelImpl(jsonNode);
        List<Product> resultSet = model.filterProducts(TITLE);
        Assert.assertEquals(1, resultSet.size());
        Assert.assertEquals(TITLE, resultSet.get(0).getTitle());
        Assert.assertEquals(FORM, resultSet.get(0).getProductForm());
        Assert.assertEquals(ID, resultSet.get(0).getId());
    }

    @Test
    public void Given_ModelWithValidSingleProduct_When_NoNMatchingTitle_Then_ReturnsNoResult() throws ProductModelException, JsonProcessingException {

        final String TITLE = "a title";
        final String ID = "9780000001306";
        final String FORM = "BC";
        String jsonString =
                "{" +
                        "\"worksById\" : { " +
                        " \"" + ID + "\": { " +
                        " \"NotificationType\": \"03\", " +
                        " \"ProductIdentifier\": [ " +
                        " {  " +
                        " \"ProductIDType\": \"03\", " +
                        " \"IDValue\": \"9780000001306\" " +
                        " }" +
                        " ], " +
                        " \"Barcode\": \"03\", " +
                        " \"ProductForm\": \"" + FORM + "\", " +
                        " \"Title\": { " +
                        " \"TitleType\": \"01\", " +
                        " \"TitleText\": \"" + TITLE +"\" " +
                        " } " +
                        " } " +
                        "} " +
                        "} ";

        JsonNode jsonNode = new ObjectMapper().readTree(jsonString);
        ProductModel model = new ProductModelImpl(jsonNode);
        List<Product> resultSet = model.filterProducts("non matching value");
        Assert.assertTrue(resultSet.isEmpty());
    }

    @Test
    public void Given_ModelWithTwoProduct_When_StartSubstringMatchingTitle_Then_ReturnsOneResult() throws ProductModelException, JsonProcessingException {

        final String TITLE = "a-longer-title";
        final String ID = "9780000001306";
        final String FORM = "BC";
        String jsonString =
                "{" +
                        "\"worksById\" : { " +
                        " \"" + ID + "\": { " +
                        " \"NotificationType\": \"03\", " +
                        " \"ProductIdentifier\": [ " +
                        " {  " +
                        " \"ProductIDType\": \"03\", " +
                        " \"IDValue\": \"9780000001306\" " +
                        " }" +
                        " ], " +
                        " \"Barcode\": \"03\", " +
                        " \"ProductForm\": \"" + FORM + "\", " +
                        " \"Title\": { " +
                        " \"TitleType\": \"01\", " +
                        " \"TitleText\": \"" + TITLE +"\" " +
                        " } " +
                        " } " +
                        "}, " +
                        " \"second-product-title\": { " +
                        " \"NotificationType\": \"03\", " +
                        " \"ProductIdentifier\": [ " +
                        " {  " +
                        " \"ProductIDType\": \"03\", " +
                        " \"IDValue\": \"9780000001306\" " +
                        " }" +
                        " ], " +
                        " \"Barcode\": \"03\", " +
                        " \"ProductForm\": \"" + FORM + "\", " +
                        " \"Title\": { " +
                        " \"TitleType\": \"01\", " +
                        " \"TitleText\": \"" + TITLE +"\" " +
                        " } " +
                        " } " +
                        "} " +
                        "} ";

        JsonNode jsonNode = new ObjectMapper().readTree(jsonString);
        ProductModel model = new ProductModelImpl(jsonNode);
        List<Product> resultSet = model.filterProducts(TITLE.substring(0 ,3));
        Assert.assertEquals(1, resultSet.size());
        Assert.assertEquals(TITLE, resultSet.get(0).getTitle());
        Assert.assertEquals(FORM, resultSet.get(0).getProductForm());
        Assert.assertEquals(ID, resultSet.get(0).getId());
    }

    @Test
    public void Given_ModelWithTwoProduct_When_EndSubstringMatchingTitle_Then_ReturnsTwoResult() throws ProductModelException, JsonProcessingException {

        final String TITLE = "a-longer-title";
        final String ID = "9780000001306";
        final String FORM = "BC";
        String jsonString =
                "{" +
                        "\"worksById\" : { " +
                        " \"" + ID + "\": { " +
                        " \"NotificationType\": \"03\", " +
                        " \"ProductIdentifier\": [ " +
                        " {  " +
                        " \"ProductIDType\": \"03\", " +
                        " \"IDValue\": \"9780000001306\" " +
                        " }" +
                        " ], " +
                        " \"Barcode\": \"03\", " +
                        " \"ProductForm\": \"" + FORM + "\", " +
                        " \"Title\": { " +
                        " \"TitleType\": \"01\", " +
                        " \"TitleText\": \"" + TITLE +"\" " +
                        " } " +
                        " }, " +
                        " \"11111111111\" : {" +
                        " \"NotificationType\": \"03\", " +
                        " \"ProductIdentifier\": [ " +
                        " {  " +
                        " \"ProductIDType\": \"03\", " +
                        " \"IDValue\": \"9780000001306\" " +
                        " }" +
                        " ], " +
                        " \"Barcode\": \"03\", " +
                        " \"ProductForm\": \"" + FORM + "\", " +
                        " \"Title\": { " +
                        " \"TitleType\": \"01\", " +
                        " \"TitleText\": \"" + TITLE +"\" " +
                        " } " +
                        " } " +
                        "} " +
                        "} " +
                        "} ";

        JsonNode jsonNode = new ObjectMapper().readTree(jsonString);
        ProductModel model = new ProductModelImpl(jsonNode);
        List<Product> resultSet = model.filterProducts("title");
        Assert.assertEquals(2, resultSet.size());
    }

}
