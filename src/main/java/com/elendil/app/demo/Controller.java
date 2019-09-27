package com.elendil.app.demo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller extends javax.servlet.http.HttpServlet {


    //FIXME
    private ProductModel productModel = new ProductModel( "C:\\Users\\work_ivor\\IdeaProjects\\JSONPublisher\\src\\main\\webapp\\WEB-INF\\ELSIO-Graph-Example.json");

    @Override
    public void init() throws ServletException
    {

        try {
            productModel.init();
            super.log("just called init-new product model"); //FIXME
        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            super.init();
        }



    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


            resp.setContentType("text/html");
//            resp.getWriter().println( "TEST {message: \"json from controller\" }" );
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/product.jsp");

            List<Product> productResult =  productModel.retrieveProducts();
            req.setAttribute("productResult", productResult  );
            dispatcher.forward(req, resp);

    }
}
