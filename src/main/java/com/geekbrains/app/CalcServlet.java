package com.geekbrains.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CalcServlet", urlPatterns = "/calc")
public class CalcServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(CalcServlet.class);

    // GET http://localhost:8080/jee/sum?a=10&b=20
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        int firstNumber = Integer.parseInt(req.getParameter("a"));
        int secondNumber = Integer.parseInt(req.getParameter("b"));
        String operation = req.getParameter("op");

        String result = "";
        if (operation.equals("add")) {
            result = String.valueOf(firstNumber + secondNumber);
        } else if (operation.equals("sub")) {
            result = String.valueOf(firstNumber - secondNumber);
        } else {
            result = "ERROR";
        }

        out.println("<html><body><h1>" + String.format("%d + %d = %s", firstNumber, secondNumber, result) + "</h1></body></html>");
        out.close();
    }
}
