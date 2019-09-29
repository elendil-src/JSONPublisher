package com.elendil.app.demo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Controller extends javax.servlet.http.HttpServlet {


    //FIXME
    private ProductModel productModel;

    @Override
    public void init() throws ServletException
    {

        try
        {
            productModel = new ProductModel( "C:\\Users\\work_ivor\\IdeaProjects\\JSONPublisher\\src\\main\\webapp\\WEB-INF\\ELSIO-Graph-Example.json");
            super.log("just called init-new product model"); //FIXME
        } catch (Exception e) {
            throw new ServletException(e);
        }


    }

    private void  handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        resp.setContentType("text/html"); //FIXME
//FIXME            resp.getWriter().println( "TEST {message: \"json from controller\" }" );


        super.log("handleRequest()  called with:"  + req.getRequestURI() + ":" + req.getQueryString()); //FIXME
        List<Product> productResult;
        String searchTerm;
        String resultState;

        searchTerm = req.getParameter("searchTerm");
        if (searchTerm == null) {
            searchTerm = "";  //equivalent to searching for all matches; indicated by empty string
        }
        else {
            searchTerm = searchTerm.trim();
        }

        String action = req.getParameter("action");
        try {
            if( action != null && action.equalsIgnoreCase("search") && !searchTerm.isEmpty())
            {
                // its a proper search
                productResult =  productModel.filterProducts(searchTerm);
                resultState = "filteredCatalogue";
            }
            else // Other states correspond to returning full product set
            {
                productResult =  productModel.retrieveProducts();
                resultState = "fullCatalogue";
            }


        }
        catch (ProductModelException e)
        {
            throw  new ServletException(e);
        }
        super.log("FIXME: searchTerm="+searchTerm+":action="+action+":resultState="+resultState+":resultNum="+productResult.size());

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/catalogue.jsp");
        req.setAttribute("productResult", productResult );
        req.setAttribute("lastSearchTerm", searchTerm );
        req.setAttribute( "resultState", resultState );
        dispatcher.forward(req, resp);



    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        handleRequest( req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }


}
