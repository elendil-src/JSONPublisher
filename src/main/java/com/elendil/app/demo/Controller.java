package com.elendil.app.demo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


/**
 * Class acts as the Controller in MVC pattern responsible for creating Model and serving requests from the view.
 * Requires:
 * <li>Config parameter that identifies the model data</li>
 * <li>View to provide action and searchTerm parameters in order to action the view's request.
 * Failure may result in error if request cannot be served</li>
 * <li>View receives the state of the results, the content of the results, and the last search term if relevant</li>
 */
public class Controller extends javax.servlet.http.HttpServlet {

    private ProductModel productModel;

    /**
     * Initialises class including creating and partially validating the model.
     */
    @Override
    public void init() throws ServletException {

        String servletCurrPath = super.getServletContext().getRealPath("");
        String configRelPath = getServletConfig().getInitParameter("ProductDataJSONFilePath");
        if (configRelPath == null) {
            throw new ServletException("Expected but did not find config parameter:" + "ProductDataJSONFilePath");
        }

        try {
            Path path = Paths.get(servletCurrPath, configRelPath);
            productModel = new ProductModel(path.toString());
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    /**
     * Handles all requests for Servlet; action parameter is mandatory in request.
     * @param req servlet's request parameter
     * @param resp servlet's response parameter
     * @throws ServletException
     * @throws IOException
     */
    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //super.log("handleRequest()  called with:" + req.getRequestURI() + ":" + req.getQueryString());
        List<Product> productResult;
        String searchTerm;
        String resultState;

        searchTerm = req.getParameter("searchTerm");
        if (searchTerm == null) {
            searchTerm = "";  //equivalent to searching for all matches; indicated by empty string
        } else {
            searchTerm = searchTerm.trim();
        }

        String action = req.getParameter("action");
        try {
            if (action != null && action.equalsIgnoreCase("search") && !searchTerm.isEmpty()) {
                // its a proper search
                productResult = productModel.filterProducts(searchTerm);
                resultState = "filteredCatalogue";
            } else // Other states correspond to returning full product set
            {
                productResult = productModel.retrieveProducts();
                resultState = "fullCatalogue";
            }
        } catch (ProductModelException e) {
            throw new ServletException(e);
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/catalogue.jsp");
        req.setAttribute("productResult", productResult);
        req.setAttribute("lastSearchTerm", searchTerm);
        req.setAttribute("resultState", resultState);
        dispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }
}
