package com.geekbrains.app.homework01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "productsServlet", urlPatterns = "/products/")
public class Servlet extends HttpServlet {

    private Repository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = (Repository) getServletContext().getAttribute("productRepository");
        if (productRepository == null) {
            throw new ServletException("Repository not initialized");
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", productRepository.findAll());
        getServletContext().getRequestDispatcher("/WEB-INF/product.jsp").forward(req, resp);
    }
}
